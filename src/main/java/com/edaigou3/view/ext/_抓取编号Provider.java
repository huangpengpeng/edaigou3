package com.edaigou3.view.ext;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.common.jdbc.page.Pagination;
import com.common.util.ParamentersUtils;
import com.edaigou3.entity.Item;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._1.SearchView_1;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;

public class _抓取编号Provider implements IOperatorProvider {
	
	private static Item item;

	private Listener listener;

	public _抓取编号Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(IBrowserView browserView) {
		Document document = Jsoup.parse(browserView.getResponseText());
		Elements itemtitles = document.getElementsByClass("item-title");
		if (itemtitles != null && itemtitles.size() > 0) {
			String href = itemtitles.get(0).attr("href");
			NewInstance.get(ItemView.class).setNumIid(
					ParamentersUtils.getQueryParams(href, "id"));
		}

		browserView.setText("");

		// 500豪秒后执行保存 在执行下一条
		Display.getDefault().timerExec(2000, new Runnable() {
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
					"http://sell.taobao.com/auction/merchandise/auction_list.htm?type=11");
			item = (Item) page.getList().get(0);
			buffer.append("&&searchKeyword=").append(item.getTitle());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}
	}
}
