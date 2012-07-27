package org.credo.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.credo.base.entity.BaseEntity;
import org.credo.model.Userinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:抽象方法,Service基类,有实体管理器EntityManager,CRUD四个方法 </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@Transactional
public abstract class BaseService<T extends BaseEntity> {
	
	@PersistenceContext	protected EntityManager em;
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * @function 查询实体所有数据
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes" })
	public List queryAll(String tableName) throws Exception {
		String jpql = "SELECT t FROM "+tableName+" t";
		System.out.println("jpql"+jpql);
		List list=new ArrayList();
		try {
			list=this.em.createQuery(jpql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	/**
	 * @function 更新实体
	 * @param super IdEntity
	 * @throws Exception
	 */
	public void update(T t) throws Exception {
		String currentUser=((Userinfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userinfo")).getAccount();
		try {
			t.setUpdateBy(currentUser);
			t.setUpdateTime(new Date());
			em.merge(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * @function 新增实体
	 * @param super IdEntity
	 * @throws Exception
	 */
	public void insert(T t) throws Exception {
		String currentUser=((Userinfo)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userinfo")).getAccount();
		try {
			t.setCreateBy(currentUser);
			t.setCreateTime(new Date());
			t.setUpdateBy(currentUser);
			t.setUpdateTime(new Date());
			em.persist(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @function 删除实体。注：通常我们不调用此方法，只做业务上的删除，而非物理记录删除
	 * @param super IdEntity
	 * @throws Exception
	 */
	public void delete(T t) throws Exception {
		try {
			em.remove(t);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
