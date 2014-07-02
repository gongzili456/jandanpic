package cn.threat.producercustomer;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列封装类
 * 
 * @author liuguili
 * 
 */
public class QueuePool {

	private static final LinkedBlockingQueue<String> pageUrlQueue = new LinkedBlockingQueue<String>();
	private static final LinkedBlockingQueue<WebPage> pageQueue = new LinkedBlockingQueue<WebPage>();
	private static final LinkedBlockingQueue<String> imageUrlQueue = new LinkedBlockingQueue<String>();

	/**
	 * 初始化要爬去页面的数据 简单删除了一些比较早的数据，所以起始页不是从1开始
	 */
	static {
		String url = "http://jandan.net/ooxx/page-{}#comments";
		for (int i = 900; i < 1127; i++) {
			pageUrlQueue.add(url.replace("{}", i + ""));
		}
	}

	public static LinkedBlockingQueue<WebPage> getPagequeue() {
		return pageQueue;
	}

	public static LinkedBlockingQueue<String> getPageurlqueue() {
		return pageUrlQueue;
	}

	public static LinkedBlockingQueue<String> getImageurlqueue() {
		return imageUrlQueue;
	}

}
