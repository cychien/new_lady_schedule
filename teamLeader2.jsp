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
				<li><a href="TeamLeader">選擇地區</a></li>
				<li><a href="#counterManagement" data-toggle="collapse" aria-expanded="false">櫃台管理</a>
					<ul class="collapse list-unstyled" id="counterManagement">
						<li><a href="NewCounter">新增專櫃</a></li>
						<li><a href="CounterManagement">專櫃管理</a></li>
					</ul>
				</li>
				<li><a href="BAManagement">專櫃人員管理</a></li>
				<!-- <li class="active"><a href="Match">櫃位/專櫃人員配對</a></li> -->
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
					<ul class="nav navbar-nav pull-right">
                        <li><a href="LogoutController2">登出</a></li>
                    </ul>
				</div>
			</nav>
			<div id="page">
				<div class="row">
					<div class="col-lg-12">
						<h2>櫃位/專櫃人員配對<c:out value="( ${sessionScope.areaName} )"></c:out></h2>
					</div>
				</div>
				<br />
				<br />
				<c:if test="${modifyMode == 0}">
					<div class="row" >
						<div class="col-lg-12">
							<button id="modify" class="btn btn-primary">
					            修改
					        </button>
						</div>
					</div>
					<br />
					<br />
				</c:if>
				<c:if test="${modifyMode == 1}">
					<div class="row" >
						<div class="col-lg-12">
							<button id="submit" class="btn btn-primary" onClick="readyToSend()">
					            修改確認
					        </button>
						</div>
					</div>
					<br />
					<br />
				</c:if>
				
				<div class="row">
					<div class="col-lg-12">
						<c:if test="${modifyMode == 0}">
							<table class="table table-hover table-responsive">
								<thead>
									<tr>
										<th>編號</th>
										<th>姓名</th>
										<th>負責專櫃名</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${matchInfoList}" var="info" varStatus="status">
										<tr>
											<td class="count">${status.count }</td>
											<td class="name">${info.employeeName }</td>
											<td class="counter">
												<c:if test="${info.employeeCounter == null }">
													無櫃台
												</c:if>
												<c:if test="${info.employeeCounter != null }">
													<c:out value="${info.employeeCounter }"></c:out>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
						
				<div class="row">
					<div class="col-lg-12">
						<c:if test="${modifyMode == 1}">
							<table id="example" class="table table-hover table-responsive">
								<thead>
									<tr>
										<th style="display:none;"></th>
										<th>編號</th>
										<th>姓名</th>
										<th>負責專櫃名</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${matchInfoList}" var="info" varStatus="status">
										<tr>
											<td style="display:none;">${info.employeeId }</td>
											<td class="count">${status.count }</td>
											<td class="name">${info.employeeName }</td>
											<td class="counter">
												<select name="counter" class="form-counter form-control" id="employee${info.employeeId }">
						            				<option value="${info.counterId }" selected>
						            					<c:if test="${info.employeeCounter == null }">無櫃台</c:if>
														<c:if test="${info.employeeCounter != null }">
															<c:out value="${info.employeeCounter }"></c:out>
														</c:if>
													</option>
					                                <c:forEach items="${counterList }" var="counter">
					                                	<c:choose>
														    <c:when test="${counter.counterID == info.counterId }">
													            <option value="0">無櫃台</option>
															</c:when>
															<c:otherwise>
													            <option value="${ counter.counterID}">${ counter.counterName}</option>
														    </c:otherwise>
														</c:choose>
					                        	    </c:forEach>
		                       	   				 </select>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
			</div>
		</div>
				
		<form action="Match" style="display:none;" method="post" id="invisibleForm">
			<input type="text" id="command" name="command">
			<input type="text" id="json" name="json">
		</form>
	</div>
	<!-- jQuery CDN -->
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	<!-- Bootstrap Js CDN -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- jQuery Nicescroll CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.6.8-fix/jquery.nicescroll.min.js"></script>
	<script src="js/bootstrap-editable.min.js"></script>
	<script src="js/manage-interface.js"></script>
	<script>
	$("#modify").click(function(){
		$("#command").val("modify");
		$("#invisibleForm").submit();
	});
	function readyToSend() {
		var counterList = [];
		var table = document.getElementById("example");
		for (var r = 1, n = table.rows.length; r < n; r++) {
	    	let employeeId = "employee" + table.rows[r].cells[0].innerHTML;
	    	let counterId = document.getElementById(employeeId).value;
			counterList.push({
	    		"employeeId": table.rows[r].cells[0].innerHTML,
	        	"counterId": counterId
	        });
	    }
	    var json = JSON.stringify(counterList);
	    document.getElementById("json").value = json;
	    document.getElementById("command").value = "submit";
	    document.getElementById("invisibleForm").submit();
	}
	</script>
</body>
</html>