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
		if(word.equalsIgnoreCase("start") || word.equalsIgnoreCase("next")){
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
		}else if(word.contains(" = ")){
			return 7;
		}
		else{
			return -1;
		}
	}
	
	public static String getFirstWord(String line){ //make this clean like following function
		//String[] lineArray = line.split(" "); //using more memory than necessary
		//return lineArray[0].toUpperCase();
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
	
	
	
}
