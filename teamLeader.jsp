<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>歡迎來到Lady 內衣排班系統</title>

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="css/manage-interface.css">
<link rel="stylesheet" href="css/bootstrap-editable.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
<link rel="stylesheet" href="css/areaButton.css">
</head>
<body>
<%
int employeeId = 0;
Object employeeIdObj = session.getAttribute("employeeId");
if(employeeIdObj != null) {
	employeeId = (int)employeeIdObj;
}
String areaName= (String)session.getAttribute("areaName");

if(employeeId != 0 && areaName != null) {
	int positionId = (int)session.getAttribute("positionId");
	if(positionId == 3) {
		response.sendRedirect("CheckPeople");
	}
}
else if(employeeId != 0) {
	response.sendRedirect("currentMonth.jsp");
}
else {
	response.sendRedirect("index.jsp");
}
%>

	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3>Lady 內衣排班系統</h3>
			</div>

			<ul class="list-unstyled components">
				<li><a href="TeamLeader">選擇地區</a></li>
				<li class="active"><a href="#counterManagement" data-toggle="collapse" aria-expanded="false">櫃台管理</a>
					<ul class="collapse list-unstyled" id="counterManagement">
						<li><a href="teamLeader.jsp">新增專櫃</a></li>
						<li><a href="CounterManagement">專櫃管理</a></li>
					</ul>
				</li>
				<li><a href="BAManagement">專櫃人員管理</a></li>
				<!-- <li><a href="Match">櫃位/專櫃人員配對</a></li> -->
				<li><a href="TeamLeader3">班表異動</a></li>
				<li><a href="bonusCalculate.jsp">獎金分配</a></li>
			</ul>
		</nav>

		<!-- Page Content Holder -->
		<div id="content">

			<nav class="navbar navbar-default">
				<div class="container-fluid">

					<div class="navbar-header">
						<button type="button" id="sidebarCollapse"
							class="btn btn-info navbar-btn">
							<i class="glyphicon glyphicon-align-left"></i> <span>隱藏/顯示導覽列</span>
						</button>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    	<ul class="nav navbar-nav">
                            <li><a href="teamLeader.jsp">新增專櫃</a></li>
                            <li><a href="CounterManagement">專櫃管理</a></li>
                        </ul>
                        <ul class="nav navbar-nav pull-right">
                            <li><a href="LogoutController2">登出</a></li>
                        </ul>
                   </div>
				</div>
			</nav>
			<div id="page">
				<c:if test="${not empty excludedCounter}">
					<div class="row">
						<div class="col-lg-12">
							<h4>以下專櫃的名稱已存在資料庫，所以新增失敗</h4>
							<c:forEach items="${excludedCounter}" var="counter" varStatus="status">
								<p style="color: red;"><c:out value="地區${status.count} 專櫃名:${counter.counterName }"></c:out></p>
							</c:forEach>
						</div>
					</div>
					<br />
				</c:if>
				
				<div class="row">
					<div class="col-lg-12">
						<h2>新增專櫃<c:out value="( ${sessionScope.areaName} )"></c:out></h2>
					</div>
				</div>
				<br />
				<br />
				
				<div class="row">
				    <div class="col-lg-2">
				        <label>專櫃名</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <input id="counter" type="text" class="form-control" required autofocus/>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="col-lg-2">
				        <label>星期一上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="monday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				    <div class="col-lg-2">
				        <label>星期五上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="friday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="col-lg-2">
				        <label>星期二上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="tuesday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				    <div class="col-lg-2">
				        <label>星期六上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="saturday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="col-lg-2">
				        <label>星期三上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="wednesday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				    <div class="col-lg-2">
				        <label>星期日上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="sunday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="col-lg-2">
				        <label>星期四上班時間</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="thursday" class="form-control selectpicker" >
				            	<option>10.5-21.5</option>
								<option>10.5-22.0</option>
								<option>10.5-22.5</option>
								<option>11.0-21.5</option>
								<option>11.0-22.0</option>
								<option>11.0-22.5</option>
				            </select>
				        </div>
				    </div>
				</div>
				<div class="row">
					<div class="col-lg-12">
				        <button id="addBtn" class="addBtn btn btn-primary pull-right">
				            新增
				        </button>
				    </div>
				</div>
				<br />
				<br />
				
				<div class="row">
				    <div class="col-lg-12">
				        <table id="example" class="table table-bordered table-responsive">
				            <thead>
				                <tr>
				                    <th class="col-lg-3 text-center">專櫃名</th>
				                    <th class="col-lg-1 text-center">星期一</th>
				                    <th class="col-lg-1 text-center">星期二</th>
				                    <th class="col-lg-1 text-center">星期三</th>
				                    <th class="col-lg-1 text-center">星期四</th>
				                    <th class="col-lg-1 text-center">星期五</th>
				                    <th class="col-lg-1 text-center">星期六</th>
				                    <th class="col-lg-1 text-center">星期日</th>
				                    <th class="col-lg-2 text-center">刪除</th>
				                </tr>
				            </thead>
				            <tbody>
				            </tbody>
				        </table>
				    </div>
				</div>
				
				<div class="row">
					<div class="col-lg-12">
						<button type="button" id="confirm" class="btn btn-danger pull-right" style="display: none;" onclick="readyToSend();">
				            確認送出
				    	</button>
					</div>
				</div>
				
				<form action="AddNewCounterConfirm" method="post" style="display:none" id="invisibleForm">
					<input type="text" name="json" id="invisibleInput">
				</form>	
			</div>
		</div>
	</div>
	<!-- jQuery CDN -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<!-- Bootstrap Js CDN -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- jQuery Nicescroll CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.6.8-fix/jquery.nicescroll.min.js"></script>
	<script src="js/manage-interface.js"></script>
	<script src="js/bootstrap-editable.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
	
	<script>
	$(".addBtn").click(function(){
	    var counter = $("#counter").val();
	    var monday = $("#monday").val();
	    var tuesday = $("#tuesday").val();
	    var wednesday = $("#wednesday").val();
	    var thursday = $("#thursday").val();
	    var friday = $("#friday").val();
	    var saturday = $("#saturday").val();
	    var sunday = $("#sunday").val();
	    var markup = "<tr><td class='text-center'>" + counter + "</td><td class='text-center'>" + monday + "</td><td class='text-center'>" + tuesday + 
	    "</td><td class='text-center'>"+wednesday+"</td><td class='text-center'>"+thursday+"</td><td class='text-center'>"+friday+"</td><td class='text-center'>"+saturday+"</td><td class='text-center'>"+
	    sunday+"</td><td class='text-center'><button class='btn btn-small delBtn'>刪除</button></td></tr>";
	    $("table tbody").append(markup);
	    $("#counter").val("");
	    $("#monday").val("10:30-21:30");
	    $("#tuesday").val("10:30-21:30");
	    $("#wednesday").val("10:30-21:30");
	    $("#thursday").val("10:30-21:30");
	    $("#friday").val("10:30-21:30");
	    $("#saturday").val("10:30-21:30");
	    $("#sunday").val("10:30-21:30");
	    $("#confirm").show();
	});
	    
	$(function () {
	    $("table").on("click", ".delBtn", function () {
	        $(this).closest('tr').remove();
	    });
	});


	function readyToSend() {
		var counterList = [];
		
	    var table = document.getElementById("example");
	    for (var r = 1, n = table.rows.length; r < n; r++) {
	    	counterList.push({
	        	"counterName": table.rows[r].cells[0].innerHTML,
	        	"areaId": "${sessionScope.areaId}",
	        	"monday": table.rows[r].cells[1].innerHTML,
	        	"tuesday": table.rows[r].cells[2].innerHTML,
	        	"wednesday": table.rows[r].cells[3].innerHTML,
	        	"thursday": table.rows[r].cells[4].innerHTML,
	        	"friday": table.rows[r].cells[5].innerHTML,
	        	"saturday": table.rows[r].cells[6].innerHTML,
	        	"sunday": table.rows[r].cells[7].innerHTML
	        });
	    }
	    var json = JSON.stringify(counterList);
	    document.getElementById("invisibleInput").value = json;
	    document.getElementById("invisibleForm").submit();
	} 
	</script>
</body>
</html>