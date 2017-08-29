<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>歡迎來到Lady 內衣排班系統</title>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<style>
	a, a:hover, a:focus {
		color: #4aaf51;
	}
	
	::-moz-selection { background: #4aaf51; color: #fff; text-shadow: none; }
	::selection { background: #4aaf51; color: #fff; text-shadow: none; }
	
	.btn-link-1 {
		background: #4aaf51;
	}
	
	.form-bottom form .input-error {
		border-color: #4aaf51;
	}
	
	button.btn {
		background: #4aaf51;
	}
	
	button.btn:focus { outline: 0; opacity: 0.6; background: #4aaf51; color: #fff; }

	button.btn:active:focus, button.btn.active:focus { outline: 0; opacity: 0.6; background: #4aaf51; color: #fff; }

	label {
		font-weight: normal;
	}
</style>
</head>
<body>
<%--<%--%>
<%--int employeeId = 0;--%>
<%--Object employeeIdObj = session.getAttribute("employeeId");--%>
<%--if(employeeIdObj != null) {--%>
	<%--employeeId = (int)employeeIdObj;--%>
<%--}--%>
<%--String areaName= (String)session.getAttribute("areaName");--%>

<%--if(employeeId != 0 && areaName != null) {--%>
	<%--int positionId = (int)session.getAttribute("positionId");--%>
	<%--if(positionId == 2) {--%>
		<%--response.sendRedirect("TeamLeader");--%>
	<%--}--%>
	<%--else if(positionId == 3) {--%>
		<%--response.sendRedirect("CheckPeople");--%>
	<%--}--%>
<%--}--%>
<%--else if(employeeId != 0) {--%>
	<%--response.sendRedirect("currentMonth.jsp");--%>
<%--}--%>
<%--%>--%>
	<nav class="navbar navbar-inverse navbar-fixed-top" >
		<div class="container-fluid">
			 <div class="navbar-header ">
			 	<a href="index.jsp" class="navbar-left"><img src="imgs/logo.png" /></a>
			 </div>
		</div>
	</nav>
	
	<!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>Lady 內衣</strong></h1>
                            <div class="description">
                            	<p>
	                            	 歡迎來到 Lady 內衣排休系統
                            	</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>註冊</h3>
                        			<p>請於下方填寫相關資料</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="RegisterController" method="post" class="login-form form-horizontal">
			                    	<div class="form-group">
			                    		<label for="form-username" class="control-label col-sm-2">姓名:</label>
			                    		<div class="col-sm-10">
			                        		<input type="text" name="username" placeholder="姓名..." class="form-username form-control" id="form-username" required autofocus>
			                        	</div>
			                        </div>
			                        <div class="form-group">
			                    		<label for="form-username" class="control-label col-sm-2">地區:</label>
			                    		<div class="col-sm-10">
			                        		<select name="area" class="form-area form-control" id="form-area">
				                        		<c:forEach items="${area }" var="area">
				                        			<option value="${ area.areaName}">${ area.areaName}</option>
				                        		</c:forEach>
			                        		</select>
			                        	</div>
			                        </div>
			                    	<div class="form-group">
			                    		<label for="form-account" class="control-label col-sm-2">帳號:</label>
			                        	<div class="col-sm-10">
			                        		<input type="text" name="account" placeholder="帳號..." class="form-account form-control" id="form-account" required>
			                        		<p>(請輸入電子信箱)</p>
			                        	</div>
			                        </div>
			                        <div class="form-group">
			                        	<label for="form-password" class="control-label col-sm-2">密碼:</label>
			                        	<div class="col-sm-10">
			                        		<input type="password" name="password" placeholder="密碼..." class="form-password form-control" id="form-password" required>
			                        	</div>
			                        </div>
			                        <button type="submit" class="btn">註冊</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
</body>
</html>