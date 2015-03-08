package com.edaigou3.view.ext;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.springframework.stereotype.Component;

import com.common.util.JsonUtils;
import com.edaigou3.entity.Item;
import com.edaigou3.view.ItemView;
import com.edaigou3.view.base.IBrowserView;
import com.edaigou3.view.base.IBrowserView.IOperatorProvider;
import com.edaigou3.view.base.IMainView.JsonFilter;
import com.edaigou3.view.base.IMainView.MessageBox2;
import com.edaigou3.view.base.IMainView.NewInstance;

@Component
public class TbkInfoProvider implements IOperatorProvider {

	private Item item;

	public void completed(IBrowserView browserView) {
		try {
			Map<String, Object> result = JsonUtils.toMap(JsonFilter
					.filter(browserView.getResponseText()));
			if (!(Boolean) result.get("ok")) {
				MessageBox2.showErrorMsg("获取失败,检查参数是否正确", null);
				return;
			}
			Item item = toModel(result);
			if (item == null) {
				MessageBox2.showErrorMsg("此商品没有淘客", null);
				return;
			}
			NewInstance.get(ItemView.class).fullContents(item);
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
		item = new Item(null, imageByteValue, null, title, auctionUrl,
				Long.valueOf(tbkNumIid), originalPrice, rebateProportion,
				rebateFee, rebateFee.multiply(new BigDecimal("0.1")), null,
				null, null, null, null, null, null);
		return item;
	}

	public <T> T getObject() {
		return (T) item;
	}
}
