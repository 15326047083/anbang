package test.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IEnterpriseDao;
import com.anbang.dao.IUnitDao;
import com.anbang.po.Enterprise;
import com.anbang.po.Item;
import com.anbang.po.Unit;


public class UnitDaoTest {

	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	static IUnitDao udao;
	
	@BeforeClass
	public static void init(){
		udao=(IUnitDao)ac.getBean("com.anbang.dao.IUnitDao");
	}
	
	@Test
	public void testSave(){
		Unit u = new Unit();
		udao.save(u);
		
	}
	
	@Test
	public void testSaveOrUpdate(){
		Unit u = new Unit();
		List<Unit> list =new ArrayList<Unit>();
		list.add(u);
		udao.saveOrUpdate(list);
	}
	
	

}
