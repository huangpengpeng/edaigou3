package com.edaigou3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.edaigou3.dao.ItemFiltersDao;
import com.edaigou3.entity.ItemFilters;

@Repository
public class ItemFiltersDaoImpl extends JdbcTemplateBaseDao implements ItemFiltersDao{

	public List<ItemFilters> query() {
		return super
				.query(new SqlBuilder("select * from ItemFilters where 1=1"));
	}

	public void add(ItemFilters itemFilters) {
		super.add(itemFilters);
	}

	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return ItemFilters.class;
	}

	public ItemFilters get(Long id) {
		return super.queryForObject(id);
	}
}
