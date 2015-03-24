package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.common.jdbc.page.Pagination;
import com.edaigou3.view.base.IBaseView;

/**
 * 进度试图
 * 
 * @author zoro
 *
 */
public class IProgressView implements IBaseView {

	protected Text _总数;
	protected Text _当前数;
	protected Text _错误数;

	public void createContents(Shell shell) {
	}

	public void createContents(Composite composite) {
		_总数 = new Text(composite, SWT.BORDER);
		_总数.setBounds(902, 10, 32, 18);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("/");
		label_6.setBounds(940, 13, 6, 12);

		_当前数 = new Text(composite, SWT.BORDER);
		_当前数.setBounds(952, 10, 32, 18);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("/");
		label_7.setBounds(990, 13, 6, 12);

		_错误数 = new Text(composite, SWT.BORDER);
		_错误数.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		_错误数.setBounds(1002, 10, 32, 18);
		_错误数.setText("0");
		_错误数.setEnabled(true);
	}

	public void createListenter() {
	}

	public void preHandle() {
	}

	public void fullContents(Object... values) {
		Pagination page = (Pagination) values[0];
		if (page == null) {
			_总数.setText("");
			_当前数.setText("");
			return;
		}
		_总数.setText(page.getTotalCount() + "");
		_当前数.setText(page.getPageNo() + "");
	}

	public void addErrors() {
		String errorsValue = _错误数.getText();
		Long errorsCount = Long.valueOf(errorsValue);
		_错误数.setText(errorsCount++ + "");
	}
}
