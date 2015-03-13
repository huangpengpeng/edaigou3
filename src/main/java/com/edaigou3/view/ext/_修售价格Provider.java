package com.edaigou3.view.ext;

import org.eclipse.swt.widgets.Listener;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._1.SearchView_1;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;

public class _修售价格Provider implements IOperatorProvider{

	private static Item item;

	private Listener listener;
	
	public _修售价格Provider(Listener listener) {
		this.listener = listener;
	}
	
	public void completed(IBrowserView browserView) {
		
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
					"http://sell.taobao.com/auction/merchandise/auction_list.htm?type=11");
			item = (Item) page.getList().get(0);
			buffer.append("&&searchKeyword=").append(item.getTitle());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}

	}
}
