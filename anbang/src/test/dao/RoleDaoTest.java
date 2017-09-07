package test.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.auth.dao.IRoleDao;
import com.anbang.auth.dao.IUserDao;
import com.anbang.auth.po.Role;
import com.anbang.auth.po.User;

public class RoleDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IRoleDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IRoleDao)ac.getBean("com.anbang.auth.dao.IRoleDao");
	}
	
	@Test
	public void testSave(){
		Role r = new Role();
		r.setRoleName("ep");
		r.setRoleDesc("企业");
		udao.save(r);
		
	}
}
