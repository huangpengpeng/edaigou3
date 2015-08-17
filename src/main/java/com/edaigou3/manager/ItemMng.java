package com.edaigou3.manager;

import java.math.BigDecimal;
import java.util.List;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;

public interface ItemMng {

	void add(Long shopId, String imageByte, String channel, String title,
			String url, Long tbkNumIid, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice);

	void update(Long id, Long shopId, String imageByte, String channel,
			String title, BigDecimal originalPrice, Double rebateProportion,
			BigDecimal rebateFee, BigDecimal serviceFee, BigDecimal realPrice,
			BigDecimal profitFee, BigDecimal lowPrice, Long numIid);

	void update(Long id, Double freshRebateProportion,
			BigDecimal freshOriginalPrice, String freshTitle,
			BigDecimal freshRealPrice);

	void update(Long id, String status);

	public Pagination getPage(Long shopId, Long[] ids, String title,
			String status, Integer pageNo, Integer pageSize, String sort);

	List<Item> query(String status);

	public void checkErrors(Long id);

	void delete(Long id);

	Item getByTbkNumIid(Long tbkNumIid);

	Item getByTitle(String title);

	Item getByNumIid(Long numIid);

	Item get(Long id);

	/**
	 * 
	 * 描述:从淘宝客更新数据到淘宝
	 * 
	 * @param shopId
	 * @param sessionKey
	 * @author liyixing 2015年8月15日 下午5:54:11
	 * @return 
	 */
	String syncItem(Long shopId, String sessionKey,boolean updatePrice);
}
