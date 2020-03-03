package com.pngyul.service;

import java.util.List;

import com.pngyul.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListBytypeCode(String dict_type_code);

}
