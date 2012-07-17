package org.credo.showcase.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.credo.showcase.vo.TableVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

//可以使用spring来管理
//@Controller
//@Scope("view")
@ManagedBean
@ViewScoped
public class TableBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<TableVO> list=new ArrayList<TableVO>();
	
	public TableBean(){
		list.add(new TableVO(1, "赵谦", "25"));
		list.add(new TableVO(2, "LionCredo", "25"));
		list.add(new TableVO(3, "forjava", "25"));
	}

	public List<TableVO> getList() {
		return list;
	}
	public void setList(List<TableVO> list) {
		this.list = list;
	}
}
