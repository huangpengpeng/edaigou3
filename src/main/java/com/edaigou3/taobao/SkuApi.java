package com.edaigou3.taobao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemSkuAddRequest;
import com.taobao.api.request.ItemSkuUpdateRequest;
import com.taobao.api.request.ItemUpdateRequest;
import com.taobao.api.response.ItemSkuAddResponse;
import com.taobao.api.response.ItemSkuUpdateResponse;
import com.taobao.api.response.ItemUpdateResponse;

/**
 * 
 * 
 * 描述:SKUapi
 *
 * @author liyixing
 * @version 1.0
 * @since 2015年8月17日 下午12:32:34
 */
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
		/**
		 * 属性值标题：aa:aa=白色
		 */
		private Map<String, String> propertiesTitleByMap = new HashMap<String, String>();

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

		public Map<String, String> getPropertiesTitleByMap() {
			return propertiesTitleByMap;
		}

		public void setPropertiesTitleByMap(
				Map<String, String> propertiesTitleByMap) {
			this.propertiesTitleByMap = propertiesTitleByMap;
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
	public List<Sku> getSkuProperties(Long id) throws Exception {
		String content = null;
		content = requestHttp("http://hws.m.taobao.com/cache/wdetail/5.0/?id="
				+ id);

		ObjectMapper objectMapper = new ObjectMapper();
		List<Sku> skus = new ArrayList<Sku>();

		/**
		 * 解析结果
		 */
		Object ret = objectMapper.readValue(content, Map.class);
		// 读取SKU属性组，每个SKU最终会生成一个唯一的ID，获取这个ID
		Map<String, Object> ppathIdmap = (Map<String, Object>) PropertyUtils
				.getProperty(ret, "data.skuModel.ppathIdmap");
		// sku属性值
		List<Map<String, Object>> skuProps = (List<Map<String, Object>>) PropertyUtils
				.getProperty(ret, "data.skuModel.skuProps");
		Map<String, String> skuPropsByProperties = new HashMap<String, String>();
		// 属性值组合成xxx:xxx=黑色格式

		for (Map<String, Object> skuProp : skuProps) {
			String properties = skuProp.get("propId").toString() + ":";
			List<Map<String, Object>> values = (List<Map<String, Object>>) skuProp
					.get("values");

			for (Map<String, Object> value : values) {
				skuPropsByProperties.put(properties + value.get("valueId"),
						value.get("name").toString());
			}
		}

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
			sku.setPropertiesTitleByMap(skuPropsByProperties);
			// 售价
			skus.add(sku);
		}

		return skus;
	}

	/**
	 * 描述:http请求
	 * 
	 * @param id
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @author liyixing 2015年8月16日 下午3:52:35
	 */

	private static final String requestHttp(String url_)
			throws MalformedURLException, IOException {
		String content;
		// 稳定移动端API，来获取销售属性
		StringBuffer html = new StringBuffer();
		URL url = new URL(url_);
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
		return content;
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

		ItemSkuUpdateResponse itemSkuUpdateResponse = taobaoClient.execute(req,
				sessionKey);

		// 确保在对方设置过

		if (itemSkuUpdateResponse.getErrorCode() != null) {
			throw new RuntimeException(itemSkuUpdateResponse.getMsg());
		}
	}

	/**
	 * 
	 * 描述:更新SKU信息
	 * 
	 * @param sku
	 * @author liyixing 2015年8月15日 下午4:54:02
	 */
	public void add(Sku sku, String sessionKey) throws Exception {
		ItemSkuAddRequest req = new ItemSkuAddRequest();
		req.setNumIid(sku.getNumId());
		req.setProperties(sku.getProperties());
		req.setQuantity(sku.getQuantity());

		req.setPrice(sku.getPrice().toString());
		ItemSkuAddResponse itemSkuAddResponse = taobaoClient.execute(req,
				sessionKey);

		if (itemSkuAddResponse.getErrorCode() != null) {
			throw new RuntimeException(itemSkuAddResponse.getMsg());
		}
	}

	/**
	 * 
	 * 描述:设置SKU的titile
	 * 
	 * @param sku
	 * @author liyixing 2015年8月17日 上午10:52:41
	 */
	public void updateSkuAlias(Sku sku, String sessionKey) throws Exception {
		boolean first = true;
		String propertyAlias = "";

		for (Map.Entry<String, String> propertyTitle : sku
				.getPropertiesTitleByMap().entrySet()) {
			if (!first) {
				propertyAlias = propertyAlias + ";";
			}

			propertyAlias = propertyAlias + propertyTitle.getKey() + ":"
					+ propertyTitle.getValue();
			first = false;
		}

		ItemUpdateRequest req = new ItemUpdateRequest();

		req.setNumIid(sku.getNumId());
		req.setPropertyAlias(propertyAlias);

		ItemUpdateResponse response = taobaoClient.execute(req, sessionKey);

		if (response.getErrorCode() != null) {
			throw new RuntimeException(response.getMsg());
		}

	}

	public static void main(String[] args) throws Exception {
	}
}
