package cn.threat.producercustomer;

public class Test {
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new PageCrawler()).start();
		}
		for (int i = 0; i < 5; i++) {
			new Thread(new PageParser()).start();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(new ImageFileDownload()).start();
		}
	}
}
