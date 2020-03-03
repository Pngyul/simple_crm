package com.pngyul.dao.impl;


import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.pngyul.dao.SaleVisitDao;
import com.pngyul.domain.SaleVisit;
@Repository("saleVisitDao")
public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}

}
