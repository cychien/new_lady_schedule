function convertToMonthNumber(monthName) {
	if(monthName == "January") {
		return 1;
	}
	else if(monthName == "February") {
		return 2;
	}
	else if(monthName == "March") {
		return 3;
	}
	else if(monthName == "April") {
		return 4;
	}
	else if(monthName == "May") {
		return 5;
	}
	else if(monthName == "June") {
		return 6;
	}
	else if(monthName == "July") {
		return 7;
	}
	else if(monthName == "August") {
		return 8;
	}
	else if(monthName == "September") {
		return 9;
	}
	else if(monthName == "October") {
		return 10;
	}
	else if(monthName == "November") {
		return 11;
	}
	else if(monthName == "December") {
		return 12;
	}
	else {
		return 0;
	}
}

function exceedConvert(monthNumber) {
	if(monthNumber >= 13) {
		return monthNumber - 12;
	}
	else {
		return monthNumber;
	}
}

function convertToMonthName(monthNumber) {
	if(monthNumber == "01") {
		return "January";
	}
	else if(monthNumber == "02") {
		return "February";
	}
	else if(monthNumber == "03") {
		return "March";
	}
	else if(monthNumber == "04") {
		return "April";
	}
	else if(monthNumber == "05") {
		return "May";
	}
	else if(monthNumber == "06") {
		return "June";
	}
	else if(monthNumber == "07") {
		return "July";
	}
	else if(monthNumber == "08") {
		return "August";
	}
	else if(monthNumber == "09") {
		return "September";
	}
	else if(monthNumber == "10") {
		return "October";
	}
	else if(monthNumber == "11") {
		return "November";
	}
	else if(monthNumber == "12") {
		return "December";
	}
	else {
		return 0;
	}
}

function convertToRestName(restFullName) {
	if(restFullName === "事假") {
		return "假";
	}
	else if(restFullName === "公休") {
		return "公休";
	}
	else if(restFullName === "特休") {
		return "特";
	}
	else if(restFullName === "婚假") {
		return "婚";
	}
	else if(restFullName === "產假") {
		return "產";
	}
	else if(restFullName === "喪假") {
		return "喪";
	}
	else if(restFullName === "公假") {
		return "公假";
	}
	else if(restFullName === "病假") {
		return "病";
	}
}

function convertToNeedHour(days) {
    if(days === 28)
    	return 160;
    else if(days === 29)
    	return 168;
    else if(days === 30)
    	return 176;
    else if(days === 31)
    	return 184;
    else
    	return null;
}