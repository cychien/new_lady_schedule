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
<link rel="stylesheet" href="css/manage-interface.css">
<link rel="stylesheet" href="css/bootstrap-editable.css">
<style>
body{padding-top:30px;}

.btn-outlined {
    border-radius: 0;
    -webkit-transition: all 0.3s;
       -moz-transition: all 0.3s;
            transition: all 0.3s;
}
.btn-outlined.btn-info {
    background: none;
    border: 3px solid #5bc0de;
    color: #5bc0de;
}
.btn-outlined.btn-info:hover,
.btn-outlined.btn-info:active {
    color: #FFF;
    background: #39b3d7;
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
				<li class="active"><a href="TeamLeader">選擇地區</a></li>
				<li><a href="#counterManagement" data-toggle="collapse" aria-expanded="false">櫃台管理</a>
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
                    	<ul class="nav navbar-nav pull-right">
                            <li><a href="LogoutController2">登出</a></li>
                        </ul>
                   </div>
				</div>
			</nav>
			<div id="page">
				<div class="row">
					<div class="col-lg-12">
						<h2>選擇地區</h2>
						<br />
						<h4>現在地區: <c:out value="${sessionScope.areaName}"></c:out></h4>
					</div>
				</div>
				<br />
				<br />
				<c:forEach items="${area}" var="area">
					<div class="row">
						<div class="col-lg-12">
							<a href="ChangeAreaSession?areaId=${area.areaId }" class="btn btn-outlined btn-block btn-info btn-lg"><c:out value="${area.areaName}"></c:out></a>
						</div>
					</div>
					<br />
				</c:forEach>
					
			</div>
		</div>	
	</div>
	<!-- jQuery CDN -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<!-- Bootstrap Js CDN -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- jQuery Nicescroll CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.6.8-fix/jquery.nicescroll.min.js"></script>
	<script src="js/bootstrap-editable.min.js"></script>
	<script src="js/manage-interface.js"></script>
</body>
</html>