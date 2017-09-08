package com.anbang.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anbang.dao.ICheckDao;
import com.anbang.dao.IItemDao;
import com.anbang.dao.IUnitDao;
import com.anbang.po.Check;
import com.anbang.po.Unit;
import com.anbang.service.ICheckService;
import com.anbang.vo.CheckVo;
import com.anbang.vo.UnitScoreVo;

@Service("com.anbang.service.ICheckService")
@Transactional
public class CheckService extends CommonService<Check>implements ICheckService {
	
	@Autowired
	IUnitDao unitDao;
	
	@Autowired
	IItemDao itemDao;
	
	@Autowired
	public void setDao(ICheckDao dao){
		this.dao=dao;
	}

	@Override
	public List<CheckVo> queryCheckByInfoId(String infoId) {
		List<CheckVo> result = new ArrayList<CheckVo>();
		List<Object[]> temp=((ICheckDao)dao).queryAllByInfoId(infoId);
		CheckVo cv;
		for(Object[] t:temp){
			cv=new CheckVo();
			cv.setItemNum(t[0].toString());
			cv.setItemContent(t[1].toString());
			cv.setItemLaw(t[2].toString());
			cv.setCheckLive(t[3].toString());
			cv.setCheckStatus(t[4].toString());
			//cv.setCheckResult(t[5].toString());
			cv.setCheckType(t[6].toString());
			result.add(cv);
			
		}
		return result;
	}

	@Override
	public Integer getTotalScoreByInfoId(String infoId) {
		//检查是否有直接否决项
		List<Object> noneIds=((ICheckDao)dao).getNoneIds(infoId);
		//没有
		if(noneIds==null||noneIds.size()==0){
		//获取check表中所有的单元总分
		List<UnitScoreVo> tmp= ((ICheckDao)dao).getScoreByInfoGByUnit(infoId);
		Unit unit;
		int score=0;
		int totalScore=100;
		//最终得分
		for(UnitScoreVo usv:tmp){
			unit=unitDao.get(usv.getUnitId());
			int uscore = (int) (unit.getScore()*unit.getKi());
			int vscore = (int) (usv.getScore()*unit.getKi());
			if(uscore<vscore)
				score+=0;
			else
			score+=(uscore-vscore)*usv.getToZero();
			
			totalScore-=unit.getScore()*unit.getKi();
		}

		return totalScore+score;}
		//有直接否决项则本次检查归零
		else{
			return 0;
		}
	}

	@Override
	public String getStatByInfoId(String infoId,String epId) {
		List<Object[]> tmp=((ICheckDao)dao).getStatByInfoGByStatus(infoId);
		String pass = "0",ex = "0",error = "0";
		for(Object[] os:tmp){
			switch (os[0].toString()){
			case "一般隐患":
				ex=os[1].toString();
				break;
			case "重大隐患":
				error=os[1].toString();
				break;
			}
		
			pass=this.countCheckByEpid2(epId)-Integer.parseInt(ex)-Integer.parseInt(error)+"";
			
		}
		return pass+":"+ex+":"+error;
	}

	@Override
	public String getExIds(String infoId) {
		String result="";
		List<Object> tmp= ((ICheckDao)dao).getExIds(infoId);
		int j=tmp.size();
		if(j>0){
		for(int i=0;i<j-1;i++){
			result+=tmp.get(i)+";";
		}
		result+=tmp.get(j-1).toString();}
		return result;
	}

	@Override
	public String getErrorIds(String infoId) {
		String result="";
		List<Object> tmp= ((ICheckDao)dao).getErrorIds(infoId);
		int j=tmp.size();
		if(j>0){
		for(int i=0;i<j-1;i++){
			result+=tmp.get(i)+";";
		}
		result+=tmp.get(j-1).toString();}
		return result;
	}

	@Override
	public String getCheckByItemNum(String itemNum,String infoId) {
		// TODO Auto-generated method stub
		return ((ICheckDao)dao).getCheckByItemNum(itemNum,infoId);
	}

	@Override
	public CheckVo queryCheckByCheckId(String checkId) {
		CheckVo result =new CheckVo();
		Object[] temp=((ICheckDao)dao).getCheckVo(checkId);
		if(null!=temp&&temp.length>0){
		result.setItemNum(temp[0].toString());
		result.setItemContent(temp[1].toString());
		result.setItemLaw(temp[2].toString());
		result.setCheckLive(temp[3].toString());
		result.setCheckStatus(temp[4].toString());	
		return result;}
		return null;
	}

	@Override
	public List<CheckVo> queryCheckByCheckIds(String[] ids) {
		List<CheckVo> result = new ArrayList<CheckVo>();
		for(String id:ids){
			result.add(this.queryCheckByCheckId(id));
		}
		
		return result;
	}

	@Override
	public Long countCheckByEpid(String infoId) {
		// TODO Auto-generated method stub
		return dao.countByParams(new String[]{"infoId"}, new String[]{infoId});
	}

	@Override
	public Long countCheckByEpid2(String epId) {
		// TODO Auto-generated method stub
		List<String> unitIds = new ArrayList<String>();
		List<Unit> units = unitDao.getUnitByEpid(epId);
		for(Unit u:units){
			unitIds.add(u.getId());
		}
		return itemDao.getItemCount(unitIds);
	}

	@Override
	public List<Check> queryAllByDeptIdAndDate(Date start, Date end) {
		// TODO Auto-generated method stub
		List<Check> check = new ArrayList<Check>();
		List<String> id = ((ICheckDao)dao).getIdByDate(start, end);
		check = ((ICheckDao)dao).queryAllByDeptIdAndDate(id);
		return check;
	}


	
	

	
	
	
	
	
}
