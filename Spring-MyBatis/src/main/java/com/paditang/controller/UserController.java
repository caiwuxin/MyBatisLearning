package com.paditang.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.paditang.domain.User;
import com.paditang.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add")
	public String add(){
		return "user/add";
	}
	@RequestMapping(method=RequestMethod.POST)//如果类上没有添加requestmapping，则在方法入参需要添加@ModelAttribute
	public ModelAndView saveUser(User user){
		userService.insertUser(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/createSuccess");
		mv.addObject("user",user);
		return mv;
	}
	
	@RequestMapping(value="/{userId}")//通过占位符，绑定到函数的入参上。
	public ModelAndView showDetail(@PathVariable("userId")int UserId){
		User user = userService.getUserById(UserId);
		System.out.println(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/showDetail");
		mv.addObject("user",user);
		return mv;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST,params="userId")
	public String deleteUser(@RequestParam("userId")int userId){	//根据入参的类型，入参的注解，将对应的参数传递进函数
		userService.delelteUser(userId);
		return "";
	}
	
	@RequestMapping(value="/handleParam")
	public String handleParam(
			@CookieValue(value="sessionId",required=false)String sessionId,
			@RequestParam("age")int age){
		System.out.println("cookie:"+sessionId);
		System.out.println(age);
		return "";
	}
	
	@RequestMapping(value="/uploadPage")
	public String uploadPage(){
		return "uploadPage";
	}
	
	@RequestMapping(value="/upload")
	public String updateThump(@RequestParam("name")String name,
								@RequestParam("file")MultipartFile file) throws IllegalStateException, IOException{
		if(!file.isEmpty()){
			file.transferTo(new File("d:/test/file/"+file.getOriginalFilename()));
			return "redirect:success.html";
		}else {
			return "redirect:fail.html";
		}
	}
}
