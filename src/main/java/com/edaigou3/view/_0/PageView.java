package com.edaigou3.view._0;

import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.view.BasePageView;
import com.edaigou3.view.base.IMainView.NewInstance;

@Component
public class PageView extends BasePageView {

	@Override
	public Pagination pre() {
		if (page == null) {
			return NewInstance.get(SearchView.class).query(1);
		}
		return NewInstance.get(SearchView.class).query(page.getPrePage());
	}

	@Override
	public Pagination next() {
		if (page == null) {
			return NewInstance.get(SearchView.class).query(1);
		}
		return NewInstance.get(SearchView.class).query(page.getNextPage());
	}

}
