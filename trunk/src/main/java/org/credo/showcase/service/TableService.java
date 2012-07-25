package org.credo.showcase.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.credo.common.entity.Userinfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class TableService {
	
	@PersistenceContext EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Userinfo> queryAllUserInfo(){
		String jpql="SELECT u from Userinfo u where 1=1";
		List<Userinfo> list=this.em.createQuery(jpql).getResultList();
		System.out.println("list.size:"+list.size());
		return list;
	}
	
}
