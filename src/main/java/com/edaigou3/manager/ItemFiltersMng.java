package com.edaigou3.manager;

import java.util.List;

import com.edaigou3.entity.ItemFilters;

public interface ItemFiltersMng {

	List<ItemFilters> query();

	ItemFilters get(Long id);

	void add(Long pNumIid, String nick);

	void delete(Long id);
}
