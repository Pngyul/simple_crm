package com.pngyul.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {
	//ָ�������ص�½����. ��������������
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		//1.���session
		Map<String, Object> session = ActionContext.getContext().getSession();
		//2.��õ�½��ʶ
		Object object = session.get("user");
		//3.�жϵ�½��ʶ�Ƿ����
		if(object == null){
			//������=>û��¼=>�ض��򵽵�¼ҳ��
			return "toLogin";
		}else{
			//����=>�Ѿ���½=>����
			return invocation.invoke();
		}
		
	}

}
