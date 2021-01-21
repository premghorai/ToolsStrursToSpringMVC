package com.file.readWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class BinClassWrite {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream bintxt=null;
		String data=null;
	 
		
		//System.out.print("Enter source file  Path- ");
		//String path = scanner.next();

		// File myObj = new
		// File("E://strutsApp//LoginExampleStruts1//src//com//example//action//LoginAction.java");
		//File filePath = new File(path);
		File filePath = new File("E://strutsApp//LoginExampleStruts1//src//com//example//form");
		File[] listOfFiles = filePath.listFiles();
		Scanner pathReader = null;
		String fileName = null;
		int i = 0;
		int in = 0;
		
		byte[] byteArray = null;
		byte[] byteArray1 = null;
		FileOutputStream binFileOut = null;
		for ( i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				//System.out.println("File " + listOfFiles[i].getName());
				fileName = listOfFiles[i].getName();
				System.out.println(" File Nme" + fileName);
			}
			File filePath2 = new File("E://strutsApp//LoginExampleStruts1//src//com//example//form" + "//" + fileName);
			pathReader = new Scanner(filePath2);
			String binClassnName = fileName.substring(0, fileName.length() - 5);
			//while (pathReader.hasNextLine()) {
			String removeextends="";
			String removeline=" ";
			
			while (pathReader.hasNextLine()) {

				  data = pathReader.nextLine();
				  System.out.println(data);
				  
				  if(data.contains("extends ActionForm")){
					  removeextends=data.substring(0, data.length() - 20);
					  data=removeextends+" "+"{";
				  }
				  
				  else if(data.contains("@Override")){
					  data=removeline;
				  }
				
				  else if(data.contains("public void reset")){
					  data=removeline;
				  }
				  
				  else if(data.contains("this.password = null;}")){
					  data=removeline;
				  }
				bintxt = new FileOutputStream(new File(
						"D://fileread//binTxt//" + binClassnName+ ".txt"), true);
				byteArray=data.getBytes();
				String binData = new String();
				binData = binData + data;
				byteArray = binData.getBytes();
				//boolean empty;
				//if(commonImportRead.read() == -1){
				bintxt.write(byteArray);
				bintxt.write(10);
				//bintxt.write(("").getBytes());
				bintxt.close();
				
			}
			
			FileInputStream binFile = new FileInputStream(
					"D://fileread//binTxt//" + binClassnName + ".txt");
			
			// read from controller txt file
			String binFileTxt = "";
			while ((in = binFile.read()) != -1) {

				binFileTxt = binFileTxt + String.valueOf((char) in);

			}
			binFileOut = new FileOutputStream(
					"D://fileread//binOut//" + binClassnName+ ".java");
			byteArray1=binFileTxt.getBytes();
			binFileOut.write(byteArray1);
			//bintxt.close();
			//binFileOut.write(("").getBytes());34
			binFileOut.close();
			FileOutputStream binTxtDelete = new FileOutputStream(
					"D://fileread//binTxt//" + binClassnName + ".txt");
			binTxtDelete.write(("").getBytes());
			binTxtDelete.close();
			//break;
			//}
	if(i==listOfFiles.length)		
    break;
	}
	}
}
