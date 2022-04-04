package com.web.searchengine.services;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.searchengine.controller.SearchController;
import com.web.searchengine.controller.SpellCheckingController;
import com.web.searchengine.references.PriorityQueue;
import com.web.searchengine.vo.Doc;

@Controller
public class Content {
	@RequestMapping("/content")
    public String getQuery(Model model) throws IOException {
		System.out.println("Query string : " + model);
		
//        SearchController searcher = new SearchController();
//        PriorityQueue<Integer,String> pq =  SearchController.occurrences(query);
//        Doc[] Docs = searcher.queue2List(pq);

//        long timeUsage = endTime - startTime;
//        if (Docs.length != 0){
//            model.addAttribute("hasResult", true);
//            model.addAttribute("resultList", Docs);
//            model.addAttribute("numResult", Docs.length);
//            model.addAttribute("timeUsage_res", timeUsage);
//            model.addAttribute("searched_string", query);
//        } else {
//            model.addAttribute("hasResult", false);
//            String[] altWords = SpellCheckingController.getAltWords(query);
//            if (altWords.length != 0) {
//                model.addAttribute("alternatives", altWords);
//                model.addAttribute("getquery", query);
//                model.addAttribute("searched_string", query);
//            } else {
//                String[] noAlt = {"None"};
//                model.addAttribute("alternatives", noAlt);
//                model.addAttribute("searched_string", query);
//            }
//        }
        return "searchResultPage";
    }
}
