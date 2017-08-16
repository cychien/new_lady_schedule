$(".addBtn").click(function(){
    var name = $("#name").val();
    var area = $("#area").val();
    var account = $("#account").val();
    var password = $("#password").val();
    var markup = "<tr><td class='text-center'>" + name + "</td><td class='text-center'>" + area + "</td><td class='text-center'>" + account + "</td><td class='text-center'>" + password + "</td><td class='text-center'><button class='btn btn-small delBtn'>刪除</button></td></tr>";
    $("table tbody").append(markup);
    $("#name").val("");
    $("#area").val("");
    $("#account").val("");
    $("#password").val("")
    $("#confirm").show();
});
    
$(function () {
    $("table").on("click", ".delBtn", function () {
        $(this).closest('tr').remove();
    });
});


function readyToSend() {
	var teamLeaderList = [];
	
    var table = document.getElementById("example");
    for (var r = 1, n = table.rows.length; r < n; r++) {
    	teamLeaderList.push({
        	"teamLeaderName": table.rows[r].cells[0].innerHTML,
        	"teamLeaderArea": table.rows[r].cells[1].innerHTML,
        	"teamLeaderAccount": table.rows[r].cells[2].innerHTML,
        	"teamLeaderPassword": table.rows[r].cells[3].innerHTML
        });
    }
    var json = JSON.stringify(teamLeaderList);
//    alert(json);
    document.getElementById("invisibleInput").value = json;
    document.getElementById("invisibleForm").submit();
} 

//for (var c = 0, m = table.rows[r].cells.length; c < m-1; c++)