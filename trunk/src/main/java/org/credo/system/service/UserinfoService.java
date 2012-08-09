package org.credo.system.service;


import java.util.List;

import org.credo.base.service.BaseService;
import org.credo.model.Userinfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserinfoService extends BaseService<Userinfo>{
	//LazyModel数据库分页
	@SuppressWarnings("unchecked")
	public List<Userinfo> queryUserinfoLazy(int firstData,int maxResults){
		String jpql="SELECT u from Userinfo u";
		List<Userinfo> list=this.em.createQuery(jpql).setFirstResult(firstData).setMaxResults(maxResults).getResultList();
		log.info(jpql);
		log.info("size:"+list.size());
		return list;
	}
}
