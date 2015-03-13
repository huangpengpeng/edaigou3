package com.edaigou3.view.ext;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._1.SearchView_1;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;

public class _同步标题_猫价格_状态Provider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public _同步标题_猫价格_状态Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(final IBrowserView browserView) {
		// 获取原价
		Document document = Jsoup.parse(browserView.getResponseText());
		Element element = document.getElementById("J_StrPriceModBox");
		if (element != null) {
			Elements elements = element.getElementsByClass("tm-price");
			if (elements != null && elements.size() > 0) {
				element = elements.get(0);
				NewInstance.get(ItemView.class).setMarketPrice(
						new BigDecimal(element.text()));
			}
		}

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
			item = (Item) page.getList().get(0);
			StringBuffer buffer = new StringBuffer(item.getUrl());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}

	}
}
