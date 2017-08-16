function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            //buildSource(this.responseText);
            constructTable(this.responseText);
        }
    };
    xhttp.open("GET", "ProduceAllShiftInfo", true);
    xhttp.send();
}
/*
var src;
var content;
function buildSource(info) {
	var json2 = JSON.parse(info);
	content = "[{value: 'O', text: 'O' }, {value: 'X', text: 'X' }";
	for(let i=0; i<json2.length; i++) {
		if(json2[i].matchInfoDTO.counterId != 0) {
			content += ",{value: '"+json2[i].matchInfoDTO.employeeCounter+"', text: '"+json2[i].matchInfoDTO.employeeCounter+"'}";
		}
	}
	content += "]";
	src = new Function("return content");
}
*/
function constructTable(allShiftInfo) {
    var json = JSON.parse(allShiftInfo)
    var txt = "";

    var origin = json[0].restArray[0].restDate;
    var arr = origin.split("-");
    var title = arr[0] + "  " + convertToMonthName(arr[1]);
    document.getElementById("title").innerHTML = title;

    for(let i=0; i<json.length; i++){
        txt += "<tr style='background-color: #EEFFBB;'>";
        txt = txt + "<td id='" + json[i].matchInfoDTO.employeeId + "-hour" + "'></td>";
        if(json[i].matchInfoDTO.counterId == 0)
            txt += "<td><a href='#' data-type='text' class='counter' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].matchInfoDTO.counterId + "' data-url='Match'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>";
        else
            txt = txt + "<td><a href='#' data-type='text' class='counter' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].matchInfoDTO.counterId + "' data-url='Match'>" + json[i].matchInfoDTO.employeeCounter + "</a></td>";
        txt += "<td class='	name'>" + json[i].matchInfoDTO.employeeName + "</td>";
        txt += "<td></td>";
        for(let j=0; j<json[i].restArray.length; j++) {
            if(json[i].restArray[j].restType === "")
                txt = txt + "<td class='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"'><a href='#' data-type='text' class='shift' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+ "' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"'>O</a></td>";
            else  {
                if(json[i].restArray[j].restDescription === "") {
                    let restInfo = convertToRestName(json[i].restArray[j].restType);
                    txt = txt + "<td class='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"'><a href='#' data-type='text' class='shift' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+ "' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"'>" + restInfo + "</a></td>";
                }
                else {
                    let restInfo = convertToRestName(json[i].restArray[j].restType);
                    txt = txt + "<td class='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"'><a data-toggle='tooltip' title='" + json[i].restArray[j].restDescription+"'>*</a><a href='#' style='color: #0066FF' data-type='text' class='shift' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+ "' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"'>"+restInfo+"</a></td>";
                }
            }

        }
        txt += "</tr>";

        txt += "<tr><td></td><td></td><td></td><td></td>";
        for(let k=0; k<json[i].restArray.length; k++) {
            if(json[i].restArray[k].restType === "") {
                if(json[i].weekday[k] === "一") {
                    if(json[i].onTime[0] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[0] + "</a></td>";
                }
                else if(json[i].weekday[k] === "二") {
                    if(json[i].onTime[1] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[1] + "</a></td>";
                }
                else if(json[i].weekday[k] === "三") {
                    if(json[i].onTime[2] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[2] + "</a></td>";
                }
                else if(json[i].weekday[k] === "四") {
                    if(json[i].onTime[3] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[3] + "</a></td>";
                }
                else if(json[i].weekday[k] === "五") {
                    if(json[i].onTime[4] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[4] + "</a></td>";
                }
                else if(json[i].weekday[k] === "六") {
                    if(json[i].onTime[5] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[5] + "</a></td>";
                }
                else if(json[i].weekday[k] === "日"){
                    if(json[i].onTime[6] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-on'></a></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-on'>" + json[i].onTime[6] + "</a></td>";
                }
            }
            else
                txt += "<td></td>";
        }
        txt += "</tr>";

        txt += "<tr><td></td><td></td><td></td><td></td>";
        for(let k=0; k<json[i].restArray.length; k++) {
            if(json[i].restArray[k].restType === "") {
                if(json[i].weekday[k] === "一") {
                    if(json[i].offTime[0] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[0] + "</a></td>";
                }
                else if(json[i].weekday[k] === "二") {
                    if(json[i].offTime[1] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[1] + "</a></td>";
                }
                else if(json[i].weekday[k] === "三") {
                    if(json[i].offTime[2] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[2] + "</a></td>";
                }
                else if(json[i].weekday[k] === "四") {
                    if(json[i].offTime[3] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[3] + "</a></td>";
                }
                else if(json[i].weekday[k] === "五"){
                    if(json[i].offTime[4] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[4] + "</a></td>";
                }
                else if(json[i].weekday[k] === "六"){
                    if(json[i].offTime[5] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[5] + "</a></td>";
                }
                else if(json[i].weekday[k] === "日"){
                    if(json[i].offTime[6] === "") {
                        if(json[i].transferArray[k].counterId != 0)
                            txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+"-off'></td>";
                        else
                            txt += "<td></td>";
                    }
                    else
                        txt += "<td style='font-size: 10px;'><a href='#' class='time' data-type='text' data-pk='" +json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off' data-url='UpdateShift' id='"+json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[k].restDate+ "-off'>" + json[i].offTime[6] + "</a></td>";
                }
            }
            else
                txt += "<td></td>";
        }
        txt += "</tr>";
    }
    document.getElementById("tbody").innerHTML = txt;

    for(let i=0; i<json.length; i++) {
        for(let j=0; j<json[i].transferArray.length; j++) {
            if(json[i].transferArray[j].counterName != "") {
                document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate).innerHTML = json[i].transferArray[j].counterName;
                document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate).style.fontSize = "xx-small";
                document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+ "-on").innerHTML = json[i].transferArray[j].onTime;
                document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+ "-off").innerHTML = json[i].transferArray[j].offTime;
            }
        }
    }

    for(let i=0; i<json.length; i++) {
        for(let j=0; j<json[i].specialArray.length; j++) {
            if(json[i].specialArray[j].changedBy != 0) {
                if(json[i].specialArray[j].onTime != "") {
                    document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"-on").innerHTML = json[i].specialArray[j].onTime;
                    document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"-on").style.color = "red";
                }
                if(json[i].specialArray[j].offTime != "") {
                    document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"-off").innerHTML = json[i].specialArray[j].offTime;
                    document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"-off").style.color = "red";
                }
            }
        }
    }

    var record = [];
    var store = [];

    for(let i=0; i<json.length; i++) {
        for(let j=0; j<json[i].restArray.length; j++) {
            let condition = document.getElementById(json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate).innerHTML;
            //console.log(condition);
            if(((condition.length === 1 && condition != "O") || condition === "公休" || condition === "公假") && json[i].matchInfoDTO.counterId != 0) {
                //console.log("find");
                let lack = json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"#"+json[i].matchInfoDTO.counterId;
                let lackMatch = json[i].restArray[j].restDate+"#"+json[i].matchInfoDTO.counterId;
                let isStored = 0;
                for(let k=0; k<store.length; k++) {
                    let tmp = store[k].split("-");
                    let storeItem = tmp[1] + "-" + tmp[2] + "-" + tmp[3];
                    if(lackMatch === storeItem) {
                        isStored = 1;
                        let index = store.indexOf(store[k]);
                        store.splice(index, 1);
                    }
                }
                if(isStored === 0) {
                    record.push(lack);
                }
            }
            else if(condition != "O") {
                let match = json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"#"+json[i].transferArray[j].counterId;
                let matchMatch = json[i].restArray[j].restDate+"#"+json[i].transferArray[j].counterId;
                let isDeleted = 0;
                for(let k=0; k<record.length; k++) {
                    let tmp = record[k].split("-");
                    let recordItem = tmp[1] + "-" + tmp[2] + "-" + tmp[3];
                    if(matchMatch === recordItem) {
                        isDeleted = 1;
                        let index = record.indexOf(record[k]);
                        record.splice(index, 1);
                    }
                }
                if(isDeleted === 0) {
                    store.push(match);
                }
            }
        }
    }

    for(let i=0; i<record.length; i++) {
        if(record != undefined) {
            let result = record[i].split("#");
            let warn = result[0];
            let x = document.getElementsByClassName(warn);
            x[0].style.backgroundColor = "#FFB7DD";
        }
    }

    for(let i=0; i<json.length; i++) {
        let workTimeTotal = 0;
        let hour = json[i].matchInfoDTO.employeeId + "-hour";
        for(let j=0; j<json[i].restArray.length; j++) {
            let staffId = json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate;
            let onTime;
            let offTime;
            let start = json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"-on";
            let end = json[i].matchInfoDTO.employeeId+"-"+json[i].restArray[j].restDate+"-off";
            let icon = document.getElementById(staffId).innerHTML;
            if((icon.length === 1 && icon != "O") || icon === "公休" || icon === "公假") {
            }
            else {
                if(document.getElementById(start) == null && document.getElementById(end) == null) {
                    onTime = 0;
                    offTime = 0;
                }
                else {
                    onTime = Number(document.getElementById(start).innerHTML);
                    offTime = Number(document.getElementById(end).innerHTML);
                }
                workTimeTotal = workTimeTotal + (offTime - onTime);
                if (onTime <= 11 && offTime >= 19)
                    workTimeTotal = workTimeTotal - 1;
                else if (onTime <= 11 && offTime >= 17 && offTime < 19)
                    workTimeTotal = workTimeTotal - 0.5;
                else if (onTime <= 11 && offTime >= 13 && offTime <= 17)
                    workTimeTotal = workTimeTotal - 0.5;
                else if (onTime > 11 && onTime <= 13 && offTime >= 19)
                    workTimeTotal = workTimeTotal - 0.5;
                else if (onTime >= 13 && onTime <= 17 && offTime >= 19)
                    workTimeTotal = workTimeTotal - 0.5;
            }
        }
        document.getElementById(hour).innerHTML = workTimeTotal;
}

    var totalAreaHour = 0;
    for(let i=0; i<json.length; i++) {
        let hour = json[i].matchInfoDTO.employeeId + "-hour";
        let days = json[0].restArray.length;
        let needHour = convertToNeedHour(days);
        let workHour = document.getElementById(hour).innerHTML;
        if(workHour < needHour || workHour > (needHour+46))
            document.getElementById(hour).style.background = "#FF3333";
        totalAreaHour = totalAreaHour + Number(workHour);
    }
    document.getElementById("totalAreaHour").innerHTML = totalAreaHour;
    document.getElementById("totalCompanyHour").innerHTML = json[0].matchInfoDTO.totalCompanyHour;

    $('[data-toggle="tooltip"]').tooltip();
    /*
    $('.shift').editable({
        type: "select",              //编辑框的类型。支持text|textarea|select|date|checklist等
        source: function() {
            var result = src();
            return result;
        },
        title: "變更班表",           //编辑框的标题
        disabled: false           //是否禁用编辑
    });
    */

    $('.time, .shift, .counter').editable();

    $('.editable').editable({
        showbuttons: false
    }).on('shown', function(ev, editable) {
        setTimeout(function() {
            editable.input.$input.select();
        },0);
    });

    $('.shift, .time, .counter').on('save', function(e, params) {
        // alert('Saved value: ' + params.newValue);
        window.onload = loadDoc();
        // window.location.href = "TeamLeader3";
    });
}