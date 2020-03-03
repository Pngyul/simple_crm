package com.pngyul.service.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pngyul.Utils.MD5Utils;
import com.pngyul.Utils.PageBean;
import com.pngyul.dao.UserDao;
import com.pngyul.domain.User;
import com.pngyul.service.UserService;
@Service("userService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao ud;

	@Override
	public User login(User user) {
		
		// ����dao�ж��Ƿ���ڸ��û�����û�
		User exitU = ud.getUserByCode(user.getUser_code());
		
		// -->�����û�����ѯ���û��Ƿ���ڣ��������׳��쳣
		if (exitU == null) {
			throw new RuntimeException("���û�������...");
		}
		// --> ���������ж��Ƿ�һ�£���һ���׳��쳣
		if (!exitU.getUser_password().equals(MD5Utils.md5(user.getUser_password()))) {
			throw new RuntimeException("�������...");
		}
		return exitU;
	}

	

	@Override
	public void save(User user) {
		//1.�жϸ��û��Ƿ����
		User exitU = ud.getUserByCode(user.getUser_code());
		//2.������ڸ��û�
		//==>�׳��쳣
		if (exitU != null) {
			throw new RuntimeException("���û��Ѿ�����...");
		}
		//3.����dao������û�
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		ud.save(user);
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}





}
