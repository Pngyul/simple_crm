package com.pngyul.service;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.Utils.PageBean;
import com.pngyul.domain.LinkMan;

public interface LinkManService {
	//添加联系人
	void save(LinkMan linkMan);
	//联系人列表
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//到修改联系人页面
	LinkMan getById(Long lkm_id);
	void delete(Long lkm_id);
	

}
