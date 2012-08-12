package org.credo.system.service;

import java.util.Date;
import java.util.Map;

import javax.persistence.Query;

import org.credo.base.service.BaseService;
import org.credo.model.Userinfo;
import org.primefaces.model.LazyDataModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserinfoService extends BaseService<Userinfo>{
	
	public LazyDataModel<Userinfo> queryData(Map<String,Object> whereExMap) throws Exception{
		log.info("the first!");
		String account=whereExMap.get("account")==null?null:whereExMap.get("account").toString();
		String realName=whereExMap.get("realName")==null?null:whereExMap.get("realName").toString();
		String sex=whereExMap.get("sex")==null?null:whereExMap.get("sex").toString();
		String address=whereExMap.get("address")==null?null:whereExMap.get("address").toString();
		Date beginTime=whereExMap.get("beginTime")==null?null:(Date) whereExMap.get("beginTime");
		Date endTime=whereExMap.get("endTime")==null?null:(Date) whereExMap.get("endTime");
		StringBuilder jpql=new StringBuilder();
		jpql.append("select u from Userinfo u where 1=1 ");
		Query query=this.em.createQuery(jpql.toString());
		if(null!=account&&"".equals(account)){
			jpql.append(" and u.account like ?1 ");
			query.setParameter(1, account);
		}
		if(null!=realName&&"".equals(realName)){
			jpql.append(" and u.account like ?2 ");
			query.setParameter(2, realName);
		}
		if(null!=sex&&"".equals(sex)){
			jpql.append(" and u.account like ?3 ");
			query.setParameter(3, sex);
		}
		if(null!=address&&"".equals(address)){
			jpql.append(" and u.account like ?4 ");
			query.setParameter(4, address);
		}
		String rowCountJpql="SELECT COUNT(t)  FROM Userinfo t";
		int rowCount=Integer.valueOf(this.em.createQuery(rowCountJpql).getSingleResult().toString());
		log.info("query:"+query);
		log.info("rowCount:"+rowCount);
		String jjj="select u from Userinfo u";
		LazyDataModel<Userinfo> lazyUsed=this.queryRealLazyModel(jjj, rowCount);
		return lazyUsed;
	}
}
