package edu.ucsc;
//ALL THE STRING LOGIC :) 
public class Input{
	public static void main(String[] args){ //remember to make ALL public
		String test = "put to the sky";
		System.out.println(test);
		System.out.println(getFirstWord(test));
		System.out.println(isCommand(test));
		System.out.println(getOtherWords(test));
		
	}
	
	public static int isCommand(String line){
		String firstWord = getFirstWord(line);
		String word = firstWord.toString();
		if(word.equalsIgnoreCase("start")){
			return 0;
		}else if(word.equalsIgnoreCase("look")){
			return 1;
		}else if(word.equalsIgnoreCase("walk")){
			return 2;
		}else if(word.equalsIgnoreCase("help")){
			return 3;
		}else if (word.equalsIgnoreCase("turn")){
			return 4;
		}else if(word.equalsIgnoreCase("enter")){
			return 5;
		}
		else if(word.equalsIgnoreCase("exit")){
			return 6;
		}else if(doesEqualsExist(line)){
			return 7;
		}else if(word.equals("Guide")){
			return 8;
		}else if(word.equals("Book")){
			return 9;
		}else if(word.equalsIgnoreCase("next")){
			return 10;
		}else if(word.equalsIgnoreCase("Yes") || word.equalsIgnoreCase("Y") ||word.equalsIgnoreCase("No") || word.equalsIgnoreCase("N")){
			return 11;
		}
		else{
			return -1;
		}
	}
	
	public static String getFirstWord(String line){ //make this clean like following function
		int indexOfSpace = line.indexOf(" ");
		if (indexOfSpace == -1){
			return line;
		}
		return line.substring(0, indexOfSpace);
	}
	
	public static String getOtherWords(String line){
		int indexOfSpace = line.indexOf(" ");
		if (indexOfSpace == -1){
			return "";
		}
		return line.substring(indexOfSpace + 1);
	}
	
	public static String getAllWords(String line){
		return line;
	}
	
	private static boolean doesEqualsExist(String line){
		int indexOfEquals = line.indexOf(" = ");
		if (indexOfEquals ==-1){
			return false;
		}else{
			return true;
		}
	}
	
	
}
