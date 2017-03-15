package com.paditang.MyBatisTest;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.paditang.domain.User;
import com.paditang.mapping.UserMapper;

public class SQLProviderMapperTest {

	SqlSessionFactory sessionFactory;
	SqlSession session;
	UserMapper userMapper;
	@Before
	public void prepare(){
        String resource = "MyBatis-conf.xml";  
        try {  
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
                    .getResourceAsReader(resource));  
            session = sessionFactory.openSession();
            userMapper = session.getMapper(UserMapper.class);
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
	
	@Test
	public void getUserByLocation(){
		List<User>list = userMapper.getUserByLocation("深圳");
		System.out.println(list);
	}
	
	@After
	public void exit(){
		session.close();
	}
}
