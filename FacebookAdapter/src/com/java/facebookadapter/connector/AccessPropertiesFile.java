package com.java.facebookadapter.connector;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccessPropertiesFile {

	private static AccessPropertiesFile propertiesObj=null;
	public Properties property;
	
	private AccessPropertiesFile(){
		property = new Properties();
		InputStream input = null;
		
		try{
			
			input = new FileInputStream("src/main/resources/FacebookAdapter.properties");
			property.load(input);
			
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			if(input!=null){
				try{
					input.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			} 
		}
	}
	
	public static AccessPropertiesFile getObject(){
		if(propertiesObj == null)
			return propertiesObj = new AccessPropertiesFile();
		else
			return propertiesObj;
	}

}
