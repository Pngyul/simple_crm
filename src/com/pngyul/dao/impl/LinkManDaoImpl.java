package com.pngyul.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.pngyul.dao.LinkManDao;
import com.pngyul.domain.LinkMan;
@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}

}
