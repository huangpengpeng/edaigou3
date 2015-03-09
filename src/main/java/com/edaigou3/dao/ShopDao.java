package com.edaigou3.dao;

import java.util.List;

import com.edaigou3.entity.Shop;

public interface ShopDao {

	public	List<Shop> query();
	
	public Shop get(Long id);
	
	public Shop getByNick(String nick);
}
