package org.credo.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.credo.base.entity.BaseEntity;
import org.credo.model.Userinfo;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Project: Credo's Base</p>
 * <p>Description:抽象方法,Service基类,有实体管理器EntityManager,CRUD四个方法,针对单表 </p>
 * <p>Copyright (c) 2012 LionCredo.All Rights Reserved.</p>
 * @author <a href="zhaoqianjava@foxmail.com">LionCredo</a>
 */
@SuppressWarnings({ "rawtypes","unchecked" })
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
	public List queryAll(String tableName) throws Exception {
		String jpql = "SELECT t FROM "+tableName+" t";
		List list=new ArrayList();
		try {
			log.info("jpql"+jpql);
			Query query=this.em.createQuery(jpql);
			log.info("jpql"+jpql);
			list=query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	public Object[] queryLazyModelNeedData(String tableName,int firstResult,int maxResults,String queryJpql) throws Exception{
		log.info(tableName);
		String jpqlPagination="SELECT COUNT(t)  FROM "+tableName+" t ";
		String jpqlQueryData="SELECT t FROM "+tableName+" t ";
		if(null!=queryJpql||"".equals(queryJpql)){
			jpqlQueryData=queryJpql;
		}
		int pagination=0;
		List<T> list=new ArrayList<T>();
		try {
			log.info("jpqlPagination:"+jpqlPagination);
			log.info("jpqlQueryData:"+jpqlQueryData);
			pagination=Integer.valueOf((this.em.createQuery(jpqlPagination).getSingleResult()).toString());
			list=this.em.createQuery(jpqlQueryData).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
			log.info("pagination:"+pagination);
			log.info("list:"+list.size());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		Object[] objArray={pagination,list};
		return objArray;
	} 
	
	public LazyDataModel<T> queryLazyModel(boolean isReal,final String tableName,final String queryJpql) throws Exception{
		LazyDataModel<T> lazyUsed=new LazyDataModel<T>() {
			private static final long serialVersionUID = 1L;
			List<T> tableData = null;
			int tableDataSize=0;
			@Override
			public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
				try {
					Object[] objArray=(Object[])queryLazyModelNeedData(tableName,first, pageSize,queryJpql);
					tableData=(List<T>) objArray[1];
					tableDataSize=(Integer) objArray[0];
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				int size = tableData.size();
				this.setRowCount(tableDataSize);
				if (size > pageSize) {
					try {
						return (List<T>) tableData.subList(first, first + pageSize);
					} catch (IndexOutOfBoundsException e) {
						return (List<T>) tableData.subList(first, first + (size % pageSize));
					}
				} else {
					return (List<T>) tableData;
				}
			}
			
			@Override
			public Object getRowKey(T object) {
				return object.getId();
			}
			
			@Override
			public T getRowData(String rowKey) {
				for (T b : tableData) {
					if (b.getId() == Integer.parseInt(rowKey)) {
						return (T) b;
					}
				}
				return null;
			}
		};
		return lazyUsed;
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
