package com.guardiangwt.parser.server;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.guardiangwt.parser.client.model.Article;
import com.guardiangwt.parser.client.model.Content;
import com.guardiangwt.parser.client.model.ContentWithDuration;
import com.guardiangwt.parser.client.model.FilterTag;
import com.guardiangwt.parser.client.model.Gallery;
import com.guardiangwt.parser.client.model.Search;
import com.guardiangwt.parser.client.model.Tag;
import com.guardiangwt.parser.client.model.Tags;

public class ApiParser {
	
	public static Search parseSearch(InputStream xml) throws SAXException, IOException, ParserConfigurationException, ParseException {
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
		
		Element searchElement = document.getDocumentElement();
		
		return buildSearch(searchElement);
	}
	
	public static Tags parseTags(InputStream xml) throws SAXException, IOException, ParserConfigurationException, ParseException {
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
		
		Element tagsElement = document.getDocumentElement();
		
		return buildTags(tagsElement);
	}
	
	public static Content parseContent(InputStream xml) throws SAXException, IOException, ParserConfigurationException, ParseException {
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xml);
		
		Element contentElement = document.getDocumentElement();
		
		return buildContent(contentElement);
	}
	
	private static Content buildContent(Element item) throws ParseException {
		
		int id = Integer.parseInt(item.getAttribute("id"));
		String type = item.getAttribute("type");
		String apiUrl = item.getAttribute("api-url");
		String webUrl = item.getAttribute("web-url");
		
	    String publication = item.getElementsByTagName("publication").item(0).getTextContent();
	    String headline = item.getElementsByTagName("headline").item(0).getTextContent();
	    
	    String standfirst = elementValueOrNull(item, "standfirst");
	    
	    String byline = item.getElementsByTagName("byline").item(0).getTextContent();
	    String sectionName = item.getElementsByTagName("section-name").item(0).getTextContent();
	    String trailText = item.getElementsByTagName("trail-text").item(0).getTextContent();
	    String linkText = item.getElementsByTagName("link-text").item(0).getTextContent();
	    String trailImage = elementValueOrNull(item, "trail-image");
	    
	   
	    Date publicationDate = new SimpleDateFormat("yyy-MM-dd HH:mm:ss").parse(item.getElementsByTagName("publication-date").item(0).getTextContent().replace("T", " "));
	    
	    Element tags = (Element) item.getElementsByTagName("tagged-with").item(0);
		NodeList tagList = tags.getElementsByTagName("tag");
		List<Tag> taggedWith = new ArrayList<Tag>();
		for (int i = 0; i < tagList.getLength(); i++) {
			taggedWith.add(buildTag((Element)tagList.item(i)));
		}
		
		if ("article".equals(type)) {
			Element typeSpecific = (Element) item.getElementsByTagName("type-specific").item(0);
			String body = typeSpecific.getElementsByTagName("body").item(0).getTextContent();
			return new Article(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith, body);
		}
		
		if ("gallery".equals(type)) {
			Element typeSpecific = (Element) item.getElementsByTagName("type-specific").item(0);
			int pictureCount = Integer.parseInt(typeSpecific.getElementsByTagName("picture-count").item(0).getTextContent());
			return new Gallery(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith, pictureCount);
		}
		
		//these two currently have the same fields
		if ("video".equals(type) || "audio".equals(type)) {
			Element typeSpecific = (Element) item.getElementsByTagName("type-specific").item(0);
			int durationMinutes = Integer.parseInt(typeSpecific.getElementsByTagName("duration-minutes").item(0).getTextContent());
			int durationSeconds = Integer.parseInt(typeSpecific.getElementsByTagName("duration-seconds").item(0).getTextContent());
			return new ContentWithDuration(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith, durationMinutes, durationSeconds);
		}
		
		//interactive currently has only default content fields
		return new Content(id, type, apiUrl, webUrl, publication, headline, standfirst, byline, sectionName, trailText, linkText, trailImage, publicationDate, taggedWith);
	}

	private static Tags buildTags(Element tagsElement) {
		int count = Integer.parseInt(tagsElement.getAttribute("count"));
		int startIndex = Integer.parseInt(tagsElement.getAttribute("start-index"));
		
		NodeList tags = tagsElement.getElementsByTagName("tag");
		List<Tag> tagList = new ArrayList<Tag>();
		for (int i = 0; i < tags.getLength(); i++) {
			tagList.add(buildTag((Element)tags.item(i)));
		}
		return new Tags(count, startIndex, tagList);
	}

	private static Search buildSearch(Element searchElement) throws ParseException {
		int count = Integer.parseInt(searchElement.getAttribute("count"));
		int startIndex = Integer.parseInt(searchElement.getAttribute("start-index"));
		
		Element results = (Element) searchElement.getElementsByTagName("results").item(0);
		NodeList contentList = results.getElementsByTagName("content");
		List<Content> resultList = new ArrayList<Content>();
		for (int i = 0; i < contentList.getLength(); i++) {
			resultList.add(buildContent((Element)contentList.item(i)));
		}
		
		Element filters = (Element) searchElement.getElementsByTagName("filters").item(0);
		NodeList tagList = filters.getElementsByTagName("tag");
		List<FilterTag> filterList = new ArrayList<FilterTag>();
		for (int i = 0; i < tagList.getLength(); i++) {
			filterList.add(buildFilterTag((Element)tagList.item(i)));
		}
		
		
		return new Search(count, startIndex, resultList, filterList);
	}

	private static FilterTag buildFilterTag(Element item) {
		String name = item.getAttribute("name");
		String type = item.getAttribute("type");
		String filter = item.getAttribute("filter");
		String apiUrl = item.getAttribute("api-url");
		String webUrl = item.getAttribute("web-url");
		int count = Integer.parseInt(item.getAttribute("count"));
		String filterUrl = item.getAttribute("filter-url");
		return new FilterTag(name, type, filter, apiUrl, webUrl, count, filterUrl);
	}

	private static String elementValueOrNull(Element item, String elementName) {
		Node standFastNode = item.getElementsByTagName(elementName).item(0);
		if (standFastNode != null) {
	    	return standFastNode.getTextContent();
	    }
		return null;
	}

	private static Tag buildTag(Element item) {
		
		String name = item.getAttribute("name");
		String type = item.getAttribute("type");
		String filter = item.getAttribute("filter");
		String apiUrl = item.getAttribute("api-url");
		String webUrl = item.getAttribute("web-url");
		return new Tag(name, type, filter, apiUrl, webUrl);
	}

}
