package com.anbang.service;

import java.io.Serializable;
import java.util.List;

public interface ICommonService<T> {
	/**
	 * 添加记录
	 * @param t
	 */
	public void save(T t);
	/**
	 * 删除记录
	 * @param t
	 */
	public void delete(T t);
	/**
	 * 修改记录
	 * @param t
	 */
	public void update(T t);
	/**
	 * 按id取得记录
	 * @param id
	 * @return
	 */
	public T get(String id);
	/**
	 * 分页查询记录
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<T> queryAll(int pageNum,int pageSize);

	/**
	 * 查询记录总数
	 * @return
	 */
	public long count();
	
	public List<T> queryAll();
	
	public List<T> queryAllByParams(String [] params,Object [] values,int pageNum,int pageSize);
	
	public long countByParams(String [] params,Object [] values);
	
	public void saveOrUpdate(T t);

}
