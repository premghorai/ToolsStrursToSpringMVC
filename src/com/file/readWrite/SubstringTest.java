package com.file.readWrite;

public class SubstringTest {
	public static void main(String[] args) {
		String path = "D://fileread//LoginController.txt";
		String input = "1234567.89";
	String	lastFourDigits = input.substring(0,input.length()-3 );
	System.out.println("len " + lastFourDigits);
	
		/*for (int i = 1; i <= path.length(); i++) {
			
		}
		System.out.println("len " + path.length());
		String kept = path.substring(0, path.indexOf("."));
		System.out.println(kept);
		
		String[] kept1=kept.split("//");
		System.out.println(kept1.toString());
		for(String i: kept1){
			String x=i.substring(i.length()-1);
			System.out.println(x);
			
		}*/
		
	}

}
