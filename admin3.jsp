<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/manage-interface.css">
<link rel="stylesheet" href="css/bootstrap-editable.css">
<!-- Latest compiled and minified CSS -->
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">--%>
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
	if(positionId == 2) {
		response.sendRedirect("TeamLeader");
	}
}
else if(employeeId != 0) {
	response.sendRedirect("CurrentMonth");
}
else {
	response.sendRedirect("/");
}
%>
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3>Lady 內衣排班系統</h3>
			</div>

			<ul class="list-unstyled components">
				<li><a href="CheckPeople">人員申請確認</a></li>
				<li><a href="AlterInfo">專櫃人員基本資料修改</a></li>
				<li><a href="#teamLeaderManagementSubmenu" data-toggle="collapse" aria-expanded="false">小組長管理</a>
					<ul class="collapse list-unstyled" id="teamLeaderManagementSubmenu">
						<li><a href="Admin2">新增小組長</a></li>
						<li><a href="TeamLeaderManagement">小組長管理</a></li>
					</ul>
				</li>
				<li class="active"><a href="#areaManagementSubmenu" data-toggle="collapse" aria-expanded="false">營業地區管理</a>
					<ul class="collapse list-unstyled" id="areaManagementSubmenu">
						<li><a href="NewArea">新增營業地區</a></li>
						<li><a href="AreaManagement">營業地區管理</a></li>
					</ul>
				</li>
				<li><a href="TotalReport">薪資計算</a></li>
			</ul>
		</nav>

		<!-- Page Content Holder -->
		<div id="content">

			<nav class="navbar navbar-default">
				<div class="container-fluid">

					<div class="navbar-header">
						<button type="button" id="sidebarCollapse" class="btn btn-info navbar-btn">
							<i class="glyphicon glyphicon-align-left"></i> <span>隱藏/顯示導覽列</span>
						</button>
					</div>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    	<ul class="nav navbar-nav">
                            <li><a href="NewArea">新增營業地區</a></li>
                            <li><a href="AreaManagement">營業地區管理</a></li>
                        </ul>
                        <ul class="nav navbar-nav pull-right">
                        	<li><a href="LogoutController2">登出</a></li>
                    	</ul>
                   </div>
				</div>
			</nav>
			<div id="page">
				<c:if test="${not empty excludedArea}">
					<div class="row">
						<div class="col-lg-12">
							<h4>以下地區的名稱已存在資料庫，所以新增失敗</h4>
							<c:forEach items="${excludedArea}" var="area" varStatus="status">
								<p style="color: red;"><c:out value="地區${status.count}: 地區名:${area.areaName } 所屬範圍:${area.areaRange }"></c:out></p>
							</c:forEach>
						</div>
					</div>
					<br />
				</c:if>
			
				<div class="row">
					<div class="col-lg-12">
						<h2>新增營業地區</h2>
					</div>
				</div>
				<br />
				<br />
				
				<div class="row">
				    <div class="col-lg-2">
				        <label>地區名</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <input id="area" type="text" class="form-control" required autofocus/>
				        </div>
				    </div>
				    <div class="col-lg-2">
				        <label>所屬範圍</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select id="range" class="form-control" >
				            	<option selected>北部</option>
								<option>中部</option>
								<option>南部</option>
								<option>東部</option>
								<option>離島</option>
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
				        <table id="example" class="table table-bordered">
				            <thead>
				                <tr>
				                    <th class="col-lg-4 text-center">地區名</th>
				                    <th class="col-lg-4 text-center">所屬範圍</th>
				                    <th class="col-lg-4 text-center">刪除</th>
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
				
				<form action="AddNewAreaConfirm" method="post" style="display:none" id="invisibleForm">
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
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
	
	<!-- (Optional) Latest compiled and minified JavaScript translation files -->
	<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>--%>
		
	<script>
	$(".addBtn").click(function(){
	    var area = $("#area").val();
	    var range = $("#range").val();
	    var markup = "<tr><td class='text-center'>" + area + "</td><td class='text-center'>" + range + "</td><td class='text-center'><button class='btn btn-small delBtn'>刪除</button></td></tr>";
	    $("table tbody").append(markup);
	    $("#area").val("");
	    $("#range").val("北部")
	    $("#confirm").show();
	});
	    
	$(function () {
	    $("table").on("click", ".delBtn", function () {
	        $(this).closest('tr').remove();
	    });
	});


	function readyToSend() {
		var areaList = [];
		
	    var table = document.getElementById("example");
	    for (var r = 1, n = table.rows.length; r < n; r++) {
	    	areaList.push({
	        	"areaName": table.rows[r].cells[0].innerHTML,
	        	"areaRange": table.rows[r].cells[1].innerHTML,
	        });
	    }
	    var json = JSON.stringify(areaList);
//	    alert(json);
	    document.getElementById("invisibleInput").value = json;
	    document.getElementById("invisibleForm").submit();
	} 
	</script>
</body>
</html>