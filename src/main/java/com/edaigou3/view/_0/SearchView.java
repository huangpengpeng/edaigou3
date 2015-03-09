package com.edaigou3.view._0;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.ShopView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.base.ISearchView;

@Component
public class SearchView implements ISearchView {

	private Text _商品标题;
	private Combo _错误类型;
	private Button _查询;
	private ShopView _淘宝店铺;

	public void createContents(Shell shell) {
	}

	public void createContents(Composite composite) {
		Label lblNewLabel_15 = new Label(composite, SWT.NONE);
		lblNewLabel_15.setBounds(359, 12, 54, 22);
		lblNewLabel_15.setText("淘宝店铺");

		_淘宝店铺=NewInstance.get(ShopView.class);
		View.addView(composite, _淘宝店铺);
		_淘宝店铺.setBounds(417, 8, 86, 20);

		Label lblNewLabel_16 = new Label(composite, SWT.NONE);
		lblNewLabel_16.setBounds(526, 13, 54, 17);
		lblNewLabel_16.setText("错误类型");

		_错误类型 = new Combo(composite, SWT.NONE);
		_错误类型.setBounds(597, 8, 86, 20);
		_错误类型.add("");
		_错误类型.add("抓低错误");
		_错误类型.add("非低价格");
		_错误类型.add("售利过低");
		_错误类型.add("编号错误");
		_错误类型.add("店售错误");
		

		Label lblNewLabel_17 = new Label(composite, SWT.NONE);
		lblNewLabel_17.setBounds(701, 13, 56, 17);
		lblNewLabel_17.setText("商品标题");

		_商品标题 = new Text(composite, SWT.BORDER);
		_商品标题.setBounds(763, 8, 222, 18);

		_查询 = new Button(composite, SWT.NONE);
		_查询.setBounds(1006, 6, 72, 22);
		_查询.setText("查询");
	}

	public void createListenter() {
		_查询.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				query(1);
			}
		});
	}

	public void preHandle() {

	}

	public void fullContents(Object... values) {
	}

	public void setValue(String name, String value) {
		if ("_商品标题".equals(name)) {
			_商品标题.setText(value);
		}
	}

	public void clearText() {
	}

	public Pagination query(Integer pageNo) {
		Long shopId = _淘宝店铺.getNumber();

		String errorType = _错误类型.getText();

		String title = _商品标题.getText();

		Pagination page = NewInstance.get(ItemMng.class).getPage(shopId, null,
				title, pageNo);

		NewInstance.get(TableView.class).fullContents(page.getList());

		NewInstance.get(PageView.class).fullContents(page);
		return page;
	}
}
