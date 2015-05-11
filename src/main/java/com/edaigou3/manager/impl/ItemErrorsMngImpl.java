package com.edaigou3.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edaigou3.dao.ItemErrorsDao;
import com.edaigou3.entity.ItemErrors;
import com.edaigou3.manager.ItemErrorsMng;

@Transactional
@Service
public class ItemErrorsMngImpl implements ItemErrorsMng {

	public void add(Long itemId, String errorType) {
		if (dao.getByItemAndType(itemId, errorType) == null) {
			ItemErrors itemErrors = new ItemErrors(itemId, errorType);
			itemErrors.init();
			dao.add(itemErrors);
		}
	}

	public ItemErrors getByItemAndType(Long itemId, String errorType) {
		return dao.getByItemAndType(itemId, errorType);
	}

	public List<ItemErrors> getByErrorType(String errorType) {
		return dao.getByErrorType(errorType);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public List<ItemErrors> getByItem(Long itemId) {
		return dao.getByItem(itemId);
	}

	@Autowired
	private ItemErrorsDao dao;
}
