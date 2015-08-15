package com.edaigou3.taobao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemSkuUpdateRequest;

@Component
public class SkuApi {
	@Autowired
	private TaobaoClient taobaoClient;

	/**
	 * 
	 * 
	 * 描述:淘宝API的SKUapi需要的参数
	 *
	 * @author liyixing
	 * @version 1.0
	 * @since 2015年8月15日 下午4:01:56
	 */
	public static class Sku {
		/**
		 * 商品ID
		 */
		private Long numId;
		/**
		 * SKU ID
		 */
		private String id;
		/**
		 * 属性值：20509:28316;1627207:28321
		 */
		private String properties;
		/**
		 * 数量
		 */
		private Long quantity;
		/**
		 * 销售价格
		 */
		private BigDecimal price;

		public String getProperties() {
			return properties;
		}

		public void setProperties(String properties) {
			this.properties = properties;
		}

		public Long getQuantity() {
			return quantity;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Long getNumId() {
			return numId;
		}

		public void setNumId(Long numId) {
			this.numId = numId;
		}
	}

	/**
	 * 
	 * 描述:将SKU转化成淘宝API需要的properties属性
	 * 
	 * @param content
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author liyixing 2015年8月15日 下午3:43:05
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static List<Sku> getSkuProperties(Long id) throws Exception {
		String content = null;
		// 稳定移动端API，来获取销售属性
		StringBuffer html = new StringBuffer();
		URL url = new URL("http://hws.m.taobao.com/cache/wdetail/5.0/?id=" + id);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStreamReader isr = new InputStreamReader(conn.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		String temp;
		while ((temp = br.readLine()) != null) {
			html.append(temp).append("\n");
		}
		br.close();
		isr.close();
		content = html.toString();

		ObjectMapper objectMapper = new ObjectMapper();
		List<Sku> skus = new ArrayList<Sku>();

		/**
		 * 解析结果
		 */
		Object ret = objectMapper.readValue(content, Map.class);
		// 读取SKU属性组，每个SKU最终会生成一个唯一的ID，获取这个ID
		Map<String, Object> ppathIdmap = (Map<String, Object>) PropertyUtils
				.getProperty(ret, "data.skuModel.ppathIdmap");
		// api内部
		List<Object> apiStack = (List<Object>) PropertyUtils.getProperty(ret,
				"data.apiStack");
		// 内部堆栈第一次解析出来是字符串，需要再次解析一次
		Map<String, Object> apiStackVal = objectMapper
				.readValue(((Map<String, Object>) apiStack.get(0)).get("value")
						.toString(), Map.class);
		// sku属性详情
		Map<String, Object> skusMap = (Map<String, Object>) PropertyUtils
				.getProperty(apiStackVal, "data.skuModel.skus");
		for (Map.Entry<String, Object> ppathId : ppathIdmap.entrySet()) {
			Sku sku = new Sku();

			sku.setId(ppathId.getValue().toString());
			sku.setProperties(ppathId.getKey());
			// 回去当前SKU详情
			Map<String, Object> values = (Map<String, Object>) skusMap.get(sku
					.getId());
			// 库存
			sku.setQuantity(Long.valueOf(values.get("quantity").toString()));
			// 价格
			sku.setPrice(new BigDecimal(
					((List<Map<String, Object>>) PropertyUtils.getProperty(
							values, "priceUnits")).get(0).get("price")
							.toString()));
			sku.setNumId(id);
			// 售价
			skus.add(sku);
			// 解析价格和数量
		}

		return skus;
	}

	/**
	 * 
	 * 描述:删除SKU，并不直接调用删除，而是将库存改成0
	 * 
	 * @param sku
	 * @author liyixing 2015年8月15日 下午4:45:00
	 * @throws Exception
	 */
	public void deleteSku(Sku sku, String sessionKey) throws Exception {
		ItemSkuUpdateRequest req = new ItemSkuUpdateRequest();
		req.setNumIid(sku.getNumId());
		req.setProperties(sku.getProperties());
		req.setQuantity(0l);
		taobaoClient.execute(req, sessionKey);
	}

	public void update(Sku sku, String sessionKey) throws Exception {
		update(sku, sessionKey, false);
	}

	/**
	 * 
	 * 描述:更新SKU信息
	 * 
	 * @param sku
	 * @author liyixing 2015年8月15日 下午4:54:02
	 */
	public void update(Sku sku, String sessionKey, boolean updatePrice)
			throws Exception {
		ItemSkuUpdateRequest req = new ItemSkuUpdateRequest();
		req.setNumIid(sku.getNumId());
		req.setProperties(sku.getProperties());
		req.setQuantity(sku.getQuantity());

		if (updatePrice) {
			req.setPrice(sku.getPrice().toString());
		}
		
		taobaoClient.execute(req, sessionKey);
	}

	public static void main(String[] args) throws Exception {
		List<Sku> skus = getSkuProperties(43652054496l);

		System.out.println("获取到的SKUS是" + skus.size());
	}
}
