package com.paditang.MyBatisTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.paditang.domain.User;

/**
 * 直接调用XML配置中的方法
 * @author cwx2334555
 *
 */
public class DirectMapperXmlTest {

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
	
	//@Test
	public void test(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "caiwuxin");
		List<?> list = (List<?>) session.selectList("com.paditang.mapping.userMapper.getIdByUser",map);
		System.out.println(list);
	}
	
	//@Test
	public void batchTest(){
		List<Integer> idlist = new ArrayList<>();
		idlist.add(10);
		idlist.add(11);
		List<?> list = (List<?>) session.selectList("com.paditang.mapping.userMapper.getBatchById",idlist);
		//int id = session.select("com.paditang.mapping.userMapper.getIdByUser",null);
		System.out.println(list);
	}
	
	//@Test
	public void insert(){
		User user = new User();
		user.setName("胡锦心");
		user.setLocation("北京");
		session.insert("com.paditang.mapping.userMapper.insertUser", user);
		session.commit();
	}
	
	@Test
	public void delete(){
		System.out.println(session.delete("com.paditang.mapping.userMapper.deleteUser",10));
		session.commit();
	}
	
	@After
	public void exit(){
		session.close();
	}
}
