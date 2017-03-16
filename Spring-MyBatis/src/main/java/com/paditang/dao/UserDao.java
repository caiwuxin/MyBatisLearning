package com.paditang.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.paditang.domain.User;
import com.paditang.mapping.UserMapper;

@Repository
public class UserDao {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserById(int id){
		return userMapper.getUser(id);
	}
	public String getLocationByUserName(String name){
		return userMapper.getLocationByUserName(name);
	}
}
