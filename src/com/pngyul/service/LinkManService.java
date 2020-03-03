package com.pngyul.service;

import org.hibernate.criterion.DetachedCriteria;

import com.pngyul.Utils.PageBean;
import com.pngyul.domain.LinkMan;

public interface LinkManService {
	//�����ϵ��
	void save(LinkMan linkMan);
	//��ϵ���б�
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//���޸���ϵ��ҳ��
	LinkMan getById(Long lkm_id);
	void delete(Long lkm_id);
	

}
