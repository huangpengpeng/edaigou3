package com.edaigou3;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;
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
import com.edaigou3.view._06.PageView_6;
import com.edaigou3.view._06.SearchView_6;
import com.edaigou3.view._06.TableView_6;
import com.edaigou3.view._1.BrowserView_1;
import com.edaigou3.view._2.SearchView_2;
import com.edaigou3.view._2.TableView_2;
import com.edaigou3.view._3.PageView_3;
import com.edaigou3.view._3.SearchView_3;
import com.edaigou3.view._3.TableView_3;
import com.edaigou3.view._4.BrowserView_4;
import com.edaigou3.view._5.BrowserView_5;
import com.edaigou3.view.base.IMainView;

@Component
public class MainView extends IMainView {

	private Table table_3;
	private Table table_4;
	
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

		View.addView(NewInstance.get(FolderView.class).getC_非低价格_店售错误(),
				NewInstance.get(BrowserView_5.class));

		View.addView(NewInstance.get(FolderView.class).getC_天猫下架(),
				NewInstance.get(PageView_6.class));

		View.addView(NewInstance.get(FolderView.class).getC_天猫下架(),
				NewInstance.get(SearchView_6.class));

		View.addView(NewInstance.get(FolderView.class).getC_天猫下架(),
				NewInstance.get(TableView_6.class));

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
	}
}
