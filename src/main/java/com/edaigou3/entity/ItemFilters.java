package com.edaigou3.entity;

import javax.persistence.Entity;

import com.edaigou3.entity.base.BaseItemFilters;

@Entity
public class ItemFilters extends BaseItemFilters{
	
	public ItemFilters(){}

	public ItemFilters(Long pNumIid, String nick) {
		super(pNumIid, nick);
	}
	
	public void init(){}

	private static final long serialVersionUID = 8794528367563453019L;
}
