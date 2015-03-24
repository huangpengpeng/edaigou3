package com.edaigou3.view._6;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.stereotype.Component;

import com.edaigou3.entity.Item;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.FolderView;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ITableView;
import com.edaigou3.view.ext.Column;
import com.edaigou3.view.ext.Column.Listener;

@Component
public class TableView_6 extends ITableView {

	private Table table;

	public void createListenter() {
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent arg0) {
				Item item = (Item) arg0.item.getData();
				NewInstance.get(ItemView.class).fullContents(item, true);
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}

	public void preHandle() {
		table = new Table(NewInstance.get(FolderView.class).getC_天猫下架(),
				SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 37, 1088, 508);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	@Override
	public void createContents(Shell shell) {
		Menu menu = new Menu(shell, SWT.POP_UP);
		table.setMenu(menu);
		super.createContents(shell);
	}

	@Override
	protected Column[] getColumns() {
		return new Column[] {
				new Column("imageByte", "商品图片", 88, Column.IMAGE),
				new Column("title", "商品标题", 128, Column.PUTONG),
				new Column("originalPrice", "原售价格", 88, Column.PUTONG),
				new Column("rebateProportion", "淘返比例", 80, Column.PUTONG),
				new Column("rebateFee", "淘返金额", 100, Column.PUTONG),
				new Column("serviceFee", "服务费", 100, Column.PUTONG),
				new Column("realPrice", "实际销价", 100, Column.PUTONG),
				new Column("profitFee", "实际利润", 100, Column.PUTONG),
				new Column("lowPrice", "最低售价", 100, Column.PUTONG),
				new Column("numIid", "商品编号", 100, Column.PUTONG),
				new Column(null, "操作", 100, Column.BUTTON, "删除", new Listener() {
					public void handleEvent(Event arg0) {
						Item item = (Item) arg0.widget.getData();
						NewInstance.get(ItemMng.class).delete(item.getId());
						NewInstance.get(SearchView_6.class).query(
								NewInstance.get(PageView_6.class).getPageNo());
					}
				})};
	}

	@Override
	protected Table getTable() {
		return table;
	}

	@Override
	protected Integer getHeight() {
		return 80;
	}

	public void selectionValue(Object value) {
		TableItem ti = table.getSelection()[table.getSelectionIndex()];
		if (ti != null) {
			ti.setData(value);
		}
	}

	public Item getSelectionValue() {
		TableItem ti = table.getSelection()[table.getSelectionIndex()];
		if (ti != null) {
			return ((Item) ti.getData());
		}
		return null;
	}
}
