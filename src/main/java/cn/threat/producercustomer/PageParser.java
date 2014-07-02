package cn.threat.producercustomer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 页面解析类
 * 
 * @author liuguili
 * 
 */
public class PageParser implements Runnable {

	public void run() {
		while (true) {
			System.out.println("******PageParser start...");
			if (QueuePool.getPagequeue().isEmpty()) {
				try {
					System.out.println("******PagePaser SELLP");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			WebPage page = null;
			try {
				page = QueuePool.getPagequeue().take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			Document doc = Jsoup.parse(page.getContent());
			Elements images = doc.select(".commentlist>li>p img");
			if (images.isEmpty()) {
				System.out.println(page.getUrl() + " === NOT CONTANT URL");
				continue;
			}
			try {
				for (Element e : images) {
					String src = e.attr("src");
					if (src != null && src.startsWith("http://")) {
						QueuePool.getImageurlqueue().put(e.attr("src"));
					}
				}
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
