package com.edaigou3.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaigou3.dao.ItemFiltersDao;
import com.edaigou3.entity.ItemFilters;
import com.edaigou3.manager.ItemFiltersMng;

@Transactional
@Service
public class ItemFiltersMngImpl implements ItemFiltersMng {

	public List<ItemFilters> query() {
		return dao.query();
	}

	public ItemFilters get(Long id) {
		return dao.get(id);
	}

	public void add(Long pNumIid, String nick) {
		ItemFilters itemFilters = new ItemFilters(pNumIid, nick);
		itemFilters.init();
		dao.add(itemFilters);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	@Autowired
	private ItemFiltersDao dao;
}
