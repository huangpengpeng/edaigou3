package com.edaigou3.view.ext;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.manager.ItemFiltersMng;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

public class _同步最低售价Provider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public _同步最低售价Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(final IBrowserView browserView) {

		// 获取最低价格
		Document document = Jsoup.parse(browserView.getResponseText());
		Elements elements = document.getElementsByClass("item");
		BigDecimal lowPrice = new BigDecimal(Integer.MAX_VALUE);
		for (Element element : elements) {
			Elements eshops = element.getElementsByClass("shop");
			if (eshops == null || eshops.size() == 0) {
				continue;
			}
			try {
				Element eshop = eshops.get(0);
				String nick = eshop.text();
				String numIid = eshop.child(0).attr("data-nid");
				boolean isFlag = false;
				for (ItemFilters filters : NewInstance
						.get(ItemFiltersMng.class).query()) {
					if (StringUtils.equals(filters.getNick(), nick)) {
						isFlag = true;
					}
					if (StringUtils.equals(numIid,
							String.valueOf(filters.getpNumIid()))) {
						isFlag = true;
					}
				}
				if (isFlag) {
					continue;
				}
			} catch (Exception e) {
			}
			Elements e2s = element.getElementsByClass("title");
			if (e2s == null || e2s.size() == 0) {
				continue;
			}
			Element e2 = e2s.get(0);
			e2s = element.getElementsByClass("price");
			if (e2s == null || e2s.size() == 0) {
				continue;
			}
			Element eP2 = e2s.get(0);
			String title = e2.text();
			String price = eP2.text().replace("¥", "");
			if (!StringUtils.startsWith(org.springframework.util.StringUtils
					.trimAllWhitespace(title),
					org.springframework.util.StringUtils.trimAllWhitespace(item
							.getTitle()))) {
				continue;
			}
			try {
				lowPrice = lowPrice.compareTo(new BigDecimal(price)) > 0 ? new BigDecimal(
						price) : lowPrice;
			} catch (Exception e) {
			}
		}

		NewInstance.get(ItemView.class).setLowPrice(lowPrice);

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
		private ISearchView searchView;

		public RequestProvider(Integer pageNo, ISearchView searchView) {
			this.pageNo = pageNo;
			this.searchView = searchView;
		}

		public String getRequestUrl(IBrowserView browserView) {
			Pagination page = searchView.query(pageNo++);
			StringBuffer buffer = new StringBuffer(
					"http://s.taobao.com/search?q=");
			item = (Item) page.getList().get(0);
			buffer.append(item.getTitle());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}

	}
}
