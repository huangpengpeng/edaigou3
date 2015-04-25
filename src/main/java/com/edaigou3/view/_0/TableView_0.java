package com.edaigou3.view._0;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.stereotype.Component;

import com.edaigou3.entity.Item;
import com.edaigou3.entity.Item.ItemStatus;
import com.edaigou3.entity.ItemErrors;
import com.edaigou3.entity.ItemErrors.ItemErrorsType;
import com.edaigou3.manager.ItemErrorsMng;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.FolderView;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ITableView;
import com.edaigou3.view.ext.Column;
import com.edaigou3.view.ext.Column.Listener;
import com.edaigou3.view.ext.ResourceUtils;

@Component
public class TableView_0 extends ITableView {

	private Table table;
	private MenuItem _全部导出;
	private MenuItem _全部上架;
	private MenuItem _全修低价;

	public void createListenter() {
		table.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent arg0) {
				Item item = (Item) arg0.item.getData();
				NewInstance.get(ItemView.class).fullContents(item, true);
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
		_全部导出.addListener(SWT.Selection,
				new org.eclipse.swt.widgets.Listener() {
					public void handleEvent(Event arg0) {
						String filename = ResourceUtils.getResource()
								+ "url.txt";
						List<Item> createItems = NewInstance.get(ItemMng.class)
								.query(ItemStatus.创建.toString());
						List<String> urls = new ArrayList<String>();
						for (Item item : createItems) {
							urls.add(item.getUrl());
						}
						try {
							FileUtils.writeLines(new File(filename), urls);
						} catch (IOException e) {
							e.printStackTrace();
						}
						NewInstance.get(SearchView_0.class).setValue("_商品标题",
								filename);
					}
				});
		_全修低价.addListener(SWT.Selection,
				new org.eclipse.swt.widgets.Listener() {
					public void handleEvent(Event arg0) {
						List<ItemErrors> itemErrors = NewInstance.get(
								ItemErrorsMng.class).getByErrorType(
								ItemErrorsType.非低价格.toString());
						for (ItemErrors itemError : itemErrors) {
							ItemMng itemMng = NewInstance.get(ItemMng.class);
							Item item = itemMng.get(itemError.getItemId());
							if (item == null) {
								NewInstance.get(ItemErrorsMng.class).delete(
										itemError.getId());
								continue;
							}
							itemMng.update(
									item.getId(),
									item.getShopId(),
									item.getImageByte(),
									item.getChannel(),
									item.getTitle(),
									item.getOriginalPrice(),
									item.getRebateProportion(),
									item.getRebateFee(),
									item.getServiceFee(),
									item.getLowPrice().subtract(BigDecimal.ONE),
									item.getProfitFee(), item.getLowPrice(),
									item.getNumIid());
						}
					}
				});
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
		_全部导出 = new MenuItem(menu, SWT.CASCADE);
		_全部导出.setText("全部导出");
		_全部上架 = new MenuItem(menu, SWT.CASCADE);
		_全部上架.setText("全部上架");
		_全修低价 = new MenuItem(menu, SWT.CASCADE);
		_全修低价.setText("全修低价");
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
				new Column(null, "操作", 100, Column.BUTTON, "删除",
						new Listener() {
							public void handleEvent(Event arg0) {
								Item item = (Item) arg0.widget.getData();
								NewInstance.get(ItemMng.class).delete(
										item.getId());
								NewInstance.get(SearchView_0.class).query(
										NewInstance.get(PageView_0.class)
												.getPageNo());
							}
						}) };
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
