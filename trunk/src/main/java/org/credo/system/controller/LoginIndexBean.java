package org.credo.system.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.credo.common.entity.Userinfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class LoginIndexBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	@PostConstruct
	public void init(){
		Userinfo userinfo=(Userinfo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userinfo");
		this.userName=null==userinfo.getRealname()?userinfo.getAccount():userinfo.getRealname();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
