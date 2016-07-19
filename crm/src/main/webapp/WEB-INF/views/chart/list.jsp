<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>DreamCRM-统计</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="chart"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">统计区间</h3>
                </div>
                <div class="box-body">

                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-aqua"><i class="fa fa-plus"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">本月新增的客户</span>
                            <span class="info-box-number">${newCustomerCount}</span>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-green"><i class="fa fa-plus"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">本月完成的交易</span>
                            <span class="info-box-number">${saleCount}</span>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="info-box">
                        <span class="info-box-icon bg-red"><i class="fa fa-plus"></i></span>
                        <div class="info-box-content">
                            <span class="info-box-text">本月交易额</span>
                            <span class="info-box-number">${saleMoney}</span>
                        </div>
                    </div>
                </div>
            </div>


            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">销售业务统计</h3>
                </div>
                <div class="box-body">
                    <div id="pieChart" style="width: 100%;height: 300px"></div>
                </div>
            </div>

            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">员工业绩图</h3>
                </div>
                <div class="box-body">
                    <div id="barChart" style="width: 100%;height: 300px"></div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/echarts/echarts.simple.min.js"></script>
<script>
    $(function () {

        var pieChart = echarts.init($("#pieChart")[0]);
        var pieOption = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series: [
                {
                    name: '业务',
                    type: 'pie',
                    data: [
                        {value:335, name:'初次接触'},
                        {value:310, name:'确定意向'},
                        {value:234, name:'提交合同'},
                        {value:135, name:'交易完成'},
                        {value:1548, name:'交易搁置'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: '#20c6eb'
                        }
                    }
                }]
        };
        // 使用刚指定的配置项和数据显示图表
        pieChart.setOption(pieOption);
        var barChart = echarts.init($("#barChart")[0]);
        var option = {
            tooltip: {},
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [
                {
                    name: '业绩',
                    type: 'bar',
                    data: []
                }
            ]
        };
        barChart.setOption(option);
    });
</script>
</body>
</html>
