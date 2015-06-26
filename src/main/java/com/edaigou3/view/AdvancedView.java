package com.edaigou3.view;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.edaigou3.view.base.IMainView.MessageBox2;

public class AdvancedView extends Dialog {

	protected Object result;
	protected Shell advancedViewShell;
	private Text searchText;
	private Button loginalimamaBtn;
	private Button btnPop;
	private Text alimamaShopUrl;
	private Browser browser;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public AdvancedView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		advancedViewShell.open();
		advancedViewShell.layout();
		Display display = getParent().getDisplay();
		while (!advancedViewShell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		advancedViewShell = new Shell(getParent(), getStyle());
		advancedViewShell.setSize(900, 600);
		advancedViewShell.setText("抓取淘客...");

		searchText = new Text(advancedViewShell, SWT.BORDER);
		searchText.setBounds(10, 10, 588, 18);

		Button searchButton = new Button(advancedViewShell, SWT.NONE);
		searchButton.addSelectionListener(searchListener);
		searchButton.setBounds(625, 8, 72, 22);
		searchButton.setText("抓取店铺");

		loginalimamaBtn = new Button(advancedViewShell, SWT.NONE);
		loginalimamaBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				browser.setUrl("http://www.alimama.com/member/login.htm");
			}
		});
		loginalimamaBtn.setBounds(812, 8, 72, 22);
		loginalimamaBtn.setText("登录阿里");

		alimamaShopUrl = new Text(advancedViewShell, SWT.BORDER);
		alimamaShopUrl.setBounds(10, 34, 588, 18);

		browser = new Browser(advancedViewShell, SWT.NONE);
		browser.setBounds(10, 58, 874, 507);
		browser.setUrl("http://www.alimama.com/member/login.htm");

		btnPop = new Button(advancedViewShell, SWT.NONE);
		btnPop.addSelectionListener(zhauqListener);
		btnPop.setBounds(720, 8, 72, 22);
		btnPop.setText("抓取比例");

		text_100 = new Text(advancedViewShell, SWT.BORDER);
		text_100.setText("100");
		text_100.setBounds(812, 34, 24, 18);

		text_30 = new Text(advancedViewShell, SWT.BORDER);
		text_30.setText("30");
		text_30.setBounds(849, 34, 24, 18);
		
		text_1 = new Text(advancedViewShell, SWT.BORDER);
		text_1.setText("我有推广渠道，希望可以合作 QQ :330659459");
		text_1.setBounds(625, 34, 167, 18);
		browser.addProgressListener(new ProgressListener() {
			public void completed(ProgressEvent arg0) {
			}

			public void changed(ProgressEvent arg0) {
				// arg0.current == arg0.total 此条件会进入多次 利用 completed控制只进入一次
				if (shopzhuaqu == false && arg0.current == arg0.total) {
					Display.getDefault().timerExec(500, new Runnable() {
						public void run() {
							doShopNumbers(browser.getText());
							PageNo1++;
							if (PageNo1 < Integer.valueOf(text_100.getText())) {
								searchListener.widgetSelected(null);
							}
						}
					});
					shopzhuaqu = true;
				}
				if (bilizhuaqu == false && arg0.current == arg0.total) {
					Display.getDefault().timerExec(500, new Runnable() {
						public void run() {
							boolean flag = doNumbers(browser.getText());
							PageNo2++;
							if (!flag && PageNo2 < shopNumbers.size()) {
								zhauqListener.widgetSelected(null);
							}
						}
					});
					bilizhuaqu = true;
				}
			}

			protected void doShopNumbers(String text) {
				Pattern pattern = Pattern
						.compile("(\"userNumberId\":\"){1}[\\w\\.\\-/:]+(\",\")");
				Matcher matcher = pattern.matcher(text);
				while (matcher.find()) {
					String txtNumber = matcher.group()
							.replace("\"userNumberId\":\"", "")
							.replace("\",\"", "");
					System.out.println(txtNumber);
					shopNumbers.add(txtNumber);
				}
			}

			protected boolean doNumbers(String text) {
				Pattern pattern = Pattern
						.compile(("(\"avgCommissionToString\":\"){1}[\\w\\.\\-/:]+( )"));
				Matcher matcher = pattern.matcher(text);
				while (matcher.find()) {
					String txtNumber = matcher.group();
					txtNumber = txtNumber.replace(
							"\"avgCommissionToString\":\"", "").trim();
					if (Double.parseDouble(txtNumber) > Integer
							.parseInt(text_30.getText())) {
						return !text.contains("exsitApplyList");
					}
				}
				return false;
			}
		});
	}

	private SelectionListener zhauqListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			shopzhuaqu = true;
			bilizhuaqu = false;
			PageNo1 = 0;
			if (shopNumbers.size() == 0) {
				MessageBox2.showErrorMsg("请选抓取店铺");
				return;
			}
			bilizhuaqu = false;
			if (PageNo2 == shopNumbers.size()) {
				MessageBox2.showErrorMsg("比例抓取完成");
				return;
			}
			StringBuffer urlBuffer = new StringBuffer(
					"http://pub.alimama.com/shopdetail/campaigns.json?oriMemberId=");
			urlBuffer.append(shopNumbers.toArray()[PageNo2]);
			alimamaShopUrl
					.setText("http://pub.alimama.com/myunion.htm?spm=a219t.7473494.1998155389.3.NGFPZH#!/promo/self/shop_detail?userNumberId="
							+ shopNumbers.toArray()[PageNo2]);
			if (shopNumbersCon.contains(shopNumbers.toArray()[PageNo2])) {
				MessageBox2.showErrorMsg("重复");
				return;
			}
			browser.setUrl(urlBuffer.toString());
		}
	};

	private SelectionListener searchListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			shopzhuaqu = false;
			bilizhuaqu = true;
			if (PageNo2 > 0) {
				shopNumbers.clear();
				shopNumbersCon.clear();
			}
			PageNo2 = 0;
			String search = searchText.getText();
			if (StringUtils.isBlank(search)) {
				MessageBox2.showErrorMsg("关键词不能为空");
				return;
			}
			StringBuffer urlBuffer = new StringBuffer(
					"http://pub.alimama.com/pubauc/searchAuctionList.json?spm=a219t.7473494.1998155389.3.NGFPZH&sort=_commrate&user_type=1&q=");
			try {
				urlBuffer.append(URLEncoder.encode(search,"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			urlBuffer.append("&toPage=").append(PageNo1);
			browser.setUrl(urlBuffer.toString());
		}
	};

	private Set<String> shopNumbers = new HashSet<String>();
	private Set<String> shopNumbersCon = new HashSet<String>();
	private Integer PageNo1 = 0, PageNo2 = 0;
	private boolean shopzhuaqu = true, bilizhuaqu = true;
	private Text text_100;
	private Text text_30;
	private Text text_1;
}
