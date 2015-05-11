package com.edaigou3.dao;

import java.util.List;

import com.edaigou3.entity.ItemErrors;

public interface ItemErrorsDao {
	
	void add(ItemErrors itemErrors);
	
	ItemErrors getByItemAndType(Long itemId,String errorType);
	
	 List<ItemErrors> getByErrorType(String errorType);
	 
	 List<ItemErrors> getByItem(Long itemId);
	 
	 void delete(Long id);
}
