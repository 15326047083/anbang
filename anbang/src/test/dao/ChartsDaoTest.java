package test.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IChartsDao;
import com.anbang.dao.impl.ChartsDao;
import com.anbang.po.Charts;
import com.anbang.vo.UnitScoreVo;


public class ChartsDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IChartsDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IChartsDao)ac.getBean("com.anbang.dao.IChartsDao");
	}
	
	@Test
	public void testSave(){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		Date d2 = null;
		try {
			d = sdf.parse("2017-01-01 01:00:00");
			d2=sdf.parse("2017-12-31 01:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

		List t=null;
		
		
		for(Object d1:t){
			System.out.println(sdf.format((Date)d1));
		}
	     


		
	}
	

	
	

}
