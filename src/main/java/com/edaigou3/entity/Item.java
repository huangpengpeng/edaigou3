package com.edaigou3.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.edaigou3.entity.base.BaseItem;

@Entity
public class Item extends BaseItem {

	public Item() {
	}

	public Item(Long shopId, String imageByte, String channel, String title,
			String url, Long tbkNumIid,
			BigDecimal originalPrice, Double rebateProportion,
			BigDecimal rebateFee, BigDecimal serviceFee, BigDecimal realPrice,
			BigDecimal profitFee, BigDecimal lowPrice, Long numIid,
			Double freshRebateProportion, BigDecimal freshOriginalPrice,
			String freshTitle, BigDecimal freshRealPrice) {
		super(shopId, imageByte, channel, title, url, tbkNumIid,
				originalPrice, rebateProportion, rebateFee, serviceFee,
				realPrice, profitFee, lowPrice, numIid, freshRebateProportion,
				freshOriginalPrice, freshTitle, freshRealPrice);
	}

	public void caleProfieFee() {
		this.setProfitFee(new BigDecimal(getRealPrice().add(getRebateFee())
				.subtract(getServiceFee()).subtract(getOriginalPrice())
				.toBigInteger().toString()));
	}

	public void caleRebate() {
		this.setRebateFee(getOriginalPrice().multiply(
				new BigDecimal(getRebateProportion().toString()).divide(
						new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP))
				.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.setServiceFee(getRebateFee().multiply(new BigDecimal("0.1"))
				.setScale(2, BigDecimal.ROUND_HALF_UP));
	}

	public void init() {
		if (getStatus() == null) {
			setStatus(ItemStatus.创建.toString());
		}
	}

	public enum ItemChannel {
		普通淘客, 高级淘客
	}

	public enum ItemStatus {
		创建, 上架, 下架
	}

	private static final long serialVersionUID = 7519304894586925507L;
}
