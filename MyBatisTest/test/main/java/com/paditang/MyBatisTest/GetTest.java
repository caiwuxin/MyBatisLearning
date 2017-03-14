package com.paditang.MyBatisTest;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.paditang.domain.User;

public class GetTest {

	SqlSessionFactory sessionFactory;
	SqlSession session;
	@Before
	public void prepare(){
        String resource = "MyBatis-conf.xml";  
        try {  
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources  
                    .getResourceAsReader(resource));  
            session = sessionFactory.openSession();
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
	}
	
	@Test
	public void test(){
		
		User user = (User)session.selectOne("com.paditang.mapping.userMapper.getUserMap",1);
		System.out.println(user);
	}
	
	//@Test
	public void insert(){
		User user = new User();
		user.setName("洪坤峰");
		user.setLocation("成都");
		session.insert("com.paditang.mapping.userMapper.insertUser", user);
	}
	
	//@Test
	public void delete(){
		System.out.println(session.delete("com.paditang.mapping.userMapper.deleteUser",10));
	}
	
	@After
	public void exit(){
		session.close();
	}
}
