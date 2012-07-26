package org.credo.model;

import java.io.Serializable;
import javax.persistence.*;

import org.credo.base.entity.BaseEntity;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description: The persistent class for the userinfo database table.</p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@Entity
public class Userinfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -5905019027934511246L;
	@Column(name = "account", length = 30,nullable=false)
	private String account;
	@Column(name = "address", length = 100,nullable=true)
	private String address;
	@Column(name = "age", length = 6,nullable=true)
	private Short age;
	@Column(name = "email", length = 50,nullable=true)
	private String email;
	@Column(name = "password", length = 20,nullable=false)
	private String password;
	@Column(name = "realname", length = 30,nullable=true)
	private String realname;
	@Column(name = "sex", length = 1,nullable=true)
	private String sex;
	@Column(name = "usable", length = 1,nullable=false)
	private String usable;
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Short getAge() {
		return age;
	}
	public void setAge(Short age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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