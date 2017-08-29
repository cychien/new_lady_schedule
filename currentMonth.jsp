<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lady 內衣排班系統</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/calendar.css">
<!--  
<link rel="stylesheet" href="http://cdn.jtsage.com/jtsage-datebox/4.2.2/jtsage-datebox-4.2.2.bootstrap.min.css" />
<link rel="stylesheet" href="http://dev.jtsage.com/DateBox/css/syntax.css" />
-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="js/util.js"></script>
<script src="js/construct_table.js"></script>
</head>
<style>
li {
	cursor: default;
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
	else if(positionId == 3) {
		response.sendRedirect("CheckPeople");
	}
}
else if(employeeId != 0){
	
}
else {
	response.sendRedirect("index.jsp");
}
%>
	<nav class="navbar navbar-inverse navbar-fixed-top" >
		<div class="container-fluid">
			 <div class="navbar-header">
			 	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>                        
		      	</button>
			 	<a href="CurrentMonth" class="navbar-left"><img src="imgs/logo.png" /></a>
			 </div>
			 <div class="collapse navbar-collapse" id="myNavbar">
		 		<ul class="nav navbar-nav">
		 			<li class="active"><a href="#" id="justWatch">查看當月班表</a></li>
		 			<li><a href="Arrange" id="arrangeRest">排休</a></li>
		 		</ul>
		 		<ul class="nav navbar-nav navbar-right">
		 			<li><a href="LogoutController">登出</a></li>
		 		</ul>
		 	</div>
		</div>
	</nav>
	
	<div class="row" id="direction-icon">
	</div>

	<div class="bg" id="root">
		<label style="font-size: 20px;">${user}, 你好!</label>
	</div>
	
	<script>
		window.onload = loadDocJustWatch();
	</script>

//下面的內容用不到
	<div class="container">
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4><span class="glyphicon glyphicon-pencil"></span> 填寫細節</h4>
					</div>
					<div class="modal-body">
						<form role="form">
							<div class="form-group">
								<label><span class="glyphicon glyphicon-calendar"></span> 日期</label>
								<input type="text" class="form-control input-lg" readonly="readonly" id="dateId" name="date"/>
							</div>
							<div class="form-group">
								<label><i class="fa fa-caret-square-o-down" aria-hidden="true"></i> 假別</label>
								<select class="form-control input-lg" name="type" id="type">
									<option value="指定休假" id="defaultSelected" selected>指定休假</option>
									<option value="特別休假">特別休假</option>
									<option value="其他休假">其他休假</option>
								</select>
							</div>
							<!-- 
							<div class="form-group">
								<label><span class="glyphicon glyphicon-time"></span> 時間</label>
            					<p>
            						<input name="startTime" class="form-control input-lg" id="startTime" type="text" value="11:00 AM" data-role="datebox" data-options='{"mode":"timebox", "useFocus":true, "useButton": false}' style="cursor: text;" />TO
            						<input name="endTime" class="form-control input-lg" id="endTime" type="text" value="09:30 PM" data-role="datebox" data-options='{"mode":"timebox", "useFocus":true, "useButton": false}' style="cursor: text;" />
            					</p>
							</div>
							-->
							<div class="form-group">
								<label><i class="fa fa-pencil-square-o" aria-hidden="true"></i> 事由</label>
								<input type="text" class="form-control input-lg" name="description" id="description">
							</div>
							<div id="add">
								<button type="button" class="btn btn-success btn-block" id="doAjax"><i class="fa fa-paper-plane" aria-hidden="true"></i> 送出</button>
							</div>
							<div class="row" id="modify">
								<div class="col-sm-6">
									<button type="button" class="btn btn-success btn-block" id="update"><i class="fa fa-hand-paper-o" aria-hidden="true"></i> 送出修改</button>																
								</div>
								<div class="col-sm-6">
									<button type="button" class="btn btn-success btn-block" id="delete"><i class="fa fa-trash" aria-hidden="true"></i> 刪除</button>							
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script> 
	<!--
    <script type="text/javascript" src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 
    <script type="text/javascript" src="http://cdn.jtsage.com/external/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="http://dev.jtsage.com/DateBox/js/doc.js"></script>
    <script type="text/javascript" src="http://cdn.jtsage.com/jtsage-datebox/4.2.2/jtsage-datebox-4.2.2.bootstrap.min.js"></script>
    <script type="text/javascript" src="http://cdn.jtsage.com/jtsage-datebox/i18n/jtsage-datebox.lang.utf8.js"></script>
	-->
</body>
</html>