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
	public String getURL(@RequestParam("urlstr") String url, Model model) {
		try {
			/// beginTime of the web crawler.
			Long beginTime = System.currentTimeMillis();

			/// create crawler object.
			CrawlerController crawler = new CrawlerController(url);

			/// endingTime of the web crawler.
			Long endingTime = System.currentTimeMillis();

			/// get list of URLs.
			String[] urlsList = crawler.getURLList();

			/// calculate time difference
			Long totalTime = endingTime - beginTime;

			/// convert and save HTML to text files.
			/// multi-threading concept is used to save the files.
			HtmlToTextConvertor htmlToText = new HtmlToTextConvertor();
			htmlToText.convertHtmlToText(urlsList);

			/// add details to model.
			if (urlsList.length != 0) {
				model.addAttribute("hasResult", true);
				model.addAttribute("urlList", urlsList);
				model.addAttribute("numURLs", urlsList.length);
				model.addAttribute("timeUsage_url", totalTime);
			} else {
				model.addAttribute("hasResult", false);
				model.addAttribute("urlList", url);
				String[] noAlt = { "Try a valid url" };
				model.addAttribute("alternatives", noAlt);
			}
		} catch (Exception e) {
			model.addAttribute("hasResult", false);
			model.addAttribute("urlList", url);
			String[] noAlt = { "Try a valid url" };
			model.addAttribute("alternatives", noAlt);
		}
		return "crawlerResultPage";
	}
}
