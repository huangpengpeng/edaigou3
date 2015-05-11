package com.edaigou3.view.ext;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._1.SearchView_1;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;

public class _同步最低售价Provider implements IOperatorProvider {

	private Listener listener;

	public _同步最低售价Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(IBrowserView browserView) {
		
		
		
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
			buffer.append(((Item) page.getList().get(0)).getTitle());
			return buffer.toString();
		}
	}
}
