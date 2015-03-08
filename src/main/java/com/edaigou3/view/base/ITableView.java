package com.edaigou3.view.base;

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
		getTable().removeAll();
		List<Object> list = (List<Object>) values[0];
		for (Object value : list) {
			TableItem ti = new TableItem(getTable(), SWT.NONE);
			for (int i = 0; i < getColumns().length; i++) {
				try {
					Column column = getColumns()[i];
					Object obj = PropertyUtils.getProperty(value,
							column.getName());
					if (column.getMode() == Column.IMAGE) {
						ti.setImage(i, ImageUtils
								.base64StringToImg((String) obj));
					}
					if (column.getMode() == Column.PUTONG) {
						ti.setText(i, obj == null ? "" : obj.toString());
					}
				} catch (Exception e) {
				}
			}
		}
	}

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

	public static void removeControlAndEditorFormUserTable(Table table) {
		TableItem[] items = table.getItems();
		for (TableItem item : items) {
			for (int i = 0; i < 3; i++) {
				TableEditor ctl = (TableEditor) item.getData(i + "e");
				if (ctl != null) {
					ctl.dispose();
				}
				Button button = (Button) item.getData(i + "b");
				if (button != null) {
					button.dispose();
				}
			}
		}
	}
}
