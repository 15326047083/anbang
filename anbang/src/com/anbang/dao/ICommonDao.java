package com.anbang.dao;

import java.util.List;
/**
 * ͨ
 * @author zyk
 *
 * @param <T>
 */
public interface ICommonDao<T> {
	/**
	 * 插入
	 * @param t
	 */
	public void save(T t);
	/**
	 * 删除
	 * @param t
	 */
	public void delete(T t);
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t);
	/**
	 * 按id查询
	 * @param id
	 * @return
	 */
	public T get(String id);
	/**
	 * 分页无条件查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<T> queryAll(int pageNum,int pageSize);
	/**
	 * 无条件计数
	 * @return
	 */
	public long count();
	/**
	 * 无条件无分页查询
	 * @return
	 */
	public List<T> queryAll();
	

	public List<T> queryAllByParams(String[] params,Object [] values,int pageNum,int pageSize);
	
	public long countByParams(String[] params,Object [] values);
	
	public void saveOrUpdate(T t);
}
