package com.edaigou3.view.base;

import com.common.jdbc.page.Pagination;

public interface ISearchView extends IBaseView{
	
	public Pagination query(Integer pageNo) ;
	
	/**
	 * 根据名称设置值
	 * @param name
	 * @return
	 */
	public void setValue(String name,String view);
	
	/**
	 * 清楚内容
	 */
	public void clearText();
}
