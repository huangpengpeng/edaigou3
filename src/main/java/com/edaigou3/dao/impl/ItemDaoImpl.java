package com.edaigou3.dao.impl;

import java.math.BigDecimal;

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

	public Pagination getPage(Long shopId, Long[] ids, String title,
			Integer pageNo,Integer pageSize) {
		SqlBuilder sqlBuilder = new SqlBuilder("select * from Item where 1=1");
		if (shopId != null) {
			sqlBuilder.andEqualTo("shopId", shopId);
		}
		if (sqlBuilder.ifNotNull(title)) {
			sqlBuilder.andEqualTo("title", title);
		}
		return super.getPage(sqlBuilder, pageNo == null ? 1 : pageNo, pageSize);
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

	public Item get(Long id) {
		return super.queryForObject(id);
	}

	public void update(Long id, Long shopId, String imageByte, String channel,
			String title, BigDecimal originalPrice, Double rebateProportion,
			BigDecimal rebateFee, BigDecimal serviceFee, BigDecimal realPrice,
			BigDecimal profitFee, BigDecimal lowPrice, Long numIid) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"update Item set gmtModify=current_timestamp()");
		if (sqlBuilder.ifNotNull(shopId)) {
			sqlBuilder.set("shopId", shopId);
		}
		if (sqlBuilder.ifNotNull(imageByte)) {
			sqlBuilder.set("imageByte", imageByte);
		}
		if (sqlBuilder.ifNotNull(channel)) {
			sqlBuilder.set("channel", channel);
		}
		if (sqlBuilder.ifNotNull(title)) {
			sqlBuilder.set("title", title);
		}
		if (sqlBuilder.ifNotNull(originalPrice)) {
			sqlBuilder.set("originalPrice", originalPrice);
		}
		if (sqlBuilder.ifNotNull(rebateProportion)) {
			sqlBuilder.set("rebateProportion", rebateProportion);
		}
		if (sqlBuilder.ifNotNull(rebateFee)) {
			sqlBuilder.set("rebateFee", rebateFee);
		}
		if (sqlBuilder.ifNotNull(serviceFee)) {
			sqlBuilder.set("serviceFee", serviceFee);
		}
		if (sqlBuilder.ifNotNull(realPrice)) {
			sqlBuilder.set("realPrice", realPrice);
		}
		if (sqlBuilder.ifNotNull(profitFee)) {
			sqlBuilder.set("profitFee", profitFee);
		}
		sqlBuilder.set("lowPrice", lowPrice);
		sqlBuilder.set("numIid", numIid);
		super.update(id, sqlBuilder);
	}
}
