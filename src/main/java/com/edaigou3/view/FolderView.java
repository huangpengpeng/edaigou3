package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.springframework.stereotype.Component;

import com.edaigou3.view.base.BaseViewAdapter;
import com.edaigou3.view.base.IMainView.NewInstance;

@Component
public class FolderView extends BaseViewAdapter {

	private CTabFolder tabFolder;
	private CTabItem _新增商品;
	private Composite c_新增商品;
	private CTabItem _新品发布;
	private CTabItem _商品过滤;
	private Composite c_新品发布 ;
	private Composite c_商品过滤;

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
	
}
