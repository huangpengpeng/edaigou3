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
				"$(\"*[for='J_TopCheckAll']\").click();");
		stringBuffer.append("$('#J_SpecPrice_" + item.getNumIid() + "').val('");
		stringBuffer.append(item.getRealPrice() + "');$('#J_SpecPrice_"
				+ item.getNumIid() + "').change();");
		stringBuffer.append("KISSY.all('#J_BtmAddToPromo')[0].click();");
		browserView.execute(stringBuffer.toString());

		// 1秒后执行保存 在执行下一条
		Display.getDefault().timerExec(1000, new Runnable() {
			public void run() {
				listener.handleEvent(null);
			}
		});
	}

	public static class JavascriptProvider implements IJavascriptProvider {

		private Integer pageNo;
		private ISearchView searchView;

		public JavascriptProvider(Integer pageNo,ISearchView searchView) {
			this.pageNo = pageNo;
			this.searchView=searchView;
		}

		public String getRequestUrl(IBrowserView browserView) {
			Pagination page = searchView.query(
					pageNo++);
			item = (Item) page.getList().get(0);
			StringBuffer stringBuffer = new StringBuffer(
					"$('#J_SearchTitle').focus();");
			stringBuffer.append("$('#J_SearchTitle').val('" + item.getTitle()
					+ "');");
			stringBuffer.append("$('#J_SearchTitle').blur();");
			stringBuffer
					.append("$('.float-tips').remove();$('#J_SearchBtn').click();");
			NewInstance.get(ItemView.class).fullContents(item, true);
			return stringBuffer.toString();
		}

	}
}
