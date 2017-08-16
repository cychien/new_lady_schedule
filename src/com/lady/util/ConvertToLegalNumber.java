package com.lady.util;

public class ConvertToLegalNumber {
	public static int convertTo(String word) {
		if(word.equals("01"))
			return 1;
		else if(word.equals("02"))
			return 2;
		else if(word.equals("03"))
			return 3;
		else if(word.equals("04"))
			return 4;
		else if(word.equals("05"))
			return 5;
		else if(word.equals("06"))
			return 6;
		else if(word.equals("07"))
			return 7;
		else if(word.equals("08"))
			return 8;
		else if(word.equals("09"))
			return 9;
		else 
			return Integer.valueOf(word);
	}
}
