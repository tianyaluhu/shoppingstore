<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>商城产品上架下架界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <style type="text/css">
        .order_lists{
            width: 100%;
            height: 30px;
            list-style: none;
        }
        .order_lists li{
            float: left;
            height: 100%;
            width: 120px;
            color: white;
        }
    </style>
</head>
<body>
<h1 align="center">商城产品上架下架界面</h1>
<div id="myDiv"></div>
<script>
    $(function(){
        $.ajax({
            url:"takeoff",
            type:"post",
            success:function(data){
                var input;
                for (var i in data) {
                    var str1="<p>"+i+ "</p>";
                    $("#myDiv").append(str1);
                    for (var j=0;j<data[i].length;j++){
                        if (data[i][j].status==0){
                            input="<input type='button' value='下架' id='down'>";
                        }else{
                            input="<input type='button' value='上架' id='up'>";
                        }

                        var str2="<ul class='order_lists'>" +
                        "    <li>\n" +
                        "        <p >"+data[i][j].intro+"</p>" +
                        "    </li>\n" +
                        "    <li>\n"  +
                        "        <p >"+data[i][j].pName+"</p>" +
                        "    </li>\n" +
                        "    <li>\n"  +
                        "        <p >"+data[i][j].brand+"</p>" +
                        "    </li>\n" +
                        "    <li>\n"  +
                        "        <p >"+data[i][j].pNum+"</p>" +
                        "    </li>\n" +
                        "    <li>\n"  +
                        "        <p >"+data[i][j].price+"</p>" +
                        "    </li>\n" +
                        "    <li>\n"  +
                        "        <p >"+input+"</p>" +
                        "    </li>\n"+
                        "  </ul >";
                        $("#myDiv").append(str2);
                    };
                };
            }
        })

        $("#myDiv").on("click","#down",function () {
            $(this).attr("value","上架");
            $(this).attr("id","up");
            $.ajax({
                url:"down",
                type:"post",
                data:{
                    "pName":$(this).parent().parent().parent().children().eq(1).find("p").text()
                }
            });
        })

        $("#myDiv").on("click","#up",function () {
            $(this).attr("value","下架");
            $(this).attr("id","down");
            $.ajax({
                url:"up",
                type:"post",
                data:{
                    "pName":$(this).parent().parent().parent().children().eq(1).find("p").text()
                }
            });
        })

    })

</script>
</body>
</html>
