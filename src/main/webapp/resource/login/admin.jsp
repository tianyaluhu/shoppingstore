
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="javax.swing.*" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理者界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>resource/css/login.css" type="text/css" media="all">
</head>
<body>
<h1>管理者界面</h1>
<div class="container w3layouts agileits">

    <div class="login w3layouts agileits">

        <input type="text"       id="username"      placeholder="用户名"   >
        <input type="password"   id="password"     placeholder="密码"     >


        <div class="send-button w3layouts agileits">
            <input type="button" value="提交" id="btn">
            <div id="msg" style="color: white"></div>
        </div>
    </div>

    <div class="clear"></div>
</div>

<div class="footer w3layouts agileits">
    <p>Copyright &copy; More Templates</p>
</div>

<input type="hidden" value="<%=basePath%>" id="hidd">
<script type="text/javascript">
    $(function(){
        $("#btn").click(function(){
            if($("#username").val()!=""&&$("#password").val()!=""){
                $.ajax({
                    url:"admin",
                    type:"post",
                    data:{
                        "username":$("#username").val(),
                        "password":$("#password").val()
                    },
                    success:function(data){
                        if (data=="empty") {
                            $("#msg").html("用户名错误或用户不存在");
                        }else if(data=="yes"){
                            location.href="<%=basePath%>resource/admin/adminBack.jsp";
                        }else if(data=="no"){
                            $("#msg").html("登录密码错误失败");
                        }
                    }
                });


            }else{
                $("#msg").html("登录信息填写不完整");
            }
        });
    })
</script>
</body>
</html>
