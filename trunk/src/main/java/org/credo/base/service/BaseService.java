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
	 * @return 返回一个List
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
	
	/**
	 * @param tableName model层实体名.
	 * @return 返回数据行数,常用语lazyModel分页使用.
	 * @throws Exception
	 */
	public int queryDataRows(String tableName) throws Exception{
		String jpql="SELECT COUNT(t)  FROM "+tableName+" t ";
		int pagination=0;
		try {
			pagination=Integer.valueOf((this.em.createQuery(jpql).getSingleResult()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pagination;
	}
	
	/**
	 * @function LazyModel真/假分页,如果是多表关联查询,必须获取到数据的总行数.
	 * @param tableName 实体名
	 * @param firstResult 需要的第一条数据的行数
	 * @param maxResults  需要的数据条数.
	 * @param queryJpql	 完整的JPQL语句,如果存在优先使用,将不再使用实体名,用于where条件等的追加,这样做是避免SQL注入.
	 * @return	返回lazyModel所需要的list数据.
	 * @throws Exception
	 */
	public List<T> queryLazyModelNeedData(String tableName,int firstResult,int maxResults,String queryJpql) throws Exception{
		String jpqlQueryData="SELECT t FROM "+tableName+" t ";
		if(null!=queryJpql||"".equals(queryJpql)){
			jpqlQueryData=queryJpql;
		}
		List<T> list=new ArrayList<T>();
		try {
			list=this.em.createQuery(jpqlQueryData).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	} 
	
	/**
	 * @function LazyModel真/假分页,如果无限定条件,只需前两参数.如果加条件,需要前三条件.如果是多表关联查询,必须获取到数据的总行数.并且事先查出.并写出专门的JPQL语句.需要1,3,4条件.
	 * @param isReal 是否为真分页,如果true将进行SQL分页,否则进行假分页(服务器内存存在所有数据),
	 * @param tableName	实体名
	 * @param queryJpql 默认为NULL.不使用.(多条件,多表情况下使用)完整的JPQL语句,如果存在优先使用,将不再使用实体名,用于where条件等的追加,这样做是避免SQL注入.
	 * @param totalRows 默认为0.不使用.(多表查询情况使用)数据的总行数,如果存在优先使用,将不再去查询tableDataSize=queryDataRows(tableName);而是直接tableDataSize=totalRows;
	 * @return lazyModel数据.页面显示使用.
	 * @throws Exception
	 */
	public LazyDataModel<T> queryLazyModel(final boolean isReal,final String tableName,final String queryJpql,final int totalRows) throws Exception{
		LazyDataModel<T> lazyUsed=new LazyDataModel<T>() {
			private static final long serialVersionUID = 1L;
			List<T> tableData = null;
			int tableDataSize=0;
			@Override
			public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
				try {
					if(isReal){//真分页
						tableData=queryLazyModelNeedData(tableName, first, pageSize, queryJpql);
					}else{//假分页
						tableData=queryAll(tableName);
					}
					if(totalRows!=0){
						tableDataSize=totalRows;
					}
					tableDataSize=queryDataRows(tableName);
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
			//获取行ID,这里的ID使用的是实体的ID或者VO的ID,所以如果VO显示lazyModel则必须继承IdEntity
			@Override
			public Object getRowKey(T object) {
				return object.getId();
			}
			//获取行数据
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
