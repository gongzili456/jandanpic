package cn.threat.producercustomer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 图片下载类
 * 
 * @author liuguili
 * 
 */
public class ImageFileDownload implements Runnable {

	private HttpClient client = HttpClients.createDefault();

	public void run() {
		while (true) {
			System.out.println("====Image Download Start...");
			System.out.println("--------" + QueuePool.getImageurlqueue()
					+ "---");
			if (QueuePool.getImageurlqueue().isEmpty()) {
				try {
					System.out.println("====Image Download Sellp...");
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			String imgUrl = null;
			try {
				imgUrl = QueuePool.getImageurlqueue().take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("==================image url==================="
					+ imgUrl);
			HttpResponse response = null;
			InputStream in = null;
			try {
				HttpGet get = new HttpGet(imgUrl);
				response = client.execute(get);
				HttpEntity entity = response.getEntity();
				in = entity.getContent();
				File file = new File("/Users/liuguili/Documents/ImageDown",
						imgUrl.substring(imgUrl.lastIndexOf("/")));
				byte[] buf = IOUtils.toByteArray(in);
				FileUtils.writeByteArrayToFile(file, buf);
				IOUtils.closeQuietly(in);
				EntityUtils.consume(entity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
