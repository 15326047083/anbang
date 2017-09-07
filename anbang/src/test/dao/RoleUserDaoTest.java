package test.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.auth.dao.IUserDao;
import com.anbang.auth.dao.IUserRoleDao;
import com.anbang.auth.po.User;
import com.anbang.auth.po.UserRole;

public class RoleUserDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IUserRoleDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IUserRoleDao)ac.getBean("com.anbang.auth.dao.IUserRoleDao");
	}
	
	@Test
	public void testSave(){
		UserRole ur = new UserRole();
		ur.setUserId("402880ea5c27dd8f015c27dd961c0001");
		ur.setRoleId("ep");
		udao.save(ur);
		
	}

}