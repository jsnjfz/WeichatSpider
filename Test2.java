package com.zhy.spider.test;

import java.io.FileWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test2 {
	public static void main(String[] args) throws Exception {
		String urlInit = "http://chuansong.me/account/yurii-says";
		String url = "";
		//Connection conn = Jsoup.connect(url);
		// doc = conn.timeout(100000).get();
		Document doc = null;
		String content = "";

		for (int i = 5; i <= 12; i++) {
			if(i!=0){
				url = urlInit + "?start=" + (i * 12);
			}
			else
			{
				url = urlInit;
			}
			System.out.println("url=****"+url+"****");
			doc = Jsoup
					.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").timeout(200000)
					.get();
			
			// 处理返回数据
			Elements results = new Elements();
			results = doc.getElementsByClass("question_link");

			for (Element result : results) {
				// 必要的筛选
				content = "";
				String linkHref = result.attr("href");
				String link = "http://chuansong.me" + linkHref;
				System.out.println("******" + link + "***********");
				Connection con = Jsoup
						.connect(link)
						.userAgent(
								"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
						.ignoreHttpErrors(true).followRedirects(true).timeout(200000);
				Connection.Response resp = con.execute();
				System.out.println(resp.statusCode());
				// Document docIn = null;
				if (resp.statusCode() == 200) {
					Document docIn = con.get();
					Elements resultsIn = docIn
							.getElementsByClass("rich_media_content");
					Elements resultsTitle = docIn
							.getElementsByClass("rich_media_title");
					System.out.println("******title:"+resultsTitle+"********");
					// content += resultsTitle.text() + "\r\n";
					// for (Element resultIn : resultsIn) {
					//
					// content += resultIn.text();
					// }
					content += "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>";
					content += resultsIn.toString();
					String title = resultsTitle.text();

					if(title.contains("\""))
					{
						title = title.replaceAll("\"", "'");
					}
					FileWriter fw = new FileWriter("D:/study/weichat_document/"+"余晟以为/"+i
							+ title + ".html");
					fw.write(content);
					fw.flush();
					fw.close();		
				}
				Thread.sleep(4000);
				// Document docIn = Jsoup
				// .connect(link).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
				// //.ignoreHttpErrors(true)
				// .followRedirects(true)
				// .header("User-Agent",
				// "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0").timeout(100000)
				// .get();

			}
			Thread.sleep(111101);
		}

	}
}
