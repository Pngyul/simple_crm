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
import com.pngyul.domain.LinkMan;
import com.pngyul.service.CustomerService;
import com.pngyul.service.LinkManService;
@Controller("linkManAction")
@Scope("prototype")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	private LinkMan linkMan = new LinkMan();
	@Resource(name="linkManService")
	private LinkManService lms;

	
	private Integer currentPage;
	private Integer pageSize;

	public String list() throws Exception {
		//��װ���߲�ѯ����
		DetachedCriteria  dc = DetachedCriteria.forClass(LinkMan.class);
		//�жϲ���װ����
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getCustomer()!=null&&linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		
		//����service��÷�ҳ���ݣ�PageBean��
		PageBean pb = lms.getPageBean(dc,currentPage,pageSize);
		//��PageBean����request��
		ActionContext.getContext().put("pageBean", pb);
		return "list";
		
	}
	//��ӿͻ�
	public String add() throws Exception {
		//����service
		lms.save(linkMan);

     	return "toList";
	}

	public String toEdit() throws Exception {
		//1����Service����id��ÿͻ�����
		LinkMan lm = lms.getById(linkMan.getLkm_id());
		//2 ���ͻ�������õ�request��,��ת�����༭ҳ��
		ActionContext.getContext().put("linkMan", lm);
		return "edit";
	}
	
	//ɾ���ͻ�
	public String delete() throws Exception {
		//����service
		lms.delete(linkMan.getLkm_id());
		//�ض��򵽹˿��б�
		
		return "toList";
	}
	

	
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	
	
	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public void setLms(LinkManService lms) {
		this.lms = lms;
	}
	

}
