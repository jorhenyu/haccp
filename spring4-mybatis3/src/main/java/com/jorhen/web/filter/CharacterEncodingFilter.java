package com.jorhen.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 篩檢程式處理表單傳到servlet的亂碼問題
 * @author jorhen
 *
 */
/*
public class CharacterEncodingFilter implements Filter {

	//存儲系統使用的字元編碼
	private String encoding=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//encoding在web.xml中指定
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//解決表單提交時的中文亂碼問題
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
*/

