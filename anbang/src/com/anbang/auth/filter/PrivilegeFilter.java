package com.anbang.auth.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anbang.auth.service.IPrivilegeService;
import com.anbang.auth.service.impl.PrivilegeService;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
@WebFilter("/privi")
public class PrivilegeFilter implements Filter {
	IPrivilegeService priviService;
	
	
    /**
     * Default constructor. 
     */
    public PrivilegeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//获得请求资源地址
		String url = req.getRequestURL().toString();
		String privi = url.substring(url.indexOf("anbang"));
		privi=privi.substring(privi.indexOf("/")+1);
	
			Set<String> priviList = (Set<String>) req.getSession().getAttribute("priviList");
			if(priviList==null||priviList.size()==0||!priviList.contains(privi))
				resp.sendRedirect("user/toLogin.do");

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		priviService  = new PrivilegeService();  
		
	}

}
