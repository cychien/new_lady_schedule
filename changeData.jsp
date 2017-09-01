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
    <!-- jQuery CDN -->
    <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <!-- Bootstrap Js CDN -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- jQuery Nicescroll CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.nicescroll/3.6.8-fix/jquery.nicescroll.min.js"></script>
    <script src="js/bootstrap-editable.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.10/handlebars.min.js"></script>
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
    /*tbody {*/
    /*counter-reset: rowNumber;*/
    /*}*/

    /*tbody tr {*/
    /*counter-increment: rowNumber;*/
    /*}*/

    /*tbody tr td:first-child::before {*/
    /*content: counter(rowNumber);*/
    /*}*/
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
            <li class="active"><a href="ChangeData">專櫃人員變動資料修改</a></li>
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

            <script id="changeData-template" type="x-handlebars-template">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>專櫃人員變動資料修改</h2>
                    </div>
                </div>
                <br />
                <br />
                <table class="table table-hover table-bordered">
                    <thead>
                    <%--<th>編號</th>--%>
                    <th width="8%">姓名</th>
                    <th>到職日</th>
                    <th>佔比獎金</th>
                    <th>目標獎金</th>
                    <th>庫存管理獎金</th>
                    <th>年資獎金</th>
                    <th>保障薪/獎金/退款</th>
                    <th>1.9%補充保費</th>
                    <th>代扣款</th>
                    <th>違規扣款</th>
                    <th>員購</th>
                    <th>電話費</th>
                    <th>盤虧扣款</th>
                    <th>借支</th>
                    <th>法院扣薪</th>
                    <th>其他扣項(及其他)</th>
                    <th>個人業績</th>
                    </thead>
                    <tbody>
                    {{#each this}}
                    <tr>
                        <%--<td></td>--%>
                        <td>{{employeeName}}</td>
                        <td>{{startDate}}</td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-businessBonus" data-url='UpdateChangeData'>{{businessBonus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-targetBonus" data-url='UpdateChangeData'>{{targetBonus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-managementBonus" data-url='UpdateChangeData'>{{managementBonus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-yearBonus" data-url='UpdateChangeData'>{{yearBonus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-otherBonus" data-url='UpdateChangeData'>{{otherBonus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-supplementMinus" data-url='UpdateChangeData'>{{supplementMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-chargeMinus" data-url='UpdateChangeData'>{{chargeMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-violationMinus" data-url='UpdateChangeData'>{{violationMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-buyMinus" data-url='UpdateChangeData'>{{buyMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-phoneMinus" data-url='UpdateChangeData'>{{phoneMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-checkMinus" data-url='UpdateChangeData'>{{checkMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-borrowMinus" data-url='UpdateChangeData'>{{borrowMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-courtMinus" data-url='UpdateChangeData'>{{courtMinus}}</a></td>
                        <td><a href="#" class="data" data-type="text" data-pk="{{employeeId}}-otherMinus" data-url='UpdateChangeData'>{{otherMinus}}</a></td>
                        <td><a href="#" id="{{employeeId}}-salesPerformance">{{salesPerformance}}</a></td>
                    </tr>
                    {{/each}}
                    </tbody>
                </table>
            </script>
        </div>
    </div>
</div>

<script>
    $(function() {
        var jsonData;
        var theTemplateScript = $("#changeData-template").html();
        var theTemplate = Handlebars.compile(theTemplateScript);
        $.ajax({url: "ProduceChangeData", success: function(result) {
            jsonData = result;
            $('#page').append(theTemplate(jsonData));
            $('.data').editable();
            $('.editable').editable({
                showbuttons: false
            }).on('shown', function(ev, editable) {
                setTimeout(function() {
                    editable.input.$input.select();
                },0);
            });
        }});
    });
</script>
<script src="js/manage-interface.js"></script>
</body>
</html>