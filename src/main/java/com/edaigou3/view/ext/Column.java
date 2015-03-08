package com.edaigou3.view.ext;


public class Column {

	public final static int PUTONG = 0, BUTTON = 1, IMAGE = 2;

	private String name;
	private String text;
	private Integer width;
	private Integer mode;

	public Column(String name,String text, Integer width,  int mode) {
		this.setName(name);
		this.setText(text);
		this.setWidth(width);
		this.setMode(mode);
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
