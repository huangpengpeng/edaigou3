package com.edaigou3.view;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
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

import com.common.util.JsonUtils;
import com.common.util.ParamentersUtils;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.base.IMainView.JsonFilter;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.ext.ImageUtils;

public class PiliangView extends Dialog {

	protected Object result;
	protected Shell advancedViewShell;
	private Text searchText;
	private Button loginalimamaBtn;
	private Text alimamaShopUrl;
	private Browser browser;
	private ShopView shopView;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public PiliangView(Shell parent, int style) {
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

		shopView = new ShopView();
		View.addView(advancedViewShell, shopView);
		shopView.setBounds(626, 10, 86, 20);

		Button searchButton = new Button(advancedViewShell, SWT.NONE);
		searchButton.addSelectionListener(piliangListenter);
		searchButton.setBounds(718, 8, 72, 22);
		searchButton.setText("抓取商品");

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
		browser.addProgressListener(new ProgressListener() {
			public void completed(ProgressEvent arg0) {
			}

			public void changed(ProgressEvent arg0) {
				if (piliang == false && arg0.current == arg0.total) {
					Display.getDefault().timerExec(500, new Runnable() {
						public void run() {
							try {
								doShopNumbers(browser.getText());
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
							PageNo1++;
							if (PageNo1 < 100) {
								piliangListenter.widgetSelected(null);
							}else{
								PageNo1=1;
							}
						}
					});
					piliang = true;
				}
			}

			protected void doShopNumbers(String text) throws MalformedURLException {
				Map<String, Object> response = JsonUtils.toMap(JsonFilter
						.filter(text));
				Map<String, Object> data = (Map<String, Object>) response
						.get("data");
				List<Map<String, Object>> pagelist = (List<Map<String, Object>>) data
						.get("pagelist");

				for (Map<String, Object> map : pagelist) {
					String imageByteValue = ImageUtils
							.imgToBase64String(ImageDescriptor.createFromURL(
									new URL(map.get("pictUrl")
											+ "_80x80q90.jpg")).createImage());

					BigDecimal zkPrice = new BigDecimal(map.get("zkPrice")
							.toString());
					BigDecimal realPrice = zkPrice.multiply(new BigDecimal(
							"0.88"));
					BigDecimal calCommission = new BigDecimal(map.get(
							"calCommission").toString());
					String title=(String) map.get("title");
					Long numIid=Long.parseLong(ParamentersUtils.getQueryParams(
							(String) map.get("auctionUrl"), "id"));
					if(NewInstance.get(ItemMng.class).getByNumIid(numIid)!=null){
						continue;
					}
					if(NewInstance.get(ItemMng.class).getByTitle(title)!=null){
						continue;
					}
					NewInstance.get(ItemMng.class).add(
							shopView.getNumber(),
							imageByteValue,
							"高级淘客",
							title,
							(String) map.get("auctionUrl"),
							numIid,
							zkPrice, (Double) map.get("commissionRatePercent"),
							calCommission,
							calCommission.multiply(new BigDecimal("0.1")),
							realPrice);
				}
			}
		});
	}

	private SelectionListener piliangListenter = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			piliang = false;
			String url = searchText.getText();
			url = url.replace("[toPage]", PageNo1 + "");
			alimamaShopUrl.setText(url);
			browser.setUrl(url);
		}
	};

	private boolean piliang = true;
	private Integer PageNo1 = 1;
}
