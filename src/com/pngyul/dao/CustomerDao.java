package com.pngyul.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	List getIndustryCount();

	List getSourceCount();

}
