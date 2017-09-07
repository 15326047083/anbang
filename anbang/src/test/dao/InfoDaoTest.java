package test.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IInfoDao;
import com.anbang.po.Info;
import com.anbang.po.Unit;

public class InfoDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IInfoDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IInfoDao)ac.getBean("com.anbang.dao.IInfoDao");
	}
	
	@Test
	public void testSave(){
		Info i = new Info();
		i.setCheckDate(new Date());
		i.setCheckObject("例行检查");
		i.setCheckEPId("8a80bd855b862fc4015b862fc7eb0001");
		
		udao.save(i);
		
	}
	

}
