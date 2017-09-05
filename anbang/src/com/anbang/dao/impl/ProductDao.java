package com.anbang.dao.impl;

import org.springframework.stereotype.Repository;

import com.anbang.dao.IProductDao;
import com.anbang.po.Product;
@Repository(value="com.anbang.dao.IProductDao")
public class ProductDao extends CommonDao<Product> implements IProductDao{

}
