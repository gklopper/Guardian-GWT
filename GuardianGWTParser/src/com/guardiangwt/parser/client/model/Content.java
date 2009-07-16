package com.guardiangwt.parser.client.model;

import java.util.Date;
import java.util.List;



public class Content {

	private int id;
	private String type;
	private String apiUrl;
	private String webUrl;
	private String publication;
	private String headline;
	private String byline;
	private String sectionName;
	private String trailText;
	private String linkText;
	private Date publicationDate;
	private List<Tag> taggedWith;
	private String standfirst;
	private String trailImage;

	public Content() {
		
	}

	public Content(int id, String type, String apiUrl, String webUrl,
			String publication, String headline, String standfirst, String byline,
			String sectionName, String trailText, String linkText,
			String trailImage, Date publicationDate, List<Tag> taggedWith) {
				this.id = id;
				this.type = type;
				this.apiUrl = apiUrl;
				this.webUrl = webUrl;
				this.publication = publication;
				this.headline = headline;
				this.standfirst = standfirst;
				this.byline = byline;
				this.sectionName = sectionName;
				this.trailText = trailText;
				this.linkText = linkText;
				this.trailImage = trailImage;
				this.publicationDate = publicationDate;
				this.taggedWith = taggedWith;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public String getPublication() {
		return publication;
	}

	public String getHeadline() {
		return headline;
	}

	public String getByline() {
		return byline;
	}

	public String getSectionName() {
		return sectionName;
	}

	public String getTrailText() {
		return trailText;
	}

	public String getLinkText() {
		return linkText;
	}
	
	public Date getPublicationDate() {
		return publicationDate;
	}

	public List<Tag> getTaggedWith() {
		return taggedWith;
	}

	public String getStandfirst() {
		return standfirst;
	}

	public String getTrailImage() {
		return trailImage;
	}
}
