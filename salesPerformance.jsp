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
    <!--File Input CSS-->
    <link rel="stylesheet" href="css/fileinput.min.css">
    <link rel="stylesheet" href="css/fileinput-rtl.min.css">
    <link rel="stylesheet" href="css/3D-button.css">
</head>
<style>
    table {
        font-size: 10px;
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
            <li class="active"><a href="SalesPerformance">業績上傳</a></li>
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
            <div class="row">
                <div class="col-lg-12">
                    <h2>業績上傳<c:out value="( ${sessionScope.selectedAreaName} )"></c:out></h2>
                </div>
            </div>
            <br />
            <br />

            <div class="col-lg-8 col-lg-offset-2">

                <div class="row">
                <c:forEach items="${areas}" var="area">
                    <div class="col-lg-3">
                        <a href="ChangeArea?areaId=${area.areaId}" class="btn btn-warning btn3d areaButton" id="${area.areaId}">${area.areaName}</a>
                    </div>
                </c:forEach>
                </div>
                <br>
                <br>

                <c:if test="${not empty result}">
                    <p style="font-size: 20px; color: #ba010f; font-weight: bold;">${result}</p>
                </c:if>

                <form method="post" action="UploadSalesPerformance" enctype="multipart/form-data">
                    <label class="control-label" style="font-size: 20px;">選擇檔案 <span style="font-weight: normal; color: blue; font-size: 16px;">(只接受csv或txt檔，編碼記得改為utf-8)</span></label>
                    <input id="bonus" type="file" class="file" name="bonus">
                </form>
                </br>
                </br>
                <c:if test="${empty modifyAppear}">
                    <form method="post" action="UploadModifySalesPerformance">
                        <label class="control-label" style="font-size: 20px;">提交修改資訊</label>
                        <br>
                        <br>
                        <div class="row">
                            <div class="col-lg-1">
                                <label style="font-size: 16px;">專櫃</label>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <select class="form-control" name="counter">
                                        <c:forEach items="${counters}" var="counter">
                                            <<option value="${counter.counterID}">${counter.counterName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-1">
                                <label style="font-size: 16px;">日期</label>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <select class="form-control" name="date">
                                        <c:forEach items="${date}" var="date">
                                            <option value="${date}">${date}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-1">
                                <label style="font-size: 16px;">業績</label>
                            </div>
                            <div class="col-lg-3">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="money" required/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <button class="btn btn-primary pull-right">確認提交</button>
                            </div>
                        </div>
                    </form>
                </c:if>
                </br>
                </br>
                <c:if test="${not empty modifyMessage}">
                    <p style="font-size: 20px; color: #ba010f; font-weight: bold;">${modifyMessage}</p>
                </c:if>

                <c:if test="${not empty isRepeat}">
                    <p style="font-size: 20px; color: #ba010f; font-weight: bold;">${isRepeat}</p>
                </c:if>
                <c:if test="${not empty isOK}">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="success" style="font-size: 16px;">櫃位</th>
                            <th class="success" style="font-size: 16px;">日期</th>
                            <th class="success" style="font-size: 16px;">業績</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${fileInfo}" var="fileInfo" varStatus="status">
                            <c:choose>
                                <c:when test="${not empty fileInfo.counterName}">
                                    <tr style="background: #ecaed0; font-weight: bold;">
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${status.count%2==0}" >
                                        <tr style="background-color: #ececec;">
                                    </c:if>
                                    <c:if test="${status.count%2!=0}" >
                                        <tr>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                            <td style="font-size: 16px;">${fileInfo.counterName}</td>
                            <td style="font-size: 16px;">${fileInfo.date}</td>
                            <td style="font-size: 16px;">$ ${fileInfo.bonus}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <button class="btn btn-primary" id="confirm" style="margin-right: 2%;" onClick="waitingDialog.show('檔案輸出中，請稍等一下')"><i class="icon-white icon-list-alt"></i> 送出</button>
                    <button class="btn btn-danger" id="delete"><i class="icon-white icon-list-alt"></i> 取消</button>
                </c:if>
                <form id="invisibleForm" style="display: none;" method="post" action="DoActionOnSalesPerformance">
                    <input type="text" id="command" name="command">
                </form>
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
<script src="js/fileinput.min.js"></script>
<script src="js/loadingModal.js"></script>
<script>
    $("#confirm").click(function(){
        $("#command").val("confirm");
        $("#invisibleForm").submit();
    });

    $("#delete").click(function(){
        $("#command").val("delete");
        $("#invisibleForm").submit();
    });
    $("#${activeId}").addClass("active");
</script>
</body>
</html>

