package com.pngyul.service;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.Utils.PageBean;
import com.pngyul.domain.SaleVisit;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	SaleVisit getById(String visit_id);

	void delete(String visit_id);


}
