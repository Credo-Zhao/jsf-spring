package org.credo.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description: Session验证，如果没有登录或者Session失效，将被拦截,返回登录界面!</p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// 用于Filter销毁前,完成某些资源的回收.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 处理方法体
		HttpServletRequest httpRequest=(HttpServletRequest)request;
		if(null==httpRequest.getSession().getAttribute("userinfo")){
			((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath()+"/login/login.xhtml");
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// 初始化
	}

}
