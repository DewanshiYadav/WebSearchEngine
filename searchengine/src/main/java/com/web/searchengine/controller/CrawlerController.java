package com.web.searchengine.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerController {

	private static ArrayList<String> linkList = new ArrayList<>();

    public CrawlerController(String url) {   // constructor
        getLinks(url);
    }
    
    private static Matcher matchPattern(String regex, String strText) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(strText);
		return matcher;
	}

    private static void getLinks(String url) {
    	linkList = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String s = link.attr("abs:href");
                String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
                Matcher matcher = matchPattern(regex, s);
                while (matcher.find()) {
                    linkList.add(matcher.group(0));
                }
            }
        } catch (IOException e) {
        	System.out.println("Exception in getLinks method " + e);
        }
    }

    public String[] getURLList () {
        String[] urlList = linkList.toArray(new String[linkList.size()]);
        return urlList;
    }
}
