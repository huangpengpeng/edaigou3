package com.edaigou3.entity.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Lob;

import org.apache.openjpa.persistence.jdbc.ClassCriteria;

import com.common.jdbc.BaseEntity;

@javax.persistence.MappedSuperclass
public class BaseItem extends BaseEntity {

	private static final long serialVersionUID = -7948930413157445187L;

	public BaseItem() {
	}

	public BaseItem(Long shopId, String imageByte, String channel,
			String title, String url, Long tbkNumIid,BigDecimal marketPrice, BigDecimal originalPrice,
			Double rebateProportion, BigDecimal rebateFee,
			BigDecimal serviceFee, BigDecimal realPrice, BigDecimal profitFee,
			BigDecimal lowPrice, Long numIid, Double freshRebateProportion,
			BigDecimal freshOriginalPrice, String freshTitle,BigDecimal freshRealPrice) {
		this.setShopId(shopId);
		this.setImageByte(imageByte);
		this.setChannel(channel);
		this.setTitle(title);
		this.setUrl(url);
		this.setTbkNumIid(tbkNumIid);
		this.setOriginalPrice(originalPrice);
		this.setRebateProportion(rebateProportion);
		this.setRebateFee(rebateFee);
		this.setServiceFee(serviceFee);
		this.setRealPrice(realPrice);
		this.setProfitFee(profitFee);
		this.setLowPrice(lowPrice);
		this.setNumIid(numIid);
		this.setFreshRebateProportion(freshRebateProportion);
		this.setFreshOriginalPrice(freshOriginalPrice);
		this.setFreshTitle(freshTitle);
		this.setFreshRealPrice(freshRealPrice);
	}

	/**
	 * 商品所属店铺
	 */
	private Long shopId;

	/**
	 * 商品图片
	 */
	@Lob
	private String imageByte;

	/**
	 * 商品渠道 （普通淘客，高级淘客）
	 */
	private String channel;

	/**
	 * 淘宝客商品标题
	 */
	private String title;

	/**
	 * 淘宝客商品URL
	 */
	private String url;

	/**
	 * 淘宝客商品编号
	 */
	@Column(unique = true)
	private Long tbkNumIid;

	/**
	 * 市场价
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal marketPrice;
	
	/**
	 * 淘宝客原始销售价格
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal originalPrice;

	/**
	 * 淘宝客返点比例
	 */
	@Column(precision = 18, scale = 2)
	private Double rebateProportion;

	/**
	 * 淘宝客返点金额
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal rebateFee;

	/**
	 * 淘宝客技术服务费
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal serviceFee;

	/**
	 * 店铺实际销售价格
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal realPrice;

	/**
	 * 店铺实际利润
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal profitFee;

	/**
	 * 整个淘宝网最低销售价格
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal lowPrice;

	/**
	 * 店铺商品编号
	 */
	@Column(unique = true)
	private Long numIid;

	/**
	 * 同步淘宝客最新返点比例
	 */
	@Column(precision = 18, scale = 2)
	private Double freshRebateProportion;

	/**
	 * 同步淘宝客最新销售价格
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal freshOriginalPrice;

	/**
	 * 淘宝客最新标题
	 */
	private String freshTitle;
	
	/**
	 * 同步店铺实际销售价格
	 */
	private BigDecimal freshRealPrice;
	
	/**
	 * 状态
	 */
	private String status;

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getTbkNumIid() {
		return tbkNumIid;
	}

	public void setTbkNumIid(Long tbkNumIid) {
		this.tbkNumIid = tbkNumIid;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Double getRebateProportion() {
		return rebateProportion;
	}

	public void setRebateProportion(Double rebateProportion) {
		this.rebateProportion = rebateProportion;
	}

	public BigDecimal getRebateFee() {
		return rebateFee;
	}

	public void setRebateFee(BigDecimal rebateFee) {
		this.rebateFee = rebateFee;
	}

	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getProfitFee() {
		return profitFee;
	}

	public void setProfitFee(BigDecimal profitFee) {
		this.profitFee = profitFee;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getFreshRebateProportion() {
		return freshRebateProportion;
	}

	public void setFreshRebateProportion(Double freshRebateProportion) {
		this.freshRebateProportion = freshRebateProportion;
	}

	public BigDecimal getFreshOriginalPrice() {
		return freshOriginalPrice;
	}

	public void setFreshOriginalPrice(BigDecimal freshOriginalPrice) {
		this.freshOriginalPrice = freshOriginalPrice;
	}

	public String getFreshTitle() {
		return freshTitle;
	}

	public void setFreshTitle(String freshTitle) {
		this.freshTitle = freshTitle;
	}

	public String getImageByte() {
		return imageByte;
	}

	public void setImageByte(String imageByte) {
		this.imageByte = imageByte;
	}

	public BigDecimal getFreshRealPrice() {
		return freshRealPrice;
	}

	public void setFreshRealPrice(BigDecimal freshRealPrice) {
		this.freshRealPrice = freshRealPrice;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	
}
