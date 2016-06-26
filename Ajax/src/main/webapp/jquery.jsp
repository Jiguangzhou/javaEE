<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>

    <link rel="stylesheet" href="/static/css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <button id="btn1">Get Json</button>
    <button id="btn2">Ajax Method</button>
    <img src="/static/img/ajax-loader.gif" id="loadImg" style="display: none">
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        $("#btn2").click(function () {
            var $this = $(this);
            $.ajax({
                url:"/user.json",//请求路径
                type:"get",//请求的方式
                data:{"name":"tom"},//发送到服务器的数据
                timeout:5000,//请求超时时间
                success: function (data) {//请求成功时的回调方法
                    console.log("success function");
                    alert(":)");
                },
                error: function () {
                    alert("请求服务器异常");
                },
                complete: function () {
                    console.log("complete function");
                    $this.text("ajax method").removeAttr("disabled");
                    $("#loadImg").hide();
                },
                beforeSend:function() { //在ajax发送之前执行的回调方法
                    console.log("before function");
                    $this.text("sending...").attr("disabled","disabled");
                    $("#loadImg").show();
                }
            });
        });
        
        $("#btn1").click(function () {
            //两种方法都是以Get方式发出的ajax异步请求
            //$.get服务器的返回值可以使任何的结果（字符串、xml、json）
            //$.getJson服务器的返回值必须是Json类型
            // get() post() getJSON() 返回 jqXHR对象中的
            // done()表示成功执行的回调函数
            // fail()表示错误执行的回调函数
            // always() 表示无论成功还是错误都会执行的回调函数
            $.getJSON("/user.json", function (data) {
                console.log(data);
            })
            .done(function(data){
                        console.log(data);
            })
            .fail(function () {
                alert("服务器请求异常");
            })
            .always(function () {
                console.log("always~~~~~~");
            })
        });
    });
</script>
</body>
</html>
