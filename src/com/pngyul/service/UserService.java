package com.pngyul.service;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.Utils.PageBean;
import com.pngyul.domain.User;

public interface UserService {

	// ÅÐ¶ÏµÇÂ¼
	User login(User user);
	//±£´æÓÃ»§
	void save(User user);

}
