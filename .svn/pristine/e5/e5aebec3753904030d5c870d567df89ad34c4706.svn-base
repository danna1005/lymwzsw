package com.thiscc.tools.filter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class LoginFilter implements Filter {
	
	//~ Static/initializer fields ==================================================================
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6899562106133365073L;
	
	//~ Instaice fields ============================================================================
	
	// 过滤参数配置
	private FilterConfig filterConfig;

    // 忽略路径的模式
	private List<Pattern> patterns;
	
	// 
	private String loginUrl;
	
	//~ Methods ====================================================================================
	
	/**
	 * 
	 * @param expression
	 * @param patterns
	 * @return
	 */
	private boolean ignore(String expression, List<Pattern> patterns) {
		if (patterns == null)
			return false;
		for (int i = 0; i < patterns.size(); ++i)
			if (patterns.get(i).matcher(expression).find())
				return true;
		return false;
	}

	/**
	 * 
	 * @param expression
	 * @param separator
	 * @return
	 */
	private List<Pattern> getPatterns(String expression, String separator) {
		String[] arrayOfString = expression.split(separator);
		List<Pattern> lstPattern = new LinkedList<Pattern>();
		for (int i = 0; i < arrayOfString.length; ++i) {
			Pattern p = Pattern.compile(arrayOfString[i]);
			if ( p != null ) {
				lstPattern.add(p);
			}
		}
		return lstPattern;
	}
	
	/**
	 * 
	 */
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		this.filterConfig = paramFilterConfig;
		String strIgnoreExpression = this.filterConfig.getInitParameter("ignoreExpression");
		String strExpressionSeparator = this.filterConfig.getInitParameter("expressionSeparator");
		this.loginUrl = this.filterConfig.getInitParameter("loginUrl");
		if (StringUtils.isBlank(strIgnoreExpression))
			return;
		this.patterns = getPatterns(strIgnoreExpression, strExpressionSeparator);
	}

	/**
	 * 
	 */
	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;
		
		Object userId = request.getSession().getAttribute("userid");
		String strContext = request.getContextPath();
		String strURI = request.getRequestURI();
		if ( strURI.startsWith(strContext) ) {
			strURI = strURI.substring(strContext.length());
		}
		if ( (userId == null) 
				&& (!StringUtils.equals(strURI, loginUrl))
				&& (!(ignore(strURI, this.patterns))) ) {
			response.sendRedirect(strContext + loginUrl);
		} else {
			paramFilterChain.doFilter(request, response);
		}
	}
	
	/**
	 * 
	 */
	public void destroy() {
	}

}