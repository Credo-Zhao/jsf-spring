package org.credo.base.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
@SuppressWarnings({ "unchecked" })
@Transactional
public abstract class BaseService<T extends BaseEntity> {
	
	@PersistenceContext	protected EntityManager em;
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * @function LazyModel真分页.适用于大数据量.
	 * @param query JPQL查询的query对象
	 * @param rowCount 所查询数据的总行数
	 * @return LazyModel真分页所需要的LazyDataModel<T>
	 * @throws Exception
	 */
	public LazyDataModel<T> queryRealLazyModel(final String query,final int rowCount) throws Exception{
		log.info("here");
		LazyDataModel<T> lazyUsed=new LazyDataModel<T>() {
			private static final long serialVersionUID = 1L;
			List<T> tableData = null;
//			@Override
//			public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
//				log.info("333");
//				return null;
//			}
			@Override
			public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
				log.info("here2");
				try {
					log.info("进入基类!");
					tableData=em.createQuery(query).setFirstResult(first).setMaxResults(pageSize).getResultList();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				 //rowCount  
				int size = tableData.size();
				this.setRowCount(rowCount);
				
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

 /*class LazySorter<T> implements Comparator<T> {

	private String sortField;
	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compare(T o1, T o2) {
		try {
			Type genType = getClass().getGenericSuperclass();  
			System.out.println("此类的超累为:"+genType);
			Object value1 = ((Class<? extends LazySorter>) genType).getField(this.sortField).get(genType);
			Object value2 = ((Class<? extends LazySorter>) genType).getField(this.sortField).get(genType);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
*/
 