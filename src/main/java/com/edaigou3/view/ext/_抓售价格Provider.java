package com.edaigou3.view.ext;

import java.math.BigDecimal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._1.SearchView_1;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;

public class _抓售价格Provider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public _抓售价格Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(IBrowserView browserView) {
		Document document = Jsoup.parse(browserView.getResponseText());
		Element element = document.getElementById("J_Price");
		if (element == null) {
			element = document.getElementById("J_StrPrice");
		}
		String text = element.text().replace("¥", "");
		NewInstance.get(ItemMng.class).update(item.getId(), null, null, null,
				new BigDecimal(text));
		// 500豪秒后执行保存 在执行下一条
		Display.getDefault().timerExec(500, new Runnable() {
			public void run() {
				NewInstance.get(ItemView.class).updateSubmit();
				listener.handleEvent(null);
			}
		});
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
					"http://item.taobao.com/item.htm?");
			item = (Item) page.getList().get(0);
			buffer.append("id=").append(item.getNumIid());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}
	}
}
