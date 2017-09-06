package com.anbang.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anbang.service.IChartsService;
import com.anbang.vo.ChartsVo;
import com.anbang.vo.CheckVo;

/**
 * 首页饼状图，折线图以及简要信息专用控制器
 * @author HP2
 *
 */
@Controller
@RequestMapping("/charts")
public class ChartsController {

	

	@Autowired
	IChartsService chartsService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getChartsVisual.do")
	public @ResponseBody List getChartsVisual(@RequestParam("epid") String epid,@RequestParam("type") String type,@RequestParam("start") String start,@RequestParam("end") String end){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate;
		try {
			startDate = sdf.parse(start);
			Date endDate = sdf.parse(end);
			return chartsService.getSaftyChartsVo(epid,type,startDate,endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getSelfVisual.do")
	public @ResponseBody List getSelfVisual(@RequestParam("epid") String epid){
		return chartsService.getSelfChartsVo(epid);
	}
	
	@RequestMapping("/getDoubleCheckDate.do")
	public @ResponseBody List<String> getDoubleCheckDate(@RequestParam("epid") String epid,@RequestParam("start")String start,@RequestParam("end")String end){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = sdf.parse(start);
			endDate=sdf.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chartsService.getDoubleTimes(epid,startDate,endDate);
	}
	
	@RequestMapping("/getDoubleChartsVo.do")
	public @ResponseBody List<ChartsVo> getDoubleChartsVo(@RequestParam("epid")String epid,@RequestParam("chartsType")String chartsType,@RequestParam("start") String start,@RequestParam("end") String end){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = sdf.parse(start);
			endDate=sdf.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chartsService.getDoubleChartsVo(epid, chartsType,startDate,endDate);
	}
	
	/**
	 * 取得相关epid的check日期，混合start与end之间
	 * @param epid
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping("/getSafetyCheckDate.do")
	public @ResponseBody List<String> getSafetyCheckDate(@RequestParam("epid")String epid,@RequestParam("start")String start,@RequestParam("end")String end,@RequestParam("chartType")String chartType){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate=null;
		Date endDate=null;
		try {
			startDate = sdf.parse(start);
			endDate=sdf.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Date> temp = chartsService.getSafetyCheckDate(epid, startDate, endDate,chartType);
		List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		for(Date d:temp){
			result.add(sdf2.format(d));
		}

		return result;
	}
	


 

	
}
