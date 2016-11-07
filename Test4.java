package com.zhy.spider.test;

public class Test4 {
	public static void main(String[] args) {
		String str = "送书|又一大波大数据技术实践书涌|来，在七月遇见更好的自己";
		str = str.replace("|", "'");
		System.out.println(str);
	}
}
