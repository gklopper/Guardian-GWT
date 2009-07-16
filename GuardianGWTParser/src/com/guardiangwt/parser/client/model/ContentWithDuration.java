package com.guardiangwt.parser.client.model;

import java.util.Date;
import java.util.List;

public class ContentWithDuration extends Content {

	private int durationMinutes;
	private int durationSeconds;

	public ContentWithDuration(int id, String type, String apiUrl, String webUrl,
			String publication, String headline, String standfirst,
			String byline, String sectionName, String trailText,
			String linkText, String trailImage, Date publicationDate, List<Tag> taggedWith,
			int durationMinutes, int durationSeconds) {
		super(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith);
		this.durationMinutes = durationMinutes;
		this.durationSeconds = durationSeconds;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public int getDurationSeconds() {
		return durationSeconds;
	}
	
}
