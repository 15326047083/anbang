package com.anbang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anbang.po.Area;
import com.anbang.po.Enterprise;
import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.po.Unit;
import com.anbang.service.IAreaService;
import com.anbang.service.IItemService;
import com.anbang.service.IStaffService;
import com.anbang.service.impl.ItemService;
import com.anbang.tools.ExportExcelUtil;
import com.anbang.tools.titleTools;

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
		mav.addObject("itemId",itemId);
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
	
	/**
	 * 导出全部Excel
	 * @param offset
	 * @return
	 */
	@RequestMapping("/exportItem.do")
	public ModelAndView exportUnit(@RequestParam("unitId") String unitId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/it/ItemList");
		List<Item> list = itService.queryAllByUnitId(unitId);
		String []title = titleTools.itemMenu;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元条目表", title, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 备份全部Excel
	 * @param offset
	 * @return
	 */
	@RequestMapping("/backupsItem.do")
	public ModelAndView backupsUnit(@RequestParam("unitId") String unitId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/it/temList");
		List<Item> list = itService.queryAllByUnitId(unitId);
		String []title = titleTools.itemMenuBackups;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元条目表", title, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 导出单项Excel
	 * @param offset
	 * @return
	 */
	@RequestMapping("/exportSingleItem.do")
	public ModelAndView exportSingleItem(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView("/it/ChangeItem");
		Item it = itService.get(id);
		List<Item> list =new ArrayList();
		list.add(it);
		String []title = titleTools.item;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元条目表", title, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 备份单项Excel
	 * @param offset
	 * @return
	 */
	@RequestMapping("/backupsSingleItem.do")
	public ModelAndView backupsSingleItem(@RequestParam("itemid") String itemid,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav=new ModelAndView("/it/ChangeItem");
		Item it = itService.get(itemid);
	
		List<Item> list =new ArrayList();
		list.add(it);
		String []title = titleTools.itemBackups;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元条目表", title, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	
}
