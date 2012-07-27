package org.credo.common.service;

import java.io.Serializable;
import java.util.List;

import org.credo.base.service.BaseService;
import org.credo.model.Userinfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:登录使用的loginService </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Transactional
@Service
public class LoginService extends BaseService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * @function 登录查询数据库用户是否存在.返回list结果集在Bean里进行控制.
	 * @return List<Userinfo>
	 */
	public List<Userinfo> loginQueryUserByAccount(String loginAccount,String password){
		String jpql="SELECT u FROM Userinfo u where u.account =:account and u.password=:password ";
		List<Userinfo> list=this.em.createQuery(jpql).setParameter("account", loginAccount).setParameter("password", password).getResultList();
		return list;
	}
	
}
