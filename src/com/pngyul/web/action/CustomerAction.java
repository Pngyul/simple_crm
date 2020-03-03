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
	// 上传的文件会自动封装到File对象
	// 在后台提供一个与前台input type=file组件 name相同的属性
	private File file;
	// 在name属性名后加上固定后缀FileName,文件名称会自动封装到属性中
	private String fileFileName;
	// 在name属性名后加上固定后缀ContentType,文件MIME类型会自动封装到属性中
	private String fileContentType;
	@Resource(name = "customerService")
	private CustomerService cs;

	private Integer currentPage;
	private Integer pageSize;

	private String state;

	public String list() throws Exception {
		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		// 判断并封装参数
		if (StringUtils.isNotBlank(customer.getCust_name())) {
			dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		// 调用service获得分页数据（PageBean）
		PageBean pb = cs.getPageBean(dc, currentPage, pageSize);
		// 将PageBean存入request域
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}

	// 添加客户
	public String add() throws Exception {
		if(file != null){
			//获得文件扩展命
			String extend = fileFileName.substring(fileFileName.lastIndexOf(".") + 1);
			
			// 得到上传文件在服务器的路径加文件名
			String newFileName = UUID.randomUUID()+"."+extend;
			
			
			String target = "D:/apache-tomcat-8.0.50/webapps/upload/"+newFileName;
			// 将上传文件保存到指定位置
			file.renameTo(new File(target));
			customer.setCust_photo(newFileName);
		}
		
		// 调用service
		cs.save(customer);
		// 从定向到顾客列表
		return "toList";
	}

	public String toEdit() throws Exception {
		// 1调用Service根据id获得客户对象
		Customer c = cs.getById(customer.getCust_id());
		// 2 将客户对象放置到request域,并转发到编辑页面
		ActionContext.getContext().put("customer", c);
		return "edit";
	}

	// 删除客户
	public String delete() throws Exception {
		// 调用service
		cs.delete(customer.getCust_id());
		// 重定向到顾客列表

		return "toList";
	}

	// 行业客户统计
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
