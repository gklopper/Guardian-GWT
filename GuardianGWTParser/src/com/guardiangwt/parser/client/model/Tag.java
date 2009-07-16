package com.guardiangwt.parser.client.model;

public class Tag {

	private String apiUrl;
	private String filter;
	private String name;
	private String type;
	private String webUrl;
	
	public Tag() {
		
	}
	
	public Tag(String name, String type, String filter, String apiUrl,
			String webUrl) {
				this.name = name;
				this.type = type;
				this.filter = filter;
				this.apiUrl = apiUrl;
				this.webUrl = webUrl;
	
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getFilter() {
		return filter;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getWebUrl() {
		return webUrl;
	}
}
