package com.file.readWrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class MethodBodyRead {
	public static void main(String[] args) throws IOException {
		
		
		getMethodBody ();
	
		}

	
	
	
	public static void getMethodBody () throws IOException {
		// TODO Auto-generated method sargstub
				FileOutputStream bintxt=null;
				String data=null;
			 
				
				//System.out.print("Enter source file  Path- ");
				//String path = scanner.next();

				// File myObj = new
				// File("E://strutsApp//LoginExampleStruts1//src//com//example//action//LoginAction.java");
				//File filePath = new File(path);
				File filePath = new File("E://strutsApp//LoginExampleStruts1//src//com//example//action");
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
					File filePath2 = new File("E://strutsApp//LoginExampleStruts1//src//com//example//action" + "//" + fileName);
					pathReader = new Scanner(filePath2);
					String actionClass = fileName.substring(0, fileName.length() - 5);
					//while (pathReader.hasNextLine()) {
					String removeextends="";
					String removeline="";
					String []formObject=null;
					
					while (pathReader.hasNextLine()) {
						data = pathReader.nextLine();
						
						
						if(!data.contains("com")&& !data.contains("org")&&! data.contains("HttpServlet")&& !data.contains("java")&& !data.contains("Action")
								&&! data.contains("throws")&&! data.contains("form")&&! data.contains("@Override")	){
							if(data.contains("DAO")){
								 continue;
							}
							 
							
							bintxt = new FileOutputStream(new File(
									"D://fileread//methodBodyTxt//" + actionClass+ ".txt"), true);
						  String failurePage=actionClass+"Failure";;
						   
						  if(data.contains("return")&& data.contains("failure")||data.contains("return")&& data.contains("error")){
							  data="return new ModelAndView("+"\"" + failurePage + "\""+");";
									  
						  }
						  
						  if(data.contains("return")&& data.contains("success")){
							  data="return  modelView("+"\"" + actionClass + "\""+");";
									  
						  }
						  
						  
							//byteArray=data.getBytes();
							String binData = new String();
							binData = binData + data;
							byteArray = binData.getBytes();
							bintxt.write(byteArray);
							bintxt.write(10);
							
						}
						
					}
					
					 FileInputStream binFile = new FileInputStream(
							"D://fileread//methodBodyTxt//" + actionClass + ".txt");
					
					// read from controller txt file
					String binFileTxt = "";
					while ((in = binFile.read()) != -1) {

						binFileTxt = binFileTxt + String.valueOf((char) in);

					}
					binFileOut = new FileOutputStream(
							"D://fileread//methodBody//" + actionClass+ ".java");
					byteArray1=binFileTxt.getBytes();
					binFileOut.write(byteArray1);
					bintxt.close();
					 FileOutputStream binTxtDelete = new FileOutputStream(
							"D://fileread//methodBodyTxt//" + actionClass + ".txt");
					binTxtDelete.write(("").getBytes());
					binTxtDelete.close(); 
					//break;
					//}
			if(i==listOfFiles.length)		
		    break;
			}
				
			}
}
