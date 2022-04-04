package com.web.searchengine.services;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.searchengine.controller.CrawlerController;
import com.web.searchengine.controller.HtmlToTextConvertor;

@Controller
public class CrawlerService {
	@RequestMapping("/crawler")
    public String getURL(@RequestParam("urlstr") String url, Model model){
        Long startTime = System.currentTimeMillis();
        CrawlerController crawler = new CrawlerController(url);
        Long endTime = System.currentTimeMillis();
        String[] urlList = crawler.getURLList();
        Long time = endTime - startTime;
        
        /// convert and save HTML to text files.
        /// multi-threading concept is used to save the files.
//        HtmlToTextConvertor.getInstance().convertHtmlToText(urlList) ;
        
        model.addAttribute("urlList", urlList);
        model.addAttribute("numURLs", urlList.length);
        model.addAttribute("timeUsage_url", time);
        return "crawlerResultPage";
    }
}
