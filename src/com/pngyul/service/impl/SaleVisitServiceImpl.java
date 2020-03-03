package com.pngyul.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pngyul.Utils.PageBean;
import com.pngyul.dao.SaleVisitDao;
import com.pngyul.domain.SaleVisit;
import com.pngyul.service.SaleVisitService;
@Service("saleVisitService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name="saleVisitDao")
	private SaleVisitDao svd;

	@Override
	public void save(SaleVisit saleVisit) {
		
		svd.saveOrUpdate(saleVisit);
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		// 封装一个Page Bean
		// 调用dao获得总记录数
		Integer totalCount = svd.getTotalCount(dc);

		// 创建pageBean
		PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);

		// 调用dao获得客户列表
		List<SaleVisit> list = svd.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
		// 封装完毕，返回pageBean
		pageBean.setList(list);
		return pageBean;
	}
	@Override
	public SaleVisit getById(String visit_id) {
		
		return svd.getById(visit_id);
	}
	
	@Override
	public void delete(String visit_id) {
		svd.delete(visit_id);
	}

	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}


}
