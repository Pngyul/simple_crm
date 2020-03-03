package com.pngyul.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pngyul.Utils.PageBean;
import com.pngyul.dao.CustomerDao;
import com.pngyul.domain.Customer;
import com.pngyul.service.CustomerService;
@Service("customerService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class CustomerServiceImpl implements CustomerService {
	@Resource(name="customerDao")
	private CustomerDao cd;

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		// 封装一个Page Bean
		// 调用dao获得总记录数
		Integer totalCount = cd.getTotalCount(dc);

		// 创建pageBean
		PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);

		// 调用dao获得客户列表
		List<Customer> list = cd.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
		// 封装完毕，返回pageBean
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public void delete(Long cust_id) {
		cd.delete(cust_id);
	}

	@Override
	public Customer getById(Long cust_id) {
		return cd.getById(cust_id);
	}

	@Override
	public void save(Customer customer) {
		cd.saveOrUpdate(customer);
	}

	@Override
	public List getIndustryCount(String state) {
		List list = null;
		if(state.equals("industry")){
			list =  cd.getIndustryCount();
		}
		else if (state.equals("source")){
			list =  cd.getSourceCount();
		}
		return list;
		
	}
	
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}


}