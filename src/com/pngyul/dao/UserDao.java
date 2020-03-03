package com.pngyul.dao;

import com.pngyul.domain.User;

public interface UserDao {
	
	User getUserByCode(String user_code);
	
	void save(User user);
}
