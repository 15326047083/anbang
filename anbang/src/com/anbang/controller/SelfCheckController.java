package com.anbang.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.po.Check;
import com.anbang.po.Info;
import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.po.Staff;
import com.anbang.po.Unit;
import com.anbang.service.IChartsService;
import com.anbang.service.ICheckService;
import com.anbang.service.IDeptService;
import com.anbang.service.IEnterpriseService;
import com.anbang.service.IInfoService;
import com.anbang.service.IItemService;
import com.anbang.service.IStaffService;
import com.anbang.service.IUnitService;

@Controller
@RequestMapping("/selfcheck")
public class SelfCheckController {

	@Autowired
	ICheckService checkService;
	
	@Autowired
	IStaffService staffService;
	
	@Autowired
	IInfoService infoService;
	
	@Autowired
	IDeptService deptService;
	
	@Autowired
	IUnitService unitService;
	
	@Autowired
	IItemService itemService;
	
	@Autowired
	IChartsService chartsService;
	@Autowired
	IEnterpriseService epService;
	
	ModelAndView mav;
	
	@RequestMapping("/toSaveInfo.do")
	public ModelAndView toSaveInfo(@RequestParam String epId){
		mav=new ModelAndView();
		Info info = new Info();
		info.setCheckEP(epService.get(epId).getEname());
		info.setCheckEPId(epId);
		info.setCheckCommit(0);
		Staff t = new Staff();
		
		List<Staff> staffs = staffService.queryAllByEp(epId);
		mav.addObject(info);
		mav.addObject("staffs",staffs);
		mav.setViewName("/selfcheck/saveInfo");
		return mav;
	}
	
	@RequestMapping(value="/saveInfo.do",method=RequestMethod.POST)
	public ModelAndView saveInfo(@ModelAttribute("info") Info info){
		info.setCheckEP("本公司");
		info.setCheckDate(new Date());
		String infoId=infoService.saveInfo(info);
		mav=new ModelAndView("/selfcheck/saveCheck");
		Check check = new Check();
		check.setInfoId(infoId);
		mav.addObject(check);
		return mav;
	}
	
	@RequestMapping("/getAllUnit.do")
	public @ResponseBody List<Unit> getAllUnit(@RequestParam("epid") String epId){
		return unitService.getAllUnitByEpId(epId);
	}
	
	@RequestMapping("/getItemByUnitId.do")
	public @ResponseBody List<Item> getItemByUnit(@RequestParam String unitId){
		return itemService.queryAllByUnitId(unitId);
	} 
	
	@RequestMapping("/getItemById.do")
	public @ResponseBody Item getItemById(@RequestParam String id){
		return itemService.get(id);
	} 
	
	@RequestMapping("/toSaveCheck.do")
	public ModelAndView toSaveCheck(@RequestParam String infoId){
		mav=new ModelAndView();
		Check check = new Check();
		check.setInfoId(infoId);
		mav.addObject(check);
		mav.setViewName("/selfcheck/saveCheck");
		return mav;
	}
	
	@RequestMapping(value="/saveCheck.do",method=RequestMethod.POST)
	public String saveCheck(@ModelAttribute("check") Check check){
		String cid = checkService.getCheckByItemNum(check.getItemId(),check.getInfoId());
		if(cid!=null&&cid.length()>0){
			check.setId(cid);
			checkService.update(check);
		}else{
		checkService.save(check);}
		return "redirect:/selfcheck/toSaveCheck.do?infoId="+check.getInfoId();
	}
	
	@RequestMapping("/commitCheck.do")
	public String commitCheck(@RequestParam String infoId,@RequestParam String epId){
		
		chartsService.saveChart(infoId,"ep",epId);
		return "redirect:/selfcheck/toCheckList.do?pager.offset=0&pager.pagesize=15";
	}
	
	@RequestMapping("/toCheckList.do")
	public ModelAndView toCheckList(@RequestParam("pager.offset") int offset,HttpServletRequest request){
		mav = new ModelAndView();
		PageModel<Info> page = new PageModel<Info>();
		String epid=(String)request.getSession().getAttribute("epId");
		page.setTotal(infoService.count(null,epid));
		
		page.setDatas(infoService.queryAllByEpidAndDeptId(offset, 15, null,epid));
 		mav.addObject("infos",page);
		mav.setViewName("/selfcheck/checkList");
		return mav;
	}
	
	@RequestMapping("/toCheckDetail.do")
	public ModelAndView toCheckDetail(@RequestParam String infoId){
		mav = new ModelAndView();
		mav.addObject("info", infoService.get(infoId));
		mav.addObject("checks",checkService.queryCheckByInfoId(infoId));
		mav.setViewName("/selfcheck/checkDetail");
		return mav;
	}
	@RequestMapping("/cancel.do")
	public String cancel(@RequestParam("infoId") String infoId){
		infoService.delete(infoService.get(infoId));
		return "redirect:toCheckList.do?pager.offset=0";
	}

	@RequestMapping("/isSaved.do")
	public @ResponseBody boolean isSaved(@RequestParam String infoId){
		if(checkService.countCheckByEpid(infoId)>0)
			return true;
		return false;
	}
	
}
