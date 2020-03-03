package com.pngyul.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pngyul.domain.BaseDict;
import com.pngyul.service.BaseDictService;

import net.sf.json.JSONArray;
@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport{
	private String dict_type_code;
	@Resource(name="baseDictService")
	private BaseDictService bds;
	
	@Override
	public String execute() throws Exception {
		//1 ����Service����typecode��������ֵ����list
		List<BaseDict>  list = bds.getListBytypeCode(dict_type_code);
		//2.��Listת��Ϊjson��ʽ
		String json = JSONArray.fromObject(list).toString();
		//��json���͸������
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;//����struts2����Ҫ���н������
	}
	
	public void setBds(BaseDictService bds) {
		this.bds = bds;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
}
