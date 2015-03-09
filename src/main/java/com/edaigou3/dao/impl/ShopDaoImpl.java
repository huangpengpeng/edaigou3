package com.edaigou3.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.edaigou3.dao.ShopDao;
import com.edaigou3.entity.Shop;

@Repository
public class ShopDaoImpl extends JdbcTemplateBaseDao implements ShopDao {

	public List<Shop> query() {
		return query(new SqlBuilder("select * from Shop where 1=1"));
	}

	public Shop getByNick(String nick) {
		SqlBuilder sqlBuilder=new SqlBuilder("select * from Shop where 1=1");
		if(nick!=null){
			sqlBuilder.andEqualTo("nick", nick);
		}
		return queryForObject(sqlBuilder);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Shop.class;
	}

	public Shop get(Long id) {
		return super.queryForObject(id);
	}
}
