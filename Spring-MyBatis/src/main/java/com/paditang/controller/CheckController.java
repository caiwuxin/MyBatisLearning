package com.paditang.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paditang.service.UserService;

@Controller
public class CheckController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index.html")
	public ModelAndView loginPage(){
		System.out.println(userService.getUserById(1));
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/checkLocation.html")
	public ModelAndView checkLocation(HttpServletRequest request,@Param(value="name")String name){
		System.out.println(name);
		String location = userService.getLocationByUserName(name);
		System.out.println(location);
		request.setAttribute("location", location);
		request.setAttribute("name", name);
		return new ModelAndView("main");
	}
	
	
	
}
