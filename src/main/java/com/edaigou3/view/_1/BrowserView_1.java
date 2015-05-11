package com.edaigou3.view._1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;
import com.edaigou3.view.BaseBrowserView;
import com.edaigou3.view.FolderView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.ext._同步最低售价Provider;

@Component
public class BrowserView_1 extends BaseBrowserView {

	private Button _同步最低售价;

	public void createContents(Shell shell) {
		Composite composite_1 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		NewInstance.get(FolderView.class).get_新品发布().setControl(composite_1);

		View.addView(composite_1, NewInstance.get(SearchView_1.class));

		_同步最低售价 = new Button(composite_1, SWT.NONE);
		_同步最低售价.setBounds(420, 8, 84, 22);
		_同步最低售价.setText("同步最低售价");

		Button btnRadioButton_1 = new Button(composite_1, SWT.NONE);
		btnRadioButton_1.setBounds(520, 8, 69, 22);
		btnRadioButton_1.setText("抓取编号");

		Button btnRadioButton_2 = new Button(composite_1, SWT.NONE);
		btnRadioButton_2.setBounds(608, 8, 75, 22);
		btnRadioButton_2.setText("修售价格");

		Button btnRadioButton_3 = new Button(composite_1, SWT.NONE);
		btnRadioButton_3.setBounds(695, 8, 80, 22);
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
	}

	public void preHandle() {
	}

	public void fullContents(Object... values) {
	}
}
