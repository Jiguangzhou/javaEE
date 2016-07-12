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
    <title>DreamCRM-发布公告</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/plugins/simditor/styles/simditor.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">新建公告</h3>
                </div>
                <div class="box-body">
                    <form id="newForm" method="post">
                        <div class="form-group">
                            <label>标题</label>
                            <input type="text" name="title" class="form-control" id="title">
                        </div>
                        <div class="form-group">
                            <label>公告内容</label>
                            <textarea name="context" id="context" class="form-control" rows="10"></textarea>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button id="saveBtn" class="btn btn-primary pull-right">提交</button>
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
<%--simditor--%>
<script src="/static/plugins/simditor/scripts/module.js"></script>
<script src="/static/plugins/simditor/scripts/hotkeys.js"></script>
<script src="/static/plugins/simditor/scripts/uploader.js"></script>
<script src="/static/plugins/simditor/scripts/simditor.min.js"></script>
<script>
    $(function () {
        var notice = new Simditor({
            textarea: $("#context"),
            placeholder: '请输入公告内容',
            upload: {
            url: '/notice/img/upload',
            fileKey: 'file'
        }
        });

        $("#saveBtn").click(function () {
            if (!$("#title").val()){
                $("#title").focus();
                return;
            }
            if (!$("#context").val()){
                $("#context").focus();
                return;
            }
            $("#newForm").submit();
        });
    });
</script>
</body>
</html>
