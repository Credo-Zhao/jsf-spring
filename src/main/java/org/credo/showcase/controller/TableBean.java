package org.credo.showcase.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.credo.common.entity.Userinfo;
import org.credo.showcase.service.TableService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//可以使用spring来管理
//@ManagedBean
//@ViewScoped
@Controller
@Scope("view")
public class TableBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Resource private TableService tableService;
	
	private List<Userinfo> list=new ArrayList<Userinfo>();
	
	
	public void queryUserInfo(){
		list=this.tableService.queryAllUserInfo();
	}
	
	
	public TableBean(){
		System.out.println("进入构造方法!");
	}

	public List<Userinfo> getList() {
		return list;
	}
	public void setList(List<Userinfo> list) {
		this.list = list;
	}
}
