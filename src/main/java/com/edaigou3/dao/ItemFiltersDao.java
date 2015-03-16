package com.edaigou3.dao;

import java.util.List;

import com.edaigou3.entity.ItemFilters;

public interface ItemFiltersDao {

	List<ItemFilters> query();
	
	ItemFilters get(Long id);
	
	void add(ItemFilters itemFilters);
	
	void delete(Long id);
}
