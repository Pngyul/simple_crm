package com.pngyul.Utils;

import java.util.List;

public class PageBean {
	//当前页
	private Integer currentPage;
	//总记录数
	private Integer totalCount;
	//每页条数
	private Integer pageSize;
	//总页数
	private Integer totalPage;
	//客户列表
	private List list;
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		
		this.currentPage = currentPage;
		
		this.totalCount = totalCount;
		
		this.pageSize = pageSize;
		
		//如果当前页 为空，则默认为第一页
		if(this.currentPage == null){
			this.currentPage = 1;
		}
		//如果每页显示条数没有指定,默认每页显示3条
		if(this.pageSize == null){
			this.pageSize = 3;
		}
		
		//计算总页数
		this.totalPage = (this.totalCount+this.pageSize)/this.pageSize;
		
		//再次判断当前页
		if(this.currentPage<1){
			//如果当前也小于1，则当前页等于1
			this.currentPage = 1;
		}
		if(this.currentPage>this.totalPage){
			//如果当前也大于总页数，则等于最大页数
			this.currentPage = this.totalPage;
		}
	}
	
	//计算其实索引
	public int getStart() {
		return (this.currentPage-1)*this.pageSize ; 
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
	
	
}
