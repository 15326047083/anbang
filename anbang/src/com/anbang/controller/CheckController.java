package com.anbang.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.auth.vo.AuthVo;
import com.anbang.po.Check;
import com.anbang.po.Info;
import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.po.Staff;
import com.anbang.po.Unit;
import com.anbang.service.IChartsService;
import com.anbang.service.ICheckService;
import com.anbang.service.IDeptService;
import com.anbang.service.IInfoService;
import com.anbang.service.IItemService;
import com.anbang.service.IStaffService;
import com.anbang.service.IUnitService;
import com.anbang.tools.ExportExcelUtil;
import com.anbang.tools.titleTools;
import com.anbang.vo.CheckVo;



@Controller
@RequestMapping("/check")
public class CheckController {

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
	
	ModelAndView mav;
	/**
	 * 转到检查信息
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/toSaveInfo.do")
	public ModelAndView toSaveInfo(@RequestParam String deptId,HttpServletRequest request){
		mav=new ModelAndView();
		Info info = new Info();
		info.setCheckDept(deptService.get(deptId).getDeptName());
		info.setCheckDeptId(deptId);
		info.setCheckCommit(0);
		
		info.setCheckEPId((String) request.getSession().getAttribute("epId"));
		List<Staff> staffs = staffService.queryAllByDept(deptId);
		mav.addObject(info);
		mav.addObject("staffs",staffs);
		
		mav.setViewName("/check/saveInfo");
		return mav;
	}
	/**
	 * 保存检查基本信息并跳转到save check页面
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/saveInfo.do",method=RequestMethod.POST)
	public ModelAndView saveInfo(@ModelAttribute("info") Info info){
		info.setCheckDate(new Date());
		String infoId=infoService.saveInfo(info);
		mav=new ModelAndView("/check/saveCheck");
		Check check = new Check();
		check.setInfoId(infoId);
		mav.addObject(check);
		mav.addObject("infoId", infoId);
		return mav;
	}
	/**
	 * ajax json 返回unit
	 * @return
	 */
	@RequestMapping("/getAllUnit.do")
	public @ResponseBody List<Unit> getAllUnit(@RequestParam("epid") String epid){
		return unitService.getAllUnitByEpId(epid);
	}
	/**
	 * ajax json 根据unit返回item
	 * @param unitId
	 * @return
	 */
	@RequestMapping("/getItemByUnitId.do")
	public @ResponseBody List<Item> getItemByUnit(@RequestParam String unitId){
		return itemService.queryAllNoExpireUnitId(unitId);
	} 
	/**
	 * ajax json 返回item
	 * @param id
	 * @return
	 */
	@RequestMapping("/getItemById.do")
	public @ResponseBody Item getItemById(@RequestParam String id){
		return itemService.get(id);
	} 
	/**
	 * 到检查项页面
	 * @param infoId
	 * @return
	 */
	@RequestMapping("/toSaveCheck.do")
	public ModelAndView toSaveCheck(@RequestParam String infoId){
		mav=new ModelAndView();
		Check check = new Check();
		check.setInfoId(infoId);
		
		mav.addObject(check);
		mav.setViewName("/check/saveCheck");
		return mav;
	}
	/**
	 * 保存检查选项
	 * @param check
	 * @return
	 */
	@RequestMapping(value="/saveCheck.do",method=RequestMethod.POST)
	public String saveCheck(@ModelAttribute("check") Check check){
		String cid = checkService.getCheckByItemNum(check.getItemId(),check.getInfoId());
		if(cid!=null&&cid.length()>0){
			check.setId(cid);
			checkService.update(check);
		}else{
			checkService.save(check);}
		//System.out.println(check.getInfoId());
		return "redirect:/check/toSaveCheck.do?infoId="+check.getInfoId();
	}
	
