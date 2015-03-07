package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.springframework.stereotype.Component;

import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ITableView;
import com.edaigou3.view.ext.Column;

@Component
public class TableView_0 extends ITableView {

	private Table table;

	public void createListenter() {

	}

	public void preHandle() {
		table = new Table(NewInstance.get(FolderView.class).getC_新增商品(),
				SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 37, 1088, 508);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}
	
	

	@Override
	public void createContents(Shell shell) {
		Menu menu = new Menu(shell, SWT.POP_UP);
		MenuItem menuItem1 = new MenuItem(menu, SWT.CASCADE);
		menuItem1.setText("全部导出");
		MenuItem menuItem2 = new MenuItem(menu, SWT.CASCADE);
		menuItem2.setText("全部上架");
		MenuItem menuItem3 = new MenuItem(menu, SWT.CASCADE);
		menuItem3.setText("全修低价");
		table.setMenu(menu);
		super.createContents(shell);
	}


	@Override
	protected Column[] getColumns() {
		return new Column[] { new Column("商品图片", 100, 1),
				new Column("掌柜昵称", 100,0) };
	}

	@Override
	protected Table getTable() {
		return table;
	}
}
