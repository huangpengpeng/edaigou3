package com.edaigou3.view.ext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
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
		String text=browserView.getResponseText();
		
		Pattern pattern = Pattern
				.compile("(\\?id=){1}[\\w\\.\\-/:]+(\\\\)");
		Matcher matcher = pattern.matcher(text);
		String txtNumber="";
		while (matcher.find()) {
		txtNumber = matcher.group().replace("?id=", "").replace("\\", "");
		System.out.println(txtNumber);
		}
		
		
		if(StringUtils.isNotBlank(txtNumber)){
			NewInstance.get(ItemView.class).setNumIid(txtNumber);
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
					"http://tbgr.huanleguang.com/itemlibrary/index/list/?q=[q]&pageSize=10&approve_status=onsale&hpm=1");
			item = (Item) page.getList().get(0);
			NewInstance.get(ItemView.class).fullContents(item, true);
			try {
				return buffer.toString().replace("[q]",URLEncoder.encode(item.getTitle(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
