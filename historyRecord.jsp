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
    <link rel="stylesheet" href="css/bootstrap-editable.css">
    <link rel="stylesheet" href="css/3D-button.css">
</head>
<style>
    table {
        font-size: 20px;
    }
    /*.editable-wrap {*/
    /*.editable-controls {*/
    /*> select {*/
    /*font-size: 16px;*/
    /*}*/
    /*}*/
    /*}*/
    tbody {
        counter-reset: rowNumber;
    }

    tbody tr {
        counter-increment: rowNumber;
    }

    tbody tr td:first-child::before {
        content: counter(rowNumber);
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
            <li><a href="HistoryReport" class="active">歷史紀錄</a></li>
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
                    <h2>歷史紀錄</h2>
                </div>
            </div>
            <br />
            <br />

            <div class="row">
                <div class="col-lg-6">
                    <form class="form">
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon" id="month-addon">月分: </span>
                                <select name="control-month" class="form-control" aria-describedby="month-addon">
                                    <option>一月</option>
                                    <option>二月</option>
                                    <option>三月</option>
                                    <option>四月</option>
                                    <option>五月</option>
                                    <option>六月</option>
                                    <option>七月</option>
                                    <option>八月</option>
                                    <option>九月</option>
                                    <option>十月</option>
                                    <option>十一月</option>
                                    <option>十二月</option>
                                </select>
                                <span class="input-group-addon" id="year-addon">年分(西元): </span>
                                <input type="text" name="control-year" class="form-control" aria-describedby="year-addon" required>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-3">
                    <button id="searchButton" type="button" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></button>
                </div>
            </div>

            <c:if test="${not empty saveSuccess}">
                <label style="font-size: 20px; color: red;">${saveSuccess}</label>
            </c:if>
            <table class="table table-hover table-bordered">
                <thead>
                <th>編號</th>
                <th>姓名</th>
                <th>本薪</th>
                <th>績效獎金</th>
                <th>教育老師</th>
                <th>主櫃津貼</th>
                <th>津貼</th>
                <th>獎金</th>
                <th>代扣勞健</th>
                <th>投保金額</th>
                </thead>
                <tbody>
                <c:forEach items="${paySummaryInfo}" var="info">
                    <tr>
                        <td></td>
                        <td>${info.employeeName}</td>
                        <td>${info.base}</td>
                        <td>${info.performance}</td>
                        <td>${info.educationBonus}</td>
                        <td>${info.ownerBonus}</td>
                        <td>${allowance}</td>
                        <td>${bonus}</td>
                        <td>${insuranceMinus}</td>
                        <td>${insurance}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${not empty message}">
                <label style="font-size: 20px; color: red;">${message}</label>
            </c:if>
            <c:if test="${not empty buttonAppear}">
                <button id="writeDBButton" type="button" class="btn btn-success btn-lg btn3d"><span class="glyphicon glyphicon-ok"></span>Success</button>
            </c:if>
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
<script>
    $("#writeDBButton").click(function() {
        window.location.href = "WriteHistoryRecord";
    });
    $("#searchButton").click(function() {
        $("form").submit();
    });
    
</script>
</body>
</html>
