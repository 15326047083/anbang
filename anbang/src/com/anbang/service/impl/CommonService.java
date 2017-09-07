package com.anbang.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anbang.dao.ICommonDao;
import com.anbang.service.ICommonService;
@Transactional
public class CommonService<T> implements ICommonService<T>{

	protected ICommonDao<T> dao;

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		dao.save(t);
		
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		dao.delete(t);
		
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		dao.update(t);
		
	}

	@Override
	public T get(String id) {
		// TODO Auto-generated method stub
		return dao.get(id);
	}

	@Override
	public List<T> queryAll(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.queryAll(pageNum, pageSize);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return dao.count();
	}

	@Override
	public List<T> queryAll() {
		// TODO Auto-generated method stub
		return dao.queryAll();
	}

	@Override
	public List<T> queryAllByParams(String[] params, Object[] values,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return dao.queryAllByParams(params, values, pageNum, pageSize);
	}

	@Override
	public long countByParams(String[] params, Object[] values) {
		// TODO Auto-generated method stub
		return dao.countByParams(params, values);
	}

	@Override
	public void saveOrUpdate(T t) {
		dao.saveOrUpdate(t);
		
	}

	
	
	
}
