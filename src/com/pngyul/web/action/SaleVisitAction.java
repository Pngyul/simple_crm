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
		//封装离线查询对象
		DetachedCriteria  dc = DetachedCriteria.forClass(SaleVisit.class);
		//判断并封装参数
		/*if(StringUtils.isNotBlank(SaleVisit.getCust_name())){
			dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}*/
		//调用service获得分页数据（PageBean）
		PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
		//将PageBean存入request域
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}
	

	public String add() throws Exception {
		//0 取出登陆用户,放入SaleVisit实体.表达关系
		System.out.println(saleVisit);
		saleVisit.setVisit_id(null);
		System.out.println(saleVisit);
		User u = (User) ActionContext.getContext().getSession().get("user");
		saleVisit.setUser(u);
		//1调用service保存
		svs.save(saleVisit);
		//2重定向到访客列表
		return "toList";
	}

	public String toEdit() throws Exception {
		//1调用Service根据id获得访问记录对象
		SaleVisit sv = svs.getById(saleVisit.getVisit_id());
		//2 将客户对象放置到request域,并转发到编辑页面
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
