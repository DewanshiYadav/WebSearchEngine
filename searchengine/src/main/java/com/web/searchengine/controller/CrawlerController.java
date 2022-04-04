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

	/**
	 * @brief : list of urls for given Website.
	 */
	private static ArrayList<String> urlList = new ArrayList<>();

	/**
	 * Constructor of CrawlerController
	 * @param urlName
	 */
	public CrawlerController(String urlName)
	{ 
		getLinks(urlName);
	}

	/**
	 * getLinks : Method to get all links for given Uniform Resource Locator.
	 * @param url
	 */
	private static void getLinks(String url)
	{
		urlList.add(url);
		Document document;
		try
		{
			/// create Jsoup document.
			document = Jsoup.connect(url).get();
			Elements allLinks = document.select("a[href]");
			
			/// iterate and add details to list.
			for (Element linkRef : allLinks) 
			{
				String s = linkRef.attr("abs:href");
				String regExp = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
				Matcher matcher = matchPattern(regExp, s);
				while (matcher.find())
				{
					if(!urlList.contains(matcher.group(0) ) )
					{
						urlList.add(matcher.group(0));
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Exception in getLinks method " + e);
		}
	}

	/**
	 * get list of urls.
	 * @return
	 */
	public String[] getURLList () 
	{
		String[] result = urlList.toArray(new String[urlList.size()]);
		return result;
	}

	/**
	 * check regular expression match in text string.
	 * @param regExpression
	 * @param textStr
	 * @return
	 */
	private static Matcher matchPattern(String regExpression, String textStr) {
		Pattern pattern = Pattern.compile(regExpression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(textStr);
		return matcher;
	}
}
