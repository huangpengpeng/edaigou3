package com.edaigou3.manager;

import java.util.List;

import com.edaigou3.entity.Shop;

public interface ShopMng {

	List<Shop> query();
	
	Shop getByNick(String nick);
	
	Shop get(Long id);
}
