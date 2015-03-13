package com.edaigou3.dao;

import java.math.BigDecimal;
import java.util.List;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;

public interface ItemDao {

	void add(Item item);
	
	void update(Long id, Long shopId, String imageByte, String channel,
			String title,  BigDecimal marketPrice,BigDecimal originalPrice, Double rebateProportion,
			BigDecimal rebateFee, BigDecimal serviceFee, BigDecimal realPrice,
			BigDecimal profitFee, BigDecimal lowPrice, Long numIid);
	
	Pagination getPage(Long shopId, Long [] ids, String title,
			Integer pageNo,Integer pageSize) ;
	
	void delete(Long id);
	
	Item getByTbkNumIid(Long tbkNumIid);
	
	Item getByTitle(String title );
	
	Item getByNumIid(Long numIid);
	
	Item get(Long id);
	
	 List<Item> query(String status);
}
