package cn.fpshop.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fpshop.dao.UserMapper;
import cn.fpshop.pojo.User;

public class test {
	@Autowired
	private UserMapper usermapper;
	@Test
	public void demo() {
//		ApplicationContext ac= new ClassPathXmlApplicationContext("applicationContext.xml");
//		usermapper=ac.getBean(UserMapper.class);
		User name = new User();
		User user = usermapper.getUser(name);
		System.out.println(user);
		
		
	}
}
