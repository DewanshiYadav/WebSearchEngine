package com.web.searchengine.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlToTextConvertor extends Thread {
	
	private static List<String> _urlList = new ArrayList<>() ;

	public static HtmlToTextConvertor _instance = null ;

	private HtmlToTextConvertor()
	{}

	public static HtmlToTextConvertor getInstance()
	{
		if( _instance == null)
		{
			_instance = new HtmlToTextConvertor() ;
		}
		return _instance ;
	}
	
	public  void convertHtmlToText(String[] urlList)
	{		
//		_urlList.clear();
		_urlList = Arrays.asList(urlList);
		
		/// start a separate thread here to save the files.
		start() ;
	}
	
	public void run()
	{
		String htmlFolder = "htmlFiles/" ;
		String textFolder = "textFiles/" ;
		
		File directory = new File( htmlFolder) ;
		
		///create directory if not existed.
		if (! directory.exists())
		{
	        directory.mkdir();
	    }
		
		File textDirectory = new File( textFolder) ;
		
		///create directory if not existed.
		if( !textDirectory.exists() )
		{
			textDirectory.mkdir() ;
		}
		
		int counter = 1 ;
		for( String urlRef : _urlList)
		{
			try
			{	        
				/// Download URL content from the Internet.
				Document doc = Jsoup.connect(urlRef).get();
				
				///save Text data to file. 
				saveDataToFile(textFolder, "textFile_" + counter + ".txt", doc.text());
				
				///save HTML data to file.
				saveDataToFile(htmlFolder, "htmlFile_" + counter + ".html", doc.html() );
				
				//System.out.println("DEV:: data saved successfully for file :" + urlRef) ;
				counter++ ;
			} 
			catch (IOException e)
			{
				System.out.println("Exception occur in run method :" + e.getMessage() ) ;
			}
		}
		System.out.println("============== HTML AND TEXT FILES ARE SAVED SUCCESSFULLY ========") ;
	}
	
	private void saveDataToFile(String folderName,
			String fileName, String data)
	{
		try
		{
			PrintWriter out = new PrintWriter(folderName + fileName);
			out.println(data);
			out.close();
		} 
		catch (IOException e)
		{
			System.out.println("Exception occur in saveDataToFile method :" + e.getMessage() ) ;
		}
	}

}
