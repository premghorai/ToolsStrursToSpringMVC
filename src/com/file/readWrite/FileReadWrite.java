package com.file.readWrite;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;
public class FileReadWrite {
	

	   public static void main(String[] args) {

	     try {

	           FileInputStream fin = new FileInputStream("D://fileread//filename.txt");

	           int i = 0;
	           String s = "";

	           while((i=fin.read())!=-1) {

	               s = s + String.valueOf((char)i);

	           }

	           FileOutputStream fout = new 
	           FileOutputStream("D://fileread//LoginController.java");
	           byte[] b = s.getBytes();

	           fout.write(b);
	           fout.close();

	           System.out.println("Done reading and writing!!");

	      } catch(Exception e){
	         System.out.println(e);
	      }

	    }


}
