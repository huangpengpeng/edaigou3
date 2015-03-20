package com.edaigou3.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.jdbc.page.Pagination;
import com.common.util.NumberUtils;
import com.edaigou3.dao.ItemDao;
import com.edaigou3.entity.Item;
import com.edaigou3.entity.ItemErrors;
import com.edaigou3.entity.ItemErrors.ItemErrorsType;
import com.edaigou3.manager.ItemErrorsMng;
import com.edaigou3.manager.ItemMng;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

@Transactional
@Service
public class ItemMngImpl implements ItemMng {

	public void add(Long shopId, String imageByte, String channel,
			String title, String url, Long tbkNumIid, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice) {
		Item item = new Item(shopId, imageByte, channel, title, url, tbkNumIid,
				originalPrice, rebateProportion, rebateFee, serviceFee,
				realPrice, null, null, null, null, null, null, null);
		item.caleProfieFee();
		item.init();
		dao.add(item);
	}

	public Pagination getPage(Long shopId, Long[] ids, String title,
			String status, Integer pageNo, Integer pageSize,String sort) {
		return dao.getPage(shopId, ids, title, status, pageNo, pageSize,sort);
	}

	public void delete(Long id) {
		dao.delete(id);

		List<ItemErrors> itemErrors = itemErrorsMng.getByItem(id);
		for (ItemErrors itemError : itemErrors) {
			itemErrorsMng.delete(itemError.getId());
		}
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

	public Item get(Long id) {
		return dao.get(id);
	}

	public void update(Long id, Long shopId, String imageByte, String channel,
			String title, BigDecimal originalPrice, Double rebateProportion,
			BigDecimal rebateFee, BigDecimal serviceFee, BigDecimal realPrice,
			BigDecimal profitFee, BigDecimal lowPrice, Long numIid) {
		dao.update(id, shopId, imageByte, channel, title, originalPrice,
				rebateProportion, rebateFee, serviceFee, realPrice, profitFee,
				lowPrice, numIid);
		checkErrors(id);
	}

	public void checkErrors(Long id) {
		Item item = dao.get(id);

		List<ItemErrors> errors = itemErrorsMng.getByItem(item.getId());
		for (ItemErrors itemErrors : errors) {
			if (ItemErrorsType.天猫下架.toString()
					.equals(itemErrors.getErrorType())) {
				continue;
			}
			itemErrorsMng.delete(itemErrors.getId());
		}

		// 当最低价格小于等于0时，说明抓取错误
		if (item.getLowPrice() == null
				|| new BigDecimal(Integer.MAX_VALUE).compareTo(item
						.getLowPrice()) == 0
				|| item.getLowPrice().compareTo(BigDecimal.ZERO) <= 0) {
			itemErrorsMng.add(item.getId(), ItemErrorsType.抓低错误.toString());
		}

		// 当实际销售小于或者等于最低价格
		if (item.getLowPrice() != null
				&& item.getRealPrice().compareTo(item.getLowPrice()) >= 0) {
			itemErrorsMng.add(item.getId(), ItemErrorsType.非低价格.toString());
		}

		// 当实际利润 小于 天猫销售价格-自己销售价格
		if (item.getProfitFee().compareTo(
				item.getOriginalPrice().subtract(item.getRealPrice())) < 0) {
			itemErrorsMng.add(item.getId(), ItemErrorsType.销利过低.toString());
		}

		// 当编号不存在
		if (item.getNumIid() == null) {
			itemErrorsMng.add(item.getId(), ItemErrorsType.编号错误.toString());
		}

		// 当同步店铺实际售价不为空 并且 实际销售价格和同步价格不一致
		if (item.getFreshRealPrice() != null
				&& item.getRealPrice().compareTo(item.getFreshRealPrice()) != 0) {
			itemErrorsMng.add(item.getId(), ItemErrorsType.店售错误.toString());
		}

		// 当同步淘宝比例不为空 并且和原始淘客比例不一致
		if (item.getFreshRebateProportion() != null
				&& !NumberUtils.equals(item.getFreshRebateProportion(),
						item.getRebateProportion())) {
			itemErrorsMng.add(item.getId(), ItemErrorsType.淘客错误.toString());
		}
	}

	public List<Item> query(String status) {
		return dao.query(status);
	}

	public void update(Long id, Double freshRebateProportion,
			BigDecimal freshOriginalPrice, String freshTitle,
			BigDecimal freshRealPrice) {
		dao.update(id, freshRebateProportion, freshOriginalPrice, freshTitle,
				freshRealPrice);
	}

	public void update(Long id, String status) {
		dao.update(id, status);
	}

	@Autowired
	private ItemErrorsMng itemErrorsMng;
	@Autowired
	private ItemDao dao;
}
