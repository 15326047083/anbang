package com.anbang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anbang.dao.IProductDao;
import com.anbang.po.Product;
import com.anbang.service.IProductService;

@Service("com.anbang.service.IProductService")
public class ProductService extends CommonService<Product>implements IProductService {
	
	@Autowired
	public void setDao(IProductDao dao){
		this.dao=dao;
	}
}
