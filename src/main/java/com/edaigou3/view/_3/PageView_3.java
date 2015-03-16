package com.edaigou3.view._3;

import org.springframework.stereotype.Component;

import com.edaigou3.view.BasePageView;
import com.edaigou3.view._0.SearchView_0;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

@Component
public class PageView_3 extends BasePageView {

	@Override
	public ISearchView getSearchView() {
		return NewInstance.get(SearchView_0.class);
	}
}
