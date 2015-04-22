package com.edaigou3.view.ext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.common.jdbc.page.Pagination;
import com.common.util.JsonUtils;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemErrors;
import com.edaigou3.entity.ItemErrors.ItemErrorsType;
import com.edaigou3.manager.ItemErrorsMng;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.JsonFilter;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

public class _抓售价格Provider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public _抓售价格Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(IBrowserView browserView) {
		ItemErrorsMng errorsMng = NewInstance.get(ItemErrorsMng.class);
		if (browserView.getResponseText().contains("宝贝不存在")) {
			errorsMng.add(item.getId(), ItemErrorsType.天猫下架.toString());
			return;
		} else if (browserView.getResponseText().contains("已下架")) {
			errorsMng.add(item.getId(), ItemErrorsType.天猫下架.toString());
			return;
		} else {
			ItemErrors itemErrors = errorsMng.getByItemAndType(item.getId(),
					ItemErrorsType.天猫下架.toString());
			if (itemErrors != null) {
				errorsMng.delete(itemErrors.getId());
			}
		}
		Map<String, Object> result = JsonUtils.toMap(JsonFilter
				.filter(browserView.getResponseText()));
		Map<String, Object> data = (Map<String, Object>) result.get("data");
		List<Map<String, Object>> apiStack = (List<Map<String, Object>>) data
				.get("apiStack");
		String value = (String) apiStack.get(0).get("value");
		Map<String, Object> api = JsonUtils.toMap(value);
		data = (Map<String, Object>) api.get("data");
		Map<String, Object> itemInfoModel = (Map<String, Object>) data
				.get("itemInfoModel");
		List<Map<String, Object>> priceUnits = (List<Map<String, Object>>) itemInfoModel
				.get("priceUnits");
		Map<String, Object> zoroMap = priceUnits.get(0);
		NewInstance.get(ItemMng.class).update(item.getId(), null, null, null,
				new BigDecimal(zoroMap.get("price").toString()));
		// 500豪秒后执行保存 在执行下一条
		Display.getDefault().timerExec(1000, new Runnable() {
			public void run() {
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
					"http://hws.m.taobao.com/cache/wdetail/5.0/?");
			item = (Item) page.getList().get(0);
			buffer.append("id=").append(item.getNumIid());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}
	}
}
