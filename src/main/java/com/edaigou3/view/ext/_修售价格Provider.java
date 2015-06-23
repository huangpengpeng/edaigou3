package com.edaigou3.view.ext;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IJavascriptProvider;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

public class _修售价格Provider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public _修售价格Provider(Listener listener) {
		this.listener = listener;
	}

	public void completed(IBrowserView browserView) {

		StringBuffer stringBuffer = new StringBuffer(
				"$(\".ui-checkbox > input[ng-disabled='$val.cache.disabled']\").click();");
		stringBuffer.append("$('.normal > input').focus();");
		stringBuffer.append("$('.normal > input').val('" + item.getRealPrice()
				+ "');");
		stringBuffer.append("$('.normal > input').blur();");
		stringBuffer.append("$('.normal > input').change();");
		stringBuffer.append("$('.main-menus-backward > button').click();");
		browserView.execute(stringBuffer.toString());

		// 1秒后执行保存 在执行下一条
		Display.getDefault().timerExec(3000, new Runnable() {
			public void run() {
				listener.handleEvent(null);
			}
		});
	}

	public static class JavascriptProvider implements IJavascriptProvider {

		private Integer pageNo;
		private ISearchView searchView;

		public JavascriptProvider(Integer pageNo, ISearchView searchView) {
			this.pageNo = pageNo;
			this.searchView = searchView;
		}

		public String getRequestUrl(IBrowserView browserView) {
			Pagination page = searchView.query(pageNo++);
			item = (Item) page.getList().get(0);
			StringBuffer stringBuffer = new StringBuffer("");
			stringBuffer
					.append("$('.ng-isolate-scope[ng-model=\"temp.q\"]').val('"
							+ item.getNumIid() + "');");
			stringBuffer
					.append("$('.ng-isolate-scope[ng-model=\"temp.q\"]').change();");
			stringBuffer.append("$('.ui-search-btn').click();");
			NewInstance.get(ItemView.class).fullContents(item, true);
			return stringBuffer.toString();
		}

	}
}
