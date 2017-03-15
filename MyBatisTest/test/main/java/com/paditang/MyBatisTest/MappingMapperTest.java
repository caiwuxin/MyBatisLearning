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

import com.paditang.domain.User;
import com.paditang.mapping.UserMapper;

/**
 * 使用Mapper类映射到Mapper.xml，执行时更符合Java方法调用
 * @author cwx2334555
 *
 */
public class MappingMapperTest {

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
	
	//@Test
	public void getId(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "蔡武鑫");
		int id = userMapper.getIdByUser(map);
		System.out.println(id);
	}
	
	//@Test
	public void batchTest(){
		List<Integer> idlist = new ArrayList<>();
		idlist.add(10);
		idlist.add(11);
		List<?> list = userMapper.getBatchById(idlist);
		System.out.println(list);
	}
	
	//@Test
	public void insert(){
		User user = new User();
		user.setName("邓铭悌");
		user.setLocation("深圳");
		userMapper.insertUser(user);
		session.commit();
	}
	
	//@Test
	public void update(){
		User user = new User(1, "蔡武鑫", "深圳");
		userMapper.updateUser(user);
		session.commit();
	}
	
	//@Test
	public void delete(){
		userMapper.deleteUser(11);
		session.commit();
	}
	
	@After
	public void exit(){
		session.close();
	}
	
}
