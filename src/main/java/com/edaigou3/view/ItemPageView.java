package com.edaigou3.view;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IPageView;
import com.edaigou3.view.base.ISearchView;

@Component
public class ItemPageView implements IPageView {
	
	private Pagination page;

	private ISearchView searchView;

	private Button _上一页;
	private CLabel _分页数据;
	private Button _下一页;

	public void createContents(Shell shell) {
	}

	public void createContents(Composite composite) {
		_上一页 = new Button(composite, SWT.NONE);
		_上一页.setBounds(10, 8, 54, 22);
		_上一页.setText("上一页");

		_分页数据 = new CLabel(composite, SWT.NONE);
		_分页数据.setAlignment(SWT.CENTER);
		_分页数据.setBounds(78, 10, 60, 18);
		_分页数据.setText("总数/总页/当前页");

		_下一页 = new Button(composite, SWT.NONE);
		_下一页.setBounds(152, 8, 72, 22);
		_下一页.setText("下一页");
	}

	public void createListenter() {

	}

	public void preHandle() {

	}

	public void fullContents(Object... values) throws IOException {
		if (values == null || values.length == 0) {
			return;
		}
		Pagination page = (Pagination) values[0];
		_分页数据.setText(page.getTotalCount() + "/" + page.getTotalPage() + "/"
				+ page.getPageNo());
	}

	public void addSearchView(ISearchView searchView) {
		this.searchView = searchView;
	}

	public Integer getPageNo() {
		return page.getPageNo();
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page=page;
	}
}
