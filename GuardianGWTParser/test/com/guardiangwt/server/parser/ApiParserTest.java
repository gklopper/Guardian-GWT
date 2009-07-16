package com.guardiangwt.server.parser;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.guardiangwt.parser.client.model.Article;
import com.guardiangwt.parser.client.model.FilterTag;
import com.guardiangwt.parser.client.model.Gallery;
import com.guardiangwt.parser.client.model.Search;
import com.guardiangwt.parser.client.model.Tag;
import com.guardiangwt.parser.client.model.Tags;
import com.guardiangwt.parser.server.ApiParser;

public class ApiParserTest {
	
	@Test
	public void shouldParseFullSearchXml() throws IOException, SAXException, ParserConfigurationException, ParseException {
		Search search = ApiParser.parseSearch(getXml("full_search.xml"));
		
		Assert.assertEquals(31569, search.getCount());
		Assert.assertEquals(14,search.getStartIndex());
		Assert.assertEquals(2, search.getResults().size());
		Assert.assertEquals(25, search.getFilters().size());
		
		Assert.assertEquals("", search.getFilters().get(0).getWebUrl());
		
		FilterTag filter = search.getFilters().get(1);
		Assert.assertEquals("UK news", filter.getName());
		Assert.assertEquals("/uk", filter.getFilter());
		Assert.assertEquals("http://api.guardianapis.com/content/search?filter=/uk", filter.getApiUrl());
		Assert.assertEquals("http://www.guardian.co.uk/uk", filter.getWebUrl());
		Assert.assertEquals(10642, filter.getCount());
		Assert.assertEquals("http://api.guardianapis.com/content/search?count=2&q=environment&format=xml&filter=/uk", filter.getFilterUrl());
		
		Article article = (Article) search.getResults().get(0);
		
		Assert.assertEquals("http://api.guardianapis.com/content/item/4080614", article.getApiUrl());
		Assert.assertEquals("http://www.guardian.co.uk/uk/2000/oct/24/labour.politics", article.getWebUrl());
		Assert.assertEquals("article", article.getType());
		Assert.assertEquals(4080614, article.getId());
		Assert.assertEquals("The Guardian", article.getPublication());
		Assert.assertEquals("Blair accused of failing the environment", article.getHeadline());
		Assert.assertEquals("The prime minister is preparing to deliver his first speech on green issues in nearly four years. But he is deeply at odds with campaigners   Special report: New Labour in power", article.getStandfirst());
		Assert.assertEquals("John Vidal", article.getByline());
		Assert.assertEquals("UK news", article.getSectionName());
		Assert.assertEquals("Green groups waiting for Tony Blair's first speech on the environment in almost four years yesterday challenged the prime minister to admit that Labour had failed to advance many environmental issues since coming to power. ", article.getTrailText());
		Assert.assertEquals("Blair accused of failing the environment", article.getLinkText());
		
		Assert.assertEquals(6, article.getTaggedWith().size());
		Tag tag = article.getTaggedWith().get(4);
		Assert.assertEquals("Politics", tag.getName());
		Assert.assertEquals("keyword", tag.getType());
		Assert.assertEquals("/politics", tag.getFilter());
		Assert.assertEquals("http://api.guardianapis.com/content/search?filter=/politics", tag.getApiUrl());
		Assert.assertEquals("http://www.guardian.co.uk/politics", tag.getWebUrl());
	}

	@Test
	public void shouldParseTagSearchXml() throws IOException, SAXException, ParserConfigurationException, ParseException {
		Tags tags = ApiParser.parseTags(getXml("tag_search.xml"));
		
		Assert.assertEquals(6517, tags.getCount());
		Assert.assertEquals(3, tags.getStartIndex());
		Assert.assertEquals(2, tags.getTags().size());
		
		Tag tag = tags.getTags().get(1);
		Assert.assertEquals("http://api.guardianapis.com/content/search?filter=/media/dmgt", tag.getApiUrl());
		Assert.assertEquals("http://www.guardian.co.uk/media/dmgt", tag.getWebUrl());
		Assert.assertEquals("Daily Mail & General Trust", tag.getName());
		Assert.assertEquals("keyword", tag.getType());
		Assert.assertEquals("/media/dmgt", tag.getFilter());
	}
	
	@Test
	public void shouldParseGalleryContent() throws SAXException, IOException, ParserConfigurationException, ParseException {
		
		Gallery content = (Gallery) ApiParser.parseContent(getXml("content_gallery.xml"));
		
		Assert.assertEquals("http://api.guardianapis.com/content/item/344089365", content.getApiUrl());
		Assert.assertEquals("http://www.guardian.co.uk/katine/gallery/2009/mar/04/owino-market-fire-kampala", content.getWebUrl());
		Assert.assertEquals("gallery", content.getType());
		Assert.assertEquals(344089365, content.getId());
		Assert.assertEquals("guardian.co.uk", content.getPublication());
		Assert.assertEquals("Fire at Owino market in Kampala", content.getHeadline());
		Assert.assertEquals("Owino, situated in Uganda's capital, Kampala, is one of the city's largest outdoor markets, with more than 500,000 vendors. Ten years ago, traders set up a secondary market in the car park of neighbouring Nakivubo stadium, which became known as Nakivubo Parkyard Market. This market, like the rest of Owino, was a warren of stalls selling clothes, food, spices and household goods. In the early hours of 25 February 2009, a fire ripped through Nakivubo Parkyard market, destroying goods and livelihoods. A Guardian team of journalists visited the market before and after the fire. Here's what they found.By Annie Kelly and Laurence Topham", content.getStandfirst());
		Assert.assertEquals("Annie Kelly", content.getByline());
		Assert.assertEquals("Katine", content.getSectionName());
		Assert.assertEquals(" Owino, situated in Uganda's capital, Kampala, is one of the city's largest outdoor markets, with more than 500,000 vendors. In the early hours of 25 February 2009, a fire ripped through a part of the market, destroying goods and livelihoods. A Guardian team of journalists visited the market before and after the fire. Here's what they found ", content.getTrailText());
		Assert.assertEquals("Fire at Owino market in Kampala", content.getLinkText());
		
		Assert.assertEquals(6, content.getTaggedWith().size());
		Tag tag = content.getTaggedWith().get(4);
		Assert.assertEquals("Uganda", tag.getName());
		Assert.assertEquals("keyword", tag.getType());
		Assert.assertEquals("/world/uganda", tag.getFilter());
		Assert.assertEquals("http://api.guardianapis.com/content/search?filter=/world/uganda", tag.getApiUrl());
		Assert.assertEquals("http://www.guardian.co.uk/world/uganda", tag.getWebUrl());
	}
	
	private InputStream getXml(String fileName) throws IOException {
		return getClass().getClassLoader().getResourceAsStream("com/guardiangwt/server/parser/" + fileName);
	}

}
