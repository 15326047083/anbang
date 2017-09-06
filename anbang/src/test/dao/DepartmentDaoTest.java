package test.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IDepartmentDao;
import com.anbang.po.Department;

public class DepartmentDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IDepartmentDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IDepartmentDao)ac.getBean("com.anbang.dao.IDepartmentDao");
	}
	
	@Test
	public void testSave(){
		Department d = new Department();
		d.setDeptName("准葛尔旗安监局");
		udao.save(d);
	}
	

}
