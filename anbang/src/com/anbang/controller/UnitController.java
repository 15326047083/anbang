package com.anbang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.po.Item;
import com.anbang.po.PageModel;
import com.anbang.po.Unit;
import com.anbang.service.IUnitService;
import com.anbang.tools.ExcelUtil;
import com.anbang.tools.ExportExcelUtil;
import com.anbang.tools.importExcelTools;
import com.anbang.tools.titleTools;
import com.anbang.vo.UnitVo;


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
	/**
	 * 导出Excel
	 * @param offset
	 * @return
	 */
	@RequestMapping("/exportUnit.do")
	public ModelAndView exportUnit(@RequestParam("epId") String epId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/unit/UnitList");
		List<Unit> list = utService.getAllUnitByEpId(epId);
		String []title = titleTools.unit;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元表", title, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 备份Excel
	 * @param offset
	 * @return
	 */
	@RequestMapping("/backupsUnit.do")
	public ModelAndView backupsUnit(@RequestParam("epId") String epId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/unit/UnitList");
		List<Unit> list = utService.getAllUnitByEpId(epId);
		String []title = titleTools.unitBackups;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元表", title, list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 恢复单元列表
	 * @return
	 */
	@RequestMapping(value="/setUnit.do", method=RequestMethod.POST)
	public ModelAndView setUnit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		ModelAndView mav = new ModelAndView("/unit/fileset");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
        /** 构建文件保存的目录* */
        String logoPathDir = "/business/shops/upload/"
                + dateformat.format(new Date());
        /** 得到文件保存目录的真实路径* */
        String logoRealPathDir = request.getSession().getServletContext()
                .getRealPath(logoPathDir);
        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        /** 页面控件的文件流* */
        MultipartFile multipartFile = multipartRequest.getFile("file");
        /** 获取文件的后缀* */
        String suffix = multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf("."));
        /** 使用UUID生成文件名称* */
        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        /** 拼成完整的文件保存路径加文件* */
        String fileName = logoRealPathDir + File.separator + logImageName;
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 打印出上传到服务器的文件的绝对路径* */
       // System.out.println("****************"+fileName+"**************");
		
		String sheet="Sheet1";
		List<UnitVo> importList = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			ExcelUtil<UnitVo> util = new ExcelUtil<UnitVo>(UnitVo.class);// 创建excel工具类
			importList = util.importExcel(sheet,fis);// 导入
			//System.out.println(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Unit> unitList = new ArrayList<Unit>();
		for(int i=0;i<importList.size();i++){
			UnitVo uv = importList.get(i);
			Unit u = new Unit(uv.getId(),uv.getUnitNum(),uv.getUnitName(),uv.getScore(),uv.getKi(),uv.getEpid());
			unitList.add(u);
			
		}
		utService.saveOrUpdate(unitList);
		return mav;
	}
	
	
}
