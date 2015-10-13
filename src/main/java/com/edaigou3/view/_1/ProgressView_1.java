package com.edaigou3.view._1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.springframework.stereotype.Component;

import com.edaigou3.view.IProgressView;
import com.edaigou3.view.base.IMainView.MessageBox2;

@Component
public class ProgressView_1 extends IProgressView {

	@Override
	public void createContents(Composite composite) {
		_总数 = new Text(composite, SWT.BORDER);
		_总数.setBounds(902, 10, 32, 18);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("/");
		label_6.setBounds(940, 13, 6, 12);

		_当前数 = new Text(composite, SWT.BORDER);
		_当前数.setBounds(952, 10, 32, 18);
		_当前数.addListener(SWT.FocusOut, new Listener() {
			public void handleEvent(Event arg0) {
				try{
				BrowserView_1.pageNo=getPageNo();
				}catch(Exception e){}
			}
		});

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("/");
		label_7.setBounds(990, 13, 6, 12);

		_错误数 = new Text(composite, SWT.BORDER);
		_错误数.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		_错误数.setBounds(1002, 10, 32, 18);
		_错误数.setText("0");
		_错误数.setEnabled(true);
	}

	public Integer getPageNo() {
		return Integer.valueOf(_当前数.getText());
	}
}
