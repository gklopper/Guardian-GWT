package com.guardiangwt.parser.client.model;


public class FilterTag extends Tag {

	private int count;
	private String filterUrl;
	
	public FilterTag() {
		
	}

	public FilterTag(String name, String type, String filter, String apiUrl,
			String webUrl, int count, String filterUrl) {
		
		super(name, type, filter, apiUrl, webUrl);
		this.count = count;
		this.filterUrl = filterUrl;
	}

	public int getCount() {
		return count;
	}

	public String getFilterUrl() {
		return filterUrl;
	}
}
