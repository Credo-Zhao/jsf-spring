package org.credo.base.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:用户的样式参数选择 </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@ManagedBean
@SessionScoped
public class GuestPreferences implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String theme="aristo"; //defalut

	public String getTheme() {
		//获取JSF的FacesContext的当前线程实例,并获取其外部上下文,然后返回一个不可变的 Map，
		//其键是包含于当前请求的请求参数名称集，其值（类型为 String）是第一个（或唯一一个）对应于底层请求所返回的每个参数名称的值。返回的 Map 必须实现不可修改映射的整个协定，
		Map<String, String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params.containsKey("theme")){
			theme=params.get("theme");
		}
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
}
