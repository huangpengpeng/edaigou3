package com.edaigou3.view.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.edaigou3.view.ext.Column;
import com.edaigou3.view.ext.ImageUtils;

public abstract class ITableView implements IBaseView {

	protected abstract Column[] getColumns();

	protected abstract Table getTable();

	protected abstract Integer getHeight();

	@SuppressWarnings("unchecked")
	public void fullContents(Object... values) {
		removeControlAndEditorFormUserTable(getTable());
		List<Object> list = (List<Object>) values[0];
		for (Object value : list) {
			TableItem ti = new TableItem(getTable(), SWT.NONE);
			for (int i = 0; i < getColumns().length; i++) {
				try {
					final Column column = getColumns()[i];
					Object obj = null;
					if (column.getName() != null) {
						obj = PropertyUtils
								.getProperty(value, column.getName());
					}
					if (column.getMode() == Column.IMAGE) {
						ti.setImage(i,
								ImageUtils.base64StringToImg((String) obj));
					}
					if (column.getMode() == Column.PUTONG) {
						ti.setText(i, obj == null ? "" : obj.toString());
					}
					if (column.getMode() == Column.BUTTON) {
						Button btn = new Button(getTable(), SWT.NONE);
						btn.setText(column.getButtonText());
						btn.addListener(SWT.Selection, new Listener() {
							public void handleEvent(Event arg0) {
								column.getListener().handleEvent(arg0);
							}
						});
						btn.setData(value);
						TableEditor editor = new TableEditor(getTable());
						editor.horizontalAlignment = SWT.CENTER;
						editor.minimumWidth = column.getWidth();
						editor.setEditor(btn, ti, i);
						editors.add(btn);
						editors.add(editor);
					}
				} catch (Exception e) {
				}
				ti.setData(value);
			}
		}
	}

	private List<Object> editors = new ArrayList<Object>();

	public void createContents(Composite composite) {
		Column[] columns = getColumns();
		for (Column column : columns) {
			TableColumn tblclmnNewColumn = new TableColumn(getTable(), SWT.NONE);
			tblclmnNewColumn.setWidth(column.getWidth());
			tblclmnNewColumn.setText(column.getText());
			tblclmnNewColumn.setAlignment(SWT.CENTER);
		}
		getTable().addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = getHeight();
			}
		});
	}

	public void createContents(Shell shell) {
	}

	public void removeControlAndEditorFormUserTable(Table table) {
		for (Object obj : editors) {
			if (obj instanceof TableEditor) {
				((TableEditor) obj).dispose();
			}
			if (obj instanceof Button) {
				((Button) obj).dispose();
			}
		}
		editors.clear();
		getTable().removeAll();
	}
}
