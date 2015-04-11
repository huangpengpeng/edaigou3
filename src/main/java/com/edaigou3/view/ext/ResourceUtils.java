package com.edaigou3.view.ext;


import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 资源路径
 * 
 * @author Administrator
 */
public abstract class ResourceUtils {

	public final static String DEFAULT = "default.jpg";

	public static String getResource() {
		URL url = ResourceUtils.class.getProtectionDomain().getCodeSource()
				.getLocation();
		String path = null;
		try {
			path = URLDecoder.decode(url.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("获取文件当前运行目录 UTF-8编码转换失败", e);
		}
		if (path.startsWith("/")) {
			path = path.substring(1, path.length());
		}
		if (path.contains("/bin/")) {
			path = path.substring(0, path.indexOf("/bin/"));
		}
		if (path.contains("/lib/")) {
			path = path.substring(0, path.indexOf("/lib/"));
		}
		return path;
	}

	public static void main(String[] args) {
	}
}
