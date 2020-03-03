package com.pngyul.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.Utils.PageBean;
import com.pngyul.domain.Customer;

public interface CustomerService {
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	void save(Customer customer);

	Customer getById(Long cust_id);

	void delete(Long cust_id);

	List getIndustryCount(String state);

}
