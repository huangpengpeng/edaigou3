package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.common.jdbc.page.Pagination;
import com.edaigou3.view.base.IPageView;
import com.edaigou3.view.base.ISearchView;

public abstract class BasePageView implements IPageView {

	protected Pagination page;

	private Button _上一页;
	private CLabel _分页数据;
	private Button _下一页;

	public void createContents(Shell shell) {
	}

	public abstract ISearchView getSearchView();

	public void createContents(Composite composite) {
		_上一页 = new Button(composite, SWT.NONE);
		_上一页.setBounds(10, 8, 54, 22);
		_上一页.setText("上一页");
		_上一页.setEnabled(false);

		_分页数据 = new CLabel(composite, SWT.NONE);
		_分页数据.setAlignment(SWT.CENTER);
		_分页数据.setBounds(78, 10, 60, 18);
		_分页数据.setText("总数/总页/当前页");

		_下一页 = new Button(composite, SWT.NONE);
		_下一页.setBounds(152, 8, 72, 22);
		_下一页.setText("下一页");
	}

	public void preHandle() {

	}

	public void fullContents(Object... values) {
		if (values == null || values.length == 0) {
			return;
		}
		Pagination page = (Pagination) values[0];
		_分页数据.setText(page.getTotalCount() + "/" + page.getTotalPage() + "/"
				+ page.getPageNo());
		if (page.isFirstPage()) {
			_上一页.setEnabled(false);
		} else {
			_上一页.setEnabled(true);
		}
		if (page.isLastPage()) {
			_下一页.setEnabled(false);
		} else {
			_下一页.setEnabled(true);
		}
	}

	public Integer getPageNo() {
		return page.getPageNo();
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public void createListenter() {
		_上一页.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (page == null) {
					page = getSearchView().query(1);
				} else {
					getSearchView().query(page.getPrePage());
				}
				fullContents(page);
			}
		});
		_下一页.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (page == null) {
					page = getSearchView().query(1);
				} else {
					getSearchView().query(page.getNextPage());
				}
				fullContents(page);
			}
		});
	}
}
