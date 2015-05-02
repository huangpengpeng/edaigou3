package com.edaigou3.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IMainView.JFrame;
import com.edaigou3.view.base.IMainView.NewInstance;

public abstract class BaseBrowserView implements IBrowserView {

	protected Browser browser;
	private IOperatorProvider operatorProvider;
	private int waitingtime = 0;
	private boolean completed = true;
	private IBrowserView browserView;
	private Composite composite;

	public void doRequest(String url) {
		browser.setUrl(url);
	}

	public boolean execute(String javascript) {
		return browser.execute(javascript);
	}

	public void createContents(Composite composite) {
		this.browserView = this;
		this.composite = composite;
		createBrowser(composite);
	}

	public void createBrowser(Composite composite) {
		browser = new Browser(composite, SWT.BORDER);
		browser.setBounds(0, 33, 1089, 512);
		browser.addProgressListener(new ProgressListener() {
			public void completed(ProgressEvent arg0) {
			}

			public void changed(ProgressEvent arg0) {
				System.out.println("2 changed ProgressEvent ");
				// arg0.current == arg0.total 此条件会进入多次 利用 completed控制只进入一次
				if (completed == false && arg0.current == arg0.total) {
					System.out.println(" changed ProgressEvent time ");
					Display.getDefault().timerExec((int) waitingtime,
							new Runnable() {
								public void run() {
									System.out
											.println(" 3 operatorProvider changed ProgressEvent time ");
									operatorProvider.completed(browserView);
								}
							});
					completed = true;
				}
			}
		});
	}

	public void doRequest(IJavascriptProvider javascriptProvider,
			IOperatorProvider operator, int time) {
		this.operatorProvider = operator;
		this.waitingtime = time;
		browser.execute(javascriptProvider.getRequestUrl(this));
		Display.getDefault().timerExec((int) waitingtime, new Runnable() {
			public void run() {
				operatorProvider.completed(browserView);
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
