
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>退货界面</title>
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
<h1 align="center">退货界面</h1>
<div id="myDiv">
    <ul class="order_lists">
        <li >客户</li>
        <li >商品名称</li>
        <li >数量</li>
        <li >金额</li>
        <li >发货状态</li>
    </ul>
</div>
<script>
    $(function(){
        $.ajax({
            url:"backDeliverOrders",
            type:"post",
            success:function(data){
                for (var i=0;i<data.length;i++){
                    if (data[i].status==1){
                        var str="<ul class='order_lists'>" +
                            "    <li>\n" +
                            "        <p >"+data[i].username+"</p>" +
                            "    </li>\n" +
                            "    <li>\n"  +
                            "        <p >"+data[i].pName+"</p>" +
                            "    </li>\n" +
                            "    <li>\n"  +
                            "        <p >"+data[i].odNum+"</p>" +
                            "    </li>\n" +
                            "    <li>\n"  +
                            "        <p >"+data[i].pPrice+"</p>" +
                            "    </li>\n" +
                            "    <li>\n"  +
                            "        <p >退货</p>" +
                            "    </li>\n" +
                            "  </ul >";
                        $("#myDiv").append(str);
                    }
                }

            }
        })
    })
</script>
</body>
</html>
