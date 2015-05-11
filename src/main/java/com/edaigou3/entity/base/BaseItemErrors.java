package com.edaigou3.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

@MappedSuperclass
public class BaseItemErrors extends BaseEntity{
	
	private static final long serialVersionUID = 8357874137680153250L;


	public BaseItemErrors(){}
	
	public BaseItemErrors(Long itemId,String errorType){
		
		this.setItemId(itemId);
		this.setErrorType(errorType);
	}

	/**
	 * 商品编号
	 */
	private Long itemId;
	
	
	/**
	 * 错误类型
	 */
	private String errorType;


	public String getErrorType() {
		return errorType;
	}


	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	
}
