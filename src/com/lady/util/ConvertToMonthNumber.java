package com.lady.util;

import java.util.Calendar;

public class ConvertToMonthNumber {
	public static int convertFrom(String month) {
		if(month.equals("January")) {
			return Calendar.JANUARY;
		}
		else if(month.equals("February")) {
			return Calendar.FEBRUARY;
		}
		else if(month.equals("March")) {
			return Calendar.MARCH;
		}
		else if(month.equals("April")) {
			return Calendar.APRIL;
		}
		else if(month.equals("May")) {
			return Calendar.MAY;
		}
		else if(month.equals("June")) {
			return Calendar.JUNE;
		}
		else if(month.equals("July")) {
			return Calendar.JULY;
		}
		else if(month.equals("August")) {
			return Calendar.AUGUST;
		}
		else if(month.equals("September")) {
			return Calendar.SEPTEMBER;
		}
		else if(month.equals("October")) {
			return Calendar.OCTOBER;
		}
		else if(month.equals("November")) {
			return Calendar.NOVEMBER;
		}
		else if(month.equals("December")) {
			return Calendar.DECEMBER;
		}
		else {
			return 0;
		}
	}
}
