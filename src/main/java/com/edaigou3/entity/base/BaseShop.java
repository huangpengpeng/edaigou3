package com.edaigou3.entity.base;

import com.common.jdbc.BaseEntity;

@javax.persistence.MappedSuperclass
public class BaseShop extends BaseEntity{

	private static final long serialVersionUID = -965694989194148967L;
	
	public BaseShop(){}
	
	public BaseShop(String name,String nick,String url){
		this.setName(name);
		this.setNick(nick);
		this.setUrl(url);
	}

	/**
	 * 店铺名称
	 */
	private String name;
	
	/**
	 * 掌柜昵称
	 */
	private String nick;
	
	/**
	 * 店铺地址
	 */
	private String url;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
