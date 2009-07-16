package com.guardiangwt.parser.client.model;

import java.util.Date;
import java.util.List;

public class Article extends Content {

	private String body;

	public Article(int id, String type, String apiUrl, String webUrl,
			String publication, String headline, String standfirst, String byline,
			String sectionName, String trailText, String linkText,
			String trailImage, Date publicationDate, List<Tag> taggedWith, String body) {
		super(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith);
		this.body = body;
	}

	public String getBody() {
		return body;
	}

}
