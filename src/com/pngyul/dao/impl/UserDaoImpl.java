package com.pngyul.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pngyul.dao.UserDao;
import com.pngyul.domain.User;
@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	@Resource(name="sessionFactory")
	public void setSF(SessionFactory sf) {
		super.setSessionFactory(sf);
	}
	
	@Override
	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public User getUserByCode(String user_code) {
		// hql方式
		// return getHibernateTemplate().execute(new HibernateCallback<User>() {
		//
		// @Override
		// public User doInHibernate(Session session) throws HibernateException
		// {
		// String hql = "from User where user_code = ?";
		// Query query = session.createQuery(hql);
		// query.setParameter(0, user_code);
		// User user = (User) query.uniqueResult();
		// return user;
		// }
		// });
		// crireria 方式
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("user_code", user_code));
		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);

		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
