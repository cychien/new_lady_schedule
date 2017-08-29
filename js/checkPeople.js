$("#confirm").click(function(){
    $("#command").val("confirm");
    $("#form1").submit();
});

$("#delete").click(function(){
	$("#command").val("delete");
	$("#form1").submit();
});

$("#send").click(function(){
    $("#form2").submit();
});

function setClickData(index)
{
	var tblObj = document.getElementById('tblList');

	for(i=0;i<tblObj.rows[index].cells.length;i++)
	{
		 if(tblObj.rows[index].cells[0].children[0].children[0].children[0].checked)
		 	tblObj.rows[index].style.background = "#D2E9FF";
		 else
		 	tblObj.rows[index].style.background = "";
	}
}

//防止不小心
function prevent() {
	var x = document.getElementById("payMethod").value;
	var arr = x.split("(");
	if(x === "正職") {
		document.getElementById("base").value = "13000";
        document.getElementById("base").readOnly = false;
        document.getElementById("performanceBonus").readOnly = false;
        document.getElementById("educationBonus").readOnly = false;
        document.getElementById("ownerBonus").readOnly = false;
        document.getElementById("allowance").readOnly = false;
        document.getElementById("insuranceMinus").readOnly = false;
        document.getElementById("insurance").readOnly = false;
    }
	if(arr[0] === "約聘" || arr[0] === "外聘" || arr[0] === "工讀生") {
		document.getElementById("base").value = 0;
        document.getElementById("base").readOnly = true;
		document.getElementById("performanceBonus").value = 0;
        document.getElementById("performanceBonus").readOnly = true;
		document.getElementById("educationBonus").value = 0;
        document.getElementById("educationBonus").readOnly = true;
		document.getElementById("ownerBonus").value = 0;
        document.getElementById("ownerBonus").readOnly = true;
		document.getElementById("allowance").value = 0;
        document.getElementById("allowance").readOnly = true;
		document.getElementById("insuranceMinus").value = 0;
        document.getElementById("insuranceMinus").readOnly = true;
		document.getElementById("insurance").value = 0;
        document.getElementById("insurance").readOnly = true;
	}
}

