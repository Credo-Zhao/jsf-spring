package org.credo.system.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.credo.model.Userinfo;
import org.credo.system.service.UserinfoService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings({"unchecked"})
@Controller
@Scope("request")
public class UserinfoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@Resource UserinfoService userinfoService;
	
	private String queryBuilderAccount;
	private List<Userinfo> list=new ArrayList<Userinfo>();
	private Userinfo userinfo;
	private String dialogHeadTxt;
	
	@PostConstruct
	public void queryUserInfo(){
		try {
			list=this.userinfoService.queryAll("Userinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addUserinfo(){
		try {
			this.userinfoService.insert(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void modifyUserinfo(){
		try {
			this.userinfoService.update(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reset(boolean isAdd){
		if(isAdd){
			this.dialogHeadTxt="添加新用户";
		}else{
			this.dialogHeadTxt="修改用户信息";
		}
		this.queryBuilderAccount="";
		this.userinfo=new Userinfo();
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

	public String getDialogHeadTxt() {
		return dialogHeadTxt;
	}

	public void setDialogHeadTxt(String dialogHeadTxt) {
		this.dialogHeadTxt = dialogHeadTxt;
	}

	public String getQueryBuilderAccount() {
		return queryBuilderAccount;
	}

	public void setQueryBuilderAccount(String queryBuilderAccount) {
		this.queryBuilderAccount = queryBuilderAccount;
	}
}
