package com.paditang.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paditang.domain.User;
import com.paditang.service.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	/*
	 * 使用@requestBody和@ResponseBody时，会调用AnnotationMethodHandler中的HttpMessageConverter
	 * 自动对请求和输出信息体进行格式转换。如需要更多格式的转换。需要在context.xml中定义HttpMessageConverter注册表
	 */
	
	@RequestMapping("/api")
	public void test1(HttpServletRequest request,HttpServletResponse response){
		System.out.println(request.getCharacterEncoding());
	}
	
	@RequestMapping("/resources")
	public String resources(){
		return "test";
	}
	
	@RequestMapping("/throw")
	public String throwException() throws Exception{
		throw new Exception("test throw");
	}
	
	/*
	 * 它会接收并处理由控制器（或其任何子类）中的@RequestMapping方法抛出的异常。
	 * 如果你将@ExceptionHandler方法定义在@ControllerAdvice类中，那么它会处理相关控制器中抛出的异常。
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String>handleException(Exception ex){
		ResponseEntity<String> entity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
		return entity;
	}
	
	
	/*
	 * 根据String 判断使用StringHtppMessageConverter
	 * 对请求信息转换并绑定到requestbody上进行入参
	 */
	@RequestMapping("/getRequestBody")
	public String getRequestBody(@RequestBody String requestbody){
		System.out.println(requestbody);							
		return "success";
	}
	
	/*
	 * 根据返回参数类型为byte[]，按照查找规则使用ByteArrayHttpMessageConverter对返回值进行转换。
	 */
	@ResponseBody
	@RequestMapping(value="/getImage/{imageId}")
	public byte[]getImage(@PathVariable(value="imageId")String imageId) throws IOException{
		System.out.println("load of image:"+imageId);
		Resource resource = new ClassPathResource("/image.jpg");
		byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		return fileData;
	}
	
	
	/*
	 * 当使用HttpEntity<T>时，不仅可以访问到报文体的数据，还可以获取到报文头的数据
	 * 且根据泛型T的类型找到合适的HttpMessageConverter进行消息转换
	 */
	@RequestMapping(value="/httpEntity",method=RequestMethod.POST)
	public String testHttpEntity(HttpEntity<String> httpEntity){//指定入参类型
		//long contentlen = httpEntity.getHeaders().getContentLength();
		System.out.println(httpEntity.getBody());
		return "success";
	}
	
	@RequestMapping(value="/httpEntity/{imageId}",method=RequestMethod.GET)
	public ResponseEntity<byte[]>getEntity(@PathVariable(value="imageId")String imageId) throws IOException{
		Resource resource = new ClassPathResource("/image.jpg");
		byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
		ResponseEntity<byte[]> responseEntity = 
				new ResponseEntity<byte[]>(fileData,HttpStatus.OK);
		return responseEntity;
	}
	
	@RequestMapping(value="/HttpMessageConvert",method=RequestMethod.POST)
	public ResponseEntity<User> messageConvert(HttpEntity<User> requestEntity){
		User user = requestEntity.getBody();
		System.out.println(user);
		user .setId(1000);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
		
	@ResponseBody
	@RequestMapping(value="/getUser/{userId}",method=RequestMethod.GET)
	public User getUser(@PathVariable(value="userId")int id){
		return userService.getUserById(id);
	}
	
}
