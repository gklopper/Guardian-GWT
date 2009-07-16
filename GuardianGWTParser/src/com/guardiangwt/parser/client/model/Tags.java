package com.guardiangwt.parser.client.model;

import java.util.List;

public class Tags {
	
	private int count;
	private int startIndex;
	private List<Tag> tags;

	public Tags(int count, int startIndex, List<Tag> tags) {
		this.count = count;
		this.startIndex = startIndex;
		this.tags = tags;
	}

	public int getCount() {
		return count;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public List<Tag> getTags() {
		return tags;
	}
}
