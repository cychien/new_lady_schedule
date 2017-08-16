function loadDoc() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			constructCal(this.responseText);
		}  
	};
	xhttp.open("GET", "CalendarController", true);
	xhttp.send();
} 

function doAjax(date, type, description, year, month) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			constructCal(this.responseText);
		}  
	};
	let info = "?date=" + date + "&type=" + type + "&description=" + description + "&year=" + year + "&month=" + month; 
	xhttp.open("GET", "CalendarController" + info, true);
	xhttp.send();
}

function doAjaxUpdate(command, date, type, description, year, month) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			constructCal(this.responseText);
		}  
	}; 
	let info = "&date=" + date + "&type=" + type + "&description=" + description + "&year=" + year + "&month=" + month; 
	xhttp.open("GET", "CalendarController?command=" + command + info, true);
	xhttp.send();
}

function doAjaxDelete(command, date, year, month) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			constructCal(this.responseText);
		}  
	};  
	xhttp.open("GET", "CalendarController?command=" + command + "&date=" + date + "&year=" + year + "&month=" + month, true);
	xhttp.send();
}

function moveMonth(year, month, addNumber) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			constructCal(this.responseText);
		}  
	};  
	xhttp.open("GET", "AddMonthController?year=" + year + "&month=" + month + "&addNumber=" + addNumber, true);
	xhttp.send();
}

function controlMoveMonthButton(monthNumber) {
	let today = new Date();
	if(monthNumber == exceedConvert(today.getMonth() + 2)) {
		document.getElementById("lastMonth").style.display = "none";
		document.getElementById("nextMonth").style.display = "";
	}
	if(monthNumber == exceedConvert(today.getMonth() + 3)) {
		document.getElementById("lastMonth").style.display = "";
		document.getElementById("nextMonth").style.display = "";
	}
	if(monthNumber == exceedConvert(today.getMonth() + 4)) {
		document.getElementById("lastMonth").style.display = "";
		document.getElementById("nextMonth").style.display = "none";
	}
}

function constructCal(calInfo) {
	var json = JSON.parse(calInfo);
	
	let currentMonth = json[0].month;
	let data = currentMonth.split("  ");
	let monthNumber = convertToMonthNumber(data[1]);
	
	controlMoveMonthButton(monthNumber);
	
	var txt = "<header><h1 id='currentMonth'>" + json[0].month + "</h1></header>";
	txt = txt + "<div id='calendar'><ul class='weekdays'><li>Sunday</li><li>Monday</li><li>Tuesday</li><li>Wednesday</li>" 
		+ "<li>Thursday</li><li>Friday</li><li>Saturday</li></ul>";
	
	for(let x=0; x<6; x++) {
		txt += "<ul class='days'>";
		for(let y=0; y<7; y++) {
			if(json[x*7+y].date == "") {
				txt = txt + "<li class='calDay other-month'></li>";
			}
			else {
				let dateInfoArray = json[x*7+y].date.split("-");
				txt = txt + "<li class='calDay' id='" + json[x*7+y].date + "' data-toggle='modal' data-target='#myModal' ><div class='date'>" + dateInfoArray[2] + "</div></li>";
			}
		}
		txt += "</ul>";
	}
	
	txt += "</div>";
		
	document.getElementById("root").innerHTML = txt;
	
	for(let x=0; x<6; x++) {
		for(let y=0; y<7; y++) {
			let currentDate = json[x*7+y].date;
			if(currentDate != "") {
				let event;
				if(json[x*7+y].type != "") {
					if(json[x*7+y].description == "") {
						if(json[x*7+y].restChangedBy === "")
							event = "<div class='event'><div class='event-type'>" + json[x*7+y].type + "</div></div>";
						else {
							event = "<div class='event'><div class='event-type'>" + json[x*7+y].type + "</div><div class='event-desc'>" + json[x*7+y].description + 
							"</br><span style='color: red;'>更改人: " + json[x*7+y].restChangedBy + "</span></div></div>";
						}
					}
					else if(json[x*7+y].restChangedBy == ""){
						event = "<div class='event'><div class='event-type'>" + json[x*7+y].type + "</div><div class='event-desc'>" + json[x*7+y].description + "</div></div>";
					}
					document.getElementById(currentDate).innerHTML += event; 
				}
				if(json[x*7+y].addedBy != "") {
					event = "<div class='event-desc' style='color: green; font-size: 12px;'>到" + json[x*7+y].transferToCounter + "上班</br>更改人: " + json[x*7+y].addedBy + "</div>";
					document.getElementById(currentDate).innerHTML += event; 
				}
				if(json[x*7+y].changedBy != "") {
					if(json[x*7+y].specialOnTime != "" && json[x*7+y].specialOffTime == "") {
						event = "<div class='event-desc' style='color: orange; font-size: 12px;'>上班時間改動: " + json[x*7+y].specialOnTime + "</br>更改人: " + json[x*7+y].changedBy + "</div>";
					}
					else if(json[x*7+y].specialOnTime == "" && json[x*7+y].specialOffTime != "") {
						event = "<div class='event-desc' style='color: orange; font-size: 12px;'>下班時間改動: " + json[x*7+y].specialOffTime + "</br>更改人: " + json[x*7+y].changedBy + "</div>";
					}
					else {
						event = "<div class='event-desc' style='color: orange; font-size: 12px;'>上班時間改動: " + json[x*7+y].specialOnTime + "</br>下班時間改動: " + json[x*7+y].specialOffTime + "</br>更改人: " + json[x*7+y].changedBy + "</div>";
					}
					document.getElementById(currentDate).innerHTML += event; 
				}
			}
		}
	}
}

