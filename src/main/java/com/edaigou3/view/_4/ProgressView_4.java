package com.edaigou3.view._4;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.springframework.stereotype.Component;

import com.edaigou3.view.IProgressView;

@Component
public class ProgressView_4 extends IProgressView {

	@Override
	public void createContents(Composite composite) {
		super.createContents(composite);
	}

	@Override
	public void createListenter() {
		_当前数.addKeyListener(new KeyListener() {
			
			public void keyReleased(KeyEvent arg0) {
				String pageNOvalue = _当前数.getText();
				BrowserView_4.pageNo = Integer.valueOf(pageNOvalue);
			}
			
			public void keyPressed(KeyEvent arg0) {
			}
		});
	}
}
