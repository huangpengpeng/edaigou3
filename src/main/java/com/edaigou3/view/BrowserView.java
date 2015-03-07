package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.springframework.stereotype.Component;

import com.edaigou3.view.base.BaseViewAdapter;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IMainView.JFrame;
import com.edaigou3.view.base.IMainView.NewInstance;

@Component
public class BrowserView extends BaseViewAdapter  implements IBrowserView {

	private Browser browser;
	private IOperatorProvider operatorProvider;
	private int waitingtime = 0;
	private boolean completed = true;

	public void doRequest(String url) {
		browser.setUrl(url);
	}

	public void createContents(Composite composite) {
		browser = new Browser(composite, SWT.BORDER);
		browser.setBounds(0, 33, 1089, 512);
		browser.addProgressListener(new ProgressListener() {
			public void completed(ProgressEvent arg0) {
			}

			public void changed(ProgressEvent arg0) {
				// arg0.current == arg0.total 此条件会进入多次 利用 completed控制只进入一次
				if (completed == false && arg0.current == arg0.total) {
					Display.getDefault().timerExec((int) waitingtime,
							new Runnable() {
								public void run() {
									operatorProvider.completed(NewInstance
											.get(BrowserView.class));
								}
							});
					completed = true;
				}
			}
		});
	}

	public void doRequest(String url, IOperatorProvider provider, int time) {
		this.operatorProvider = provider;
		this.waitingtime = time;
		// 表示开始请求
		completed = false;
		browser.setUrl(url);
	}

	public void doRequest(IRequestProvider requestProvider,
			IOperatorProvider operatorProvider, int time) {
		doRequest(requestProvider.getRequestUrl(this), operatorProvider, time);
	}

	public String getResponseText() {
		return JFrame.getText(browser);
	}
}
