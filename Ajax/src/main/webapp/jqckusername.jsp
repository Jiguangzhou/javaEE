<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>
<input type="text" id="username">
<span id="help_text"></span>
<hr>
<button id="btn">sned Post Ajax</button>
<hr>
<button id="jsonbtn">get Json Data</button>
<ul id="jsonList">get XML Data</ul>
<hr>
<button id="xmlbtn">get XML data</button>
<div id="userBox">

</div>
<script src="/static/js/jquery-1.11.1.js"></script>
<script>
    $(function () {
        $("#xmlbtn").click(function () {
            var $username = $("#userBox");
            $userBox.html("");
            $.get("/user.xml", function (xml) {
                $(xml).find("user").each(function () {
                    var id = $(this).attr("id");
                    var name = $(this).find("username").text();
                    var address = $(this).find("address").text();
                    console.log("id:" + id + "name:" + name + "address:" + address);
                    $userBox.append("<div><h4>" + name + "<small>" + id + "<small></h4><p>" + address + "</p></div>")
                });
            });
        });

        $("#jsonbtn").click(function () {
            var $ul = $("#jsonList");
            $.get("/user.json", function (data) {
                for (var i = 0; i < data.length; i++) {
                    var user = data[i];
                    $ul.append("<li>" + user.name + "</li>");
                }
            });
        });

        $("btn").click(function () {
            $.post("/ajax", {"name": "jQuery", "address": "Ajax"}, function (result) {
                console.log(result);
            });
        });

        $("#username").change(function () {
            var name = $(this).val();
            $.get("/checkusername", {"username": name}, function (result) {
                if (result == "yes") {
                    $("#help_text").text("√");
                } else {
                    $("#help_text").text("帐号已被占用")
                }
            })
        })
    });
</script>
</body>
</html>
