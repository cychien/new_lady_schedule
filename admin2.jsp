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
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/manage-interface.css">
<link rel="stylesheet" href="css/bootstrap-editable.css">
<style>
#example table {
    counter-reset: rowNumber;
}

#example table tr > td:first-child {
    counter-increment: rowNumber;
}
                
#example table tr td:first-child::before {
    content: counter(rowNumber);
    min-width: 1em;
    margin-right: 0.5em;
}
</style>
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
				<li><a href="CheckPeople">人員申請確認</a></li>
				<li class="active"><a href="#teamLeaderManagementSubmenu" data-toggle="collapse" aria-expanded="false">小組長管理</a>
					<ul class="collapse list-unstyled" id="teamLeaderManagementSubmenu">
						<li><a href="Admin2">新增小組長</a></li>
						<li><a href="TeamLeaderManagement">小組長管理</a></li>
					</ul>
				</li>
				<li><a href="#areaManagementSubmenu" data-toggle="collapse" aria-expanded="false">營業地區管理</a>
					<ul class="collapse list-unstyled" id="areaManagementSubmenu">
						<li><a href="admin3.jsp">新增營業地區</a></li>
						<li><a href="AreaManagement">營業地區管理</a></li>
					</ul>
				</li>
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
			
				<c:if test="${not empty excludedPeople}">
					<div class="row">
						<div class="col-lg-12">
							<h4>以下人員負責的地區已有人負責，所以新增失敗</h4>
							<c:forEach items="${excludedPeople}" var="person" varStatus="status">
								<p style="color: red;"><c:out value="人員${status.count}:  ${person.teamLeaderName }  ${person.teamLeaderArea }  帳號:${person.teamLeaderAccount } 密碼:${person.teamLeaderPassword }"></c:out></p>
							</c:forEach>
						</div>
					</div>
					<br />
				</c:if>
					
				<div class="row">
					<div class="col-lg-12">
						<h2>新增小組長</h2>
					</div>
				</div>
				<br />
				<br />
			
				<div class="row">
				    <div class="col-lg-2">
				        <label>姓名</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <input id="name" type="text" class="form-control" required autofocus/>
				        </div>
				    </div>
				    <div class="col-lg-2">
				        <label>地區</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <select name="area" class="form-area form-control" id="area">
                        		<c:forEach items="${area }" var="area">
                        			<option value="${ area.areaName}">${ area.areaName}</option>
                        		</c:forEach>
                       		</select>
				        </div>
				    </div>
				</div>
				
				<div class="row">
					<div class="col-lg-2">
				        <label>帳號</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <input id="account" type="text" class="form-control" required/>
				        </div>
				    </div>
				    <div class="col-lg-2">
				        <label>密碼</label>
				    </div>
				    <div class="col-lg-4">
				        <div class="form-group">
				            <input id="password" type="text" class="form-control" required/>
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
				                    <th class="col-lg-2 text-center">姓名</th>
				                    <th class="col-lg-2 text-center">地區</th>
				                    <th class="col-lg-3 text-center">帳號</th>
				                    <th class="col-lg-3 text-center">密碼</th>
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
				
				<form action="AddTeamLeaderConfirm" method="post" style="display:none" id="invisibleForm">
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
	<script src="js/addTeamLeader.js"></script>
</body>
</html>