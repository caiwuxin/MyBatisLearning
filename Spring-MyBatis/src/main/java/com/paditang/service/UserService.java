package com.paditang.service;

import com.paditang.domain.User;

public interface UserService {

	User getUserById(int id);
	
	String getLocationByUserName(String name);
}
