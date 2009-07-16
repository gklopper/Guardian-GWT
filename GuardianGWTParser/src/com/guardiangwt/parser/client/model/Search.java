package com.guardiangwt.parser.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Search implements Serializable{
	
	private int count;
	private List<FilterTag> filters = new ArrayList<FilterTag>();
	private List<Content> results = new ArrayList<Content>(); 
	private int startIndex;
	
	public Search() {
		
	}
	
	public Search(int count, int startIndex, List<Content> results, List<FilterTag> filters) {
		this.count = count;
		this.startIndex = startIndex;
		this.results = results;
		this.filters = filters;
	}

	public int getCount() {
		return count;
	}

	public List<FilterTag> getFilters() {
		return filters;
	}

	public List<Content> getResults() {
		return results;
	}

	public int getStartIndex() {
		return startIndex;
	}

}
