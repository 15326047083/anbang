package com.anbang.controller;

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

import com.anbang.auth.vo.AuthVo;
import com.anbang.po.Area;
import com.anbang.po.DangerInfo;
import com.anbang.po.Enterprise;
import com.anbang.po.Info;
import com.anbang.po.PageModel;
import com.anbang.po.Product;
import com.anbang.po.Staff;
import com.anbang.service.IAreaService;
import com.anbang.service.IDangerInfoService;
import com.anbang.service.IEnterpriseService;
import com.anbang.service.IInfoService;
import com.anbang.service.IProductService;
import com.anbang.service.IStaffService;
import com.anbang.vo.EpVo;

@Controller
@RequestMapping("/enterprise")
public class EntrepriceController {
	
	@Autowired
	IEnterpriseService epSerivce;
	@Autowired
	IAreaService areaService;
	@Autowired
	IStaffService staffService;
	@Autowired
	IEnterpriseService enterpriseService;
	@Autowired
	IProductService productService;
	@Autowired
	IDangerInfoService dangerInfoService;
	
	ModelAndView mav;
	@RequestMapping("/toShow.do")
	public ModelAndView toShowEntreprise(@RequestParam("epid") String epid){
		mav = new ModelAndView("/ep/showEnterprise");
		List<Area> areas = areaService.queryAll(0, 15);
		mav.addObject("areas", areas);
		Enterprise ep = epSerivce.get(epid);
		mav.addObject("ep", ep);
		return mav;
	}
	
	/**
	 * 获得企业所有信息用于导出
	 * @param epid
	 * @return
	 */
	@RequestMapping("/toExport.do")
	public ModelAndView getEpVoById(@RequestParam("epid") String epid){
		mav = new ModelAndView("/ep/enterpriseExport");
		
		mav.addObject("ep", enterpriseService.getEpVoById(epid));
		
		return mav;
	}
	
	/**
	 * 定向至添加企业页面
	 * @return
	 */
	
	@RequestMapping("/toAdd.do")
	public ModelAndView toAddEntreprise(){
		
		mav = new ModelAndView("/ep/addEnterprise");
		List<Area> areas = areaService.queryAll(0, 15);
		Enterprise ep = new Enterprise();
		mav.addObject("ep", ep);
		mav.addObject("areas", areas);
		return mav;
	}
	
	/**
	 * 添加企业
	 * @return
	 */
	@RequestMapping(value="/add.do",method=RequestMethod.POST)
	public String addEntreprise(@ModelAttribute("ep") Enterprise ep){
		epSerivce.save(ep);
		return "redirect:toList.do?pager.offset=0";
		
	}
	
	/**
	 *修改企业信息
	 * @param ep
	 * @return
	 */
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String updateEntreprise(@ModelAttribute("ep") Enterprise ep,HttpServletRequest request){
		epSerivce.update(ep);
		AuthVo user = (AuthVo) request.getSession().getAttribute("user");
		if(user.getUserType().equals("admin"))
		return "redirect:toList.do?pager.offset=0";
		else
			return "redirect:toShow.do?epid="+ep.getId();
	}
	
	
	/**
	 * 定向至根据id查询页面
	 * @param epid
	 * @return
	 */
	@RequestMapping("/toSearchById.do")
	public ModelAndView getEntreprise(@RequestParam("epid") String epid){
		mav=new ModelAndView("/ep/findEnterprise");
		List<Area> areas=(List<Area>) areaService.get(epid);
		Enterprise ep=new Enterprise();
		mav.addObject("ep",ep);
		mav.addObject("areas",areas);
		return mav;
	}

	/**
	 * 定向至修改企业页面
	 * @param epId
	 * @return
	 */
	@RequestMapping("/toChangeEnterprise.do")
	public ModelAndView changeEnterprise(@RequestParam String epId){
		mav=new ModelAndView("/ep/changeEnterprise");
		List<Area> areas = areaService.queryAll(0, 15);
		
		mav.addObject("areas", areas);
		mav.addObject("ep", enterpriseService.get(epId));//传入ep对象
		return mav;
	}
	
	/**
	 * 定向至企业信息列表页面
	 * @param offset
	 * @return
	 */
	@RequestMapping("/toList.do")
	public ModelAndView toEnterpriseList(@RequestParam("pager.offset") int offset){
		mav = new ModelAndView();
		PageModel<Enterprise> page=new PageModel<Enterprise>();
		page.setTotal(enterpriseService.count());
		page.setDatas(enterpriseService.queryAll(offset, 10));//
		mav.addObject("infos",page);
		mav.setViewName("/ep/enterpriseList");
		return mav;
	}
	
	/**
	 * json 根据所属监管部门获得所有企业
	 * @return
	 */
	@RequestMapping("/getEpByDeptid.do")
	public @ResponseBody List<Enterprise> getEntrepriseByDeptId(@RequestParam("deptId") String deptId){
		return enterpriseService.getEnterpriseByDeptId(deptId);
		
	}
	
