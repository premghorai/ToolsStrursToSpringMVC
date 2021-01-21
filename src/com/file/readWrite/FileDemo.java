package com.file.readWrite;

import java.io.File;

 

public class FileDemo {
	   public static void main(String[] args) {      
	      File f = null;
	      File f1 = null;
	      String v, v1;
	      boolean bool = false;
	      String data="public class LoginForm extends ActionForm {";
	      try {
	    	  
	    	String  removeextends=data.substring(0, data.length() - 20);
	    	
	    	System.out.println(removeextends);
	    	  
	    	  
	     /*    // create new files
	         f = new File("D://fileread");
	         f1 = new File("D://fileread//filename.txt");*/
	         
	         
	        /* File[] listOfFiles = f.listFiles();

	         for (int i = 0; i < listOfFiles.length; i++) {
	           if (listOfFiles[i].isFile()) {
	             System.out.println("File " + listOfFiles[i].getName());
	           } else if (listOfFiles[i].isDirectory()) {
	             System.out.println("Directory " + listOfFiles[i].getName());
	           }
	         }*/
	         
	        
	         
	      } catch(Exception e) {
	         // if any error occurs
	         e.printStackTrace();
	      }
	   }
	}