package com.paditang.controllerTest;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.paditang.domain.User;

public class TestControllerTest {
	
	private static final String SERVER_URL = "http://localhost:8080/Spring-MyBatis";
	private static final String SUFFIX_TEST = "/test";
	
	//@Test
	public void testRequestBody(){
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("name", "cwx");
		form.add("location", "sz");
		restTemplate.postForLocation
			(SERVER_URL+SUFFIX_TEST+"/getRequestBody.html", form);
	}
	
	//@Test
	public void testImage() throws IOException{
		RestTemplate restTemplate = new RestTemplate();
		byte[] response = restTemplate.postForObject(
				SERVER_URL+SUFFIX_TEST+"/getImage/1.html",
				null,byte[].class,"1233");
		Resource outFile = new FileSystemResource("d:/test/image_test.jpg");
		FileCopyUtils.copy(response, outFile.getFile());
	}
	
	@Test
	public void messageConvertXML(){
		RestTemplate restTemplate = new RestTemplate();
		User user = new User();
		user.setName("蔡武鑫");
		user.setLocation("深圳");
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(MediaType.valueOf("application/json;UTF-8"));
		entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		HttpEntity<User> requestEntity = new HttpEntity<>(user,entityHeaders);
		ResponseEntity<User> responseEntity = restTemplate.exchange(
				SERVER_URL+SUFFIX_TEST+"/HttpMessageConvert.html",
				HttpMethod.POST,requestEntity,User.class);
		
		User responUser = responseEntity.getBody();
		System.out.println(responUser);
	}
	

}
