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
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.springframework.stereotype.Component;

import com.edaigou3.view.BasePageView;
import com.edaigou3.view.FolderView;
import com.edaigou3.view.ItemView;
import com.edaigou3.view._0.SearchView_0;
import com.edaigou3.view._0.TableView;
import com.edaigou3.view._1.BrowserView_1;
import com.edaigou3.view.base.IMainView;

@Component
public class MainView extends IMainView {

	private Text text_13;
	private Table table_1;
	private Text text_14;
	private Table table_2;
	private Text text_15;
	private Text text_16;
	private Text text_17;
	private Table table_3;
	private Table table_4;
	private Table table_5;
	private Table table_6;
	private Table table_7;
	private Text text_21;
	private Table table_8;
	private Text text_22;
	private Text text_23;
	private Text text_24;
	private Table table_9;

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

		View.addView(NewInstance.get(BrowserView_1.class));

		CTabItem tbtmNewItem_2 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmNewItem_2.setText("已上架");

		Composite composite_2 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmNewItem_2.setControl(composite_2);

		Button btnNewButton_10 = new Button(composite_2, SWT.NONE);
		btnNewButton_10.setBounds(10, 8, 60, 27);
		btnNewButton_10.setText("上一页");

		CLabel lblNewLabel_21 = new CLabel(composite_2, SWT.NONE);
		lblNewLabel_21.setAlignment(SWT.CENTER);
		lblNewLabel_21.setBounds(82, 8, 67, 23);
		lblNewLabel_21.setText("0/0/0");

		Button btnNewButton_11 = new Button(composite_2, SWT.NONE);
		btnNewButton_11.setBounds(158, 8, 60, 27);
		btnNewButton_11.setText("下一页");

		Label label = new Label(composite_2, SWT.NONE);
		label.setBounds(438, 15, 61, 17);
		label.setText("淘宝店铺");

		Combo combo_6 = new Combo(composite_2, SWT.NONE);
		combo_6.setBounds(505, 12, 99, 25);

		Label lblNewLabel_22 = new Label(composite_2, SWT.NONE);
		lblNewLabel_22.setBounds(780, 15, 61, 17);
		lblNewLabel_22.setText("商品标题");

		text_13 = new Text(composite_2, SWT.BORDER);
		text_13.setBounds(847, 10, 160, 23);

		Button btnNewButton_14 = new Button(composite_2, SWT.NONE);
		btnNewButton_14.setBounds(1018, 8, 60, 27);
		btnNewButton_14.setText("查询");

		table_1 = new Table(composite_2, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setBounds(0, 40, 1088, 505);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);

		Label lblNewLabel_27 = new Label(composite_2, SWT.NONE);
		lblNewLabel_27.setBounds(618, 15, 54, 12);
		lblNewLabel_27.setText("错误类型");

		Combo combo_9 = new Combo(composite_2, SWT.NONE);
		combo_9.setBounds(678, 12, 86, 20);

		CTabItem tbtmNewItem_3 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmNewItem_3.setText("已下架");

		Composite composite_3 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmNewItem_3.setControl(composite_3);

		Button btnNewButton_15 = new Button(composite_3, SWT.NONE);
		btnNewButton_15.setBounds(10, 8, 60, 27);
		btnNewButton_15.setText("上一页");

		CLabel lblNewLabel_23 = new CLabel(composite_3, SWT.NONE);
		lblNewLabel_23.setAlignment(SWT.CENTER);
		lblNewLabel_23.setBounds(84, 8, 67, 23);
		lblNewLabel_23.setText("0/0/0");

		Button btnNewButton_16 = new Button(composite_3, SWT.NONE);
		btnNewButton_16.setBounds(154, 8, 80, 27);
		btnNewButton_16.setText("下一页");

		Label lblNewLabel_24 = new Label(composite_3, SWT.NONE);
		lblNewLabel_24.setBounds(577, 8, 61, 17);
		lblNewLabel_24.setText("淘宝店铺");

		Combo combo_7 = new Combo(composite_3, SWT.NONE);
		combo_7.setBounds(639, 6, 88, 25);

		Label lblNewLabel_25 = new Label(composite_3, SWT.NONE);
		lblNewLabel_25.setBounds(742, 8, 61, 17);
		lblNewLabel_25.setText("商品标题");

		text_14 = new Text(composite_3, SWT.BORDER);
		text_14.setBounds(809, 8, 194, 23);

		Button btnNewButton_17 = new Button(composite_3, SWT.NONE);
		btnNewButton_17.setBounds(1009, 6, 70, 27);
		btnNewButton_17.setText("查询");

