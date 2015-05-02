package com.edaigou3.view.ext;

import java.math.BigDecimal;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemErrors;
import com.edaigou3.entity.ItemErrors.ItemErrorsType;
import com.edaigou3.manager.ItemErrorsMng;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

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

		ItemErrorsMng errorsMng = NewInstance.get(ItemErrorsMng.class);
		if (document.text().contains("此宝贝已下架")) {
			errorsMng.add(item.getId(), ItemErrorsType.天猫下架.toString());
		} else {
			ItemErrors itemErrors = errorsMng.getByItemAndType(item.getId(),
					ItemErrorsType.天猫下架.toString());
			if (itemErrors != null) {
				errorsMng.delete(itemErrors.getId());
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
		private ISearchView searchView;

		public RequestProvider(Integer pageNo, ISearchView searchView) {
			this.pageNo = pageNo;
			this.searchView = searchView;
		}

		public String getRequestUrl(IBrowserView browserView) {
			Pagination page = searchView.query(pageNo++);
			StringBuffer buffer = new StringBuffer(
					"http://item.taobao.com/item.htm?");
			item = (Item) page.getList().get(0);
			buffer.append("id=").append(item.getNumIid());
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}
	}
}
