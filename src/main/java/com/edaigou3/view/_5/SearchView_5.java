package com.edaigou3.view._5;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item.ItemStatus;
import com.edaigou3.entity.ItemErrors;
import com.edaigou3.entity.ItemErrors.ItemErrorsType;
import com.edaigou3.manager.ItemErrorsMng;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.ShopView;
import com.edaigou3.view.ShopView.Listener;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.base.ISearchView;

@Component
public class SearchView_5 implements ISearchView {

	private ShopView shopView;
	private Pagination page;

	public void createContents(Shell shell) {

	}

	public void createContents(Composite composite) {
		Label lblNewLabel_18 = new Label(composite, SWT.NONE);
		lblNewLabel_18.setBounds(9, 10, 54, 22);
		lblNewLabel_18.setText("淘宝店铺");

		if (shopView == null) {
			shopView = new ShopView();
			View.addView(composite, shopView);
			shopView.setBounds(69, 7, 86, 20);
		}

	}

	public void createListenter() {
		shopView.addListener(new Listener() {
			public void handleEvent() {
				query(1);
				NewInstance.get(BrowserView_5.class).fullContents(
						page == null ? 0 : page.getPageNo(),
						page == null ? 0 : page.getTotalCount());
			}
		});
	}

	public void preHandle() {

	}

	public void fullContents(Object... values) {

	}

	public Pagination query(Integer pageNo) {
		if (shopView.getNumber() != null) {
			Long[] ids = ArrayUtils.EMPTY_LONG_OBJECT_ARRAY;
			List<ItemErrors> itemErrors = NewInstance.get(ItemErrorsMng.class)
					.getByErrorType(
							new String[] { ItemErrorsType.店售错误.toString(),
									ItemErrorsType.非低价格.toString() });
			for (ItemErrors error : itemErrors) {
				ids = (Long[]) ArrayUtils.add(ids, error.getItemId());
			}
			if (ArrayUtils.isEmpty(ids)) {
				ids = (Long[]) ArrayUtils.add(ids, 0L);
			}

			page = NewInstance.get(ItemMng.class).getPage(shopView.getNumber(),
					ids, null, ItemStatus.上架.toString(), pageNo, 1);
		} else {
			page = null;
		}
		NewInstance.get(ProgressView_5.class).fullContents(page);
		NewInstance.get(BrowserView_5.class).fullContents(
				page == null ? 0 : page.getPageNo(),
				page == null ? 0 : page.getTotalCount());
		return page;
	}

	public void setValue(String name, String view) {

	}

	public void clearText() {

	}
}
