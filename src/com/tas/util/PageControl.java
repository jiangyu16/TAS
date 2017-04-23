package com.tas.util;
import java.util.List;
public class PageControl <T>{
	private int curPage = 1;
	private int pageSize;
	private int totalRows;
	private int totalPages;	
	private List<T> list;//用于存放分页数据
	
	public PageControl(int curPageStr, int totalRows,int pageSize) {
		
		 
			this.curPage =  curPageStr ;    //初始化当前页数
		 	
		this.totalRows = totalRows;							//初始化总行数		
		this.pageSize=pageSize;//设置每页显示的记录数
		
		//计算总页数
		this.totalPages = (this.totalRows / this.pageSize) + ((this.totalRows % this.pageSize) > 0 ? 1 : 0);
	}
	

	public List<T> getList() {
		return list;
	}

	public int getCurPage() {
		return curPage;
	}


	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
