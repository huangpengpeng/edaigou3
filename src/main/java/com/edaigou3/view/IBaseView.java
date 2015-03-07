package com.edaigou3.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public interface IBaseView {

	/**
	 * 创建内容
	 * 
	 * @param shell
	 */
	void createContents(Shell shell);

	/**
	 * 创建内容
	 * 
	 * @param composite
	 */
	void createContents(Composite composite);

	/**
	 * 创建监听
	 */
	void createListenter();

	/**
	 * 前置处理
	 */
	void preHandle();
	/**
	 * 填充内容
	 */
	void fullContents(Object... values);
}
