package com.zhy.spider.test;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeichatArticleSpider {
	public static void main(String[] args) throws Exception {

		String url = "file:///C:/Users/admin/Desktop/%E6%9F%A5%E7%9C%8B%E5%8E%86%E5%8F%B2%E6%B6%88%E6%81%AF.html";
		String[] randomAgent = {
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
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
				"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10" };

		String content = "";
		String title = "";

		System.out.println("url=****" + url + "****");
		Random randAgent = new Random();
		int randNumAgent = randAgent.nextInt(32);
		System.out.println("-------------" + randNumAgent + "-----------");


		File htmlf=new File("C:\\Users\\admin\\Desktop\\history.html");
		Document doc=Jsoup.parse(htmlf, "UTF-8");


		System.out.println("********start*********");
		// 处理返回数据
		Elements results = new Elements();
		results = doc.getElementsByClass("msg_list");


		for (Element result : results) {

			content = "";

			Elements resultList = null;
			resultList = result.getElementsByClass("msg_cover");

			
			if(resultList.size()==0)
			{
				Elements resultList3 = result.getElementsByClass("msg_text");

				content = resultList3.text();

				Random randTitle = new Random();
				int randNumTitle = randTitle.nextInt(999999);
				title = "无题" + randNumTitle;

			}
			else
			{
				String link = resultList.attr("hrefs");

				System.out.println("******" + link + "***********");
				Random randAgent1 = new Random();
				int randNumAgent1 = randAgent1.nextInt(33);
				System.out.println("Agent------------" + randomAgent[randNumAgent1] + "--------------");
				Connection con = Jsoup.connect(link).userAgent(randomAgent[randNumAgent1]).ignoreHttpErrors(true)
						.followRedirects(true).timeout(200000);
				Connection.Response resp = con.execute();
				System.out.println(resp.statusCode());
				// Document docIn = null;
				if (resp.statusCode() == 200) {
					Document docIn = con.get();
					Elements resultsIn = docIn.getElementsByClass("rich_media_content");

					title = docIn.title();
					System.out.println("******title:" + title + "********");

					content += "<!DOCTYPE html><head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/></head>";
					content += resultsIn.toString();


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

				}
			}
			FileWriter fw = new FileWriter("D:/study/weichat_document/" + title + ".html");
			fw.write(content);
			fw.flush();
			fw.close();

			Random rand = new Random();
			int randNum = rand.nextInt(1000);
			Thread.sleep(2000 + randNum);

		}

	}
}
