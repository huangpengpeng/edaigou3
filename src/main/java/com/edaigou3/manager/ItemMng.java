package com.edaigou3.manager;

import java.math.BigDecimal;

import com.common.jdbc.page.Pagination;

public interface ItemMng {

	void add(Long shopId, String imageByte, String channel, String title,
			String url, Long tbkNumIid, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice);

	public Pagination getPage(Long shopId, String errorType, String title,
			Integer pageNo);
}
