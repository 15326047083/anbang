package com.anbang.po;

import java.util.List;

/**
 * 分页工具类 配合pager-taglib
 * @author Administrator
 *
 */
public class PageModel<T> {

      
    private long total;  

    private List<T> datas;  
   
    public long getTotal() {  
        return total;  
    }  
    public void setTotal(long total) {  
        this.total = total;  
    }  
    public List<T> getDatas() {  
        return datas;  
    }  
    public void setDatas(List<T> datas) {  
        this.datas = datas;  
    }  
      
	
	
	
}
