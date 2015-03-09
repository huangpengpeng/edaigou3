package com.edaigou3.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.edaigou3.entity.Shop;
import com.edaigou3.manager.ShopMng;
import com.edaigou3.view.base.IBaseView;
import com.edaigou3.view.base.IMainView.NewInstance;

@Component
@Scope(value = "prototype")
public class ShopView implements IBaseView {

	private Combo shopcommbo;

	private Listener listener;

	public void createContents(Shell shell) {

	}

	public void createContents(Composite composite) {
		shopcommbo = new Combo(composite, SWT.NONE);
		List<Shop> shops = NewInstance.get(ShopMng.class).query();
		shopcommbo.setText("请选择");
		shopcommbo.add("请选择");
		for (Shop shop : shops) {
			shopcommbo.add(shop.getNick());
		}
	}

	public void setBounds(int x, int y, int width, int height) {
		shopcommbo.setBounds(x, y, width, height);
	}

	public void createListenter() {
		shopcommbo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent arg0) {
				if (listener != null) {
					listener.handleEvent();
				}
			}

			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});
	}

	public void addListener(Listener listener) {
		this.listener = listener;
	}

	public void preHandle() {

	}

	public void fullContents(Object... values) {
	}

	public static interface Listener {
		public void handleEvent();
	}

	public Long getNumber() {
		Shop shop = NewInstance.get(ShopMng.class).getByNick(
				shopcommbo.getText());
		if (shop == null) {
			return null;
		}
		return shop.getId();
	}
	
	public void clearText(){
		shopcommbo.setText("请选择");
	}
	
	public void setText(String text){
		shopcommbo.setText(text);
	}
}
