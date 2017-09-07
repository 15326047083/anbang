package com.anbang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IAreaDao;
import com.anbang.dao.IDangerInfoDao;
import com.anbang.dao.IEnterpriseDao;
import com.anbang.dao.impl.AreaDao;
import com.anbang.po.Area;
import com.anbang.po.DangerInfo;
import com.anbang.po.Enterprise;
import com.anbang.service.IEnterpriseService;
import com.anbang.vo.EpVo;

@Service("com.anbang.service.IEnterpriseService")
public class EnterpriseService extends CommonService<Enterprise>implements IEnterpriseService {
	
	@Autowired
	public void setDao(IEnterpriseDao dao){
		this.dao=dao;
	}
	@Autowired
	private IDangerInfoDao infoDao;
	
	@Autowired
	private IAreaDao areaDao;
	

	

	
	//private 
	 
	@Override
	public List<Enterprise> getEnterpriseByDeptId(String deptId) {
		// TODO Auto-generated method stub
		return ((IEnterpriseDao)dao).getEnterpriceByDept(deptId);
	}

	@Override
	public List<String> deleteAll(String[] ids) {
		// TODO Auto-generated method stub
		return ((IEnterpriseDao)dao).deleteAll(ids);
	}

	@Override
	public EpVo getEpVoById(String id) {
		EpVo epVo = new EpVo();
		// TODO Auto-generated method stub
		List<DangerInfo> temp=infoDao.queryAllByParams(new String[]{"epId"},new Object[]{id},0,1);
		if(!temp.isEmpty()){
			DangerInfo info = temp.get(0);
			epVo.setSource(info.getSource());
			epVo.setTech(info.getTech());
		}
		Enterprise ep = ((IEnterpriseDao)dao).get(id);
		Area area = areaDao.get(ep.getAreaId());
		
		epVo.setEname(ep.getEname());
		epVo.setAddress(ep.getAddress());
		epVo.setArea(area.getAname());
		epVo.setCorporation(ep.getCorporation());
		epVo.setCount(ep.getCount());
		epVo.setDesc(ep.getDesc());
		epVo.setEcode(ep.getEcode());
		epVo.setEmail(ep.getEmail());
		epVo.setEspecial(ep.getEspecial());
		epVo.setEtype(ep.getEtype());
		epVo.setFax(ep.getFax());
		epVo.setLeaderCount(ep.getLeaderCount());
		epVo.setLiveWorker(ep.getLiveWorker());
		epVo.setMiddleCount(ep.getMiddleCount());
		epVo.setPermision(ep.getPermision());
		epVo.setSafetyCount(ep.getSafetyCount());
		
		epVo.setTechCount(ep.getTechCount());
		epVo.setTel(ep.getTel());
		epVo.setWorker(ep.getWorker());
		
		return epVo;
	}

	public IDangerInfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(IDangerInfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public IAreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(IAreaDao areaDao) {
		this.areaDao = areaDao;
	}
	
	
}
