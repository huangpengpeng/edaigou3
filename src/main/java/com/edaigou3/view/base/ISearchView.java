package com.edaigou3.view.base;

import java.io.IOException;

public interface ISearchView extends IBaseView{
	
	public void addTableView(ITableView tableView);
	
	public void addPageView(IPageView pageView);

	public void query() throws IOException;
	
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
