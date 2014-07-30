package edu.ucsc;

public class InputTest {
	public enum Command{
		PUT, LOOK, WALK
	}
	public static void main(String[] args){ //remember to make ALL public
		String test = "look something this this this";
		System.out.println(test);
		System.out.println(getFirstWord(test));
		System.out.println(isCommand(test));
	}
	public static boolean isCommand(String line){
		String firstWord = getFirstWord(line);
		Command command = Command.valueOf(firstWord.toUpperCase()); //test how it fails later
		switch(command){
			case PUT:  //can "fall through" 
			case LOOK:
			case WALK:
				return true;
			default:
				return false;
		}
	}
	public static String getFirstWord(String line){
		String[] lineArray = line.split(" ");
		return lineArray[0];
	}

}
