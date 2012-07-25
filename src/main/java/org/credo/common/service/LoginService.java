package org.credo.common.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.credo.common.entity.Userinfo;
import org.springframework.stereotype.Service;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:登录使用的loginService </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@SuppressWarnings("unchecked")
@Service
public class LoginService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext EntityManager em;
	
	/**
	 * @param loginAccount
	 * @param password
	 * @return List<Userinfo>
	 */
	public List<Userinfo> loginQueryUserByAccount(String loginAccount,String password){
		String jpql="SELECT u FROM Userinfo u where u.account =:account and u.password=:password ";
		List<Userinfo> list=this.em.createQuery(jpql).setParameter("account", loginAccount).setParameter("password", password).getResultList();
		return list;
	}
}
