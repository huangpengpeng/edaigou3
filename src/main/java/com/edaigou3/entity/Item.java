package com.edaigou3.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.edaigou3.entity.base.BaseItem;

@Entity
public class Item extends BaseItem{

	public Item(){}
	
	public Item(Long shopId, String imageByte, String channel, String title,
			String url, Long tbkNumIid, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice, BigDecimal profitFee,
			BigDecimal lowPrice, Long numIid, Double freshRebateProportion,
			BigDecimal freshOriginalPrice, String freshTitle) {
		super(shopId, imageByte, channel, title, url, tbkNumIid, originalPrice,
				rebateProportion, rebateFee, serviceFee, realPrice, profitFee,
				lowPrice, numIid, freshRebateProportion, freshOriginalPrice,
				freshTitle);
	}
	
	public void init(){}

	private static final long serialVersionUID = 7519304894586925507L;
}
