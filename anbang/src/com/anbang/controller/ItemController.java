package com.anbang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.anbang.tools.ExcelUtil;
import com.anbang.tools.ExportExcelUtil;
import com.anbang.tools.titleTools;
import com.anbang.vo.ItemVo;
import com.anbang.vo.UnitVo;

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
		List<Item> list = itService.queryAllItemByUnitId(unitId);
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
		List<Item> list = itService.queryAllItemByUnitId(unitId);
		String []title = titleTools.itemMenuBackups;
		ExportExcelUtil eeu = new ExportExcelUtil();
		try {
			eeu.export(response, "检查单元条目表(备份)", title, list);
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
	
	
	@RequestMapping(value="/setItem.do", method=RequestMethod.POST)
	public ModelAndView setItem(@RequestParam("unitId") String unitId,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		ModelAndView mav = new ModelAndView("/item/fileset");
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
		List<ItemVo> importList = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			ExcelUtil<ItemVo> util = new ExcelUtil<ItemVo>(ItemVo.class);// 创建excel工具类
			importList = util.importExcel(sheet,fis);// 导入
			//System.out.println(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Item> itemList = new ArrayList<Item>();
		for(int i=0;i<importList.size();i++){
			ItemVo iv = importList.get(i);
			Item im = new Item(iv.getId(),iv.getItemNum(),iv.getItemScore(),iv.getExpire(),iv.getToNone(),iv.getItemContent(),iv.getItemType(),iv.getItemLaw(),iv.getUnitId());
			itemList.add(im);
		}
		itService.saveOrUpdate(itemList, unitId);
		return mav;
	}
}
