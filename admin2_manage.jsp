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
</head>
<style>
.modal-header{
    background-color:#333;
    color:#fff;
}
</style>
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
				<li><a href="CheckPeople">人員申請確認</a></li>
				<li><a href="AlterInfo">專櫃人員基本資料修改</a></li>
				<li class="active"><a href="#teamLeaderManagementSubmenu" data-toggle="collapse" aria-expanded="false">小組長管理</a>
					<ul class="collapse list-unstyled" id="teamLeaderManagementSubmenu">
						<li><a href="Admin2">新增小組長</a></li>
						<li><a href="TeamLeaderManagement">小組長管理</a></li>
					</ul>
				</li>
				<li><a href="#areaManagementSubmenu" data-toggle="collapse" aria-expanded="false">營業地區管理</a>
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
                            <li><a href="Admin2">新增小組長</a></li>
                            <li><a href="TeamLeaderManagement">小組長管理</a></li>
                        </ul>
                        <ul class="nav navbar-nav pull-right">
                        	<li><a href="LogoutController2">登出</a></li>
                    	</ul>
                   </div>
				</div>
			</nav>
			
			<div id="page">
				<div class="row">
					<div class="col-lg-12">
						<h2>小組長管理</h2>
					</div>
				</div>
				<br />
				<br />
				
				<div class="row">
					<div class="col-lg-12">
						<table class="table table-hover table-responsive">
							<thead>
								<tr>
									<th>姓名</th>
									<th>地區</th>
									<th>帳號</th>
									<th>密碼</th>
									<th>編輯</th>
									<th>刪除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${teamLeaderInfo }" var="teamLeader">
									<tr>
										<td class="name">${teamLeader.teamLeaderName }</td>
										<td class="area">${teamLeader.teamLeaderArea }</td>
										<td class="account">${teamLeader.teamLeaderAccount }</td>
										<td class="password">${teamLeader.teamLeaderPassword }</td>
										<td><button type="button" data-toggle="modal" data-target="#edit" data-uid="${teamLeader.teamLeaderId }" class="update btn btn-warning btn-sm"><span class="glyphicon glyphicon-pencil"></span></button></td>
		            					<td><button type="button" data-toggle="modal" data-target="#delete" data-uid="${teamLeader.teamLeaderId }" class="delete btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
				<div id="edit" class="modal fade" role="dialog">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal">×</button>
				        <h4 class="modal-title">更改資料</h4>
				      </div>
				      <div class="modal-body">
				          <form role="form">
				          	  <div class="form-group">
							  	  <input id="modal-id" type="hidden" class="form-control">
							  </div>
				          	  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-user"></span> 姓名</label>
							  	  <input id="modal-name" type="text" class="form-control" required autofocus />
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-flag"></span> 區域</label>
							  	  <input id="modal-area" type="text" class="form-control" readonly />
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-book"></span> 帳號</label>
							  	  <input id="modal-account" type="text" class="form-control" required />
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 密碼</label>
							  	  <input id="modal-password" type="text" class="form-control" required />
							  </div>
				          </form>	
				      </div>
				      <div class="modal-footer">
				        <button type="button" id="up" class="btn btn-warning" data-dismiss="modal">確認更改</button>
				        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
				      </div>
				    </div>
				  </div>
				</div>
				
				<form action="DoActionOnTeamLeader" method="post" style="display:none" id="invisibleForm">
					<input type="text" name="command" id="invisibleCommand">
					<input type="text" name="id" id="invisibleId">
					<input type="text" name="name" id="invisibleName">
					<input type="text" name="area" id="invisibleArea">
					<input type="text" name="account" id="invisibleAccount">
					<input type="text" name="password" id="invisiblePassword">
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
	<script>
	$(".delete").click(function(e){
		let id = $(this).data("uid");
		$("#invisibleCommand").val("delete");
        $("#invisibleId").val(id);
     	readyToSubmit();   
	});
	$(".update").click(function(e){
       let id = $(this).data("uid");
       let name = $($(e.target).closest("tr")[0]).find(".name").html();
       let area = $($(e.target).closest("tr")[0]).find(".area").html();
   	   let account = $($(e.target).closest("tr")[0]).find(".account").html();
   	   let password = $($(e.target).closest("tr")[0]).find(".password").html();
       
   	   $("#invisibleId").val(id);
   	   $("#modal-name").val(name);
   	   $("#modal-area").val(area);
       $("#modal-account").val(account);
       $("#modal-password").val(password);
	});
    $("#up").click(function(){
           //let id = $("#modal-id").val();
           let name = $("#modal-name").val();
           let area = $("#modal-area").val();
           let account = $("#modal-account").val();  
           let password = $("#modal-password").val(); 
           
           $("#invisibleCommand").val("update");
           $("#invisibleName").val(name);
           $("#invisibleArea").val(area);
           $("#invisibleAccount").val(account);
           $("#invisiblePassword").val(password);
           readyToSubmit();   
    });
    function readyToSubmit() {
    	$("#invisibleForm").submit();
    }
	</script>
</body>
</html>