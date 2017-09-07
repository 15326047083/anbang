package test.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IStaffDao;
import com.anbang.po.Info;
import com.anbang.po.Staff;

public class StaffDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IStaffDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IStaffDao)ac.getBean("com.anbang.dao.IStaffDao");
	}
	
	@Test
	public void testSave(){
		Staff s = new Staff();
		s.setName("蔡瑜靓");
		s.setEpId("8a80bd855b862fc4015b862fc7eb0001");		
		udao.save(s);
		
	}
}
