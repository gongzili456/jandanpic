package cn.threat.producercustomer;

/**
 * 网页封装类
 * 
 * @author liuguili
 * 
 */

public class WebPage {

	private String content;

	private String url;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "WebPage [content=" + content + ", url=" + url + "]";
	}

}
