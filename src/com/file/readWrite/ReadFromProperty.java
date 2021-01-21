package com.file.readWrite;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFromProperty {
	
	static public String  getProperty(String key){
		 String str=null;
	try  {
		 FileReader reader=new FileReader("D://fileread//annotation//annotation.properties");  
      Properties prop = new Properties();

      if (reader == null) {
          System.out.println("Sorry, unable to find config.properties");
          return "Sorry, unable to find properties";
      }

      //load a properties file from class path, inside static method
      prop.load(reader);
      str=prop.getProperty("key.restController");
    String  str1=prop.getProperty("key.requestmapping");
      
      System.out.println(" String With property value========"+str+"+++++++++++"+str1);

  } catch (IOException ex) {
      ex.printStackTrace();
     }
	return str;
	
}
	
	public static void main(String[] args){
		getProperty("key.restController");
	}

}