$(document).on("click", ".calDay", function (e) {
    var dateId;
    if($(e.target).is("li"))
    	dateId = e.target.id;
    else
    	dateId = $(e.target).closest("li")[0].id;
    $("#dateId").val(dateId);
    
    if($($(e.target).closest("li")[0]).find(".event-type").html()!=null){
    	$("#description").val($($(e.target).closest("li")[0]).find(".event-desc").html());
    	$("#type").val($($(e.target).closest("li")[0]).find(".event-type").html());
    	$("#add").hide();
    	$("#modify").show();
    }else {
    	$("#description").val("");
    	$("#type").val($("#defaultSelected").val());
    	$("#add").show();
    	$("#modify").hide();
    }
    	
});

$(function() {
   $("#doAjax").on("click", function(){
	   let currentMonth = $("#currentMonth").text();
	   let data = currentMonth.split("  ");
	   doAjax($("#dateId").val(), $("#type").val(), $("#description").val(), data[0], data[1]);
	   $('#myModal').modal('hide');
   });
});
  
$(function() {
   $("#update").on("click", function(){
	   let currentMonth = $("#currentMonth").text();
	   let data = currentMonth.split("  ");
	   doAjaxUpdate("update", $("#dateId").val(), $("#type").val(), $("#description").val(), data[0], data[1]);
	   $('#myModal').modal('hide');
   });
});

$(function() {
   $("#delete").on("click", function(){
	   let currentMonth = $("#currentMonth").text();
	   let data = currentMonth.split("  ");
	   doAjaxDelete("delete", $("#dateId").val(), data[0], data[1]);
	   $('#myModal').modal('hide');
   });
});

$(function() {
   $("#nextMonth").on("click", function(){
	   let currentMonth = $("#currentMonth").text();
	   let data = currentMonth.split("  ");
	   moveMonth(data[0], data[1], 1);
   });
});

$(function() {
   $("#lastMonth").on("click", function(){
	   let currentMonth = $("#currentMonth").text();
	   let data = currentMonth.split("  ");
	   moveMonth(data[0], data[1], -1);
   });
});

function loadDocJustWatch() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			constructCalJustWatch(this.responseText);
		}  
	};
	xhttp.open("GET", "CalendarController?justWatch=true", true);
	xhttp.send();
} 

function constructCalJustWatch(calInfo) {
	var json = JSON.parse(calInfo);
	
	var txt = "<header><h1>" + json[0].month + "</h1></header>";
	txt = txt + "<div id='calendar'><ul class='weekdays'><li>Sunday</li><li>Monday</li><li>Tuesday</li><li>Wednesday</li>" 
		+ "<li>Thursday</li><li>Friday</li><li>Saturday</li></ul>";
	
	for(let x=0; x<6; x++) {
		txt += "<ul class='days'>";
		for(let y=0; y<7; y++) {
			if(json[x*7+y].date == "") {
				txt = txt + "<li class='calDay other-month'></li>";
			}
			else {
				let dateInfoArray = json[x*7+y].date.split("-");
				txt = txt + "<li class='calDay' id='" + json[x*7+y].date + "'><div class='date'>" + dateInfoArray[2] + "</div></li>";
			}
		}
		txt += "</ul>";
	}
	
	txt += "</div>";
		
	document.getElementById("root").innerHTML = txt;
	
	for(let x=0; x<6; x++) {
		for(let y=0; y<7; y++) {
			let currentDate = json[x*7+y].date;
			if(currentDate != "") {
				let event;
				if(json[x*7+y].type != "") {
					if(json[x*7+y].description == "") {
						if(json[x*7+y].restChangedBy === "")
							event = "<div class='event'><div class='event-type'>" + json[x*7+y].type + "</div></div>";
						else {
							event = "<div class='event'><div class='event-type'>" + json[x*7+y].type + "</div><div class='event-desc'>" + json[x*7+y].description + 
							"</br><span style='color: red;'>更改人: " + json[x*7+y].restChangedBy + "</span></div></div>";
						}
					}
					else if(json[x*7+y].restChangedBy == ""){
						event = "<div class='event'><div class='event-type'>" + json[x*7+y].type + "</div><div class='event-desc'>" + json[x*7+y].description + "</div></div>";
					}
					document.getElementById(currentDate).innerHTML += event; 
				}
				if(json[x*7+y].addedBy != "") {
					event = "<div class='event-desc' style='color: green; font-size: 12px;'>到" + json[x*7+y].transferToCounter + "上班</br>更改人: " + json[x*7+y].addedBy + "</div>";
					document.getElementById(currentDate).innerHTML += event; 
				}
				if(json[x*7+y].changedBy != "") {
					if(json[x*7+y].specialOnTime != "" && json[x*7+y].specialOffTime == "") {
						event = "<div class='event-desc' style='color: orange; font-size: 12px;'>上班時間改動: " + json[x*7+y].specialOnTime + "</br>更改人: " + json[x*7+y].changedBy + "</div>";
					}
					else if(json[x*7+y].specialOnTime == "" && json[x*7+y].specialOffTime != "") {
						event = "<div class='event-desc' style='color: orange; font-size: 12px;'>下班時間改動: " + json[x*7+y].specialOffTime + "</br>更改人: " + json[x*7+y].changedBy + "</div>";
					}
					else {
						event = "<div class='event-desc' style='color: orange; font-size: 12px;'>上班時間改動: " + json[x*7+y].specialOnTime + "</br>下班時間改動: " + json[x*7+y].specialOffTime + "</br>更改人: " + json[x*7+y].changedBy + "</div>";
					}
					document.getElementById(currentDate).innerHTML += event; 
				}
			}
		}
	}
}