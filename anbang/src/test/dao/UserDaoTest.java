package test.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.auth.dao.IUserDao;
import com.anbang.auth.po.User;

public class UserDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IUserDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IUserDao)ac.getBean("com.anbang.auth.dao.IUserDao");
	}
	
	@Test
	public void testSave(){
		User u = new User();
		u.setUsername("dongda");
		u.setPassword("1234567");
	
		udao.save(u);
		
	}

}
