package com.edaigou3.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.edaigou3.dao.ItemErrorsDao;
import com.edaigou3.entity.ItemErrors;

@Repository
public class ItemErrorsDaoImpl extends JdbcTemplateBaseDao implements
		ItemErrorsDao {

	public void add(ItemErrors itemErrors) {
		super.add(itemErrors);
	}

	public ItemErrors getByItemAndType(Long itemId, String errorType) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from ItemErrors where 1=1");
		if (itemId != null) {
			sqlBuilder.andEqualTo("itemId", itemId);
		}
		if (errorType != null) {
			sqlBuilder.andEqualTo("errorType", errorType);
		}
		return queryForObject(sqlBuilder);
	}

	protected Class<?> getEntityClass() {
		return ItemErrors.class;
	}

	public List<ItemErrors> getByErrorType(String errorType) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from ItemErrors where 1=1");

		if (errorType != null) {
			sqlBuilder.andEqualTo("errorType", errorType);
		}
		return query(sqlBuilder);
	}

	public void delete(Long id) {
		super.delete(id);
	}

	public List<ItemErrors> getByItem(Long itemId) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from ItemErrors where 1=1");
		if (itemId != null) {
			sqlBuilder.andEqualTo("itemId", itemId);
		}
		return query(sqlBuilder);
	}

	public List<ItemErrors> getByErrorType(String[] errorTypes) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from ItemErrors where 1=1");
		if (sqlBuilder.ifNotNull(errorTypes)) {
			sqlBuilder.andIn("errorType", errorTypes);
		}
		return query(sqlBuilder);
	}
}
