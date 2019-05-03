package com.java.facebookadapter.connector;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.java.facebookadapter.common.FacebookCommon;

public class FacebookAdapterMain {
	
	private static String tage1 = "name";
	private static String type = "/feed";
	
	//start publisher
	public static void main(String argc[]){
		
		fbParser();
	}
	
	public static void fbParser(){
		
		JSONParser parser = new JSONParser();
		JSONObject page;
		String page_id;
		String url = null;
		try{
			 
			 JSONArray fileObj = (JSONArray)parser.parse(new FileReader(AccessPropertiesFile.getObject().property.getProperty(FacebookCommon.PAGES_FILE)));
			 for(Object o:fileObj){
				 
				 page = (JSONObject)o;
				 page_id = (String) page.get(tage1);
				 url = URIFormation.urlFormationForFacebookPage(page_id,type);
				 URIConnection.getContentOfFacebookPage(url); 
			 }
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
	}
}


//EEE MMM dd HH:mm:ss Z yyyy
//flickr, tumblr, pinterest, foursquare/swarm
//10 YouTube Channels (e.g. SG Reckless Drivers, etc) 


