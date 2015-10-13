package com.edaigou3.view.ext;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.common.jdbc.page.Pagination;
import com.common.util.JsonUtils;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.manager.ItemFiltersMng;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.JsonFilter;
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
		Map<String, Object> result = JsonUtils.toMap(JsonFilter
				.filter(browserView.getResponseText()));
		List<Map<String,Object>> listItem=(List<Map<String, Object>>) result.get("listItem");
		BigDecimal lowPrice = new BigDecimal(Integer.MAX_VALUE);
		for (Map<String,Object> element : listItem) {
			try {
				String nick = (String) element.get("nick");
				String numIid = (String) element.get("item_id");
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
			String title = (String) element.get("name");
			System.out.println(title);
			String price = (String) element.get("price");
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
					"http://s.m.taobao.com/search?event_submit_do_new_search_auction=1&_input_charset=utf-8&topSearch=1&atype=b&searchfrom=1&action=home%3Aredirect_app_action&from=1&q=[q]&sst=1&n=20&buying=buyitnow&m=api4h5&abtest=%24abtest&wlsort=%24abtest&page=1");
			item = (Item) page.getList().get(0);
			NewInstance.get(ItemView.class).fullContents(item, true);
			try {
				return buffer.toString().replace("[q]",URLEncoder.encode( item.getTitle(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}

	}
}
