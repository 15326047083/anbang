package com.anbang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.auth.vo.AuthVo;
import com.anbang.po.Enterprise;
import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.po.Unit;
import com.anbang.service.IEnterpriseService;
import com.anbang.service.IItemService;
import com.anbang.service.IUnitService;
import com.anbang.tools.ExportExcelUtil;
import com.anbang.tools.titleTools;

/**
 * 功能跳转专用控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	IEnterpriseService enterpriseService;
	@Autowired
	IItemService itService;
	@Autowired
	IUnitService unitService;
	

	@RequestMapping("toDeptCheckMain.do")
	public String DeptCheckMain(@RequestParam("epId") String epId,HttpServletRequest request){
		request.getSession().setAttribute("epId", epId);
		return "menu/main";
		
	}
	
	@RequestMapping("/toSafetyCheckVisual.do")
	public String toSafetyCheckVisual(@RequestParam("start") String start,@RequestParam("end") String end){
		return "/charts/safetyCheck_visual";
	}
	
	@RequestMapping("/toSelfCheckVisual.do")
	public String toSelfCheckVisual(@RequestParam("start") String start,@RequestParam("end") String end){
		return "/charts/selfCheck_visual";
	}
	
	@RequestMapping("/toDoubleVisual.do")
	public String toDoubleVisual(@RequestParam("start") String start,@RequestParam("end") String end){
		return "/charts/safetyCheck_visual_double";
	}
	
	@RequestMapping("/toEnterpriseList.do")
	public ModelAndView toEnterpriseList(@RequestParam("pager.offset") int offset){
		ModelAndView mav = new ModelAndView();
		PageModel<Enterprise> page=new PageModel<Enterprise>();
		page.setTotal(enterpriseService.count());
		page.setDatas(enterpriseService.queryAll(offset, 10));//
		mav.addObject("infos",page);
		mav.setViewName("/ep/enterpriseList");
		return mav;
	}
	
	@RequestMapping("/toUnitEps.do")
	public ModelAndView toUnitEps(@RequestParam("pager.offset") int offset){
		ModelAndView mav = new ModelAndView();
		PageModel<Enterprise> page=new PageModel<Enterprise>();
		page.setTotal(enterpriseService.count());
		page.setDatas(enterpriseService.queryAll(offset, 10));//
		mav.addObject("infos",page);
		mav.setViewName("/unit/enterpriseList");
		return mav;
	}
	
	@RequestMapping("/toItemList.do")
	public ModelAndView toItemList(@RequestParam("unitId") String unitId){
		ModelAndView mav = new ModelAndView();
		mav.addObject("infos",itService.queryAllByUnitId(unitId));
		mav.addObject("unitId",unitId);
		mav.setViewName("/it/ItemList");
		return mav;
	}
	/**
	 * 准备列表
	 * @param offset
	 * @return
	 */
	@RequestMapping("/toUnitList.do")
	public ModelAndView toUnitList(@RequestParam("epId") String epId){
		ModelAndView mav = new ModelAndView();
		PageModel<Unit> page=new PageModel<Unit>();
		page.setDatas(unitService.getAllUnitByEpId(epId));//
		mav.addObject("infos",page);
		mav.addObject("epId",epId);
		mav.setViewName("/unit/UnitList");
		return mav;
	}
	
	@RequestMapping("toMainPage.do")
	public String toMainPage(HttpServletRequest request){
		AuthVo user = (AuthVo) request.getSession().getAttribute("user");
		String result="";
		switch(user.getUserType()){
		case "dept":
			result="redirect:toSafetyCheckVisual.do?start=2017-01-01&end=2017-12-31&type=dept";
			break;
		case "ep":
			result="redirect:toSelfCheckVisual.do?start=2017-01-01&end=2017-12-31";
			break;
		case "admin":
			result="redirect:toUnitEps.do?pager.offset=0";
			break;
		
		}
		return result;
		
		
	}
	
	
}
