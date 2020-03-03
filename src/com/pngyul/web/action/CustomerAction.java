package com.pngyul.web.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pngyul.Utils.PageBean;
import com.pngyul.domain.Customer;
import com.pngyul.service.CustomerService;

@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	// �ϴ����ļ����Զ���װ��File����
	// �ں�̨�ṩһ����ǰ̨input type=file��� name��ͬ������
	private File file;
	// ��name����������Ϲ̶���׺FileName,�ļ����ƻ��Զ���װ��������
	private String fileFileName;
	// ��name����������Ϲ̶���׺ContentType,�ļ�MIME���ͻ��Զ���װ��������
	private String fileContentType;
	@Resource(name = "customerService")
	private CustomerService cs;

	private Integer currentPage;
	private Integer pageSize;

	private String state;

	public String list() throws Exception {
		// ��װ���߲�ѯ����
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		// �жϲ���װ����
		if (StringUtils.isNotBlank(customer.getCust_name())) {
			dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		// ����service��÷�ҳ���ݣ�PageBean��
		PageBean pb = cs.getPageBean(dc, currentPage, pageSize);
		// ��PageBean����request��
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}

	// ��ӿͻ�
	public String add() throws Exception {
		if(file != null){
			//����ļ���չ��
			String extend = fileFileName.substring(fileFileName.lastIndexOf(".") + 1);
			
			// �õ��ϴ��ļ��ڷ�������·�����ļ���
			String newFileName = UUID.randomUUID()+"."+extend;
			
			
			String target = "D:/apache-tomcat-8.0.50/webapps/upload/"+newFileName;
			// ���ϴ��ļ����浽ָ��λ��
			file.renameTo(new File(target));
			customer.setCust_photo(newFileName);
		}
		
		// ����service
		cs.save(customer);
		// �Ӷ��򵽹˿��б�
		return "toList";
	}

	public String toEdit() throws Exception {
		// 1����Service����id��ÿͻ�����
		Customer c = cs.getById(customer.getCust_id());
		// 2 ���ͻ�������õ�request��,��ת�����༭ҳ��
		ActionContext.getContext().put("customer", c);
		return "edit";
	}

	// ɾ���ͻ�
	public String delete() throws Exception {
		// ����service
		cs.delete(customer.getCust_id());
		// �ض��򵽹˿��б�

		return "toList";
	}

	// ��ҵ�ͻ�ͳ��
	public String industryOrSourceCount() throws Exception {
		List list = cs.getIndustryCount(state);

		ActionContext.getContext().put("list", list);

		return "industryOrSourceCount";
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
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
	public Customer getModel() {
		return customer;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


}
