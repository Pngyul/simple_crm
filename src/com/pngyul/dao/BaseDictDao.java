package com.pngyul.dao;

import java.util.List;

import com.pngyul.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getListBytypeCode(String dict_type_code);

}
