package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.edaigou3.view._1.BrowserView_1;
import com.edaigou3.view._2.SearchView_2;
import com.edaigou3.view._4.BrowserView_4;
import com.edaigou3.view._5.BrowserView_5;
import com.edaigou3.view.base.BaseViewAdapter;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IMainView.NewInstance;

@Component
public class FolderView extends BaseViewAdapter {

	private CTabFolder tabFolder;
	private CTabItem _新增商品;
	private Composite c_新增商品;
	private CTabItem _新品发布;
	private CTabItem _商品过滤;
	private Composite c_新品发布;
	private Composite c_商品过滤;
	private CTabItem _已上架;
	private Composite c_已上架;
	private CTabItem _商品同步;
	private Composite c_商品同步;
	private CTabItem _非低价格_店售错误;
	private Composite c_非低价格_店售错误;
	private CTabItem _天猫下架;
	private Composite c_天猫下架;
	private CTabItem  _售利过低;
	private Composite c_售利过低;

	@Override
	public void createContents(Shell shell) {

		tabFolder = new CTabFolder(shell, SWT.BORDER);
		tabFolder.setBounds(0, 102, 1094, 574);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		_新增商品 = new CTabItem(tabFolder, SWT.NONE);
		_新增商品.setText("新增商品");
		tabFolder.setSelection(_新增商品);

		c_新增商品 = new Composite(tabFolder, SWT.NONE);
		_新增商品.setControl(c_新增商品);

		_新品发布 = new CTabItem(tabFolder, SWT.NONE);
		_新品发布.setText("新品发布");

		c_新品发布 = new Composite(tabFolder, SWT.NONE);
		_新品发布.setControl(c_新品发布);

		_商品过滤 = new CTabItem(tabFolder, SWT.NONE);
		_商品过滤.setText("商品过滤");

		c_商品过滤 = new Composite(tabFolder, SWT.NONE);
		_商品过滤.setControl(c_商品过滤);

		_已上架 = new CTabItem(tabFolder, SWT.NONE);
		_已上架.setText("已上架");

		c_已上架 = new Composite(tabFolder, SWT.NONE);
		_已上架.setControl(c_已上架);

		_商品同步 = new CTabItem(tabFolder, SWT.NONE);
		_商品同步.setText("商品同步");

		c_商品同步 = new Composite(tabFolder, SWT.NONE);
		_商品同步.setControl(c_商品同步);

		_非低价格_店售错误 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		_非低价格_店售错误.setText("非低价格|店售错误");

		c_非低价格_店售错误 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		_非低价格_店售错误.setControl(c_非低价格_店售错误);

		_天猫下架 = new CTabItem(NewInstance.get(FolderView.class).getTabFolder(),
				SWT.NONE);
		_天猫下架.setText("天猫下架");

		c_天猫下架 = new Composite(
				NewInstance.get(FolderView.class).getTabFolder(), SWT.NONE);
		_天猫下架.setControl(c_天猫下架);
		
		 _售利过低 = new CTabItem(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		 _售利过低.setText("售利过低");

		 c_售利过低 = new Composite(NewInstance.get(FolderView.class)
				.getTabFolder(), SWT.NONE);
		_售利过低.setControl(c_售利过低);

		tabFolder.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent arg0) {
				if (tabFolder.getSelection().equals(_商品过滤)) {
					NewInstance.get(SearchView_2.class).query(1);
				}
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}

	public IBrowserView getCurrentBrowserView() {
		if (tabFolder.getSelection().equals(_新品发布)) {
			return NewInstance.get(BrowserView_1.class);
		}

		if (tabFolder.getSelection().equals(_商品同步)) {
			return NewInstance.get(BrowserView_4.class);
		}

		if (tabFolder.getSelection().equals(_非低价格_店售错误)) {
			return NewInstance.get(BrowserView_5.class);
		}

		return NewInstance.get(BrowserView_1.class);
	}

	public CTabItem get_新品发布() {
		return _新品发布;
	}

	public void selection(CTabItem item) {
		getTabFolder().setSelection(item);
	}

	@Override
	public void createListenter() {
	}

	public CTabFolder getTabFolder() {
		return tabFolder;
	}

	public CTabItem get_新增商品() {
		return _新增商品;
	}

	public Composite getC_新增商品() {
		return c_新增商品;
	}

	public Composite getC_新品发布() {
		return c_新品发布;
	}

	public CTabItem get_商品过滤() {
		return _商品过滤;
	}

	public Composite getC_商品过滤() {
		return c_商品过滤;
	}

	public CTabItem get_已上架() {
		return _已上架;
	}

	public Composite getC_已上架() {
		return c_已上架;
	}

	public CTabItem get_商品同步() {
		return _商品同步;
	}

	public Composite getC_商品同步() {
		return c_商品同步;
	}

	public CTabItem get_非低价格_店售错误() {
		return _非低价格_店售错误;
	}

	public Composite getC_非低价格_店售错误() {
		return c_非低价格_店售错误;
	}

	public CTabItem get_天猫下架() {
		return _天猫下架;
	}

	public Composite getC_天猫下架() {
		return c_天猫下架;
	}

	public CTabItem get_售利过低() {
		return _售利过低;
	}

	public Composite getC_售利过低() {
		return c_售利过低;
	}
}
