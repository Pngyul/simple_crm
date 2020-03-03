package com.pngyul.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pngyul.Utils.PageBean;
import com.pngyul.dao.LinkManDao;
import com.pngyul.domain.LinkMan;
import com.pngyul.service.LinkManService;
@Service("linkManService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class LinkManServiceImpl implements LinkManService {
	@Resource(name="linkManDao")
	private LinkManDao lmd;

	@Override
	public void save(LinkMan linkMan) {
		lmd.saveOrUpdate(linkMan);
	}

	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		// ��װһ��Page Bean
		// ����dao����ܼ�¼��
		Integer totalCount = lmd.getTotalCount(dc);

		// ����pageBean
		PageBean pageBean = new PageBean(currentPage, totalCount, pageSize);

		// ����dao��ÿͻ��б�
		List<LinkMan> list = lmd.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
		// ��װ��ϣ�����pageBean
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public LinkMan getById(Long lkm_id) {
		return lmd.getById(lkm_id);
	}

	@Override
	public void delete(Long lkm_id) {
		lmd.delete(lkm_id);
	}
}