	/**
	 * 获得本企业安全信息
	 * @param epId
	 * @return
	 */
	@RequestMapping("/getDangerInfoList.do")
	public ModelAndView getDangerInfoList(@RequestParam("epid")String epid){
		mav = new ModelAndView("ep/dangerInfoList");
		DangerInfo di = new DangerInfo();
		List<DangerInfo> temp = dangerInfoService.queryAllByParams(new String[]{"epId"},new Object[]{epid},0,1);
		if(temp.size()>0)
			mav.addObject("di",temp.get(0));
		else
		{
			di.setEpId(epid);
			mav.addObject("di",di);}
		return mav;
		
	}
	
	/**
	 * 获得危险品信息
	 * @param dangerid
	 * @return
	 */
	@RequestMapping("/getProductList.do")
	public ModelAndView getProductList(@RequestParam("dangerid")String dangerid,@RequestParam("pager.offset") int offset){
		mav = new ModelAndView("ep/productList");
		
	
			PageModel<Product> page = new PageModel<Product>();
			String [] params = new String[]{"dInfoId"};
			Object [] values =  new Object[]{dangerid};
			page.setDatas(productService.queryAllByParams(params,values, offset, 16));
			page.setTotal(productService.countByParams(params, values));
			mav.addObject("infos",page);
	
		return mav;
		
	}
	
	/**
	 * 转向添加dangerinfo页面
	 * @param epId
	 * @return
	 */
	@RequestMapping("/newDangerInfo.do")
	public ModelAndView newDangerInfo(@RequestParam("epid") String epId){
		mav =new ModelAndView("ep/changeDangerInfo");
		DangerInfo di = new DangerInfo();
		di.setEpId(epId);
		mav.addObject("di", di);
		return mav;

	}
	
	/**
	 * 添加dangerINfo
	 * @param di
	 * @return
	 */
	@RequestMapping(value="/addDangerInfo.do",method=RequestMethod.POST)
	public String addDangerInfo(@ModelAttribute("di") DangerInfo di){
		dangerInfoService.saveOrUpdate(di);
		return "redirect:getDangerInfoList.do?epid="+di.getEpId();
	}
	
	/**
	 * 根据id取得dangerinfo
	 * @param id
	 * @return
	 */
	@RequestMapping("/getDangerInfo.do")
	public ModelAndView getDangerInfo(@RequestParam("id") String id){
		mav =new ModelAndView("/ep/changeDangerInfo");
		DangerInfo di = dangerInfoService.get(id);
		mav.addObject("di", di);
		return mav;
	}
	
	/**
	 * 修改dangerINfo
	 * @param di
	 * @return
	 */
	@RequestMapping("/updateDangerInfo.do")
	public String updateDangerInfo(@ModelAttribute("di") DangerInfo di){
		System.out.println(di.getId());
		System.out.println(di.getEpId());
		dangerInfoService.saveOrUpdate(di);
		return "redirect:getDangerInfoList.do?epid="+di.getEpId();
		
	}
	
	/**
	 * 转向添加product页面
	 * @param dangerId
	 * @return
	 */
	@RequestMapping("/newProduct.do")
	public ModelAndView newProduct(@RequestParam("dangerId") String dangerId){
		Product p = new Product();
		p.setdInfoId(dangerId);
		mav =new ModelAndView("/ep/addProduct");
		mav.addObject("p", p);
		
		return mav;
	}
	
	/**
	 * 添加product
	 * @param p
	 * @return
	 */
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("p") Product p){
		String dangerId=p.getdInfoId();
		productService.save(p);
		return "redirect:getProductList.do?pager.offset=0&dangerid="+dangerId;
	}
	
	/**
	 * 根据id获得product列表
	 * @param id
	 * @return
	 */
	@RequestMapping("/getProduct.do")
	public ModelAndView getProduct(@RequestParam("id") String id){
		mav = new ModelAndView("/ep/updateProduct");
		Product p = productService.get(id);
		mav.addObject("p", p);
		return mav;
	}
	
	/**
	 * 修改product
	 * @param p
	 * @return
	 */
	@RequestMapping("updateProduct.do")
	public String updateProduct(@ModelAttribute("p") Product p){
		productService.update(p);
		return "redirect:getProductList.do?pager.offset=0&dangerid="+p.getdInfoId();
	}
	
	/**
	 * 删除products
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteProduct.do")
	public String deleteProduct(@RequestParam("id") String id){
		Product p = productService.get(id);
		
		productService.delete(p);
		return "redirect:getProductList.do?pager.offset=0&dangerid="+p.getdInfoId();
		
	}

	@RequestMapping("/deleteEps1.do")
	public String deleteEps(@RequestParam("ids") String ids){
		System.out.println("delete ep");
		enterpriseService.deleteAll(ids.split(","));
		return "redirect:toList.do?pager.offset=0";
	}
	
}
