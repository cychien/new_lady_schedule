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
				<li class="active"><a href="#counterManagement" data-toggle="collapse" aria-expanded="false">櫃台管理</a>
					<ul class="collapse list-unstyled" id="counterManagement">
						<li><a href="NewCounter">新增專櫃</a></li>
						<li><a href="CounterManagement">專櫃管理</a></li>
					</ul>
				</li>
				<li><a href="BAManagement">專櫃人員管理</a></li>
				<!-- <li><a href="Match">櫃位/專櫃人員配對</a></li> -->
				<li><a href="TeamLeader3">班表異動</a></li>
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
                            <li><a href="NewCounter">新增櫃台</a></li>
                            <li><a href="CounterManagement">櫃台管理</a></li>
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
						<c:forEach items="${area}" var="area">
							<a href="ChangeAreaSession?areaId=${area.areaId }" class="btn btn-outlined btn-primary"><c:out value="${area.areaName}"></c:out></a>
						</c:forEach>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<h2>專櫃管理<c:out value="( ${sessionScope.areaName} )"></c:out></h2>
					</div>
				</div>
				<br />
				<br />
				
				<div class="row">
					<div class="col-lg-12">
						<table class="table table-hover table-responsive">
							<thead>
								<tr>
									<th>專櫃名</th>
									<th>星期一</th>
									<th>星期二</th>
									<th>星期三</th>
									<th>星期四</th>
									<th>星期五</th>
									<th>星期六</th>
									<th>星期日</th>
									<th>編輯</th>
									<th>刪除</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${counterInfo }" var="counter">
									<tr>
										<td class="name">${counter.counterName }</td>
										<td class="monday">${counter.monday }</td>
										<td class="tuesday">${counter.tuesday }</td>
										<td class="wednesday">${counter.wednesday }</td>
										<td class="thursday">${counter.thursday }</td>
										<td class="friday">${counter.friday }</td>
										<td class="saturday">${counter.saturday }</td>
										<td class="sunday">${counter.sunday }</td>
										<td><button type="button" data-toggle="modal" data-target="#edit" data-uid="${counter.counterID }" class="update btn btn-warning btn-sm"><span class="glyphicon glyphicon-pencil"></span></button></td>
		            					<td><button type="button" data-toggle="modal" data-target="#delete" data-uid="${counter.counterID }" class="delete btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></button></td>
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
						      	  <label><span class="glyphicon glyphicon-bookmark"></span> 專櫃名</label>
							  	  <input id="modal-name" type="text" class="form-control" required autofocus>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期一上班時間</label>
							  	   <select id="modal-monday" class="form-control" >
						            	<option>10.5-21.5</option>
										<option>10.5-22.0</option>
										<option>10.5-22.0</option>
										<option>11.0-21.5</option>
										<option>11.0-22.0</option>
										<option>11.0-22.5</option>
						            </select>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期二上班時間</label>
							  	  <select id="modal-tuesday" class="form-control" >
						            	<option>10.5-21.5</option>
										<option>10.5-22.0</option>
										<option>10.5-22.0</option>
										<option>11.0-21.5</option>
										<option>11.0-22.0</option>
										<option>11.0-22.5</option>
								  </select>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期三上班時間</label>
							  	  <select id="modal-wednesday" class="form-control" >
						              <option>10.5-21.5</option>
									  <option>10.5-22.0</option>
									  <option>10.5-22.0</option>
									  <option>11.0-21.5</option>
									  <option>11.0-22.0</option>
									  <option>11.0-22.5</option>
						          </select>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期四上班時間</label>
							  	  <select id="modal-thursday" class="form-control" >
						            	<option>10.5-21.5</option>
										<option>10.5-22.0</option>
										<option>10.5-22.0</option>
										<option>11.0-21.5</option>
										<option>11.0-22.0</option>
										<option>11.0-22.5</option>
						            </select>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期五上班時間</label>
							  	  <select id="modal-friday" class="form-control" >
						            	<option>10.5-21.5</option>
										<option>10.5-22.0</option>
										<option>10.5-22.0</option>
										<option>11.0-21.5</option>
										<option>11.0-22.0</option>
										<option>11.0-22.5</option>
						            </select>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期六上班時間</label>
							  	  <select id="modal-saturday" class="form-control" >
						            	<option>10.5-21.5</option>
										<option>10.5-22.0</option>
										<option>10.5-22.0</option>
										<option>11.0-21.5</option>
										<option>11.0-22.0</option>
										<option>11.0-22.5</option>
						            </select>
							  </div>
							  <div class="form-group">
						      	  <label><span class="glyphicon glyphicon-star"></span> 星期日上班時間</label>
							  	  <select id="modal-sunday" class="form-control" >
						            	<option>10.5-21.5</option>
										<option>10.5-22.0</option>
										<option>10.5-22.0</option>
										<option>11.0-21.5</option>
										<option>11.0-22.0</option>
										<option>11.0-22.5</option>
						            </select>
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
				
				<form action="DoActionOnCounter" method="post" style="display:none" id="invisibleForm">
					<input type="text" name="command" id="invisibleCommand">
					<input type="text" name="id" id="invisibleId">
					<input type="text" name="name" id="invisibleName">
					<input type="text" name="monday" id="invisibleMonday">
					<input type="text" name="tuesday" id="invisibleTuesday">
					<input type="text" name="wednesday" id="invisibleWednesday">
					<input type="text" name="thursday" id="invisibleThursday">
					<input type="text" name="friday" id="invisibleFriday">
					<input type="text" name="saturday" id="invisibleSaturday">
					<input type="text" name="sunday" id="invisibleSunday">
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
	$(".delete").click(function(e){
		let id = $(this).data("uid");
		$("#invisibleCommand").val("delete");
        $("#invisibleId").val(id);
     	readyToSubmit();   
	});
	$(".update").click(function(e){
       let id = $(this).data("uid");
       let name = $($(e.target).closest("tr")[0]).find(".name").html();
   	   let monday = $($(e.target).closest("tr")[0]).find(".monday").html();
   	   let tuesday = $($(e.target).closest("tr")[0]).find(".tuesday").html();
   	   let wednesday = $($(e.target).closest("tr")[0]).find(".wednesday").html();
	   let thursday = $($(e.target).closest("tr")[0]).find(".thursday").html();
	   let friday = $($(e.target).closest("tr")[0]).find(".friday").html();
	   let saturday = $($(e.target).closest("tr")[0]).find(".saturday").html();
	   let sunday = $($(e.target).closest("tr")[0]).find(".sunday").html();
	   
   	   $("#invisibleId").val(id);
   	   $("#modal-name").val(name);
       $("#modal-monday").val(monday);
       $("#modal-tuesday").val(tuesday);
       $("#modal-wednesday").val(wednesday);
       $("#modal-thursday").val(thursday);
       $("#modal-friday").val(friday);
       $("#modal-saturday").val(saturday);
       $("#modal-sunday").val(sunday);
	});
    $("#up").click(function(){
           //let id = $("#modal-id").val();
           let name = $("#modal-name").val();
           let monday = $("#modal-monday").val();
           let tuesday = $("#modal-tuesday").val();  
           let wednesday = $("#modal-wednesday").val(); 
           let thursday = $("#modal-thursday").val();
           let friday = $("#modal-friday").val();
           let saturday = $("#modal-saturday").val();
           let sunday = $("#modal-sunday").val();
           
           $("#invisibleCommand").val("update");
           $("#invisibleName").val(name);
           $("#invisibleMonday").val(monday);
           $("#invisibleTuesday").val(tuesday);
           $("#invisibleWednesday").val(wednesday);
           $("#invisibleThursday").val(thursday);
           $("#invisibleFriday").val(friday);
           $("#invisibleSaturday").val(saturday);
           $("#invisibleSunday").val(sunday);
           readyToSubmit();   
    });
    function readyToSubmit() {
    	$("#invisibleForm").submit();
    }
	</script>
</body>
</html>