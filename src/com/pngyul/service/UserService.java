package com.pngyul.service;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.Utils.PageBean;
import com.pngyul.domain.User;

public interface UserService {

	// �жϵ�¼
	User login(User user);
	//�����û�
	void save(User user);

}
