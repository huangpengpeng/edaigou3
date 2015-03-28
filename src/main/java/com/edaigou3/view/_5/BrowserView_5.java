package com.edaigou3.view._5;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.edaigou3.view.BaseBrowserView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.ext.TbkInfoProvider;
import com.edaigou3.view.ext._修售价格Provider;
import com.edaigou3.view.ext._同步最低售价Provider;
import com.edaigou3.view.ext._抓售价格Provider;

@Component
public class BrowserView_5 extends BaseBrowserView {

	private Button _修售价格;
	private Button _抓售价格;

	public void createContents(Shell shell) {
	}

	@Override
	public void createContents(Composite composite) {

		View.addView(composite, NewInstance.get(SearchView_5.class));

		_修售价格 = new Button(composite, SWT.NONE);
		_修售价格.setBounds(640, 8, 75, 22);
		_修售价格.setText("修售价格");

		_抓售价格 = new Button(composite, SWT.NONE);
		_抓售价格.setBounds(730, 8, 100, 22);
		_抓售价格.setText("抓售价格|状态");

		View.addView(composite, NewInstance.get(ProgressView_5.class));

		super.createContents(composite);
	}

	public static Integer pageNo = 0, totalCount = 0;

	public void createListenter() {
		_修售价格.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._修售价格Provider.JavascriptProvider(
								pageNo++, NewInstance.get(SearchView_5.class)),
						new _修售价格Provider(this), 3000);
			}
		});
		_抓售价格.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				System.out.println("count:" + totalCount + " pageNo:" + pageNo);
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._抓售价格Provider.RequestProvider(
								pageNo++, NewInstance.get(SearchView_5.class)),
						new _抓售价格Provider(this), 1000);
			}
		});
	}

	public void preHandle() {
	}

	public void fullContents(Object... values) {
		pageNo = (Integer) values[0];
		totalCount = (Integer) values[1];
	}

	public String getUrl() {
		return browser.getUrl();
	}

	public void setText(String text) {
		browser.setText(text);
	}
}
