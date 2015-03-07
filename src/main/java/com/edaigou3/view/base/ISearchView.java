package com.edaigou3.view.base;

public interface ISearchView {
	
	public void addBaseView(IBaseView baseView);

	/**
	 * 根据名称获取值
	 * @param name
	 * @return
	 */
	public String getValue(String name);
	
	/**
	 * 根据名称设置值
	 * @param name
	 * @return
	 */
	public String setValue(String name);
}
