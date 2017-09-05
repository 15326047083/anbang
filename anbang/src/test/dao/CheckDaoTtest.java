package test.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.ICheckDao;
import com.anbang.po.Check;
import com.anbang.vo.UnitScoreVo;

public class CheckDaoTtest {
	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static ICheckDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(ICheckDao)ac.getBean("com.anbang.dao.ICheckDao");
	}
	
	

	@Test
	public void testGetScoreByInfoGByUnit(){
		List<UnitScoreVo> list = udao.getScoreByInfoGByUnit("8a80bd945bb44a2f015bb44a32560001");
		for(UnitScoreVo usv:list){
			System.out.println(usv.getUnitId()+":"+usv.getScore()+":"+usv.getToZero());
		}
		
	}
	
	
	@Test
	public void testGetExIds(){
		List<Object> list = udao.getExIds("8a80bd945bb44a2f015bb44a32560001");
		for(Object usv:list){
			System.out.println(usv.toString());
		}
	}
	
	@Test
	public void testGetErrorIds(){
		List<Object> list = udao.getErrorIds("8a80bd945bb44a2f015bb44a32560001");
		for(Object usv:list){
			System.out.println(usv.toString());
		}
	}


	@Test
	public void testSave(){
		Check c = new Check();
		c.setCheckType("查资料");
		c.setStatus("重大隐患");
		c.setInfoId("8a80bd945bb44a2f015bb44a32560001");
		c.setUnitId("8a80bd855b676fcf015b676fd2270001");
		c.setScore(2);
		c.setToZero(1);
		c.setItemId("8a80bd855b678c9f015b678ca2b90001");
		udao.save(c);
	}

}
