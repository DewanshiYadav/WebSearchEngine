package com.web.searchengine.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.web.searchengine.references.Entry;
import com.web.searchengine.references.In;
import com.web.searchengine.references.PriorityQueue;
import com.web.searchengine.references.SortedPriorityQueue;
import com.web.searchengine.references.TST;
import com.web.searchengine.vo.DocumentVo;


public class SearchController {
	private static void searchKeys(String filename, TST<Integer> tst) {
		In in = new In(filename);
		String lines = in.readAll();
		StringTokenizer tokens = new StringTokenizer(lines, " ,`*$|~(){}_@><=+[]\\\\?;/&#<-.!:\\\"\\\"''\\n");

		while (tokens.hasMoreTokens()) {
			String key = tokens.nextToken();
			if(tst.contains(key)) {
				int count = tst.get(key);
				tst.put(key, count + 1);
			}
			else {
				tst.put(key, 1);
			}
		}
	}
	
	public static PriorityQueue<Integer,String> occurrences(String scan) throws IOException {
        String txtPath = "src/main/resources/static/data/text/";
        String htmlPath = "src/main/resources/static/data/html/";

        File txt = new File(txtPath);
        File[] Files = txt.listFiles();
        File html = new File(htmlPath);
        File[] Webs = html.listFiles();

        PriorityQueue<Integer,String> pq = new SortedPriorityQueue<>();
        // scan new files
        for (int i = 0; i < Files.length; i++) {
            if (Files[i].isFile()) {
                TST<Integer> tst = new TST<Integer>();
                String path = (txtPath + Files[i].getName());
                searchKeys(path, tst);// get occurrence from matching given word
                if (tst.get(scan) != null) {
                    //store occurrence and web name in priority queue
                	System.out.println("Scan: " + tst.get(scan) + "  Webs:: " + Webs[i].getName());
                    pq.insert(tst.get(scan), Webs[i].getName());
                }
            }
        }
        return pq;
    }
	
	private static String readFile(String filename) {
		In in = new In(filename);
		String lines = "";
		int count = 2;
		while (!in.isEmpty() && count!=0) {
			String s = in.readLine();
			lines +=s;
			count--;
		}
		
		return lines;
	}
	
	public DocumentVo[] queue2List(PriorityQueue<Integer, String> pq) throws IOException{
		DocumentVo[] queryResults = new DocumentVo[pq.size()];
        Iterator<Entry<Integer, String>> s = pq.iterator();
        int flag = 0;
        while(s.hasNext()) {
            Entry<Integer, String> tmp = s.next();
            String filename = tmp.getValue().split(".html")[0].trim()+"l.txt";
            String txtPath = "src/main/resources/static/data/text/";
            System.out.println(filename);
            String content = readFile(txtPath+filename);
            String subString = content.substring(0, 200) + "...";
            System.out.println(tmp.getValue());
            DocumentVo doc = new DocumentVo(tmp.getKey(), tmp.getValue(), subString, content);
            queryResults[(pq.size() - 1) - flag] = doc;
            flag++;
        }
        return queryResults;
    }
}
