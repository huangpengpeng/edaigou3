package com.edaigou3.view._2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.springframework.stereotype.Component;

import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.manager.ItemFiltersMng;
import com.edaigou3.view.FolderView;
import com.edaigou3.view.base.IMainView.MessageBox2;
import com.edaigou3.view.base.ITableView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.ext.Column;
import com.edaigou3.view.ext.Column.Listener;

@Component
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
		return new Column[] { new Column("nick", "掌柜昵称", 400, Column.PUTONG),
				new Column("pNumIid", "商品编号", 400, Column.PUTONG),
				new Column(null, "操作", 270, Column.BUTTON,"删除",new Listener() {
							public void handleEvent(Event arg0) {
								ItemFilters itemFilters = (ItemFilters) arg0.widget
										.getData();
								NewInstance.get(ItemFiltersMng.class).delete(
										itemFilters.getId());
								NewInstance.get(SearchView_2.class).query(1);
							}
				}) };
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
