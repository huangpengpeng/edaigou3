package com.edaigou3.view._2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import com.edaigou3.view.FolderView;
import com.edaigou3.view.base.ITableView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.ext.Column;

public class TableView_2 extends ITableView{
	
	private Table table;

	public void createListenter() {

	}

	public void preHandle() {
		table = new Table(NewInstance.get(FolderView.class).getC_商品过滤(),
				SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 37, 1088, 508);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	@Override
	protected Column[] getColumns() {
		return new Column[] { new Column("nick", "掌柜昵称", 200, Column.PUTONG),
				new Column("pNumIid", "商品编号", 200, Column.PUTONG),
				new Column(null, "操作", 500, Column.BUTTON) };
	}

	@Override
	protected Table getTable() {
		return table;
	}

	@Override
	protected Integer getHeight() {
		return 20;
	}

}
