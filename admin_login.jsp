<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		color: #de615e;
	}
	::-moz-selection { background: #de615e; color: #fff; text-shadow: none; }
	::selection { background: #de615e; color: #fff; text-shadow: none; }
	
	.btn-link-1 {
		background: #de615e;
	}
	
	.form-bottom form .input-error {
		border-color: #de615e;
	}
	
	button.btn {
		background: #de615e;
	}
	
	button.btn:focus { outline: 0; opacity: 0.6; background: #de615e; color: #fff; }

	button.btn:active:focus, button.btn.active:focus { outline: 0; opacity: 0.6; background: #de615e; color: #fff; }


</style>
</head>
<%--<body>--%>
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
				 	<a href="#" class="navbar-left"><img src="imgs/logo.png" /></a>
				 </div>
			</div>
		</nav>
 
        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                	<div class="row">
                		<div  class="col-sm-6 col-sm-offset-3">
                			<div class="alert alert-warning alert-dismissable" style="display: none;">
    							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            					<strong>對不起!</strong><strong id="alertBox">&nbsp;&nbsp;</strong>
        					</div>
                		</div>
                	</div>
                	<script>
			 			var msg = "${message}";
			 			if(msg != "") {
			 				//alert(msg);
			 				document.getElementById("alertBox").innerHTML += msg;
			 				$('.alert').show()
			 			}
			 		</script>
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
                        			<h3>登入</h3>
                            		<p>請於下方輸入帳號、密碼</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-lock" aria-hidden="true"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="LoginAdminController" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-account">帳號</label>
			                        	<input type="text" name="account" placeholder="帳號..." class="form-username form-control" id="form-username" required autofocus>
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密碼</label>
			                        	<input type="password" name="password" placeholder="密碼..." class="form-password form-control" id="form-password" required>
			                        </div>
			                        <button type="submit" class="btn">登入</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
</body>
</html>