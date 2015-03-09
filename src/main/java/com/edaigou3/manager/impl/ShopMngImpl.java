package com.edaigou3.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaigou3.dao.ShopDao;
import com.edaigou3.entity.Shop;
import com.edaigou3.manager.ShopMng;

@Transactional
@Service
public class ShopMngImpl implements ShopMng{

	public List<Shop> query() {
		return dao.query();
	}
	
	public Shop getByNick(String nick) {
		return dao.getByNick(nick);
	}

	public Shop get(Long id) {
		return dao.get(id);
	}
	
	@Autowired
	private ShopDao dao;
}
