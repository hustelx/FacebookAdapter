package com.java.facebookadapter.connector;

import com.java.facebookadapter.common.FacebookCommon;

public class URIFormation 
{
	
	public static AccessPropertiesFile accessFile=AccessPropertiesFile.getObject();
	private static String access_token = accessFile.property.getProperty(FacebookCommon.FB_APPID) + "|" 
										+ accessFile.property.getProperty(FacebookCommon.FB_APPSECRET);
	private static String baseForFacebookGraphAPI = "https://graph.facebook.com/v3.2";
	private static String node;
	private static String parameters;
	
	public static String urlFormationForFacebookPage(String id,String type){
		
		String url = null;
		 node = "/" + id + type;
		 parameters = "/?access_token=" + access_token;
		 url = baseForFacebookGraphAPI + node + parameters;
		 
		 return url;
	}
	
	public static String urlFormationForFacebookPost(String id,String type){
		
		String url = null;
		 node = "/" + id;
		 parameters = "/?fields=" + accessFile.property.getProperty(FacebookCommon.FB_URLFIELDS) + 
				 		"&access_token="+access_token;
		 url = baseForFacebookGraphAPI + node + parameters;
		 
		 return url;	
	}
}
