package test.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anbang.dao.IItemDao;
import com.anbang.po.Item;


public class ItemDaoTest {

	static IItemDao idao;
	static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@BeforeClass
	public static void init(){
		idao=(IItemDao)ac.getBean("com.anbang.dao.IItemDao");
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void SaveOrUpdateTest(){
		String unitId="8a80bd855b676fcf015b676fd2270001";
		Item it = new Item();
		it.setExpire(1);
		it.setItemContent("asdsa");
		it.setItemLaw("asdas");
		it.setItemNum("45");
		it.setItemScore(1);
		it.setItemType("asda");
		it.setToNone(0);
		it.setUnitId(unitId);
		List<Item> list = new ArrayList<Item>();
		list.add(it);		
		idao.saveOrUpdate(list, unitId);
	}
	

}
