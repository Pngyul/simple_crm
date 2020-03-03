package com.pngyul.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	//��
	void save(T t);
	//ɾ
	void delete(T t);
	//ɾ
	void delete(Serializable id);
	//��
	void update(T t);
	//�� ����id��ѯ
	T getById(Serializable id);
	//�� ����������ѯ����¼��
	Integer getTotalCount(DetachedCriteria dc);
	//�� ��ѯ��ҳ�б�����
	List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
	//��������
	void saveOrUpdate(T t);
	
}