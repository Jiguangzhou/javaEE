<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body>

<button id="btn">Get JSON</button>
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

        document.querySelector("#btn").onclick = function () {
            var xmlHttp = createXmlHttp();
            xmlHttp.open("get", "/user.json", true);
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {

                        var result = xmlHttp.responseText;
                        var json = JSON.parse(result);
                        for (var i = 0; i < json.length; i++) {
                            var user = json[i];
                            var id = user.id;
                            var name = user.name;
                            var score  = user.score;

                            var li = document.createElement("li");
                            var text = document.createTextNode(id+"->"+name);
                            li.appendChild(text);
                            document.getElementById("list").appendChild("li");


                        }

                    }
                }
            };
            xmlHttp.send();
        };


        /*var array = [];
         var obj = {
         "name":"Tom",
         "address":"USA"
         };
         alert(obj.name);
         alert(obj["name"]);*/
    })();
</script>
</body>
</html>
