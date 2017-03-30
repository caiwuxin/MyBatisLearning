package com.paditang.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paditang.dao.UserDao;
import com.paditang.domain.User;
import com.paditang.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	public String getLocationByUserName(String name) {
		return userDao.getLocationByUserName(name);
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public void delelteUser(int UserId) {
		userDao.deleteUser(UserId);
	}

}
