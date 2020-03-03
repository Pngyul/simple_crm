package com.pngyul.web.action;


import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pngyul.Utils.PageBean;
import com.pngyul.domain.SaleVisit;
import com.pngyul.domain.User;
import com.pngyul.service.SaleVisitService;
@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{

	private SaleVisit saleVisit = new SaleVisit();
	@Resource(name="saleVisitService")
	private SaleVisitService  svs;
	
	private Integer currentPage;
	private Integer pageSize;
	
	public String list() throws Exception {
		//��װ���߲�ѯ����
		DetachedCriteria  dc = DetachedCriteria.forClass(SaleVisit.class);
		//�жϲ���װ����
		/*if(StringUtils.isNotBlank(SaleVisit.getCust_name())){
			dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}*/
		//����service��÷�ҳ���ݣ�PageBean��
		PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
		//��PageBean����request��
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	

	public String add() throws Exception {
		//0 ȡ����½�û�,����SaleVisitʵ��.����ϵ
		System.out.println(saleVisit);
		saleVisit.setVisit_id(null);
		System.out.println(saleVisit);
		User u = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(u);
		//1����service����
		svs.save(saleVisit);
		//2�ض��򵽷ÿ��б�
		return "toList";
	}

	public String toEdit() throws Exception {
		//1����Service����id��÷��ʼ�¼����
		SaleVisit sv = svs.getById(saleVisit.getVisit_id());
		//2 ���ͻ�������õ�request��,��ת�����༭ҳ��
		ActionContext.getContext().put("saleVisit", sv);
		return "edit";
	}
	
	public String delete() throws Exception {
		svs.delete(saleVisit.getVisit_id());
		return "toList";
	}

	public void setSvs(SaleVisitService svs) {
		this.svs = svs;
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
	public SaleVisit getModel() {
		return saleVisit ;
	}

}
