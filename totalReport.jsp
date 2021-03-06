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
    <link rel="stylesheet" href="css/3D-button.css">
</head>
<style>
    th {
        font-size: 16px;
        background: #D3EFF9;
    }
    td {
        font-weight: 600;
        font-size: 16px;
    }
    .newlink {
        text-decoration: underline;
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
            <li><a href="ChangeData">專櫃人員變動資料修改</a></li>
            <li><a href="BonusCalculate">獎金上傳</a></li>
            <li><a href="SalesPerformance">業績上傳</a></li>
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
            <li class="active"><a href="TotalReport">薪資計算</a></li>
            <li><a href="HistoryRecord">歷史紀錄</a></li>
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

            <%--<div class="row">--%>
                <%--<div class="="col-lg-12>--%>
                    <%--<div class="panel panel-primary">--%>
                        <%--<!-- Default panel contents -->--%>
                        <%--<div class="panel-heading">--%>
                            <%--<h2 class="panel-title">--%>
                                <%--加班時數與加班費統計--%>
                            <%--</h2>--%>
                        <%--</div>--%>
                        <%--<div class="panel-body">--%>
                            <%--<p style="color: red; font-weight: 500;">加班費的計算以 133元/hr 計</p>--%>
                        <%--</div>--%>
                        <%--<ul class="list-group">--%>

                            <%--<li class="list-group-item">--%>
                                <%--<h4>北部</h4>--%>
                                <%--<table class="table table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th>縣市名</th>--%>
                                        <%--<th>BA人數</th>--%>
                                        <%--<th>總加班時數</th>--%>
                                        <%--<th>總加班費</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                        <%--<c:forEach items="${north}" var="north">--%>
                                            <%--<tr>--%>
                                                <%--<td>${north.name}</td>--%>
                                                <%--<td>${north.people}</td>--%>
                                                <%--<td>${north.hours}</td>--%>
                                                <%--<td>${north.cost}</td>--%>
                                            <%--</tr>--%>
                                        <%--</c:forEach>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</li>--%>

                            <%--<li class="list-group-item">--%>
                                <%--<h4>中部</h4>--%>
                                <%--<table class="table table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th>縣市名</th>--%>
                                        <%--<th>BA人數</th>--%>
                                        <%--<th>總加班時數</th>--%>
                                        <%--<th>總加班費</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                        <%--<c:forEach items="${central}" var="central">--%>
                                            <%--<tr>--%>
                                                <%--<td>${central.name}</td>--%>
                                                <%--<td>${central.people}</td>--%>
                                                <%--<td>${central.hours}</td>--%>
                                                <%--<td>${central.cost}</td>--%>
                                            <%--</tr>--%>
                                        <%--</c:forEach>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</li>--%>

                            <%--<li class="list-group-item">--%>
                                <%--<h4>南部</h4>--%>
                                <%--<table class="table table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th>縣市名</th>--%>
                                        <%--<th>BA人數</th>--%>
                                        <%--<th>總加班時數</th>--%>
                                        <%--<th>總加班費</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                        <%--<c:forEach items="${south}" var="south">--%>
                                            <%--<tr>--%>
                                                <%--<td>${south.name}</td>--%>
                                                <%--<td>${south.people}</td>--%>
                                                <%--<td>${south.hours}</td>--%>
                                                <%--<td>${south.cost}</td>--%>
                                            <%--</tr>--%>
                                        <%--</c:forEach>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</li>--%>

                            <%--<li class="list-group-item">--%>
                                <%--<h4>東部</h4>--%>
                                <%--<table class="table table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th>縣市名</th>--%>
                                        <%--<th>BA人數</th>--%>
                                        <%--<th>總加班時數</th>--%>
                                        <%--<th>總加班費</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                        <%--<c:forEach items="${east}" var="east">--%>
                                            <%--<tr>--%>
                                                <%--<td>${east.name}</td>--%>
                                                <%--<td>${east.people}</td>--%>
                                                <%--<td>${east.hours}</td>--%>
                                                <%--<td>${east.cost}</td>--%>
                                            <%--</tr>--%>
                                        <%--</c:forEach>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</li>--%>

                            <%--<li class="list-group-item">--%>
                                <%--<h4>離島</h4>--%>
                                <%--<table class="table table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th>縣市名</th>--%>
                                        <%--<th>BA人數</th>--%>
                                        <%--<th>總加班時數</th>--%>
                                        <%--<th>總加班費</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                        <%--<c:forEach items="${island}" var="island">--%>
                                            <%--<tr>--%>
                                                <%--<td>${island.name}</td>--%>
                                                <%--<td>${island.people}</td>--%>
                                                <%--<td>${island.hours}</td>--%>
                                                <%--<td>${island.cost}</td>--%>
                                            <%--</tr>--%>
                                        <%--</c:forEach>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</li>--%>

                            <%--<li class="list-group-item" style="border-top: 3px dashed red;">--%>
                                <%--<h3 style="margin-top: 10px;">總和</h3>--%>
                                <%--<table class="table table-hover">--%>
                                    <%--<thead>--%>
                                    <%--<tr>--%>
                                        <%--<th>BA人數</th>--%>
                                        <%--<th>總加班時數</th>--%>
                                        <%--<th>總加班費</th>--%>
                                    <%--</tr>--%>
                                    <%--</thead>--%>
                                    <%--<tbody>--%>
                                        <%--<tr>--%>
                                            <%--<td>${total.people}</td>--%>
                                            <%--<td>${total.hours}</td>--%>
                                            <%--<td>${total.cost}</td>--%>
                                        <%--</tr>--%>
                                    <%--</tbody>--%>
                                <%--</table>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="row">
                <div class="="col-lg-12>

                    <h2>薪資計算</h2>
                    <br>

                    <c:if test="${not empty saveSuccess}">
                        <div><label style="font-size: 20px; color: red;">${saveSuccess}</label></div><br>
                    </c:if>

                    <div class="panel panel-primary">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            <h2 class="panel-title">
                                薪資計算
                            </h2>
                        </div>
                        <div class="panel-body">
                            <span style="color: red; font-weight: 500;">加班費的計算以 133元/hr 計
                                <button class="btn btn-info btn-link" id="open"> <span class="glyphicon glyphicon-hand-down"></span> 全部展開</button></span>
                        </div>

                        <div class="panel-group" id="accordion">

                            <c:forEach items="${salaryMap}" var="entry" varStatus="status">
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse${status.count}" class="newlink">${entry.key}</a>
                                        </h4>
                                    </div>
                                    <div id="collapse${status.count}" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <table class="table table-hover">
                                                <thead>
                                                <tr>
                                                    <th width="5%">姓名</th>
                                                    <th>到職日</th>
                                                    <th>支薪方式</th>
                                                    <th width="5%">類別</th>
                                                    <th>本薪</th>
                                                    <th>加班費</th>
                                                    <th>營業獎金</th>
                                                    <th>目標獎金</th>
                                                    <th>庫存管理獎金</th>
                                                    <th>績效獎金</th>
                                                    <th>年資獎金</th>
                                                    <th>佔比獎金</th>
                                                    <th>教育老師補貼</th>
                                                    <th>主櫃津貼</th>
                                                    <th>津貼</th>
                                                    <th>保障薪/獎金/退款</th>
                                                    <th>投保金額</th>
                                                </tr>
                                                <tr>
                                                    <th></th>
                                                    <th></th>
                                                    <th></th>
                                                    <th>類別</th>
                                                    <th>代扣勞健保費</th>
                                                    <th>1.9%補充保費</th>
                                                    <th>代扣款</th>
                                                    <th>違規扣款</th>
                                                    <th>員購</th>
                                                    <th>電話費</th>
                                                    <th>盤虧扣損</th>
                                                    <th>借支</th>
                                                    <th>法院扣薪</th>
                                                    <th>其他扣項(及其他)</th>
                                                    <th></th>
                                                    <th></th>
                                                    <th>總薪水</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${entry.value}" var="salaryInfo">
                                                        <tr bgcolor="#D5DCEA">
                                                            <td>${salaryInfo.employeeName}</td>
                                                            <td>${salaryInfo.startTime}</td>
                                                            <td>${salaryInfo.payMethod}</td>
                                                            <td>應領</td>
                                                            <td>${salaryInfo.base}</td>
                                                            <td>${salaryInfo.overtime}</td>
                                                            <td>${salaryInfo.bonus}</td>
                                                            <td>${salaryInfo.targetBonus}</td>
                                                            <td>${salaryInfo.managementBonus}</td>
                                                            <td>${salaryInfo.performanceBonus}</td>
                                                            <td>${salaryInfo.yearBonus}</td>
                                                            <td>${salaryInfo.businessBonus}</td>
                                                            <td>${salaryInfo.educationBonus}</td>
                                                            <td>${salaryInfo.ownerBonus}</td>
                                                            <td>${salaryInfo.allowance}</td>
                                                            <td>${salaryInfo.otherBonus}</td>
                                                            <td>${salaryInfo.insurance}</td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td></td>
                                                            <td></td>
                                                            <td>應扣</td>
                                                            <td>${salaryInfo.insuranceMinus}</td>
                                                            <td>${salaryInfo.supplementMinus}</td>
                                                            <td>${salaryInfo.chargeMinus}</td>
                                                            <td>${salaryInfo.violationMinus}</td>
                                                            <td>${salaryInfo.buyMinus}</td>
                                                            <td>${salaryInfo.phoneMinus}</td>
                                                            <td>${salaryInfo.checkMinus}</td>
                                                            <td>${salaryInfo.borrowMinus}</td>
                                                            <td>${salaryInfo.courtMinus}</td>
                                                            <td>${salaryInfo.otherMinus}</td>
                                                            <td></td>
                                                            <td></td>
                                                            <td style="color: red; font-size:20px;">$${salaryInfo.salary}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <br>
                    <br>
                    <c:choose>
                        <c:when test="${not empty buttonDisabled}">
                            <button id="writeDBButton" type="button" class="btn btn-danger btn-lg btn3d" disabled><span class="glyphicon glyphicon-ok"></span>當月薪資已送出</button>
                        </c:when>
                        <c:otherwise>
                            <button id="writeDBButton" type="button" class="btn btn-danger btn-lg btn3d"><span class="glyphicon glyphicon-ok"></span>當月薪資確認送出</button>
                        </c:otherwise>
                    </c:choose>
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
<script>
    $("#writeDBButton").click(function() {
        window.location.href = "WriteHistoryRecord";
    });
    $("#open").click(function () {
        $(".panel-collapse").addClass("collapse in");
    });
</script>
</body>
</html>
