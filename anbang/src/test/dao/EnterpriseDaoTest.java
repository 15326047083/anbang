package test.dao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IEnterpriseDao;
import com.anbang.po.Enterprise;


public class EnterpriseDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IEnterpriseDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IEnterpriseDao)ac.getBean("com.anbang.dao.IEnterpriseDao");
	}
	
	@Test
	public void testSave(){
		Enterprise u = new Enterprise();
		udao.save(u);
		
	}
	
	

}
