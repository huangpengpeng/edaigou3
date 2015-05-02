package com.edaigou3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.springframework.stereotype.Component;

import com.edaigou3.view.FolderView;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._0.PageView_0;
import com.edaigou3.view._0.SearchView_0;
import com.edaigou3.view._0.TableView_0;
import com.edaigou3.view._1.BrowserView_1;
import com.edaigou3.view._2.SearchView_2;
import com.edaigou3.view._2.TableView_2;
import com.edaigou3.view._3.PageView_3;
import com.edaigou3.view._3.SearchView_3;
import com.edaigou3.view._3.TableView_3;
import com.edaigou3.view._4.BrowserView_4;
import com.edaigou3.view.base.IMainView;

@Component
public class MainView extends IMainView {

	private Table table_3;
	private Table table_4;
	private Table table_5;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainView window = new MainView();
		window.load();
	}

	@Override
	public void createShell() {
		shell = new Shell(SWT.BORDER | SWT.MIN);
		shell.setSize(1100, 700);
		shell.setText("易代购");
	}

	@Override
	public String getApplicationXml() {
		return "classpath:/com/edaigou3/config/application-context.xml";
	}

	@Override
	public void createContents() {

		View.addView(NewInstance.get(ItemView.class));

		View.addView(NewInstance.get(FolderView.class));

		View.addView(NewInstance.get(FolderView.class).getC_新品发布(),
				NewInstance.get(BrowserView_1.class));

		View.addView(NewInstance.get(FolderView.class).getC_商品过滤(),
				NewInstance.get(SearchView_2.class));

		View.addView(NewInstance.get(FolderView.class).getC_商品过滤(),
				NewInstance.get(TableView_2.class));

		View.addView(NewInstance.get(FolderView.class).getC_已上架(),
				NewInstance.get(PageView_3.class));

		View.addView(NewInstance.get(FolderView.class).getC_已上架(),
				NewInstance.get(SearchView_3.class));

		View.addView(NewInstance.get(FolderView.class).getC_已上架(),
				NewInstance.get(TableView_3.class));

		View.addView(NewInstance.get(FolderView.class).getC_新增商品(),
				NewInstance.get(PageView_0.class));

		View.addView(NewInstance.get(FolderView.class).getC_新增商品(),
				NewInstance.get(SearchView_0.class));

		View.addView(NewInstance.get(FolderView.class).getC_新增商品(),
				NewInstance.get(TableView_0.class));

		View.addView(NewInstance.get(FolderView.class).getC_商品同步(),
				NewInstance.get(BrowserView_4.class));

		CTabItem tabItem = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem.setText("非低价格|店售错误");

		Composite composite_5 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem.setControl(composite_5);

		Combo combo_8 = new Combo(composite_5, SWT.NONE);
		combo_8.setBounds(70, 10, 86, 20);

		Button button = new Button(composite_5, SWT.NONE);
		button.setText("操作");
		button.setBounds(731, 10, 42, 22);

		Button button_1 = new Button(composite_5, SWT.NONE);
		button_1.setText("上一页");
		button_1.setBounds(803, 10, 54, 22);

		CLabel label_1 = new CLabel(composite_5, SWT.NONE);
		label_1.setText("0/0/0");
		label_1.setAlignment(SWT.CENTER);
		label_1.setBounds(863, 13, 60, 18);

		Button button_2 = new Button(composite_5, SWT.NONE);
		button_2.setText("下一页");
		button_2.setBounds(929, 10, 54, 22);

		Button button_3 = new Button(composite_5, SWT.NONE);
		button_3.setText("自动");
		button_3.setBounds(1041, 10, 48, 22);

		Browser browser_1 = new Browser(composite_5, SWT.BORDER);
		browser_1.setBounds(0, 36, 1089, 512);

		Label label_2 = new Label(composite_5, SWT.NONE);
		label_2.setText("淘宝店铺");
		label_2.setBounds(10, 13, 54, 22);

		Button btnRadioButton_4 = new Button(composite_5, SWT.RADIO);
		btnRadioButton_4.setBounds(504, 14, 69, 16);
		btnRadioButton_4.setText("退出活动");

		Button btnRadioButton_5 = new Button(composite_5, SWT.RADIO);
		btnRadioButton_5.setBounds(603, 14, 93, 16);
		btnRadioButton_5.setText("修改低价");

		Button btnNewButton_22 = new Button(composite_5, SWT.NONE);
		btnNewButton_22.setBounds(181, 8, 72, 22);
		btnNewButton_22.setText("全部最低价");

		CTabItem tabItem_3 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_3.setText("售利过低");

		Composite composite_6 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_3.setControl(composite_6);

		Button button_4 = new Button(composite_6, SWT.NONE);
		button_4.setText("上一页");
		button_4.setBounds(10, 10, 60, 27);

		CLabel label_3 = new CLabel(composite_6, SWT.NONE);
		label_3.setText("0/0/0");
		label_3.setAlignment(SWT.CENTER);
		label_3.setBounds(82, 10, 67, 23);

		Button button_5 = new Button(composite_6, SWT.NONE);
		button_5.setText("下一页");
		button_5.setBounds(158, 10, 60, 27);

		table_3 = new Table(composite_6, SWT.BORDER | SWT.FULL_SELECTION);
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);
		table_3.setBounds(0, 42, 1088, 505);

		Label lblNewLabel_20 = new Label(composite_6, SWT.NONE);
		lblNewLabel_20.setBounds(1024, 17, 54, 12);
		lblNewLabel_20.setText("全部下架");

		CTabItem tabItem_5 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_5.setText("淘客变动");

		Composite composite_7 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_5.setControl(composite_7);

		Button button_6 = new Button(composite_7, SWT.NONE);
		button_6.setText("上一页");
		button_6.setBounds(10, 10, 60, 27);

		CLabel label_4 = new CLabel(composite_7, SWT.NONE);
		label_4.setText("0/0/0");
		label_4.setAlignment(SWT.CENTER);
		label_4.setBounds(82, 10, 67, 23);

		Button button_7 = new Button(composite_7, SWT.NONE);
		button_7.setText("下一页");
		button_7.setBounds(158, 10, 60, 27);

		table_4 = new Table(composite_7, SWT.BORDER | SWT.FULL_SELECTION);
		table_4.setLinesVisible(true);
		table_4.setHeaderVisible(true);
		table_4.setBounds(0, 42, 1088, 505);

		Label label_8 = new Label(composite_7, SWT.NONE);
		label_8.setText("全部下架");
		label_8.setBounds(1024, 17, 54, 12);

		CTabItem tabItem_6 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_6.setText("天猫下架");

		Composite composite_8 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_6.setControl(composite_8);

		Button button_8 = new Button(composite_8, SWT.NONE);
		button_8.setText("上一页");
		button_8.setBounds(10, 10, 60, 27);

		CLabel label_9 = new CLabel(composite_8, SWT.NONE);
		label_9.setText("0/0/0");
		label_9.setAlignment(SWT.CENTER);
		label_9.setBounds(82, 10, 67, 23);

		Button button_9 = new Button(composite_8, SWT.NONE);
		button_9.setText("下一页");
		button_9.setBounds(158, 10, 60, 27);

		table_5 = new Table(composite_8, SWT.BORDER | SWT.FULL_SELECTION);
		table_5.setLinesVisible(true);
		table_5.setHeaderVisible(true);
		table_5.setBounds(0, 42, 1088, 505);

		Label label_10 = new Label(composite_8, SWT.NONE);
		label_10.setText("全部下架");
		label_10.setBounds(1024, 17, 54, 12);

	}
}
