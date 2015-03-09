package com.edaigou3.view.base;

import com.common.jdbc.page.Pagination;

public interface IPageView extends IBaseView {

	public Pagination getPage();
	
	Integer getPageNo();
}
