package org.credo.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description: 字符编码过滤器,因为使用了Spring的字符编码过滤器,这里在web.xml中未配置!</p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@Deprecated
public class SetCharacterEncodingFilter implements Filter {
	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		if (encoding == null)encoding = "UTF-8";
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}
}
