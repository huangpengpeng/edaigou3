package com.edaigou3.dao;

import com.common.jdbc.page.Pagination;
import com.edaigou3.entity.Item;

public interface ItemDao {

	void add(Item item);
	
	Pagination getPage(Long shopId, String errorType, String title,
			Integer pageNo) ;
}
