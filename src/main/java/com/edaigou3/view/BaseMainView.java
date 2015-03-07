package com.edaigou3.view;

import java.awt.Toolkit;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public abstract class BaseMainView {

	protected Shell shell;

	public abstract void createShell();

	public abstract void createContents();

	public abstract String getApplicationXml();

	protected void addView(IBaseView baseView) {
		baseView.createContents(shell);
		baseView.createListenter();
		baseView.preHandle();
	}
	

	protected void addView(Composite composite, IBaseView baseView) {
		baseView.createContents(composite);
		baseView.createListenter();
		baseView.preHandle();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * 窗口居中
	 */
	protected void centerShell() {
		shell.setLocation(
				((Toolkit.getDefaultToolkit().getScreenSize().width - shell
						.getBounds().width) / 2),
				((Toolkit.getDefaultToolkit().getScreenSize().height - 35 - shell
						.getBounds().height) / 2));
	}

	protected void load() {
		ApplicationUtils.newInstance(getApplicationXml());
		createShell();
		createContents();
	}

	/**
	 * spring 上下文工具类
	 * 
	 * @author zoro
	 *
	 */
	public static class ApplicationUtils {

		private static ApplicationContext applicationContext;

		public static void newInstance(String file) {
			applicationContext = new FileSystemXmlApplicationContext(file);
		}

		@SuppressWarnings("unchecked")
		public static <T> T getBean(Class<?> requiredType) {
			return (T) applicationContext.getBean(requiredType);
		}
	}
}
