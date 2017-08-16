$("#confirm").click(function(){
    $("#command").val("confirm");
    $("form").submit();
});

$("#delete").click(function(){
	$("#command").val("delete");
	$("form").submit();
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

