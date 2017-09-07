package com.anbang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.po.Unit;
import com.anbang.service.IUnitService;

@Controller
@RequestMapping("/unit")
public class UnitController {

	private String epId;
	
	@Autowired
	IUnitService utService;
	@RequestMapping("/toShow.do")
	/**
	 * 显示
	 * @param itemid
	 * @return
	 */
	public ModelAndView toShowUnit(@RequestParam("unitId") String unitId){
		ModelAndView mav = new ModelAndView("/unit/ShowUnit");
		Unit ut = utService.get(unitId);
		mav.addObject("ut", ut);
		return mav;
	}
	

	/**
	 * 添加
	 * @return
	 */
	@RequestMapping("/toAdd.do")
	public ModelAndView toAddUnit(@RequestParam("epId") String epId){
		ModelAndView mav = new ModelAndView("/unit/AddUnit");
		Unit ut = new Unit();
		ut.setEpid(epId);
		mav.addObject("ut", ut);
		return mav;
	}
	
	@RequestMapping(value="/add.do",method=RequestMethod.POST)
	public String addUnit(@ModelAttribute("ut") Unit ut){
		utService.save(ut);
		return "redirect:/menu/toUnitList.do?epId="+ut.getEpid();
	}
	/**
	 * 修改
	 * @param epId
	 * @return
	 */
	@RequestMapping("/toChangeUnit.do")
	public ModelAndView changeUnit(@RequestParam String unitId){
		ModelAndView mav=new ModelAndView("/unit/ChangeUnit");
		List<Unit> units = utService.queryAll(0, 15);
		Unit ut = new Unit();
		mav.addObject("units", units);
		mav.addObject("ut", utService.get(unitId));//传入ep对象
		return mav;
	}
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String updateUnit(@ModelAttribute("ut") Unit ut){
		utService.update(ut);
		return "redirect:toList.do?pager.offset=0";
	}
	/**
	 * 准备列表
	 * @param offset
	 * @return
	 */
	@RequestMapping("/toList.do")
	public ModelAndView toUnitList(@RequestParam("pager.offset") int offset){
		ModelAndView mav = new ModelAndView();
		PageModel<Unit> page=new PageModel<Unit>();
		page.setTotal(utService.count());
		page.setDatas(utService.queryAll(offset, 15));//
		mav.addObject("infos",page);
		mav.setViewName("/unit/UnitList");
		return mav;
	}
	@RequestMapping("/toScoreList.do")
	public ModelAndView toScoreList(@RequestParam("epId") String epId){
		ModelAndView mav = new ModelAndView();
		PageModel<Unit> page=new PageModel<Unit>();
		page.setDatas(utService.getAllUnitByEpId(epId));
		page.setTotal(utService.count());
		mav.addObject("infos",page);
		mav.setViewName("/unit/UnitScoreList");
		this.epId=epId;
		return mav;
	}
	
	@RequestMapping("/updateKiAndScore.do")
	public String updateKiAndScore(HttpServletRequest request){
		String [] ids = request.getParameterValues("ids");
		String [] kiStrings = request.getParameterValues("kis");
		String [] scoreStrings = request.getParameterValues("scores");
		double [] kis = new double[kiStrings.length];
		int [] scores = new int[scoreStrings.length];
		for(int i=0;i<kis.length;i++){
			kis[i]=Double.parseDouble(kiStrings[i]);
			scores[i]=Integer.parseInt(scoreStrings[i]);
		}
		utService.updateScore(ids, kis, scores);
		return "redirect:toScoreList.do?epId="+epId;
		
	}
	
}