		table_2 = new Table(composite_3, SWT.BORDER | SWT.FULL_SELECTION);
		table_2.setBounds(0, 41, 1088, 504);
		table_2.setHeaderVisible(true);
		table_2.setLinesVisible(true);

		View.addView(NewInstance.get(FolderView.class).getC_新增商品(),
				NewInstance.get(BasePageView.class));

		View.addView(NewInstance.get(FolderView.class).getC_新增商品(),
				NewInstance.get(SearchView_0.class));

		View.addView(NewInstance.get(FolderView.class).getC_新增商品(),
				NewInstance.get(TableView.class));

		CTabItem tbtmNewItem_4 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmNewItem_4.setText("商品同步");

		Composite composite_4 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmNewItem_4.setControl(composite_4);

		Button btnNewButton_12 = new Button(composite_4, SWT.NONE);
		btnNewButton_12.setBounds(324, 10, 72, 22);
		btnNewButton_12.setText("同步淘客");

		Button btnNewButton_13 = new Button(composite_4, SWT.NONE);
		btnNewButton_13.setBounds(402, 10, 72, 22);
		btnNewButton_13.setText("同步库存");

		Button btnNewButton_18 = new Button(composite_4, SWT.NONE);
		btnNewButton_18.setBounds(480, 10, 72, 22);
		btnNewButton_18.setText("同步SKU内容");

		Button btnNewButton_19 = new Button(composite_4, SWT.NONE);
		btnNewButton_19.setBounds(558, 10, 157, 22);
		btnNewButton_19.setText("同步标题|猫售价|下架状态");

		Button btnNewButton_20 = new Button(composite_4, SWT.NONE);
		btnNewButton_20.setBounds(721, 10, 84, 22);
		btnNewButton_20.setText("同步最低售价");

		text_15 = new Text(composite_4, SWT.BORDER);
		text_15.setBounds(938, 12, 32, 18);

		Label lblNewLabel_26 = new Label(composite_4, SWT.NONE);
		lblNewLabel_26.setBounds(976, 15, 6, 12);
		lblNewLabel_26.setText("/");

		text_16 = new Text(composite_4, SWT.BORDER);
		text_16.setBounds(988, 12, 32, 18);

		Label lblNewLabel_28 = new Label(composite_4, SWT.NONE);
		lblNewLabel_28.setBounds(10, 15, 54, 12);
		lblNewLabel_28.setText("淘宝店铺");

		Combo combo_11 = new Combo(composite_4, SWT.NONE);
		combo_11.setBounds(70, 12, 86, 20);

		Label label_5 = new Label(composite_4, SWT.NONE);
		label_5.setText("/");
		label_5.setBounds(1026, 15, 6, 12);

		text_17 = new Text(composite_4, SWT.BORDER);
		text_17.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		text_17.setBounds(1038, 12, 32, 18);

		Label lblNewLabel_29 = new Label(composite_4, SWT.NONE);
		lblNewLabel_29.setBounds(162, 15, 54, 12);
		lblNewLabel_29.setText("错误类型");

		Combo combo_12 = new Combo(composite_4, SWT.NONE);
		combo_12.setBounds(222, 12, 86, 20);

		Button btnNewButton_7 = new Button(composite_4, SWT.NONE);
		btnNewButton_7.setBounds(811, 10, 78, 22);
		btnNewButton_7.setText("同步店铺售价");

		Browser browser_2 = new Browser(composite_4, SWT.BORDER);
		browser_2.setBounds(0, 33, 1089, 512);

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

		CTabItem tbtmSku_1 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmSku_1.setText("SKU数量变动");

		Composite composite_9 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tbtmSku_1.setControl(composite_9);

		Button button_10 = new Button(composite_9, SWT.NONE);
		button_10.setText("上一页");
		button_10.setBounds(10, 10, 60, 27);

		CLabel label_11 = new CLabel(composite_9, SWT.NONE);
		label_11.setText("0/0/0");
		label_11.setAlignment(SWT.CENTER);
		label_11.setBounds(82, 10, 67, 23);

		Button button_11 = new Button(composite_9, SWT.NONE);
		button_11.setText("下一页");
		button_11.setBounds(158, 10, 60, 27);

