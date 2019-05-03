package com.java.facebookadapter.connector;

import java.io.File;
import java.io.FileWriter;
import org.json.simple.JSONObject;

public class FileHandler {
	
	private static File contentFile;
	
	public static void putDataIntoFile(JSONObject pageContent,String fileName){
		
		FileWriter fileWriter = null;
		try{
			
			contentFile=new File(fileName);
			contentFile.createNewFile();
			fileWriter = new FileWriter(contentFile.getAbsoluteFile(),true);
			fileWriter.write(pageContent.toJSONString());
			fileWriter.flush();
			
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			
			try{
				if(fileWriter != null)
					fileWriter.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}

}