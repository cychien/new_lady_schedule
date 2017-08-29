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
	response.sendRedirect("CurrentMonth");
}
else {
	response.sendRedirect("index.jsp");
}
%>

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Our Custom CSS -->
<link rel="stylesheet" href="css/manage-interface.css">
<link rel="stylesheet" href="css/bootstrap-editable.css">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<!-- Bootstrap Js CDN -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery Nicescroll CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.6.8-fix/jquery.nicescroll.min.js"></script>
<script src="js/bootstrap-editable.min.js"></script>
<script src="js/util.js"></script>
<script src="js/forTeamLeader3.js"></script>
<style>
.table > tbody > tr > td {
     vertical-align: middle;
}
</style>
</head>
<body>
	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3>Lady 內衣排班系統</h3>
			</div>

			<ul class="list-unstyled components">
				<li><a href="TeamLeader">選擇地區</a></li>
				<li><a href="#counterManagement" data-toggle="collapse" aria-expanded="false">櫃台管理</a>
					<ul class="collapse list-unstyled" id="counterManagement">
						<li><a href="NewCounter">新增專櫃</a></li>
						<li><a href="CounterManagement">專櫃管理</a></li>
					</ul>
				</li>
				<li><a href="BAManagement">專櫃人員管理</a></li>
				<!-- <li><a href="Match">櫃位/專櫃人員配對</a></li> -->
				<li class="active"><a href="TeamLeader3">班表異動</a></li>
				<li><a href="BonusCalculate">獎金分配</a></li>
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
					<ul class="nav navbar-nav pull-right">
                        <li><a href="LogoutController2">登出</a></li>
                    </ul>
				</div>
			</nav>

			<div class="table-responsive" id="table">
				<div class="row">
					<div class="col-lg-3">
						<h2 id="title"></h2>
					</div>
					<div class="col-lg-9">
						<div class="panel panel-success">
      						<div class="panel-heading">代號對照區</div>
      						<div class="panel-body" id="panel-body">
								<div class="col-lg-2">
									<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" data-html="true" title="<li>全班 - 0</li><li>休假 - /</li><li>公休 - 01</li><li>特休 - 02</li><li>婚假 - 03</li><li>產假 - 04</li><li>喪假 - 05</li><li>公假 - 06</li><li>病假 - 07</li>" style="margin-right: 2%;">休假類別代碼</button>
								</div>
								<div class="col-lg-2">
									<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="bottom" data-html="true" title="<li>無櫃台 - 1</li><c:forEach items='${counterList }' var='counterList' varStatus='status'><li>${counterList.counterName } - ${counterList.counterID }</li></c:forEach>" style="margin-right: 2%;">專櫃代碼</button>
								</div>
								<div class="col-lg-2">
									<div style="font-size: 18px;">地區總時數: <span id="totalAreaHour" style="color: #EE7700;"></span></div>
								</div>
								<div class="col-lg-2">
									<div style="font-size: 18px;">公司總時數: <span id="totalCompanyHour" style="color: #EE7700;"></span></div>
								</div>
      						</div>
   						 </div>
					</div>
				</div>
				</br>
				<table class="table table-hover table-bordered">
					<thead>
						<tr>
							<th class="thours">上班時數</th>
							<th class="tcounter">櫃名</th>
							<th class="tname">姓名</th>
							<th class="ttime">日期<br />星期</th>
							<c:forEach items="${nextMonth }" var="weekday" varStatus="status">
								<th>
									<c:choose>
  										<c:when test="${status.count < 10}">
  											0${status.count }
  										</c:when>
  										<c:otherwise>
  											${status.count }
  										</c:otherwise>
									</c:choose><br />${weekday }
								</th>
							</c:forEach>
						</tr>
					</thead>
					
					<script>
					window.onload = loadDoc();
					</script>	
					
					<tbody id="tbody">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script src="js/manage-interface.js"></script>
	<script>
	//$('.shift').on('save', function(e, params) {
	//    alert('Saved value: ' + params.newValue);
	//});
	</script>
</body>
</html>