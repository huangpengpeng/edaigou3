package com.edaigou3.entity;

import javax.persistence.Entity;

import com.edaigou3.entity.base.BaseItemErrors;

@Entity
public class ItemErrors extends BaseItemErrors {

	public ItemErrors() {
	}

	public ItemErrors(Long itemId, String errorType) {
		super(itemId, errorType);
	}

	public void init() {
	}

	public enum ItemErrorsType {
		抓低错误, 非低价格, 销利过低, 编号错误, 店售错误, 淘客错误, 天猫下架, SKU数量错误, 猫价变动, 标题错误
	}

	private static final long serialVersionUID = -6521164283095986651L;
}
