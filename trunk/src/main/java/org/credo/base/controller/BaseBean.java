package org.credo.base.controller;


import javax.annotation.Resource;

import org.credo.base.entity.BaseEntity;
import org.credo.common.controller.LoginBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseBean<T extends BaseEntity> {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource protected LoginBean loginBean;
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
