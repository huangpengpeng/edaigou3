package com.edaigou3.view._4;

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
import com.edaigou3.view.ext._同步最低售价Provider;
import com.edaigou3.view.ext._抓售价格Provider;

@Component
public class BrowserView_4 extends BaseBrowserView {

	private Button _同步最低售价;
	private Button _同步淘客;
	private Button _抓售价格_状态;
	

	public void createContents(Shell shell) {
	}

	@Override
	public void createContents(Composite composite) {

		View.addView(composite, NewInstance.get(SearchView_4.class));

		_同步最低售价 = new Button(composite, SWT.NONE);
		_同步最低售价.setBounds(320, 8, 84, 22);
		_同步最低售价.setText("最低售价");

		_同步淘客 = new Button(composite, SWT.NONE);
		_同步淘客.setBounds(460, 8, 100, 22);
		_同步淘客.setText("同步淘客|原价");

		_抓售价格_状态 = new Button(composite, SWT.NONE);
		_抓售价格_状态.setBounds(730, 8, 100, 22);
		_抓售价格_状态.setText("抓售价格|状态");


		View.addView(composite, NewInstance.get(ProgressView_4.class));

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
								pageNo++, NewInstance.get(SearchView_4.class)),
						new _同步最低售价Provider(this), 3000);
			}
		});
		_抓售价格_状态.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._抓售价格Provider.RequestProvider(
								pageNo++, NewInstance.get(SearchView_4.class)),
						new _抓售价格Provider(this), 1000);
			}
		});
		_同步淘客.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(new TbkInfoProvider.RequestProvider(pageNo++,
						NewInstance.get(SearchView_4.class)),
						new TbkInfoProvider(this), 0);
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
