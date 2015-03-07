package com.edaigou3.view;

import java.io.IOException;

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
import com.edaigou3.view.base.BaseViewAdapter;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.ext.ImageUtils;
import com.edaigou3.view.ext.TbkInfoProvider;

@Component
public class ItemView extends BaseViewAdapter {

	private Text title;
	private Text url;
	private Text text_2;
	private Text rebateProportion;
	private Text rebateFee;
	private Text serviceFee;
	private Text text_8;
	private Text text_9;
	private Text text_10;
	private Text text_11;
	private Button btnloginalimama;
	private Button btnovershot;
	private Button image;

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

		Combo combo = new Combo(grpSp, SWT.NONE);
		combo.setBounds(171, 16, 86, 20);

		Label lblNewLabel_1 = new Label(grpSp, SWT.NONE);
		lblNewLabel_1.setBounds(270, 19, 54, 22);
		lblNewLabel_1.setText("商品渠道 ");

		Combo combo_1 = new Combo(grpSp, SWT.NONE);
		combo_1.setBounds(330, 16, 86, 20);

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

		text_2 = new Text(grpSp, SWT.BORDER);
		text_2.setBounds(171, 48, 86, 18);

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

		text_8 = new Text(grpSp, SWT.BORDER);
		text_8.setToolTipText("");
		text_8.setBounds(171, 74, 86, 18);

		Label lblNewLabel_11 = new Label(grpSp, SWT.NONE);
		lblNewLabel_11.setBounds(270, 78, 54, 18);
		lblNewLabel_11.setText("实际利润");

		text_9 = new Text(grpSp, SWT.BORDER);
		text_9.setBounds(330, 74, 86, 18);

		Label lblNewLabel_12 = new Label(grpSp, SWT.NONE);
		lblNewLabel_12.setBounds(440, 78, 54, 18);
		lblNewLabel_12.setText("最低售价");

		text_10 = new Text(grpSp, SWT.BORDER);
		text_10.setBounds(502, 74, 70, 18);

		Label lblNewLabel_13 = new Label(grpSp, SWT.NONE);
		lblNewLabel_13.setBounds(595, 78, 48, 18);
		lblNewLabel_13.setText("商品编号");

		text_11 = new Text(grpSp, SWT.BORDER);
		text_11.setBounds(655, 74, 70, 18);

		Button btnNewButton_2 = new Button(grpSp, SWT.NONE);
		btnNewButton_2.setBounds(1043, 72, 41, 22);
		btnNewButton_2.setText("保存");

		Button btnNewButton_3 = new Button(grpSp, SWT.NONE);
		btnNewButton_3.setBounds(996, 72, 41, 22);
		btnNewButton_3.setText("编辑");

		btnloginalimama = new Button(grpSp, SWT.NONE);
		btnloginalimama.setBounds(797, 72, 80, 22);
		btnloginalimama.setText("登录阿里妈妈");
	}

	@Override
	public void fullContents(Object... values) throws IOException {
		Item item = (Item) values[0];
		image.setImage(ImageUtils.base64StringToImg(item.getImageByte()));
		title.setText(item.getTitle());
		rebateProportion.setText(String.valueOf(item.getRebateProportion()));
		rebateFee.setText(String.valueOf(item.getRebateFee()));
		serviceFee.setText(String.valueOf(item.getServiceFee()));
	}

	@Override
	public void createListenter() {
		btnloginalimama.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				NewInstance.get(BrowserView.class).doRequest(
						"http://www.alimama.com/member/login.htm");
			}
		});
		btnovershot.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				final String urlValue = url.getText();
				NewInstance.get(BrowserView.class).doRequest(
						new IRequestProvider() {
							public String getRequestUrl(IBrowserView browserView) {
								StringBuffer buffer = new StringBuffer(
										"http://pub.alimama.com/pubauc/searchAuctionList.json?q=");
								buffer.append(urlValue);
								return buffer.toString();
							}
						}, NewInstance.get(TbkInfoProvider.class), 0);
			}
		});
	}
}
