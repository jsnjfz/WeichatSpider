package com.zhy.spider.test;

import java.io.FileWriter;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChuansongmenArticleSpider {
	public static void main(String[] args) throws Exception {
		
		System.getProperties().setProperty("proxySet", "true");

		// 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
		// 设置代理，防爬虫
		String ip = "183.50.136.162";
		System.getProperties().setProperty("http.proxyHost", ip);
		System.getProperties().setProperty("http.proxyPort", "3128");
		
		String[] author = {  
				  
				"caozsay" };
		String urlInit = "http://chuansong.me/account/";
		String url = "";

		//设置多个agent，防爬虫
		String[] randomAgent = {    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
			    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506)",
			    "Mozilla/4.0 (compatible; MSIE 7.0; AOL 9.5; AOLBuild 4337.35; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
			    "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
			    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
			    "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
			    "Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.04506.30)",
			    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)",
			    "Mozilla/5.0 (X11; U; Linux; en-US) AppleWebKit/527+ (KHTML, like Gecko, Safari/419.3) Arora/0.6",
			    "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2pre) Gecko/20070215 K-Ninja/2.1.1",
			    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9) Gecko/20080705 Firefox/3.0 Kapiko/3.0",
			    "Mozilla/5.0 (X11; Linux i686; U;) Gecko/20070322 Kazehakase/0.4.5",
			    "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.8) Gecko Fedora/1.9.0.8-1.fc10 Kazehakase/0.5.6",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
			    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
			    "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
			    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)",
			    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 LBBROWSER",
			    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
			    "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)",
			    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
			    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; 360SE)",
			    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
			    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
			    "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
			    "Mozilla/5.0 (iPad; U; CPU OS 4_2_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5",
			    "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b13pre) Gecko/20110307 Firefox/4.0b13pre",
			    "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) Gecko/20100101 Firefox/16.0",
			    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
			    "Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10"};

		Document doc = null;
		String content = "";

		//按作者循环
		for (int j = 0; j < author.length; j++) {
			String urlTitle = urlInit + author[j];

			//按文章循环 每页12篇文章
			for (int i = 0; i <= 200; i++) {
				if (i != 0) {
					url = urlTitle + "/?start=" + (i * 12);
				} else {
					url = urlTitle;
				}
				System.out.println("url=****" + url + "****");
				Random randAgent = new Random();
				int randNumAgent = randAgent.nextInt(32);
				System.out.println("-------------"+randNumAgent+"-----------");
				doc = Jsoup
						.connect(url)
						.header("User-Agent",
								randomAgent[randNumAgent])
						.timeout(200000).get();
				
				if (doc.toString().contains("暂无文章")) {
					break;
				}
				System.out.println("********start*********");

				// 处理返回数据
				Elements results = new Elements();
				results = doc.getElementsByClass("question_link");

				for (Element result : results) {
					// 必要的筛选
					content = "";
					String linkHref = result.attr("href");
					String link = "http://chuansong.me" + linkHref;
					System.out.println("******" + link + "***********");
					Random randAgent1 = new Random();
					int randNumAgent1 = randAgent1.nextInt(33);
					System.out.println("Agent------------"+randomAgent[randNumAgent1]+"--------------");
					Connection con = Jsoup
							.connect(link)
							.userAgent(randomAgent[randNumAgent1])
							.ignoreHttpErrors(true).followRedirects(true)
							.timeout(200000);
					Connection.Response resp = con.execute();
					System.out.println(resp.statusCode());

					if (resp.statusCode() == 200) {
						Document docIn = con.get();
						Elements resultsIn = docIn
								.getElementsByClass("rich_media_content");
						Elements resultsTitle = docIn
								.getElementsByClass("rich_media_title");
						System.out.println("******title:" + resultsTitle
								+ "********");

						content += "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>";
						content += resultsIn.toString();
						String title = resultsTitle.text();

						//处理文章中特殊字符
						if (title.contains("\"")) {
							title = title.replace("\"", "'");
						}
						if (title.contains("\\")) {
							title = title.replace("\\", "'");
						}
						if (title.contains("/")) {
							title = title.replace("/", "'");
						}
						if (title.contains("?")) {
							title = title.replace("?", "'");
						}
						if (title.contains(":")) {
							title = title.replace(":", "'");
						}
						if (title.contains("*")) {
							title = title.replace("*", "'");
						}
						if (title.contains("<")) {
							title = title.replace("<", "'");
						}
						if (title.contains(">")) {
							title = title.replace(">", "'");
						}
						if (title.contains("|")) {
							title = title.replace("|", "'");
						}
						FileWriter fw = new FileWriter(
								"D:/study/weichat_document/" + author[j] + "/"
										+ i + title + ".html");
						fw.write(content);
						fw.flush();
						fw.close();
						
					}

					//防爬虫
					Random rand = new Random();
					int randNum = rand.nextInt(2000);
					Thread.sleep(4000+randNum);


				}

				//防爬虫
				Thread.sleep(51101);
			}
		}

	}
}
