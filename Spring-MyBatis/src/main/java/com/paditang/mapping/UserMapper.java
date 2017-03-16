package com.paditang.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.paditang.domain.User;
import com.paditang.sqlprovider.UserSQLBuilder;

public interface UserMapper {

	User getUser(int id);
	
	int getIdByUser(Map<String, Object> map);
	
	List<User> getBatchById(List<Integer> idList);
	
	int insertUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(int id);
	
	String getLocationByUserName(String userName);
	
	@SelectProvider(type=UserSQLBuilder.class,method="getUserByLocation")
	List<User> getUserByLocation(@Param("location")String location);
	
}
