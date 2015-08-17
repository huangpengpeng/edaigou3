package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.base.IMainView.NewInstance;
import org.eclipse.swt.widgets.Combo;

/**
 * 
 * 
 * 描述:商品同步
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月15日 下午5:30:44
 */
public class ItemSyncView extends Dialog {
	private Text txtSessionkey;
	private Text textShop;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public ItemSyncView(Shell parent, int style) {
		super(parent, style);
		setText("销售属性");
	}

	public String open() {
		Shell parent = this.getParent();

		Shell dialog = new Shell(parent, SWT.DIALOG_TRIM
				| SWT.APPLICATION_MODAL);
		dialog.setSize(1020, 710);

		txtSessionkey = new Text(dialog, SWT.BORDER);
		txtSessionkey.setText("6100110f79bc740ea515c6d6619e3e56523b8bf90ce2263405179875");
		txtSessionkey.setBounds(133, 10, 349, 23);

		Label lblNewLabel = new Label(dialog, SWT.NONE);
		lblNewLabel.setBounds(8, 13, 71, 17);
		lblNewLabel.setText("sessionkey");

		Label label = new Label(dialog, SWT.NONE);
		label.setBounds(8, 42, 61, 17);
		label.setText("\u5E97\u94FA ");

		textShop = new Text(dialog, SWT.BORDER);
		textShop.setText("1");
		textShop.setBounds(133, 39, 349, 23);

		final Combo combo = new Combo(dialog, SWT.NONE);
		combo.setItems(new String[] { "\u5426", "\u662F" });
		combo.setBounds(133, 77, 88, 25);
		combo.setText("\u66F4\u65B0\u4EF7\u683C");

		Button button = new Button(dialog, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				ItemMng itemMng = NewInstance.get(ItemMng.class);
				itemMng.syncItem(Long.valueOf(textShop.getText()),
						txtSessionkey.getText(),
						combo.getSelectionIndex() < 1 ? false : true);
			}
		});
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {

			}
		});
		button.setBounds(10, 112, 80, 27);
		button.setText("\u5F00\u59CB\u66F4\u65B0");

		Label lblNewLabel_1 = new Label(dialog, SWT.NONE);
		lblNewLabel_1.setBounds(8, 80, 61, 17);
		lblNewLabel_1.setText("\u66F4\u65B0\u4EF7\u683C");

		dialog.open();
		Display display = parent.getDisplay();
		while (!dialog.isDisposed()) {
			if (display.readAndDispatch()) {
				display.sleep();
			}
		}
		return "我闭了";
	}
}