		table_6 = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION);
		table_6.setLinesVisible(true);
		table_6.setHeaderVisible(true);
		table_6.setBounds(0, 42, 1088, 505);

		Label lblNewLabel_30 = new Label(composite_9, SWT.NONE);
		lblNewLabel_30.setBounds(1024, 21, 54, 12);
		lblNewLabel_30.setText("全部修复");

		CTabItem tabItem_2 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_2.setText("店铺授权");

		Composite composite_12 = new Composite(NewInstance
				.get(FolderView.class).getTabFolder(), SWT.NONE);
		tabItem_2.setControl(composite_12);

		Label lblNewLabel_33 = new Label(composite_12, SWT.NONE);
		lblNewLabel_33.setBounds(10, 10, 43, 12);
		lblNewLabel_33.setText("APPKEY");

		text_22 = new Text(composite_12, SWT.BORDER);
		text_22.setBounds(62, 4, 98, 18);

		Label lblNewLabel_34 = new Label(composite_12, SWT.NONE);
		lblNewLabel_34.setBounds(183, 10, 54, 12);
		lblNewLabel_34.setText("APPSECRET");

		text_23 = new Text(composite_12, SWT.BORDER);
		text_23.setBounds(251, 4, 98, 18);

		Label lblNewLabel_35 = new Label(composite_12, SWT.NONE);
		lblNewLabel_35.setBounds(369, 10, 54, 12);
		lblNewLabel_35.setText("掌柜昵称");

		text_24 = new Text(composite_12, SWT.BORDER);
		text_24.setBounds(429, 4, 104, 18);

		Button btnNewButton_23 = new Button(composite_12, SWT.NONE);
		btnNewButton_23.setBounds(986, 5, 43, 22);
		btnNewButton_23.setText("保存");

		table_9 = new Table(composite_12, SWT.BORDER | SWT.FULL_SELECTION);
		table_9.setBounds(0, 28, 1088, 517);
		table_9.setHeaderVisible(true);
		table_9.setLinesVisible(true);

		Button btnNewButton_24 = new Button(composite_12, SWT.NONE);
		btnNewButton_24.setBounds(1035, 5, 43, 22);
		btnNewButton_24.setText("获取");

		CTabItem tabItem_1 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_1.setText("商品过滤");

		Composite composite_11 = new Composite(NewInstance
				.get(FolderView.class).getTabFolder(), SWT.NONE);
		tabItem_1.setControl(composite_11);

		Label lblNewLabel_31 = new Label(composite_11, SWT.NONE);
		lblNewLabel_31.setBounds(10, 10, 54, 12);
		lblNewLabel_31.setText("淘宝店铺");

		Combo combo_10 = new Combo(composite_11, SWT.NONE);
		combo_10.setBounds(70, 7, 86, 20);

		Label lblNewLabel_32 = new Label(composite_11, SWT.NONE);
		lblNewLabel_32.setBounds(175, 10, 54, 12);
		lblNewLabel_32.setText("掌柜昵称");

		text_21 = new Text(composite_11, SWT.BORDER);
		text_21.setBounds(238, 7, 97, 18);

		Button btnNewButton_8 = new Button(composite_11, SWT.NONE);
		btnNewButton_8.setBounds(1024, 5, 54, 22);
		btnNewButton_8.setText("保存");

		table_8 = new Table(composite_11, SWT.BORDER | SWT.FULL_SELECTION);
		table_8.setBounds(0, 28, 1088, 517);
		table_8.setHeaderVisible(true);
		table_8.setLinesVisible(true);

		CTabItem tabItem_7 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		tabItem_7.setText("猫价变动");

		Composite composite_10 = new Composite(NewInstance
				.get(FolderView.class).getTabFolder(), SWT.NONE);
		tabItem_7.setControl(composite_10);

		Button button_12 = new Button(composite_10, SWT.NONE);
		button_12.setText("上一页");
		button_12.setBounds(10, 10, 60, 27);

		CLabel label_12 = new CLabel(composite_10, SWT.NONE);
		label_12.setText("0/0/0");
		label_12.setAlignment(SWT.CENTER);
		label_12.setBounds(82, 10, 67, 23);

		Button button_13 = new Button(composite_10, SWT.NONE);
		button_13.setText("下一页");
		button_13.setBounds(158, 10, 60, 27);

		table_7 = new Table(composite_10, SWT.BORDER | SWT.FULL_SELECTION);
		table_7.setLinesVisible(true);
		table_7.setHeaderVisible(true);
		table_7.setBounds(0, 42, 1088, 505);

		Label label_13 = new Label(composite_10, SWT.NONE);
		label_13.setText("全部下架");
		label_13.setBounds(1024, 17, 54, 12);
	}
}
