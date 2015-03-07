package com.edaigou3.view.base;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.edaigou3.view.ext.Column;

public abstract class ITableView implements IBaseView {

	protected abstract Column[] getColumns();

	protected abstract Table getTable();

	public void fullContents(Object... values) throws IOException {

	}

	public void createContents(Composite composite) {
		Column[] columns = getColumns();
		for (Column column : columns) {
			TableColumn tblclmnNewColumn = new TableColumn(getTable(), SWT.NONE);
			tblclmnNewColumn.setWidth(column.getWidth());
			tblclmnNewColumn.setText(column.getText());
			tblclmnNewColumn.setAlignment(SWT.CENTER);
		}
	}
	
	public void createContents(Shell shell) {
	}
}
