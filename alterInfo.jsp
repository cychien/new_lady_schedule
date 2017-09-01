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
            <li><a href="AlterInfo" class="active">專櫃人員基本資料修改</a></li>
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

            <script id="basicInfo-template" type="x-handlebars-template">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>專櫃人員基本資料修改</h2>
                    </div>
                </div>
                <br />
                <br />
                <table class="table table-hover table-bordered">
                    <thead>
                        <%--<th>編號</th>--%>
                        <th>姓名</th>
                        <th>到職日</th>
                        <th>支薪方式</th>
                        <th>本薪</th>
                        <th>績效獎金</th>
                        <th>教育老師</th>
                        <th>主櫃津貼</th>
                        <th>津貼</th>
                        <th>代扣勞健</th>
                        <th>投保金額</th>
                        <th>公司</th>
                    </thead>
                    <tbody>
                        {{#each this}}
                            <tr>
                                <%--<td></td>--%>
                                <td>{{employeeName}}</td>
                                <td>{{startDate}}</td>
                                <td><a href="#" class="payMethod" data-type="select" data-pk="{{employeeID}}-payMethod" data-url="UpdateBasicInfo" data-value="{{payMethod}}">{{payMethod}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-base" data-url='UpdateBasicInfo'>{{base}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-performanceBonus" data-url='UpdateBasicInfo'>{{performanceBonus}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-educationBonus" data-url='UpdateBasicInfo'>{{educationBonus}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-ownerBonus" data-url='UpdateBasicInfo'>{{ownerBonus}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-allowance" data-url='UpdateBasicInfo'>{{allowance}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-insuranceMinus" data-url='UpdateBasicInfo'>{{insuranceMinus}}</a></td>
                                <td><a href="#" class="info" data-type="text" data-pk="{{employeeID}}-insurance" data-url='UpdateBasicInfo'>{{insurance}}</a></td>
                                <td><a href="#" class="company" data-type="select" data-pk="{{employeeID}}-company" data-url='UpdateBasicInfo' data-value="{{company}}">{{company}}</a></td>
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
        var theTemplateScript = $("#basicInfo-template").html();
        var theTemplate = Handlebars.compile(theTemplateScript);
        $.ajax({url: "ProduceBasicInfo", success: function(result) {
            jsonData = result;
            $('#page').append(theTemplate(jsonData));
            $('.info').editable();
            $('.editable').editable({
                showbuttons: false
            }).on('shown', function(ev, editable) {
                setTimeout(function() {
                    editable.input.$input.select();
                },0);
            });
            $('.payMethod').editable({
                source: [
                    {value: "正職", text: "正職"},
                    {value: "約聘(日薪1: $1200/日)", text: "約聘(日薪1: $1200/日)"},
                    {value: "約聘(日薪2: $1300/日)", text: "約聘(日薪2: $1300/日)"},
                    {value: "約聘(時薪1: $100/時)", text: "約聘(時薪1: $100/時)"},
                    {value: "約聘(時薪2: $125/時)", text: "約聘(時薪2: $125/時)"},
                    {value: "約聘(時薪3: $130/時)", text: "約聘(時薪3: $130/時)"},
                    {value: "外聘(時薪1: $100/時)", text: "外聘(時薪1: $100/時)"},
                    {value: "外聘(時薪2: $125/時)", text: "外聘(時薪2: $125/時)"},
                    {value: "外聘(時薪3: $130/時)", text: "外聘(時薪3: $130/時)"},
                    {value: "工讀生(時薪1: $100/時)", text: "工讀生(時薪1: $100/時)"},
                    {value: "工讀生(時薪2: $125/時)", text: "工讀生(時薪2: $125/時)"},
                    {value: "工讀生(時薪3: $130/時)", text: "工讀生(時薪3: $130/時)"},
                ],
//                title: "支薪方式",           //编辑框的标题
                disabled: false,           //是否禁用编辑
            });
            $('.company').editable({
                source: [
                    {value: "伊登", text: "伊登"},
                    {value: "伊登(高雄區)", text: "伊登(高雄區)"},
                    {value: "儷丰", text: "儷丰"}
                ],
//                title: "支薪方式",           //编辑框的标题
                disabled: false           //是否禁用编辑
            });

//            $(".info, .company, .payMethod").on('save', function(e, params) {
//                location.reload();
//            });
        }});
    });
</script>
<script src="js/manage-interface.js"></script>
</body>
</html>