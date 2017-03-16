package com.paditang.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

public class UserSQLBuilder {

	public String getUserByLocation(){
		return new SQL(){{
			SELECT("*");
			FROM("t_user");
			WHERE("location =#{location}");
		}}.toString();
	}
}
