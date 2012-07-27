package org.credo.common.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.credo.common.service.LoginService;
import org.credo.model.Userinfo;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:登录使用的javaBean </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@Controller
@Scope("session")
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource private LoginService loginService;
	
	private String loginAccount;
	private String loginPassword;
	private Userinfo userinfo;
	private String newPwdFirst;
	private String newPwdSecond;
	
	/**
	 * 登录处理
	 * @return faces-config所需字符串
	 */
	public String loginProcess(){
		log.info("进入Bean方法!");
		log.warn(loginAccount);
		log.warn(loginPassword);
		List<Userinfo> userinfoList=loginService.loginQueryUserByAccount(loginAccount, loginPassword);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(null==userinfoList||userinfoList.size()<1){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "登录错误:", "用户名或密码不正确!"));
			return "";
		}
		if(userinfoList.size()>2){
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "登录错误:", "有多个相同账户,请联系管理员!"));
			return "";
		}
		Userinfo userinfo=userinfoList.get(0);
		// success,装入session中
		Map<String, Object> map =facesContext.getExternalContext().getSessionMap();
		map.put("userinfo", userinfo);
		this.userinfo=userinfo;
		return "LoginSuccess";
	}
	
	public void resetForm(){
		this.newPwdFirst=null;
		this.newPwdSecond=null;
	}
	

	/**
	 * @return 获取当前用户的信息
	 */
	public Userinfo getCurrentUserinfo(){
		Userinfo currentUserinfo=this.userinfo;
		return currentUserinfo;
	}
	
	/**
	 * 用户修改资料,修改密码
	 */
	@SuppressWarnings("unchecked")
	public void modifyAboutMe(){
		if(null!=this.newPwdFirst && !("").equals(this.newPwdFirst.trim())&&null!=this.newPwdSecond && !("").equals(this.newPwdSecond.trim())){
			if(this.newPwdFirst.equals(this.newPwdSecond)){
				this.userinfo.setPassword(newPwdFirst);
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"新密码错误:","两次输入的密码不一致!"));
				return;
			}
		}
		try {
			loginService.update(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"数据库错误:","数据库更新数据失败!请联系管理员!"));
			return;
		}
		RequestContext.getCurrentInstance().addCallbackParam("aboutMeAddInfo", "yes");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("成功","更新用户资料成功，请查询并确认！"));
	}
	
	
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getNewPwdFirst() {
		return newPwdFirst;
	}

	public void setNewPwdFirst(String newPwdFirst) {
		this.newPwdFirst = newPwdFirst;
	}

	public String getNewPwdSecond() {
		return newPwdSecond;
	}

	public void setNewPwdSecond(String newPwdSecond) {
		this.newPwdSecond = newPwdSecond;
	}
}
