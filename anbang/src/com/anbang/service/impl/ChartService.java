package com.anbang.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anbang.dao.IChartsDao;
import com.anbang.dao.ICheckDao;
import com.anbang.dao.IInfoDao;
import com.anbang.dao.IItemDao;
import com.anbang.po.Charts;
import com.anbang.po.Check;
import com.anbang.po.Info;
import com.anbang.service.IChartsService;
import com.anbang.service.ICheckService;
import com.anbang.tools.GeneralTools;
import com.anbang.vo.ChartsVo;
@Service(value="com.anbang.service.IChartsService")
public class ChartService extends CommonService<Charts> implements IChartsService {

	private List<ChartsVo> result;
	private ChartsVo tempCharts;
	
	@Autowired
	private IItemDao itemDao;
	
	@Autowired
	private ICheckService checkService;
	
	@Autowired
	private ICheckDao checkDao;
	
	@Autowired
	private IInfoDao infoDao;
	
	@Autowired
	public void setDao(IChartsDao dao){
		this.dao=dao;
	}
	
	@Override
	public List<ChartsVo> getSaftyChartsVo(String epid,String type,Date start, Date end) {
		List<Charts> temp=((IChartsDao)dao).getDeptChartsByEpId(epid, type,start,end);
		return chartsToVo(temp);
	}
	

	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveChart(String infoId,String chartsType,String epid) {
		Info info = infoDao.get(infoId);
		info.setCheckCommit(1);
		
		
		Charts c = new Charts();
		c.setChartType(chartsType);
		c.setScore(checkService.getTotalScoreByInfoId(infoId));
		c.setDate(info.getCheckDate());
		c.setEpid(info.getCheckEPId());
		c.setPieData(checkService.getStatByInfoId(infoId,epid));
		try {
			String exids=checkService.getExIds(infoId);
			if(exids.length()>0)
			c.setExpData(new javax.sql.rowset.serial.SerialClob(exids.toCharArray()));
			String errorids=checkService.getErrorIds(infoId);
			if(errorids.length()>0)
			c.setErrorData(new javax.sql.rowset.serial.SerialClob(errorids.toCharArray()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infoDao.update(info);
		c.setInfoId(infoId);
		((IChartsDao)dao).save(c);
	}

	@Override
	public List<String> getDoubleTimes(String epid,Date start,Date end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		List<Date> tempDate = ((IChartsDao)dao).getDoubleChartsDateByEpId(epid,start,end);
		tempDate.addAll(this.getAllDay(start, end));
		Collections.sort(tempDate);
		List<String> result = new ArrayList<String>();
		for(Date d:tempDate){
			result.add(sdf.format(d));
		}
		return result;
	}

	@Override
	public List<ChartsVo> getDoubleChartsVo(String epid, String chartType,Date start,Date end) {
		List<Charts> temp=((IChartsDao)dao).getDoubleChartsByEpId(epid, chartType,start,end);
		result = new ArrayList<ChartsVo>();
		for(Charts t:temp){
			tempCharts = new ChartsVo();
			//转换数据格式为页面折线图横坐标做准备
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
			tempCharts.setIndex(sdf.format(t.getDate()));
			tempCharts.setScore(t.getScore());
			result.add(tempCharts);
		}
		//System.out.println(result.size());
		return result; 
	}
	
	public List<ChartsVo> chartsToVo(List<Charts> temp){
		result = new ArrayList<ChartsVo>();
		for(Charts t:temp){
			tempCharts = new ChartsVo();
			//转换数据格式为页面折线图横坐标做准备
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
			tempCharts.setIndex(sdf.format(t.getDate()));
			tempCharts.setScore(t.getScore());
			int [] pieData = new int[3];
			
			//分解饼图数据，组成饼图数据
			String [] pieDataString = t.getPieData().split(":");
			for(int i=0;i<pieDataString.length;i++){
				pieData[i]=Integer.parseInt(pieDataString[i]);
			}
			tempCharts.setStat(pieData);
			
			//根据信息返回一般隐患的item 信号
			if(t.getExpData()!=null){
			try {
				
				String [] expIds=(GeneralTools.ClobToString(t.getExpData()).split(";"));
				//System.out.println(expIds.length);
				String [] exCheck=new String[expIds.length];
				Check tempc;
				for(int i=0;i<expIds.length;i++){
					tempc = checkDao.getExCheck(expIds[i]);
					String live = tempc.getLive();
					if(live!=null&&live.length()>=12){
						live=live.substring(0,11);}
					exCheck[i]=tempc.getId()+":"+live+"......";
				}
				tempCharts.setException(exCheck);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(t.getErrorData()!=null){
			try {
				String [] expIds=(GeneralTools.ClobToString(t.getErrorData()).split(";"));
				String [] exCheck=new String[expIds.length];
				Check tempc;
				for(int i=0;i<expIds.length;i++){
					tempc = checkDao.getExCheck(expIds[i]);
					String live = tempc.getLive();
					if(live!=null&&live.length()>=12){
						live=live.substring(0,11);}
					exCheck[i]=tempc.getId()+":"+live+"......";
				}
				tempCharts.setError(exCheck);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			tempCharts.setCheck_id(t.getEpid());
			tempCharts.setInfoId(t.getInfoId());
			result.add(tempCharts);
		}
		return result; 
	}

	@Override
	public List<ChartsVo> getSelfChartsVo(String epid) {
		List<Charts> temp=((IChartsDao)dao).getSelfChatsByEpId(epid);
		return chartsToVo(temp);
	}

	@Override
	public List<Date> getSafetyCheckDate(String epid, Date Start, Date end,String chartType) {
		// TODO Auto-generated method stub
		return ((IChartsDao)dao).getSafetyCheckDate(epid, Start, end,chartType);
	}

	
	public List<Date> getAllDay(Date startDay,Date endDay){
		
		List<Date> result = new ArrayList<Date>();
		Calendar start = Calendar.getInstance();  
        start.setTime(startDay); 
        Long startTIme = start.getTimeInMillis();  
      
        Calendar end = Calendar.getInstance();  
        end.setTime(endDay);  
        Long endTime = end.getTimeInMillis();  
      
        Long oneDay = 1000 * 60 * 60 * 24l;  
      
        Long time = startTIme;  
        result.add(startDay);
        while (time <= endTime) {  
            Date d = new Date(time);  
            result.add(d);
            time += oneDay;  
        } 
	
	return result;
	
	
}

}
