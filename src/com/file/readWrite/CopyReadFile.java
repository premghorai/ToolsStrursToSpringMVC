package com.file.readWrite;

import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files

public class CopyReadFile {

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
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					fileName = listOfFiles[i].getName();
					System.out.println(" File Nme" + fileName);
				}
				File filePath2 = new File(path + "\\" + fileName);

				pathReader = new Scanner(filePath2);

				// Removing .java from action class fileName
				String actionName = fileName.substring(0, fileName.length() - 5);

				// Write the class name Dynamic
				fileOut = new FileOutputStream("D://fileread//text//" + actionName + ".txt");

				dynamicClassName = "public class " + " " + actionName + "Controller" + " " + "{" + "\r" + "}";
				byteArray = dynamicClassName.getBytes();

				fileOut.write(byteArray);
			
					while (pathReader.hasNextLine()) {

						data = pathReader.nextLine();
						System.out.println(data);

						// internal while loop for finding all line in the class
						while (pathReader.hasNextLine()) {
							
							/*File f= new File("D://fileread//text//" + actionName + "Import" + ".txt");
							if(f.delete()){
								System.out.println("Existing file has been deleted");
							}*/
							String data1 = pathReader.nextLine();
							System.out.println(data1);
							byte[] byteCommon = null;//
							FileOutputStream commonImport = null;

							if (data1.contains("import java")) {

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
							//commonImport.flush();
							
						}

						FileInputStream dynamicFile = new FileInputStream(
								"D://fileread//text//" + actionName + ".txt");

						FileInputStream fileInputStream = new FileInputStream(
								"D://fileread//input//CommonImport.txt");

						FileInputStream commonFileRead = new FileInputStream(
								"D://fileread//text//" + actionName + "Import" + ".txt");

						in = 0;
						dynamicClassName = "";
						String commonReadString = "";
						String margeFile = "";
						// read from common import file
						while ((in = commonFileRead.read()) != -1) {

							commonReadString = commonReadString + String.valueOf((char) in);

						}

						// read from controller txt file
						while ((in = fileInputStream.read()) != -1) {

							dynamicClassName = dynamicClassName + String.valueOf((char) in);

						}
						// read from dynamic txt file
						while ((in = dynamicFile.read()) != -1) {

							margeFile = margeFile + String.valueOf((char) in);

						}

						dynamicClassName = dynamicClassName.concat(margeFile);
						commonReadString = commonReadString.concat(dynamicClassName);
						String outPutControllerName = actionName + "Controller.java";
						fileOut = new FileOutputStream("D://fileread//output//" + outPutControllerName);
						byteArray = commonReadString.getBytes();

						fileOut.write(byteArray);
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
