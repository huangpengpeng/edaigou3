
package com.edaigou3.manager;

import java.util.List;

import com.edaigou3.entity.ItemErrors;

public interface ItemErrorsMng {

	void add(Long itemId,String errorType);

	ItemErrors getByItemAndType(Long itemId, String errorType);
	
	List<ItemErrors>  getByErrorType(String errorType);
	
	List<ItemErrors>  getByErrorType(String [] errorTypes);
	
	List<ItemErrors>  getByItem(Long itemId);
	
	void delete(Long id);
}
