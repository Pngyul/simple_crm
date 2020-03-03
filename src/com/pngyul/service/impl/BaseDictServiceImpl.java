package com.pngyul.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pngyul.dao.BaseDictDao;
import com.pngyul.domain.BaseDict;
import com.pngyul.service.BaseDictService;
@Service("baseDictService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class BaseDictServiceImpl implements BaseDictService {
	
	@Resource(name="baseDictDao")
	private BaseDictDao bdd;

	@Override
	public List<BaseDict> getListBytypeCode(String dict_type_code) {
		return bdd.getListBytypeCode(dict_type_code);
	}

	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}


	
	

}
