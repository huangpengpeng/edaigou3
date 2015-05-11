package com.edaigou3.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

@MappedSuperclass
public class BaseItemFilters extends BaseEntity{

	private static final long serialVersionUID = 2853976326027141448L;
	
	public BaseItemFilters(){}
	
	public BaseItemFilters(Long pNumIid,String nick){
		this.setpNumIid(pNumIid);
		this.setNick(nick);
	}
	
	private Long pNumIid;
	
	private String nick;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Long getpNumIid() {
		return pNumIid;
	}

	public void setpNumIid(Long pNumIid) {
		this.pNumIid = pNumIid;
	}
}
