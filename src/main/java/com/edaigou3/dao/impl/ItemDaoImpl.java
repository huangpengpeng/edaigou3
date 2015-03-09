package com.edaigou3.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.common.jdbc.page.Pagination;
import com.edaigou3.dao.ItemDao;
import com.edaigou3.entity.Item;

@Repository
public class ItemDaoImpl extends JdbcTemplateBaseDao implements ItemDao {

	public void add(Item item) {
		super.add(item);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Item.class;
	}

	public Pagination getPage(Long shopId, String errorType, String title,
			Integer pageNo) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Item where 1=1");
		if (shopId != null) {
			sqlBuilder.andEqualTo("shopId", shopId);
		}
		if (errorType != null) {
			sqlBuilder.andEqualTo("errorType", errorType);
		}
		if (title != null) {
			sqlBuilder.andEqualTo("title", title);
		}
		return super.getPage(sqlBuilder, pageNo == null ? 1 : pageNo, 10);
	}

	public void delete(Long id) {
		super.delete(id);
	}

	public Item getByTbkNumIid(Long tbkNumIid) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Item where 1=1");
		if (tbkNumIid != null) {
			sqlBuilder.andEqualTo("tbkNumIid", tbkNumIid);
		}
		return queryForObject(sqlBuilder);
	}

	public Item getByTitle(String title) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Item where 1=1");
		if (title != null) {
			sqlBuilder.andEqualTo("title", title);
		}
		return queryForObject(sqlBuilder);
	}

	public Item getByNumIid(Long numIid) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Item where 1=1");
		if (numIid != null) {
			sqlBuilder.andEqualTo("numIid", numIid);
		}
		return queryForObject(sqlBuilder);
	}
}