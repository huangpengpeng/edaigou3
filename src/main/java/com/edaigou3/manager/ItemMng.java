package com.edaigou3.manager;

import java.math.BigDecimal;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;

public interface ItemMng {

	void add(Long shopId, String imageByte, String channel, String title,
			String url, Long tbkNumIid, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice);

	public Pagination getPage(Long shopId, String errorType, String title,
			Integer pageNo);

	void delete(Long id);

	Item getByTbkNumIid(Long tbkNumIid);

	Item getByTitle(String title);

	Item getByNumIid(Long numIid);
}
