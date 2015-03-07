package com.edaigou3.view;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.view.base.IPageView;
import com.edaigou3.view.base.ISearchView;
import com.edaigou3.view.base.ITableView;

@Component
public class ItemSearchView implements ISearchView {
	

	private IPageView pageView;
	private ITableView tableView;
	private Text _商品标题;
	private Combo _淘宝店铺;
	private Combo _错误类型;

	public void createContents(Shell shell) {

	}

	public void createContents(Composite composite) {
		Label lblNewLabel_15 = new Label(composite, SWT.NONE);
		lblNewLabel_15.setBounds(359, 12, 54, 22);
		lblNewLabel_15.setText("淘宝店铺");

		_淘宝店铺 = new Combo(composite, SWT.NONE);
		_淘宝店铺.setBounds(417, 8, 86, 20);

		Label lblNewLabel_16 = new Label(composite, SWT.NONE);
		lblNewLabel_16.setBounds(526, 13, 54, 17);
		lblNewLabel_16.setText("错误类型");

		_错误类型 = new Combo(composite, SWT.NONE);
		_错误类型.setBounds(597, 8, 86, 20);

		Label lblNewLabel_17 = new Label(composite, SWT.NONE);
		lblNewLabel_17.setBounds(701, 13, 56, 17);
		lblNewLabel_17.setText("商品标题");

		_商品标题 = new Text(composite, SWT.BORDER);
		_商品标题.setBounds(763, 8, 222, 18);

		Button btnNewButton_6 = new Button(composite, SWT.NONE);
		btnNewButton_6.setBounds(1006, 6, 72, 22);
		btnNewButton_6.setText("查询");
	}

	public void createListenter() {

	}

	public void preHandle() {

	}

	public void fullContents(Object... values) throws IOException {
	}

	public void addTableView(ITableView tableView) {
		this.tableView=tableView;
	}


	public void setValue(String name, String value) {
		if ("_商品标题".equals(name)) {
			_商品标题.setText(value);
		}
		if ("_淘宝店铺".equals(name)) {
			_淘宝店铺.setText(value);
		}
		if ("_错误类型".equals(name)) {
			_错误类型.setText(value);
		}
	}

	public void clearText() {
		_商品标题.setText("");
		_淘宝店铺.setText("");
		_错误类型.setText("");
	}

	public void query() throws IOException {
		this.pageView.setPage(new Pagination(0, 0, 0));
		tableView.fullContents(this.pageView.getPage());
	}

	public void addPageView(IPageView pageView) {
		this.pageView = pageView;
	}
}