	/**
	 * 生成点
	 * @param infoId
	 * @return
	 */
	@RequestMapping("/commitCheck.do")
	public String commitCheck(@RequestParam String infoId,@RequestParam String epId){
		
		chartsService.saveChart(infoId,"dept",epId);
		return "redirect:/check/toCheckList.do?pager.offset=0&pager.pagesize=15";
	}
	
	/**
	 * 到检查列表
	 * @param offset
	 * @return
	 */
	@RequestMapping("/toCheckList.do")
	public ModelAndView toCheckList(@RequestParam("pager.offset") int offset,HttpSession session){
		mav = new ModelAndView();
		PageModel<Info> page = new PageModel<Info>();
		AuthVo user = (AuthVo) session.getAttribute("user");
		String epId=(String) session.getAttribute("epId");
		page.setTotal(infoService.count());
		page.setDatas(infoService.queryAllByEpidAndDeptId(offset, 15,user.getDeptId(),epId));
 		mav.addObject("infos",page);
		mav.setViewName("/check/checkList");
		return mav;
	}
	
	@RequestMapping("/cancel.do")
	public String cancel(@RequestParam("infoId") String infoId){
		infoService.delete(infoService.get(infoId));
		return "redirect:toCheckList.do?pager.offset=0";
	}
	
	/**
	 * 查看检查细节
	 * @param infoId
	 * @return
	 */
	@RequestMapping("/toCheckDetail.do")
	public ModelAndView toCheckDetail(@RequestParam String infoId){
		mav = new ModelAndView();
		mav.addObject("info", infoService.get(infoId));
		mav.addObject("checks",checkService.queryCheckByInfoId(infoId));
		mav.setViewName("/check/checkDetail");
		return mav;
	}
	/**
	 * 查看单个 检查细节，显示在首页
	 * @param checkId
	 * @return
	 */
	@RequestMapping("/getCheckVo.do")
	public @ResponseBody CheckVo getCheckVo(@RequestParam("checkId") String checkId){
		return checkService.queryCheckByCheckId(checkId);
		
	}
	

	/**
	 * 根据一组id取得checkVo
	 * @param ids
	 * @return
	 */
	public List<CheckVo>getExChecks (String [] ids){
	
		List<CheckVo> result = new ArrayList<CheckVo>();
		for(String id:ids){
			result.add(checkService.queryCheckByCheckId(id));
		}
		return result;
		
	}
	
	
	@RequestMapping("/getExChecks.do")
	public @ResponseBody List<CheckVo> getExCheckVos(@RequestParam String infoId){
		String[] ids = checkService.getExIds(infoId).split(";");
		return getExChecks(ids);
	}
	
	@RequestMapping("/getErrorChecks.do")
	public @ResponseBody List<CheckVo> getErrorCheckVos(@RequestParam String infoId){
		String[] ids = checkService.getErrorIds(infoId).split(";");
		return getExChecks(ids);
	}
	
	@RequestMapping("/isSaved.do")
	public @ResponseBody boolean isSaved(@RequestParam String infoId){
		if(checkService.countCheckByEpid(infoId)>0)
			return true;
		return false;
	}
	/**
	 * 备份全部Excel
	 * @param offset
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/backupsInfo.do")
	public ModelAndView backupsInfo(@RequestParam("deptId") String deptId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/check/checkList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = "1997-03-15";
		String e1 = "3000-03-15";
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(s);
			 end = sdf.parse(e1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List<Info> list = infoService.queryAllByCheckIdAndDate(deptId, start, end);
		String []title = titleTools.infoBackups;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "info表(备份)", title, list);
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
	 * @throws ParseException 
	 */
	@RequestMapping("/backupsCheck.do")
	public ModelAndView backupsCheck(@RequestParam("deptId") String deptId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/check/checkList");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = "1997-03-15";
		String e1 = "3000-03-15";
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(s);
			 end = sdf.parse(e1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List<Check> check = checkService.queryAllByDeptIdAndDate(start, end);
		String []title = titleTools.checkBackups;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "check表(备份)", title, check);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	
}
