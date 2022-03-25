package com.web.searchengine.vo;

public class DocumentVo {
	private int rank;
    private String name;
    private String subString;
    private String content;

    public DocumentVo(int rank, String name, String subString, String content) {
        this.rank = rank;
        this.name = name;
        this.subString = subString;
        this.content = content;
    }

    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSubString() {
        return subString;
    }

    public void setSubString(String subString) {
        this.subString = subString;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
