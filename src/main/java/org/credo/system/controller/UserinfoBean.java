package org.credo.system.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.credo.base.controller.BaseBean;
import org.credo.model.Userinfo;
import org.credo.system.service.UserinfoService;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("view")
public class UserinfoBean extends BaseBean<Userinfo> implements Serializable{
	// extends BaseBean
	private static final long serialVersionUID = 1L;
	@Resource private UserinfoService userinfoService;
	
	private String queryBuilderAccount;
	private List<Userinfo> list=new ArrayList<Userinfo>();
	private Userinfo userinfo=new Userinfo();
	private boolean isModify=true;
	private String sex;
	private String usable;
	private LazyDataModel<Userinfo> lazyUser;
	
	@PostConstruct
	public void queryUserInfo(){
		try {
			this.lazyUser=userinfoService.queryLazyModel(true, "Userinfo", null);
		} catch (Exception e) {
			System.out.println("LazyModel出现错误!");
			e.printStackTrace();
		}
	}
	
	
	public void sumbitDlgData(){
		userinfo.setSex(sex);
		userinfo.setUsable(usable);
		System.out.println("look you what:"+userinfo.getAccount().length());
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
		//queryUserInfo();
		//更新session中user信息,在用户修改自己资料的情况下.
		Map<String, Object> map =FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Userinfo userinfoSession=(Userinfo) map.get("userinfo");
		if(userinfo.getId()==userinfoSession.getId()){
			map.put("userinfo", userinfo);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("成功!", ""));
		RequestContext.getCurrentInstance().addCallbackParam("aboutMeAddInfo", "Y");
	}
	
	public void resetDlg(){
		this.userinfo=null;
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
	public LazyDataModel<Userinfo> getLazyUser() {
		return lazyUser;
	}
	public void setLazyUser(LazyDataModel<Userinfo> lazyUser) {
		this.lazyUser = lazyUser;
	}
}
