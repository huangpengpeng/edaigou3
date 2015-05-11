package com.edaigou3.view.ext;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._1.SearchView_1;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;

public class _同步最低售价Provider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public _同步最低售价Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(final IBrowserView browserView) {

		BigDecimal lowPrice=PriceUtils.getMinPrice(browserView.getResponseText(), item.getTitle(),
				new ArrayList<ItemFilters>());
		
		NewInstance.get(ItemView.class).setLowPrice(lowPrice
				);

		NewInstance.get(ItemView.class).updateSubmit();

		Display.getDefault().timerExec(1000, new Runnable() {
			public void run() {
				listener.handleEvent(null);
			}
		});
	}

	public <T> T getObject() {
		return null;
	}

	public static class RequestProvider implements IRequestProvider {

		private Integer pageNo;

		public RequestProvider(Integer pageNo) {
			this.pageNo = pageNo;
		}

		public String getRequestUrl(IBrowserView browserView) {
			Pagination page = NewInstance.get(SearchView_1.class).query(
					pageNo++);
			StringBuffer buffer = new StringBuffer(
					"http://s.taobao.com/search?q=");

			item = (Item) page.getList().get(0);

			buffer.append(item.getTitle());

			NewInstance.get(ItemView.class).fullContents(item, true);

			return buffer.toString();
		}
	}
}
