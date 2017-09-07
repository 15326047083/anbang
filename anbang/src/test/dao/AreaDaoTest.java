package test.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IAreaDao;
import com.anbang.dao.IChartsDao;
import com.anbang.po.Area;
import com.anbang.po.Charts;

public class AreaDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IAreaDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IAreaDao)ac.getBean("com.anbang.dao.IAreaDao");
	}
	
	@Test
	public void testSave(){
		Area a = new Area();
		a.setAname("其他");
		
		udao.save(a);
		
	}

}
