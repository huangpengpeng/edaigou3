package com.edaigou3.view.base;

public interface IPageView {

	/**
	 * 设置显示试图
	 * @param tableView
	 */
	void addTableView(ITableView tableView);
	
	/**
	 * 设置查询试图
	 * @param searchView
	 */
	void addSearchView(ISearchView searchView);
}
