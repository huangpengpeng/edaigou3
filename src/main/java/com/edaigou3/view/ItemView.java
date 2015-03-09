package com.edaigou3.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.springframework.stereotype.Component;

import com.edaigou3.entity.Item;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view._0.SearchView;
import com.edaigou3.view.base.BaseViewAdapter;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.MessageBox2;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.IMainView.View;
import com.edaigou3.view.ext.ImageUtils;
import com.edaigou3.view.ext.TbkInfoProvider;

@Component
public class ItemView extends BaseViewAdapter {

	private Text title;
	private Text url;
	private Text originalPrice;
	private Text rebateProportion;
	private Text rebateFee;
	private Text serviceFee;
	private Text realPrice;
	private Text profitFee;
	private Text lowPrice;
	private Text numIid;
	private Button btnloginalimama;
	private Button btnovershot;
	private Button image;
	private Combo channel;
	private Button save;
	private ShopView shopView;

	@Override
	public void createContents(Shell shell) {
		Group grpSp = new Group(shell, SWT.NONE);
		grpSp.setText("商品信息");
		grpSp.setBounds(0, 0, 1094, 100);

		image = new Button(grpSp, SWT.NONE);
		image.setBounds(10, 16, 80, 80);

		Label lblNewLabel = new Label(grpSp, SWT.NONE);
		lblNewLabel.setBounds(107, 19, 54, 22);
		lblNewLabel.setText("淘宝店铺");

		shopView = NewInstance.get(ShopView.class);
		View.addView(grpSp, shopView);
		shopView.setBounds(171, 16, 86, 20);

		Label lblNewLabel_1 = new Label(grpSp, SWT.NONE);
		lblNewLabel_1.setBounds(270, 19, 54, 22);
		lblNewLabel_1.setText("商品渠道 ");

		channel = new Combo(grpSp, SWT.NONE);
		channel.setBounds(330, 16, 86, 20);
		channel.setText("普通淘客");
		channel.add("普通淘客");
		channel.add("高级淘客");

		Label labTitle = new Label(grpSp, SWT.NONE);
		labTitle.setBounds(440, 19, 54, 18);
		labTitle.setText("商品标题");

		title = new Text(grpSp, SWT.BORDER);
		title.setBounds(502, 16, 163, 18);

		Label lblNewLabel_3 = new Label(grpSp, SWT.NONE);
		lblNewLabel_3.setBounds(675, 19, 54, 18);
		lblNewLabel_3.setText("商品地址");

		url = new Text(grpSp, SWT.BORDER);
		url.setBounds(735, 16, 258, 18);

		btnovershot = new Button(grpSp, SWT.NONE);
		btnovershot.setBounds(1012, 14, 72, 22);
		btnovershot.setText("抓取商品");

		Label lblNewLabel_4 = new Label(grpSp, SWT.NONE);
		lblNewLabel_4.setBounds(107, 52, 54, 14);
		lblNewLabel_4.setText("原售价格");

		originalPrice = new Text(grpSp, SWT.BORDER);
		originalPrice.setBounds(171, 48, 86, 18);

		Label labrebateProportion = new Label(grpSp, SWT.NONE);
		labrebateProportion.setBounds(270, 52, 54, 14);
		labrebateProportion.setText("淘返比例");

		rebateProportion = new Text(grpSp, SWT.BORDER);
		rebateProportion.setBounds(330, 48, 86, 18);

		Label labrebateFee = new Label(grpSp, SWT.NONE);
		labrebateFee.setBounds(440, 52, 54, 18);
		labrebateFee.setText("淘返金额");

		rebateFee = new Text(grpSp, SWT.BORDER);
		rebateFee.setBounds(502, 48, 70, 18);

		Label labserviceFee = new Label(grpSp, SWT.NONE);
		labserviceFee.setBounds(595, 52, 54, 14);
		labserviceFee.setText("服务费");

		serviceFee = new Text(grpSp, SWT.BORDER);
		serviceFee.setBounds(655, 47, 70, 18);

		Label lblNewLabel_10 = new Label(grpSp, SWT.NONE);
		lblNewLabel_10.setBounds(108, 78, 54, 18);
		lblNewLabel_10.setText("实际售价");

		realPrice = new Text(grpSp, SWT.BORDER);
		realPrice.setToolTipText("");
		realPrice.setBounds(171, 74, 86, 18);

		Label lblNewLabel_11 = new Label(grpSp, SWT.NONE);
		lblNewLabel_11.setBounds(270, 78, 54, 18);
		lblNewLabel_11.setText("实际利润");

		profitFee = new Text(grpSp, SWT.BORDER);
		profitFee.setBounds(330, 74, 86, 18);

		Label lblNewLabel_12 = new Label(grpSp, SWT.NONE);
		lblNewLabel_12.setBounds(440, 78, 54, 18);
		lblNewLabel_12.setText("最低售价");

		lowPrice = new Text(grpSp, SWT.BORDER);
		lowPrice.setBounds(502, 74, 70, 18);

		Label lblNewLabel_13 = new Label(grpSp, SWT.NONE);
		lblNewLabel_13.setBounds(595, 78, 48, 18);
		lblNewLabel_13.setText("商品编号");

		numIid = new Text(grpSp, SWT.BORDER);
		numIid.setBounds(655, 74, 70, 18);

		save = new Button(grpSp, SWT.NONE);
		save.setBounds(1043, 72, 41, 22);
		save.setText("保存");

		Button btnNewButton_3 = new Button(grpSp, SWT.NONE);
		btnNewButton_3.setBounds(996, 72, 41, 22);
		btnNewButton_3.setText("编辑");

		btnloginalimama = new Button(grpSp, SWT.NONE);
		btnloginalimama.setBounds(797, 72, 80, 22);
		btnloginalimama.setText("登录阿里妈妈");
	}

