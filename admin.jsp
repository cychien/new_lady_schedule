<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<link rel="stylesheet" href="css/checkPeople.css">
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
				<li class="active"><a href="CheckPeople">人員申請確認</a></li>
				<li><a href="AlterInfo">專櫃人員基本資料修改</a></li>
				<li><a href="#teamLeaderManagementSubmenu" data-toggle="collapse" aria-expanded="false">小組長管理</a>
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
				<label style="font-size: 20px;">用戶身分: ${user}</label>
				<br />
				<br />

				<div class="row">
					<div class="col-lg-12">
						<h2>申請人員確認</h2>
					</div>
				</div>
				<br />
				<br />

				<form action="CheckPeopleConfirm" method="post" id="form1">
					<input type="hidden" name="command" id="command" />
					<table class="table table-hover" id="tblList">
						<thead>
				      		<tr>
				        		<th>選取</th>
				        		<th>姓名</th>
				        		<th>區域</th>
				      		</tr>
	    				</thead>
	    				<tbody>
	    					<c:forEach items="${uncheckedPeople}" var="person" varStatus="status">
	    						<tr>
	    							<td>
	    								<div class="checkbox">
	    							    	<label>
	            								<input type="checkbox" name="checkbox" value="${person.employeeId }" onclick="setClickData(${status.count});">
	            								<span class="cr"><i class="cr-icon glyphicon glyphicon-ok"></i></span> 
											</label>
	                  					</div>
	           						</td>
	    							<td class="information">
	    								<div class="checkbox">
	    									<c:out value="${person.employeeName }" />
	    								</div>
	    							</td>
	    							<td class="information">
	    								<div class="checkbox">
	    									<c:out value="${person.employeeArea }" />
	    								</div>
	    							</td>
	    						</tr>
	    					</c:forEach>
	    				</tbody>
					</table>
				</form>
				<div class="row">
					<button type="button" class="btn btn-success btn-sm" id="confirm" style="margin-right: 2%;">允許註冊</button>
					<button type="button" class="btn btn-danger btn-sm" id="delete">刪除</button>
				</div>

				<br>
				<br>
				<br>
				<br>

				<div class="row">
					<div class="col-lg-12">
						<h2>尚未填寫基本資料的人員</h2>
					</div>
				</div>
				<br />
				<br />

				<%--填基本資料--%>
				<form action="BasicInfo" method="post" id="form2">
					<table class="table table-hover" id="infoList">
						<thead>
						<tr>
							<th></th>
							<th width="5%">姓名</th>
							<th width="5%">區域</th>
							<th>支薪方式</th>
							<th>本薪</th>
							<th>績效獎金</th>
							<th>教育老師</th>
							<th>主櫃津貼</th>
							<th>津貼</th>
							<th>代扣勞健</th>
							<th>投保金額</th>
							<th>所屬公司</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${incompletePeople}" var="employee" varStatus="status">
							<tr>
								<td>
									<input type="hidden" name="employeeId" value="${employee.employeeId}" />
								</td>
								<td>
									<label style="color: red;">${employee.employeeName}</label>
								</td>
								<td>
									<label style="color: red;">${employee.areaName}</label>
								</td>
								<td>
									<select class="form-control" name="payMethod" id="payMethod" onchange="prevent()">
										<option value="正職" selected>正職</option>
										<option value="約聘(日薪1: $1200/日)">約聘(日薪1: $1200/日)</option>
										<option value="約聘(日薪2: $1300/日)">約聘(日薪2: $1300/日)</option>
										<option value="約聘(時薪1: $100/時)">約聘(時薪1: $100/時)</option>
										<option value="約聘(時薪2: $125/時)">約聘(時薪2: $125/時)</option>
										<option value="約聘(時薪3: $130/時)">約聘(時薪3: $130/時)</option>
										<option value="外聘(時薪1: $100/時)">外聘(時薪1: $100/時)</option>
										<option value="外聘(時薪2: $125/時)">外聘(時薪2: $125/時)</option>
										<option value="外聘(時薪3: $130/時)">外聘(時薪3: $130/時)</option>
										<option value="工讀生(時薪1: $100/時)">工讀生(時薪1: $100/時)</option>
										<option value="工讀生(時薪2: $125/時)">工讀生(時薪2: $125/時)</option>
										<option value="工讀生(時薪3: $130/時)">工讀生(時薪3: $130/時)</option>
									</select>
								</td>
								<td>
									<input type="text" class="form-control" name="base" id="base" value="13000" required />
								</td>
								<td>
									<input type="text" class="form-control" name="performanceBonus" id="performanceBonus" value="0" required />
								</td>
								<td>
									<input type="text" class="form-control" name="educationBonus" id="educationBonus" value="0" required />
								</td>
								<td>
									<input type="text" class="form-control" name="ownerBonus" id="ownerBonus" value="0" required />
								</td>
								<td>
									<input type="text" class="form-control" name="allowance" id="allowance" value="0" required />
								</td>
								<td>
									<input type="text" class="form-control" name="insuranceMinus" id="insuranceMinus" value="0" required />
								</td>
								<td>
									<input type="text" class="form-control" name="insurance" id="insurance" value="0" required />
								</td>
								<td>
									<select class="form-control" name="company">
										<option value="伊登" selected>伊登</option>
										<option value="伊登(高雄區)">伊登(高雄區)</option>
										<option value="儷丰">儷丰</option>
									</select>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</form>
				<div class="row">
					<div class="col-lg-12">
						<button type="button" class="btn btn-primary pull-right" id="send">確認送出</button>
					</div>
				</div>
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
	<script src="js/checkPeople.js"></script>
</body>
</html>