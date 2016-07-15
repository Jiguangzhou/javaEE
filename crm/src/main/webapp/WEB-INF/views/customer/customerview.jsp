<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>DreamCRM-客户信息-${customer.name}</title>
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
        <jsp:param name="menu" value="customer"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <button class="btn btn-primary btn-xs" id="backBtn"> 返回</button>
            <ol class="breadcrumb">
                <li><a href="/customer"><i class="fa fa-dashboard"></i> 客户列表</a></li>
                <li class="active">${customer.name}</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">
                        <c:choose>
                            <c:when test="${customer.type == 'person'}">
                                <i class="fa fa-user"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fa fa-bank"></i>
                            </c:otherwise>
                        </c:choose>
                        ${customer.name}
                    </h3>
                    <div class="box-tools">
                        <c:if test="${customer.userid !=null}">
                        <button class="btn btn-danger btn-xs" id="openCustomer">公开客户</button>
                        </c:if>
                        <button class="btn btn-primary btn-xs" id="recommend">推荐客户</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr>
                            <td style="width: 100px">联系电话</td>
                            <td style="width: 200px">${customer.tel}</td>
                            <td style="width: 100px">电子邮件</td>
                            <td>${customer.email}</td>
                            <td style="width: 100px">微信</td>
                            <td style="width: 200px">${customer.weixin}</td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td colspan="3">${customer.address}</td>
                            <td>等级</td>
                            <td style='color: #f6d828'>${customer.level}</td>
                        </tr>
                        <c:if test="${not empty customer.companyid}">
                            <tr>
                                <td>所属公司</td>
                                <td colspan="5"><a href="/customer/${customer.companyid}">${customer.companyname}</a>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty customerList}">
                            <tr>
                                <td>关联客户</td>
                                <td colspan="5">
                                    <c:forEach items="${customerList}" var="customer">
                                        <a href="/customer/${customer.id}">${customer.name}</a>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-list"></i>项目列表</h3>
                        </div>
                        <div class="box-body">
                            <h5>暂无项目</h5>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-default">
                        <div class="box-header with-border">
                            <h3 class="box-title"><i class="fa fa-calendar-check-o"></i>代办事项</h3>
                        </div>
                        <div class="box-body">
                            <h5>暂无事项</h5>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->
<div class="modal fade" id="remModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">推荐客户</h4>
            </div>
            <div class="modal-body">
                <form id="remForm" action="/customer/rem" method="post">
                    <input type="hidden" name="id" value="${customer.id}">
                    <div class="form-group" id="customerList">
                        <label>请选择要推荐给的员工</label>
                        <select class="form-control" name="userid">
                            <c:forEach items="${userList}" var="user">
                                <option value="${user.id}">${user.realname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script>
    $(function () {
       //公开客户
        $("#openCustomer").click(function () {
            if (confirm("确认公开客户么？")){
                var id = ${customer.id};
                window.location.href="/customer/open/"+id;
            }
        });

        //推荐客户
        $("#recommend").click(function () {
            $("#remModal").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
        });
        $("#saveBtn").click(function () {
            $("#remForm").submit();
        });

        $("#backBtn").click(function () {
            window.location.href="/customer";
        });
    });
</script>
</body>
</html>
