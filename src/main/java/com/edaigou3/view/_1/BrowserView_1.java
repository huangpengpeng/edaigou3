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
import com.edaigou3.view.ext._同步标题_猫价格_状态Provider;
import com.edaigou3.view.ext._抓取编号Provider;

@Component
public class BrowserView_1 extends BaseBrowserView {

	private Button _同步最低售价;
	private Button _抓取编号;
	private Button _同步标题_猫价格_状态;
	private Button _修售价格;

	public void createContents(Shell shell) {
		Composite composite_1 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		NewInstance.get(FolderView.class).get_新品发布().setControl(composite_1);

		View.addView(composite_1, NewInstance.get(SearchView_1.class));

		_同步最低售价 = new Button(composite_1, SWT.NONE);
		_同步最低售价.setBounds(320, 8, 84, 22);
		_同步最低售价.setText("同步最低售价");

		_抓取编号 = new Button(composite_1, SWT.NONE);
		_抓取编号.setBounds(420, 8, 69, 22);
		_抓取编号.setText("抓取编号");
		
		_同步标题_猫价格_状态 = new Button(composite_1, SWT.NONE);
		_同步标题_猫价格_状态.setBounds(508, 8, 120, 22);
		_同步标题_猫价格_状态.setText("同步标题|猫价格|状态");

		_修售价格 = new Button(composite_1, SWT.NONE);
		_修售价格.setBounds(640, 8, 75, 22);
		_修售价格.setText("修售价格");

		Button btnRadioButton_3 = new Button(composite_1, SWT.NONE);
		btnRadioButton_3.setBounds(730, 8, 80, 22);
		btnRadioButton_3.setText("抓售价格");

		View.addView(composite_1, NewInstance.get(ProgressView_1.class));
		super.createContents(composite_1);
	}

	@Override
	public void createContents(Composite composite) {
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
		_同步标题_猫价格_状态.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._同步标题_猫价格_状态Provider.RequestProvider(
								pageNo++), new _同步标题_猫价格_状态Provider(this), 1000);
			}
		});
		_修售价格.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				if (totalCount == 0 || pageNo > totalCount) {
					return;
				}
				doRequest(
						new com.edaigou3.view.ext._修售价格Provider.RequestProvider(
								pageNo++), new _修售价格Provider(this), 1000);
			}
		});
	}

	public void preHandle() {
	}

	public void fullContents(Object... values) {
		pageNo = (Integer) values[0];
		totalCount = (Integer) values[1];
	}
}
