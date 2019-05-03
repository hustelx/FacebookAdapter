package com.java.facebookadapter.connector;

import java.io.IOException;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;  

import com.java.facebookadapter.common.FacebookCommon;


public class URIConnection 
{
	
	private static JSONParser parser = new JSONParser();
	
	private static String page_content;
	private static String post_content;
	private static String tage1 = "data";
	private static String tage2 = "id";
	private static String tage3 = "paging";
	private static String tage4 = "next";
	private static String type  = "?fields=/comments,is_published";
	private static String uri;
	
	public static void getContentOfFacebookPage(String url) throws IOException, ParseException{
		page_content = Jsoup.connect(url).ignoreContentType(true).execute().body();
		getPostId();
	}
	
	
	
	private static void getPostId() throws IOException, ParseException{
		
		 
		 JSONObject post = new JSONObject();
		 String post_id;
		 String postStringData;
		 JSONObject pageContentJsonObj = (JSONObject)parser.parse(page_content);
		 JSONArray pageContentJsonArray = (JSONArray) pageContentJsonObj.get(tage1);
		 JSONObject postContentJsonObj;
		 for(Object o : pageContentJsonArray){
			 
			 post = (JSONObject)o;
			 post_id = (String) post.get(tage2);
			 uri = URIFormation.urlFormationForFacebookPost(post_id,type);
			 postStringData = getContentOfPost(uri);
			 postContentJsonObj = (JSONObject) parser.parse(postStringData);
			 postContentJsonObj=formateJsonObject(postContentJsonObj);
			 
			 
			 
			 //FileHandler.putDataIntoFile(postContentJsonObj,AccessPropertiesFile.getObject().property.getProperty(FacebookCommon.POST_DATA_FILE));
		 }
		 
		 JSONObject arr1 = (JSONObject)pageContentJsonObj.get(tage3);
		 String link = (String) arr1.get(tage4);
		 if(link != null){
			 getContentOfFacebookPage(link);	 
		 }
	}
	
	
	private static JSONObject formateJsonObject(JSONObject postContentJsonObject){
		
		 postContentJsonObject.put("type", AccessPropertiesFile.getObject().property.getProperty(FacebookCommon.FB_TYPE));
		 
		 postContentJsonObject.put("media", postContentJsonObject.get("picture"));
		 postContentJsonObject.put("title", postContentJsonObject.get("message"));
		 postContentJsonObject.put("timestamp", postContentJsonObject.get("created_time"));
		  
		 
		 JSONObject temp=(JSONObject) postContentJsonObject.get("from");
		 temp.remove("id");
		 
		 postContentJsonObject.put("author",temp);
		 postContentJsonObject.remove("from");
		 postContentJsonObject.remove("picture");
		 postContentJsonObject.remove("message");
		 postContentJsonObject.remove("created_time");
		 
		 System.out.println(postContentJsonObject);
		 
		return postContentJsonObject;
	}
	
	 private static String getContentOfPost(String uri) throws IOException, ParseException{
		 post_content = Jsoup.connect(uri).ignoreContentType(true).execute().body();
		 return post_content;
	 }
	//"timestamp": "2017-07-21T08:28:02+0000"
}


