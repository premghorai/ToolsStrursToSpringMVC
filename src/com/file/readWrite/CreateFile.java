package com.file.readWrite;

import java.io.FileWriter; 
import java.io.IOException; 
class CreateFile 
{ 
    public static void main(String[] args) throws IOException 
    { 

    	
    	
    	String str= "@Controller\r"+
    			"public class UserController {\r"+
    		"@Autowired\r"+
    		"UserRepository userRepo;\r"+

    		
    		 "}" ;
  
        // attach a file to FileWriter  
        FileWriter fw=new FileWriter("D://fileread//Login.java"); 
  
        // read character wise from string and write  
        // into FileWriter  
        for (int i = 0; i < str.length(); i++) 
            fw.write(str.charAt(i)); 
  
        System.out.println("Writing successful"); 
        //close the file  
        fw.close(); 
    }
}