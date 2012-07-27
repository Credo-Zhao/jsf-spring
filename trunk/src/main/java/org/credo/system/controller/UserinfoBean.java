package org.credo.system.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.credo.model.Userinfo;
import org.credo.system.service.UserinfoService;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings({"unchecked"})
@Controller
@Scope("view")
public class UserinfoBean implements Serializable{

	protected Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	@Resource UserinfoService userinfoService;
	
	private String queryBuilderAccount;
	private List<Userinfo> list=new ArrayList<Userinfo>();
	private Userinfo userinfo=new Userinfo();
	private boolean isModify=true;
	private String sex;
	private String usable;
	
	@PostConstruct
	public void queryUserInfo(){
		try {
			list=this.userinfoService.queryAll("Userinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sumbitDlgData(){
		userinfo.setSex(sex);
		userinfo.setUsable(usable);
		try {
			if (isModify) {
				this.userinfoService.update(userinfo);
			} else {
				this.userinfoService.insert(userinfo);
				this.isModify=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "数据库错误,请联系管理员!", ""));
			return;
		}
		queryUserInfo();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("成功!", ""));
		RequestContext.getCurrentInstance().addCallbackParam("aboutMeAddInfo", "Y");
	}
	
	public void resetDlg(){
		this.userinfo=new Userinfo();
		this.isModify=false;
		this.sex=null;
		this.usable="";
	}
	
	public List<Userinfo> getList() {
		return list;
	}
	public void setList(List<Userinfo> list) {
		this.list = list;
	}
	public Userinfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}
	public String getQueryBuilderAccount() {
		return queryBuilderAccount;
	}
	public void setQueryBuilderAccount(String queryBuilderAccount) {
		this.queryBuilderAccount = queryBuilderAccount;
	}
	public boolean getIsModify() {
		return isModify;
	}
	public void setIsModify(boolean isModify) {
		this.isModify = isModify;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsable() {
		return usable;
	}
	public void setUsable(String usable) {
		this.usable = usable;
	}
}
