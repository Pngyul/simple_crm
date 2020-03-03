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
		
		// 调用dao判断是否存在该用户民的用户
		User exitU = ud.getUserByCode(user.getUser_code());
		
		// -->根据用户名查询该用户是否存在，不存在抛出异常
		if (exitU == null) {
			throw new RuntimeException("该用户不存在...");
		}
		// --> 根据密码判断是否一致，不一致抛出异常
		if (!exitU.getUser_password().equals(MD5Utils.md5(user.getUser_password()))) {
			throw new RuntimeException("密码错误...");
		}
		return exitU;
	}

	

	@Override
	public void save(User user) {
		//1.判断该用户是否存在
		User exitU = ud.getUserByCode(user.getUser_code());
		//2.如果存在该用户
		//==>抛出异常
		if (exitU != null) {
			throw new RuntimeException("该用户已经存在...");
		}
		//3.调用dao保存该用户
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		ud.save(user);
	}

	public void setUd(UserDao ud) {
		this.ud = ud;
	}





}
