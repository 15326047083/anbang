package com.anbang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import com.anbang.po.Area;
import com.anbang.po.Enterprise;
import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.service.IAreaService;
import com.anbang.service.IItemService;
import com.anbang.service.IStaffService;
import com.anbang.service.impl.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	IItemService itService;
	@Autowired
	IAreaService areaService;
	@Autowired
	IStaffService staffService;
	@RequestMapping("/toShow.do")
	/**
	 * 显示
	 * @param itemid
	 * @return
	 */
	public ModelAndView toShowItem(@RequestParam("itemid") String itemid){
		ModelAndView mav = new ModelAndView("/it/ShowItem");
		Item it = itService.get(itemid);
		mav.addObject("it", it);
	
		return mav;
	}
	
	
	
	/**
	 * 添加
	 * @return
	 */
	@RequestMapping("/toAdd.do")
	public ModelAndView toAddItem(@RequestParam("unitId")String unitId){
		ModelAndView mav = new ModelAndView("/it/AddItem");

		Item it = new Item();

		mav.addObject("unitId",unitId);
		mav.addObject("it", it);

		return mav;
	}
	
	@RequestMapping(value="/add.do",method=RequestMethod.POST)
	public String addItem(@ModelAttribute("it") Item it){
		itService.save(it);
		return "redirect:toList.do?unitId="+it.getUnitId();
		
	}
	
	
	
	
	/**
	 * 修改
	 * @param epId
	 * @return
	 */
	@RequestMapping("/toChangeItem.do")
	public ModelAndView changeItem(@RequestParam String itemId){
		ModelAndView mav=new ModelAndView("/it/ChangeItem");
		mav.addObject("it", itService.get(itemId));//传入ep对象
		return mav;
	}
	
	
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String updateItem(@ModelAttribute("it") Item it){
		itService.update(it);
		return "redirect:toList.do?unitId="+it.getUnitId();
	}
	
	
	
	/**
	 * 准备列表
	 * @param offset
	 * @return
	 */
	@RequestMapping("/toList.do")
	public ModelAndView toItemList(@RequestParam("unitId") String unitId){
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("infos",itService.queryAllByUnitId(unitId));
		mav.setViewName("/it/ItemList");
		return mav;
	}
}
