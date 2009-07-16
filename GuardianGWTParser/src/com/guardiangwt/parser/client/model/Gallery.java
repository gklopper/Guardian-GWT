package com.guardiangwt.parser.client.model;

import java.util.Date;
import java.util.List;

public class Gallery extends Content {

	private final int pictureCount;

	public Gallery(int id, String type, String apiUrl, String webUrl,
			String publication, String headline, String standfirst,
			String byline, String sectionName, String trailText,
			String linkText, String trailImage, Date publicationDate, List<Tag> taggedWith,
			int pictureCount) {
		super(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith);
		this.pictureCount = pictureCount;
		
	}

	public int getPictureCount() {
		return pictureCount;
	}
}
