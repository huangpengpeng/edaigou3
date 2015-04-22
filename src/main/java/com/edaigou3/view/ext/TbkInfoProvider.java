package com.edaigou3.view.ext;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;

import com.common.jdbc.page.Pagination;
import com.common.util.JsonUtils;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.Item.ItemChannel;
import com.edaigou3.entity.ItemErrors.ItemErrorsType;
import com.edaigou3.manager.ItemErrorsMng;
import com.edaigou3.manager.ItemMng;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IBrowserView.IRequestProvider;
import com.edaigou3.view.base.IMainView.JsonFilter;
import com.edaigou3.view.base.IMainView.MessageBox2;
import com.edaigou3.view.base.IMainView.NewInstance;
import com.edaigou3.view.base.ISearchView;

public class TbkInfoProvider implements IOperatorProvider {

	private static Item item;

	private Listener listener;

	public TbkInfoProvider(Listener listener) {
		this.listener = listener;
	}

	public void completed(IBrowserView browserView) {
		try {
			Map<String, Object> result = JsonUtils.toMap(JsonFilter
					.filter(browserView.getResponseText()));
			if (!(Boolean) result.get("ok")) {
				MessageBox2.showErrorMsg("获取失败,检查参数是否正确", null);
				return;
			}

			Item itemModel = toModel(result);

			if (listener == null) {

				if (itemModel == null) {
					MessageBox2.showErrorMsg("此商品没有淘客", null);
					return;
				}

				NewInstance.get(ItemView.class).fullContents(itemModel);
			}

			// 500豪秒后执行保存 在执行下一条
			if (listener != null) {
				if (itemModel == null) {
					NewInstance.get(ItemErrorsMng.class).add(item.getId(),
							ItemErrorsType.淘客错误.toString());
					return;
				}

				if (!ItemChannel.高级淘客.toString().equals(item.getChannel())) {
					NewInstance.get(ItemView.class).setRebateProportion(
							itemModel.getRebateProportion());
				}

				NewInstance.get(ItemMng.class).update(item.getId(), null,
						itemModel.getOriginalPrice(), null, null);

				NewInstance.get(ItemView.class).setTitle(itemModel.getTitle());

				Display.getDefault().timerExec(1000, new Runnable() {
					public void run() {
						NewInstance.get(ItemView.class).updateSubmit();
						listener.handleEvent(null);
					}
				});
			}
		} catch (Exception e) {
			MessageBox2.showErrorMsg("获取失败,检查是否登录", e);
		}
	}

	/**
	 * 根据 tbk 返回信息 拼装 item 模型
	 * 
	 * @param result
	 * @return
	 * @throws MalformedURLException
	 */
	@SuppressWarnings("unchecked")
	protected Item toModel(Map<String, Object> result)
			throws MalformedURLException {
		if ((Integer) ((Map<String, Object>) ((Map<String, Object>) result
				.get("data")).get("paginator")).get("length") == 0) {
			return null;
		}
		List<Map<String, Object>> pageList = (List<Map<String, Object>>) ((Map<String, Object>) result
				.get("data")).get("pagelist");
		Map<String, Object> model = pageList.get(0);
		String imageByteValue = ImageUtils.imgToBase64String(ImageDescriptor
				.createFromURL(new URL(model.get("pictUrl") + "_80x80q90.jpg"))
				.createImage());
		Double rebateProportion = (Double) model.get("commissionRate") / 100;
		BigDecimal rebateFee = BigDecimal.valueOf((Double) model
				.get("calCommission"));
		String title = (String) model.get("title");
		String auctionUrl = (String) model.get("auctionUrl");
		String tbkNumIid = (String) model.get("userNumberId");
		BigDecimal originalPrice = BigDecimal.valueOf(((Double) model
				.get("zkPrice")).intValue());
		return new Item(null, imageByteValue, null, title, auctionUrl,
				Long.valueOf(tbkNumIid), originalPrice, rebateProportion,
				rebateFee, rebateFee.multiply(new BigDecimal("0.1")), null,
				null, null, null, null, null, null, null);
	}

	public static class RequestProvider implements IRequestProvider {

		private Integer pageNo;
		private ISearchView searchView;

		public RequestProvider(Integer pageNo, ISearchView searchView) {
			this.pageNo = pageNo;
			this.searchView = searchView;
		}

		public String getRequestUrl(IBrowserView browserView) {
			Pagination page = searchView.query(pageNo++);
			item = (Item) page.getList().get(0);
			StringBuffer buffer = new StringBuffer(
					"http://pub.alimama.com/pubauc/searchAuctionList.json?q=");
			try {
				buffer.append(URLEncoder.encode(item.getUrl(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
			}
			NewInstance.get(ItemView.class).fullContents(item, true);
			return buffer.toString();
		}
	}
}
