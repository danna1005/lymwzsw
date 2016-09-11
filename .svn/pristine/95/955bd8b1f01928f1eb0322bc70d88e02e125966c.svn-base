package com.thiscc.tools.filter;

import com.thiscc.tools.common.BaseDAO;
import com.thiscc.tools.common.BizFactory;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class SetcharsetEncoding extends HttpServlet implements Filter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4662558434143826753L;
	
	private BaseDAO dao = (BaseDAO) BizFactory.getBean("basedao");

	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		paramServletRequest.setCharacterEncoding("UTF-8");
		paramServletResponse.setCharacterEncoding("UTF-8");
		((HttpServletResponse) paramServletResponse).setHeader("X-UA-Compatible", "IE=EmulateIE8"); // or IE=EmulateIE8
		 
		try {
			paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {
			this.dao.closeSession();
		}
	}

	public void init(FilterConfig paramFilterConfig) throws ServletException {
	}
}