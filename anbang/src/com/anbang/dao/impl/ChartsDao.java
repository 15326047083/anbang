package com.anbang.dao.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.anbang.dao.IChartsDao;
import com.anbang.po.Charts;

@Repository("com.anbang.dao.IChartsDao")
public class ChartsDao extends CommonDao<Charts> implements IChartsDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Charts> getDeptChartsByEpId(String epid,String chartType,Date start,Date end) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().findByNamedParam("from Charts c where c.chartType=(:chartType) and c.epid=(:epid) and c.date >=(:start) and c.date<=(:end) order by c.date asc", new String[]{"chartType","epid","start","end"}, new Object[]{chartType,epid,start,end});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getDoubleChartsDateByEpId(final String epid,final Date start,final Date end) {
		
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Date> >() {

			@Override
			public List<Date> doInHibernate(Session arg0) throws HibernateException, SQLException {
				Query q = arg0.createQuery("select c.date from Charts c where c.epid=(:epid)  and c.date >=(:start) and c.date<=(:end) order by c.date asc");
				q.setParameter("epid", epid).setParameter("start", start).setParameter("end", end);
				return q.list();
			}
		});
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Charts> getDoubleChartsByEpId(String epid, String chartType,Date start,Date end) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().findByNamedParam("select new Charts(score,date) from Charts c where c.chartType=(:chartType) and c.epid=(:epid) and c.date >= (:start) and c.date <= (:end) order by c.date asc", new String[]{"chartType","epid","start","end"}, new Object[]{chartType,epid,start,end});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Charts> getSelfChatsByEpId(String epid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().findByNamedParam("from Charts c where  (c.epid=(:epid1) and c.chartType ='ep')  order by c.date asc", new String[]{"epid1"}, new String[]{epid});
	}
	@Override
	public List<Date> getSafetyCheckDate(final String epid,final Date Start,final Date end,final String chartType){
		List<Date> dates=this.getHibernateTemplate().executeFind(new HibernateCallback<List<Date> >() {

			@Override
			public List<Date>  doInHibernate(Session arg0) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				String hql = "select c.date from  Charts c where c.chartType=(:chartType) and c.epid=(:epid) and c.date<= (:end) and c.date >=(:Start) order by c.date asc";
				Query q = arg0.createQuery(hql);
				q.setParameter("chartType", chartType);
				q.setParameter("epid", epid);
				q.setParameter("Start", Start);
				q.setParameter("end", end);
				return (List<Date>)q.list();
			}
		});
		dates.addAll(this.getAllDay(Start, end));
		Collections.sort(dates);
		return dates;
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
