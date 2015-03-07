package com.edaigou3.view.base;


/**
 * 浏览器视图
 * 
 * @author zoro
 *
 */
public interface IBrowserView extends IBaseView {

	/**
	 * 请求
	 * 
	 * @param url
	 */
	public void doRequest(String url);

	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param operatorProvider
	 * @param time
	 */
	public void doRequest(String url, IOperatorProvider provider,
			int time);

	/**
	 * 请求
	 * 
	 * @param url
	 */
	public void doRequest(IRequestProvider requestProvider,
			IOperatorProvider operatorProvider, int time);
	
	/**
	 * 获取请求内容
	 * @return
	 */
	public String getResponseText();
	
	/**
	 * 操作提供者
	 * 
	 * @author zoro
	 *
	 */
	public static interface IOperatorProvider {

		public void completed(IBrowserView browserView);
	}

	/**
	 * 操作请求者
	 * 
	 * @author zoro
	 *
	 */
	public static interface IRequestProvider {

		public String getRequestUrl(IBrowserView browserView);
	}
}
