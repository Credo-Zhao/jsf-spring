package org.credo.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.credo.base.entity.IdEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:抽象方法,Service基类,有实体管理器EntityManager,CRUD四个方法 </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
public abstract class BaseService<T extends IdEntity> {
	
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
		String jpql = "SELECT t FROM " + tableName+" t";
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
		try {
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
		try {
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
