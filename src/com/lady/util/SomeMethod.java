package com.lady.util;

public class SomeMethod {
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

	public static String convertToRestName(String str) {
		if(str.equals("01"))
			return "公休";
		else if(str.equals("02"))
			return "特休";
		else if(str.equals("03"))
			return "婚假";
		else if(str.equals("04"))
			return "產假";
		else if(str.equals("05"))
			return "喪假";
		else if(str.equals("06"))
			return "公假";
		else if(str.equals("07"))
			return "病假";
		else
			return null;
	}
}
