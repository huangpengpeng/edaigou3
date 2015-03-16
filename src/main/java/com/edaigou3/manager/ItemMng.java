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

	public Pagination getPage(Long shopId, Long[] ids, String title,String status,
			Integer pageNo, Integer pageSize);

	List<Item> query(String status);

	public void checkErrors(Long id);

	void delete(Long id);

	Item getByTbkNumIid(Long tbkNumIid);

	Item getByTitle(String title);

	Item getByNumIid(Long numIid);

	Item get(Long id);
}