	@Override
	public void fullContents(Object... values) {
		Item item = (Item) values[0];
		try {
			image.setImage(ImageUtils.base64StringToImg(item.getImageByte()));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		title.setText(item.getTitle());
		if (!StringUtils.equals("高级淘客", channel.getText())) {
			rebateProportion
					.setText(String.valueOf(item.getRebateProportion()));
		} else {
			item.setRebateProportion(Double.valueOf(rebateProportion.getText()));
		}
		item.caleRebate();
		rebateFee.setText(String.valueOf(item.getRebateFee()));
		serviceFee.setText(String.valueOf(item.getServiceFee()));
		originalPrice.setText(String.valueOf(item.getOriginalPrice()));
		item.setRealPrice(item.getOriginalPrice().multiply(
				new BigDecimal("0.88")));
		realPrice.setText(String.valueOf(item.getRealPrice()));
		item.caleProfieFee();
		profitFee.setText(String.valueOf(item.getProfitFee()));
	}

	public Item getViewToModel() {
		try {
			Item item = new Item();
			item.setShopId(shopView.getNumber());
			item.setChannel(channel.getText());
			item.setTitle(title.getText());
			item.setUrl(url.getText());
			item.setOriginalPrice(new BigDecimal(originalPrice.getText()));
			item.setRebateProportion(Double.valueOf(rebateProportion.getText()));
			item.setRebateFee(new BigDecimal(rebateFee.getText()));
			item.setServiceFee(new BigDecimal(serviceFee.getText()));
			item.setRealPrice(new BigDecimal(realPrice.getText()));
			item.setProfitFee(new BigDecimal(profitFee.getText()));
			if (StringUtils.isNotBlank(lowPrice.getText())) {
				item.setLowPrice(new BigDecimal(lowPrice.getText()));
			}
			if (StringUtils.isNotBlank(numIid.getText())) {
				item.setNumIid(Long.valueOf(numIid.getText()));
			}
			item.setImageByte(ImageUtils.imgToBase64String(image.getImage()));
			return item;
		} catch (Exception e) {
			return null;
		}
	}

	public void clearText() {
		title.setText("");
		url.setText("");
		originalPrice.setText("");
		if (!StringUtils.equals("高级淘客", channel.getText())) {
			rebateProportion.setText("");
		}
		rebateFee.setText("");
		serviceFee.setText("");
		realPrice.setText("");
		profitFee.setText("");
		lowPrice.setText("");
		numIid.setText("");
		image.setImage(null);
	}

	@Override
	public void createListenter() {
		btnloginalimama.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				NewInstance.get(FolderView.class).selection(
						NewInstance.get(FolderView.class).get_新品发布());
				NewInstance.get(BrowserView.class).doRequest(
						"http://www.alimama.com/member/login.htm");
			}
		});
		btnovershot.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				NewInstance.get(FolderView.class).selection(
						NewInstance.get(FolderView.class).get_新增商品());
				final String urlValue = url.getText();
				NewInstance.get(BrowserView.class).doRequest(
						new IRequestProvider() {
							public String getRequestUrl(IBrowserView browserView) {
								StringBuffer buffer = new StringBuffer(
										"http://pub.alimama.com/pubauc/searchAuctionList.json?q=");
								try {
									buffer.append(URLEncoder.encode(urlValue,
											"UTF-8"));
								} catch (UnsupportedEncodingException e) {
								}
								return buffer.toString();
							}
						}, NewInstance.get(TbkInfoProvider.class), 0);
			}
		});
		save.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				Item item = getViewToModel();
				if (item == null) {
					MessageBox2.showErrorMsg("参数转换错误");
					return;
				}
				if (item.getShopId() == null) {
					MessageBox2.showErrorMsg("请选择店铺");
					return;
				}
				if (StringUtils.isBlank(item.getChannel())) {
					MessageBox2.showErrorMsg("请选择渠道");
					return;
				}
				if (StringUtils.isBlank(item.getTitle())) {
					MessageBox2.showErrorMsg("请输入标题");
					return;
				}
				if (StringUtils.isBlank(item.getUrl())) {
					MessageBox2.showErrorMsg("请输入地址");
					return;
				}
				if (item.getOriginalPrice() == null) {
					MessageBox2.showErrorMsg("请输入原价");
					return;
				}
				if (item.getRebateProportion() == null) {
					MessageBox2.showErrorMsg("请输入淘客返点比率");
					return;
				}
				if (item.getRebateFee() == null) {
					MessageBox2.showErrorMsg("请输入返点金额");
					return;
				}
				if (item.getRealPrice() == null) {
					MessageBox2.showErrorMsg("请输入实际销售价格");
					return;
				}
				NewInstance.get(ItemMng.class).add(item.getShopId(),
						item.getImageByte(), item.getChannel(),
						item.getTitle(), item.getUrl(), item.getTbkNumIid(),
						item.getOriginalPrice(), item.getRebateProportion(),
						item.getRebateFee(), item.getServiceFee(),
						item.getRealPrice());
				clearText();
				NewInstance.get(SearchView.class).query(1);
			}
		});
	}
}
