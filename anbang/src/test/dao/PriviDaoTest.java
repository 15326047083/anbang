package test.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.auth.dao.IPrivilegeDao;
import com.anbang.auth.dao.IUserDao;
import com.anbang.auth.po.Privilege;
import com.anbang.auth.po.User;

public class PriviDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IPrivilegeDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IPrivilegeDao)ac.getBean("com.anbang.auth.dao.IPrivilegeDao");
	}
	
	@Test
	public void testSave(){
		Privilege p = new Privilege();
		p.setPrivilegeName("enterprice/toShow.do");
		
	}

}
