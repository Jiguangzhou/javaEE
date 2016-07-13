<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>DreamCRM-文件管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/fontawesome/css/font-awesome.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="doc"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">文件列表</h3>
                    <div class="box-tools">
                        <div id="uploadBtn"><span class="text"><i class="fa fa-upload"></i> 上传文件</span></div>
                        <button class="btn btn-bitbucket btn-xs" id="newDir"><i class="fa fa-folder"></i> 新建文件夹</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>名称</th>
                            <th>大小</th>
                            <th>上传者</th>
                            <th>上传时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty docList}">
                            <tr>
                                <td colspan="5">暂时没有任何数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${docList}" var="doc">
                            <tr>
                                <c:choose>
                                    <c:when test="${doc.type == 'dir'}">
                                        <td><i class="fa fa-folder-o"></i></td>
                                        <td><a href="/doc?fid=${doc.id}">${doc.name}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><i class="fa fa-doc-o"></i></td>
                                        <td><a href="/doc/download/${doc.id}">${doc.name}</a></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${doc.size}</td>
                                <td>${doc.createuser}</td>
                                <td><fmt:formatDate value="${doc.createtime}" pattern="y-M-d H:m"/></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<!-- ./wrapper -->
<div class="modal fade" id="dirModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新建文件夹</h4>
            </div>
            <div class="modal-body">
                <form id="newDirForm" action="/doc/newDir" method="post">
                    <input type="hidden" name="fid" value="${fid}">
                    <div class="form-group">
                        <label>文件夹名称</label>
                        <input type="text" class="form-control" name="name" id="dirname">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveDirBtn">保存</button>
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
<script src="/static/plugins/webuploader/webuploader.min.js"></script>
<script>
    $(function () {

        //上传文件
        var upload = WebUploader.create({
            swf: "/static/plugins/webuploader/Uploader.swf",
            pick: "#uploadBtn",
            server: "/doc/upload",
            fileValL: "file",
            formData: {"fid": "${fid}"},
            auto: true
        });

        //新建文件夹
        $("#newDir").click(function () {
            $("#dirModal").modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        });
        $("#saveDirBtn").click(function () {
            if (!$("#dirname").val()) {
                $("#dirname").focus();
                return;
            }
            $("#newDirForm").submit();
        });

        //文件上传结果
        upload.on("startUpload", function () {
            $("#uploadBtn .text").html('<i class="fa fa-spinner fa-spin"></i>上传中...');
        });
        upload.on('uploadSuccess', function (file,data) {
            if (data._raw == "success"){
                window.history.go(0);
            }
        });
        upload.on('uploadError', function (file) {
            alert("上传文件失败");
        });
        upload.on('uploadComplete', function (file) {
            $("#uploadBtn .text").html('<i class="fa fa-upload"></i>上传文件').removeAttr("disabled");;
        });

    });
</script>
</body>
</html>
