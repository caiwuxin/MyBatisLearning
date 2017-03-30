package com.paditang.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paditang.domain.Grade;
import com.paditang.domain.validatingGroup.ValidatingGroup1;
import com.paditang.domain.validatingGroup.ValidatingGroup2;

/*
 * 注意如果需要进行参数校验时，必须配置相关validator，所以使用简单配置<mvc:annotation/>也可以
 */
@Controller
@RequestMapping(value="/valid")
public class ValidatorController {

	@ResponseBody
	@RequestMapping(value="/checkGrade/checkSubject",method=RequestMethod.POST)
	public Grade getGrade( @RequestBody @Validated(value={ValidatingGroup1.class}) Grade grade,
			BindingResult result){
		if(result.hasErrors()){
			System.out.println("check failed");
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(new String(objectError.getDefaultMessage()));
			}
			return null;
		}else {
			System.out.println("check success");
			return grade;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/checkGrade/checkScore",method=RequestMethod.POST)
	public Grade getGrade2( @RequestBody @Validated(value={ValidatingGroup2.class}) Grade grade,
			BindingResult result){
		if(result.hasErrors()){
			System.out.println("check failed");
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(new String(objectError.getDefaultMessage()));
			}
			return null;
		}else {
			System.out.println("check success");
			return grade;
		}
	}
	
}
