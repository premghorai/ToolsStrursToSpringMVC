package com.file.readWrite;

import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String data = null;
		try {

			System.out.print("Enter source file  Path- ");
			String path = scanner.next();

			// File myObj = new
			// File("E://strutsApp//LoginExampleStruts1//src//com//example//action//LoginAction.java");
			File filePath = new File(path);

			File[] listOfFiles = filePath.listFiles();
			Scanner pathReader = null;
			String fileName = null;
			int in = 0;
			String dynamicClassName = "";
			byte[] byteArray = null;
			FileOutputStream fileOut = null;
			String [] formArray=null;
			String formObject="";
			String [] daoArray=null;
			String daoObject="";
			String daoConcade="";
			
			String [] serviceArray=null;
			String serviceObject="";
			
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					fileName = listOfFiles[i].getName();
					System.out.println(" File Nme" + fileName);
				}
				File filePath2 = new File(path + "\\" + fileName);
				String	data2=null;
				pathReader = new Scanner(filePath2);

				// Removing .java from action class fileName
				String actionName = fileName.substring(0, fileName.length() - 5);
                // Calling  methodBody calss
				MethodBodyRead.getMethodBody();
				// Write the class name Dynamic
				fileOut = new FileOutputStream("D://fileread//text//" + actionName + ".txt");

				dynamicClassName = "public class " + " " + actionName + "Controller" + " " + "{" + "\r" ;
				byteArray = dynamicClassName.getBytes();

				fileOut.write(byteArray);
				// Read from property file Annotation
				
				 FileReader annotationReader=new FileReader("D://fileread//annotation//annotation.properties");  
			      
				    Properties annotationProperties=new Properties();  
				    annotationProperties.load(annotationReader);
			
					while (pathReader.hasNextLine()) {

						data = pathReader.nextLine();
						 
						
						System.out.println(data);

						// internal while loop for finding all line in the class
						while (pathReader.hasNextLine()) {
							String data1 = pathReader.nextLine();
							System.out.println(data1);
							byte[] byteCommon = null;//
							FileOutputStream commonImport = null;

							if (data1.contains("import java") || data1.contains(".form.")) {

								commonImport = new FileOutputStream(new File(
										"D://fileread//text//" + actionName + "Import" + ".txt"), true);
								
								String commonData = new String();
								commonData = commonData + data1;
								byteCommon = commonData.getBytes();
								//boolean empty;
								//if(commonImportRead.read() == -1){
								commonImport.write(byteCommon);
								commonImport.write(10);
								commonImport.write(("").getBytes());
								//}
								commonImport.close();
								
							}
							//Form object finding
							
							if(data1.contains("form;")){
								 formArray=data1.trim().split(" ");
								  formObject=formArray[1];
								  data1="@ModelAttribute"+"("+"\"" + formObject + "\""+")".replaceAll("\\s", "");//+formObject[0]+" "+removeline;
								  //data="@ModelAttribute"+"("+"\"" + removeline + "\""+")"+formObject[0]+" "+removeline;
								   data1=data1+formArray[0]+" "+formObject;
								   data2=data1;
								  System.out.println("data2++=================="+data2);
							  }
							// DAO object finding
							if(data1.contains("DAO") && !data1.contains(".dao")){
								 daoArray=data1.trim().split("=");
								  daoObject=daoArray[0];
								  daoConcade=daoObject;
								  System.out.println("data3++=================="+daoObject);
							  }
							
							// Service object finding
							if(data1.contains("ServiceImpl") && !data1.contains(".service")){
								serviceArray=data1.trim().split("=");
								serviceObject=serviceArray[0];
								  //daoConcade=daoObject;
								  System.out.println("data4++=================="+serviceObject);
							  }
							
						}

						FileInputStream dynamicFile = new FileInputStream(
								"D://fileread//text//" + actionName + ".txt");

						FileInputStream fileInputStream = new FileInputStream(
								"D://fileread//input//ReadImport.txt");

						FileInputStream commonFileRead = new FileInputStream(
								"D://fileread//text//" + actionName + "Import" + ".txt");
						
						FileInputStream methodBodyRead = new FileInputStream(
								"D://fileread//methodBody//" + actionName + ".java");

						in = 0;
						dynamicClassName = "";
						String controllerImport="";
						String importActionReadString = "";
						String margeFile = "";
						String methodBody="";
						// read from common import file
						while ((in = commonFileRead.read()) != -1) {

							importActionReadString = importActionReadString + String.valueOf((char) in);

						}

						// read from controller txt file
						while ((in = fileInputStream.read()) != -1) {

							controllerImport = controllerImport + String.valueOf((char) in);

						}
						// read from dynamic txt file
						while ((in = dynamicFile.read()) != -1) {

							margeFile = margeFile + String.valueOf((char) in);

						}
						
						// read from MethodBody java file
						while ((in = methodBodyRead.read()) != -1) {

							methodBody = methodBody + String.valueOf((char) in);

						}
						
					//****************** For generating get mapping method*************************************************
						
					String actionImportConcate = importActionReadString.concat(controllerImport);
					String controllerAnnotation = annotationProperties.getProperty("key.controller");
					String controllerAnnotationStr = actionImportConcate + "\r".concat(controllerAnnotation);
					// dynamicClassName = dynamicClassName.concat(margeFile);
					String controllerAnnotationStrMarge = dynamicClassName.concat(controllerAnnotationStr + "\r");
					dynamicClassName = controllerAnnotationStrMarge.concat(margeFile);

					//String getMapping = annotationProperties.getProperty("key.requestmapping");
					String daoAutowire="";
					if(daoObject!=""){
					 daoAutowire="@AutoWire"+"\r"+daoObject+";";
					 
					 System.out.println("Inside if codition"+daoAutowire);
					}
					
					
					String serviceAutowire="";
					if(serviceObject!=""){
						serviceAutowire="@AutoWire"+"\r"+serviceObject+";";
					 
					 System.out.println("Inside if codition service"+serviceObject);
					}
					
					String getMapping =serviceAutowire+"\r\r"+ daoAutowire+"\r\r"+ "@GetMapping("+"\"/get" + actionName + "\""+")";
					String modelView = annotationProperties.getProperty("key.modelview");
					//String formBoject=MethodBodyRead.getFormObject();
					
					//System.out.println("formBojectformBoject++++"+formBoject);
					/*String getMappingConcade = getMapping + "\r".concat(modelView) + " "
							+ actionName.substring(0, actionName.length() - 6).toLowerCase()+"(" + data2 + ")"+ " " + "{";*/
					
					String getMappingConcade = getMapping + "\r".concat(modelView) + " "
							+ actionName.substring(0, actionName.length() - 6).toLowerCase()+"()"+ " " + "{";
					
					
					String controllerMethodStrConcade = dynamicClassName + "\r".concat(getMappingConcade);

					//String modelViewproperty = annotationProperties.getProperty("key.mav");
					
					String modelViewproperty =  "ModelAndView modelview = new ModelAndView("+"\"" + actionName + "\""+")";
					
					String modelViewConcade = controllerMethodStrConcade + "\r".concat(modelViewproperty);
					// modelViewproperty+"\r".concat(controllerMethodStrConcade);

					String returnProperty = annotationProperties.getProperty("key.return");
					String returnPropertyConcade = modelViewConcade + "\r".concat(returnProperty);

					String finalWriteForController = returnPropertyConcade + "\r" + "}";
					
					//********************* For generating post mapping method********************************************************************
					String postMapping =  "@PostMapping("+"\"/post" + actionName + "\""+")";
					String modelViewPost = annotationProperties.getProperty("key.modelview");
					//String formBoject=MethodBodyRead.getFormObject();
					
					//System.out.println("formBojectformBoject++++"+formBoject);
					String postMappingConcade = postMapping + "\r".concat(modelViewPost) + " "
							+ actionName.substring(0, actionName.length() - 6).toLowerCase()+"(" + data2 + ")"+ " " + "{";
					
					String modelViewpropertyPost =  "ModelAndView modelview = new ModelAndView("+"\"" + actionName + "\""+")";
					
					String modelViewConcadePost = postMappingConcade + "\r".concat(modelViewpropertyPost);
					// modelViewproperty+"\r".concat(controllerMethodStrConcade);

					String returnPropertyPost = annotationProperties.getProperty("key.return");
					//String returnPropertyConcadePost = modelViewConcadePost + "\r".concat(returnPropertyPost);
					String returnPropertyConcadePost = modelViewConcadePost + "\r".concat(methodBody);
					String finalWriteForControllerPost = returnPropertyConcadePost + "\r" + "}";
					 byte[] byteArrayPost=finalWriteForControllerPost.getBytes();
					
					// commonReadString =
					// commonReadString.concat(controllerAnnotationStr);
					String outPutControllerName = actionName + "Controller.java";
					fileOut = new FileOutputStream("D://fileread//output//" + outPutControllerName);
					byteArray = finalWriteForController.getBytes();

						fileOut.write(byteArray);
						fileOut.write(10);
						fileOut.write(byteArrayPost);
						fileOut.close();
						// Empty the existing file ActionImport
						FileOutputStream fileOutDelete = new FileOutputStream("D://fileread//text//" + actionName + "Import" + ".txt");
						fileOutDelete.write(("").getBytes());
						fileOutDelete.close();

						System.out.println("Done reading and writing!!");
						break;
					}

				//}
			}
			pathReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
