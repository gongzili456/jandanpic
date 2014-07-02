package cn.threat.producercustomer;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Page crawler
 * 
 * download web page to save queue
 * 
 */
public class PageCrawler implements Runnable {

	private HttpClient client = HttpClients.createDefault();

	public void run() {
		while (true) {
			System.out.println("----PageCrawler Start----");
			if (QueuePool.getPageurlqueue().isEmpty()) {
				try {
					System.out.println("----PageCrawler Seelp----");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			StringWriter sw = new StringWriter();
			HttpResponse response = null;
			HttpEntity entity = null;
			try {
				String url = QueuePool.getPageurlqueue().take();
				HttpGet get = new HttpGet(url);
				response = client.execute(get);
				entity = response.getEntity();
				IOUtils.copy(entity.getContent(), sw, "UTF-8");
				WebPage page = new WebPage();
				page.setContent(sw.toString());
				page.setUrl(url);
				QueuePool.getPagequeue().put(page);
				EntityUtils.consume(entity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
