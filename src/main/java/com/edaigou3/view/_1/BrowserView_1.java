package com.edaigou3.view._1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.edaigou3.view.BaseBrowserView;
import com.edaigou3.view.FolderView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.ext._修售价格Provider;
import com.edaigou3.view.ext._同步最低售价Provider;
import com.edaigou3.view.ext._抓取编号Provider;
import com.edaigou3.view.ext._抓售价格Provider;

@Component
public class BrowserView_1 extends BaseBrowserView {

	private Button _同步最低售价;
	private Button _抓取编号;
	private Button _修售价格;
	private Button _抓售价格;

	public void createContents(Shell shell) {
	}

	@Override
	public void createContents(Composite composite) {

		View.addView(composite, NewInstance.get(SearchView_1.class));

		_同步最低售价 = new Button(composite, SWT.NONE);
		_同步最低售价.setBounds(320, 8, 84, 22);
		_同步最低售价.setText("同步最低售价");

		_抓取编号 = new Button(composite, SWT.NONE);
		_抓取编号.setBounds(420, 8, 69, 22);
		_抓取编号.setText("抓取编号");

		_修售价格 = new Button(composite, SWT.NONE);
		_修售价格.setBounds(640, 8, 75, 22);
		_修售价格.setText("修售价格");

		_抓售价格 = new Button(composite, SWT.NONE);
		_抓售价格.setBounds(730, 8, 80, 22);
		_抓售价格.setText("抓售价格");

		View.addView(composite, NewInstance.get(ProgressView_1.class));

		super.createContents(composite);
	}

	public static Integer pageNo = 0, totalCount = 0;

	public void createListenter() {
		_同步最低售价.addListener(SWT.Selection, new Listener() {
			public void handleEvent(final Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._同步最低售价Provider.RequestProvider(
								pageNo++), new _同步最低售价Provider(this), 1000);
			}
		});
		_抓取编号.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._抓取编号Provider.RequestProvider(
								pageNo++), new _抓取编号Provider(this), 1000);
			}
		});
		_修售价格.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._修售价格Provider.JavascriptProvider(
								pageNo++), new _修售价格Provider(this), 5000);
			}
		});
		_抓售价格.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._抓售价格Provider.RequestProvider(
								pageNo++), new _抓售价格Provider(this), 1000);
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
}
