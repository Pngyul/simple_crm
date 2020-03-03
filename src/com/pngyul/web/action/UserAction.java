package com.pngyul.web.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pngyul.Utils.PageBean;
import com.pngyul.domain.Customer;
import com.pngyul.domain.User;
import com.pngyul.service.UserService;
import com.pngyul.service.impl.UserServiceImpl;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();
	@Resource(name="userService")
	private UserService us;
	
	

	public String login() throws Exception {
		//1.����serviceִ�е�¼����
		
		User u = us.login(user);
		
		//2.����ѯ��u����Session ��
		ActionContext.getContext().getSession().put("user", u);
		
		//3.�ض��� toHome
		return "toHome";
	}
	public String regist() throws Exception {
		//����service�����û�
		try {
			us.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().put("error", e.getMessage());
			return "regist";
		}
		//�ض���loginҳ��
		return "toLogin";
	}
	


	public User getModel() {
		return user;
	}

	public void setUs(UserService us) {
		this.us = us;
	}

	
}
