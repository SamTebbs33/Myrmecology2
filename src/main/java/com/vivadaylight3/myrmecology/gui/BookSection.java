package com.vivadaylight3.myrmecology.gui;

import java.util.LinkedList;

public class BookSection {

    public LinkedList<Page> pages = new LinkedList<Page>();
    public String title, tag;
    
    public BookSection(String tag, String title, String contents) {
	this.tag = tag;
	this.title = title;
	setContents(contents);
    }
    
    public BookSection setContents(String contents){
	int maxLines = 13, maxLength = 21;
	String[] split = contents.split(" ");
	int line = 0, length = 0;
	Page page = new Page();
	String currentLine = "";
	for(String word : split){
	    int wordLen = word.length();
	    int lineLen = length + wordLen + (length == 0 ? 0 : 1);
	    if(lineLen > maxLength){
		if(line + 2 > maxLines){
		    page.lines.add(currentLine);
		    pages.add(page);
		    page = new Page();
		    currentLine = word;
		    line = 0;
		}else{
		    page.lines.add(currentLine);
		    currentLine = word;
		    line++;
		}
	    }else{
		currentLine += (length == 0 ? "" : " ") + word;
	    }
	    length = currentLine.length();
	}
	if(!currentLine.equals("")) page.lines.add(currentLine);
	if(page.lines.size() > 0) pages.add(page);
	return this;
    }
    
    public LinkedList<String> getPage(int page){
	return pages.get(page).lines;
    }

}

class Page{
    public int numLines;
    public LinkedList<String> lines = new LinkedList<String>();
}
