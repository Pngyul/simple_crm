package com.pngyul.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.pngyul.dao.BaseDictDao;
import com.pngyul.domain.BaseDict;

@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict>implements BaseDictDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	@Override
	public List<BaseDict> getListBytypeCode(String dict_type_code) {
		//Criteria
		//��װ���߲�ѯ����
		DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		dc.add(Restrictions.eq("dict_type_code", dict_type_code));
		//ִ�в�ѯ
		return (List<BaseDict>) getHibernateTemplate().findByCriteria(dc);
		
	}

}
