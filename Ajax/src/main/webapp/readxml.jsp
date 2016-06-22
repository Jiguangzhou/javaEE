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
    <div class="page-header">
        <h4>Ajax读取XML</h4>
    </div>
    <button id="btn">Read</button>
    <div id="userMsg">

    </div>
    <%--<div >
        <h4>Tom<small>100</small>></h4>
        <p>USA</p>
    </div>--%>
</div>

<script>
    (function () {
        function createXmlHttp() {
            var xmlHttp = null;
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        function createUserDiv(id, name, address,tel) {
            var div = document.createElement("div");
            var h4 = document.createElement("h4");
            var small = document.createElement("small");
            var p = document.createElement("p");

            var idText = document.createTextNode(id);
            var nameText = document.createTextNode(name);
            var addressText = document.createTextNode(address);
            var telText = document.createTextNode(tel);

            p.appendChild(addressText);
            p.appendChild(telText);
            small.appendChild(idText);
            h4.appendChild(nameText);
            h4.appendChild(small);
            div.appendChild(h4);
            div.appendChild(p);
            document.getElementById("userMsg").appendChild(div);

        }

        document.getElementById("btn").onclick = function () {
            var xmlHttp = createXmlHttp();
            xmlHttp.open("get", "/user.xml", true);
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    var status = xmlHttp.status;
                    if (status == 200) {
                        //清除userMsg中的内容
                        document.getElementById("userMsg").innerHTML = "";
                        //获取返回的xml值
                        var xmlDoc = xmlHttp.responseXML;

                        var userArray = xmlDoc.getElementsByTagName("user");

                        for (var i = 0; i < userArray.length; i++) {
                            var user = userArray[i];
                            var id = user.getAttribute("id");
                            var name = user.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                            var address = user.getElementsByTagName("address")[0].childNodes[0].nodeValue;
                            var tel = user.getElementsByTagName("tel")[0].childNodes[0].nodeValue;
                            console.log("ID:" + id + "Name:" + name + "Address:" + address+"Tel:"+tel);

                            createUserDiv(id, name, address,tel);
                        }

                    } else {
                        alert("请求服务器异常:" + status);
                    }
                }
            };
            xmlHttp.send();
        };

    })();

</script>
</body>
</html>
