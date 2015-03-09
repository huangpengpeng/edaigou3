package com.edaigou3.manager.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.jdbc.page.Pagination;
import com.edaigou3.dao.ItemDao;
import com.edaigou3.entity.Item;
import com.edaigou3.manager.ItemMng;

@Transactional
@Service
public class ItemMngImpl implements ItemMng {

	public void add(Long shopId, String imageByte, String channel,
			String title, String url, Long tbkNumIid, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice) {
		Item item = new Item(shopId, imageByte, channel, title, url, tbkNumIid,
				originalPrice, rebateProportion, rebateFee, serviceFee,
				realPrice, null, null, null, null, null, null);
		item.caleProfieFee();
		item.init();
		dao.add(item);
	}


	public Pagination getPage(Long shopId, String errorType, String title,
			Integer pageNo) {
		return dao.getPage(shopId, errorType, title, pageNo);
	}
	
	public void delete(Long id) {
		dao.delete(id);
	}


	public Item getByTbkNumIid(Long tbkNumIid) {
		return dao.getByTbkNumIid(tbkNumIid);
	}


	public Item getByTitle(String title) {
		return dao.getByTitle(title);
	}


	public Item getByNumIid(Long numIid) {
		return dao.getByNumIid(numIid);
	}
	
	@Autowired
	private ItemDao dao;

}