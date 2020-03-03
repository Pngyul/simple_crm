package com.pngyul.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pngyul.dao.CustomerDao;
import com.pngyul.domain.Customer;
@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	@Override
	public List getIndustryCount() {
		String sql = "	SELECT bd.`dict_item_name`, COUNT(*) total "+
					"	from base_dict bd,cst_customer c "+
					"	where bd.dict_id = c.cust_industry"+
					"	GROUP by c.cust_industry";
		List list = getHibernateTemplate().execute(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session session) throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		
		return list;
	}

	@Override
	public List getSourceCount() {
		String sql = "	SELECT bd.`dict_item_name`, COUNT(*) total "+
				"	from base_dict bd,cst_customer c "+
				"	where bd.dict_id = c.cust_source"+
				"	GROUP by c.cust_source";
	List list = getHibernateTemplate().execute(new HibernateCallback<List>() {

		@Override
		public List doInHibernate(Session session) throws HibernateException {
			SQLQuery query = session.createSQLQuery(sql);
			return query.list();
		}
	});
	
	return list;
	}

}
