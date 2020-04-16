
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>商品展示</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="<%=basePath%>resource/js/modernizr.custom.js"></script>
    <link href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>resource/css/demo.css" type="text/css" media="all">
    <link rel="stylesheet" href="<%=basePath%>resource/css/component.css" type="text/css" media="all">

    <style>
        #tips{
            width: 150px;
            height: 150px;
            font-size: 30px;
            position: fixed;
            left: 45%;
            bottom: 50%;
            display: none;
        }
    </style>
</head>
<body>

<!-- Compare basket -->
<div class="compare-basket">
    <button class="action action--button action--compare"><i class="fa fa-check"></i><span class="action__text">Compare</span></button>
</div>
<!-- Main view -->
<div class="view">
    <!-- Product grid -->
    <section class="grid">
        <!-- Products -->


    </section>
</div><!-- /view -->
<!-- product compare wrapper -->
<section class="compare">
    <button class="action action--close"><i class="fa fa-remove"></i><span class="action__text action__text--invisible">Close comparison overlay</span></button>
</section>
<div id="tips"></div>
<script>

    $(function(){
        $.ajax({
            url: "getProductByPtype",
            type: "post",
            data: {
                "pType": getQueryString("pType")
            },
            success: function (data) {
                $(".grid").html("");
                for (var i = 0; i < data.length; i++) {
                    if(data[i].status==1) {
                        var str = "<div class='product'>\n" +
                            "            <div class='product__info'>\n" +
                            "                <img class='product__image' src='<%=basePath%>"+data[i].pic+"' alt='Product 1' />\n" +
                            "                <h3 class='product__title'>" + data[i].pName + "</h3>\n" +
                            "                <span class='product__region extra highlight'>" + data[i].intro + "</span>\n" +
                            "                <span class='product__price highlight'>" + data[i].price + "</span>\n" +
                            "                <span class='product__price highlight'>" + data[i].brand + "</span>\n" +
                            "                <button class='action action--button action--buy' pid='" + data[i].pId + "'><i class='fa fa-shopping-cart'></i><span class='action__text'>加入购物车</span></button>\n" +
                            "            </div>\n" +
                            "            <label class='action action--compare-add'><input class='check-hidden' type='checkbox' /><i class='fa fa-plus'></i><i class='fa fa-check'></i><span class='action__text action__text--invisible'>添加商品</span></label>\n" +
                            "        </div>"

                        $(".grid").append(str);
                    }
                }

                    loadjscssfile("<%=basePath%>resource/js/classie.js", "js");
                    loadjscssfile("<%=basePath%>resource/js/main.js", "js");

                }
        });


        $(".grid").on("click",".action--buy",function(){
        checkLogin();
        $.ajax({
            url:"addCar",
            type:"post",
            data:{
                "username":getQueryString("username"),
                "pid":$(this).attr("pid")
            },
            success:function(data){

                if(data=="yes") {
                    $("#tips").show();
                    $("#tips").html("加入成功");
                    //定时器
                    window.setTimeout(showTips,2000);
                }
            }


        })
        });

        function showTips(){
            $("#tips").hide();
        }

        /*检查是否登录*/
        function checkLogin(){
         if(getQueryString("username")=="null"){
             location.href="<%=basePath%>/resource/login/login.jsp";
         }
        }








        function loadjscssfile(filename, filetype) {
            if (filetype == "js") { //判定文件类型
                var fileref = document.createElement('script');//创建标签
                fileref.setAttribute("type", "text/javascript");//定义属性type的值为text/javascript
                fileref.setAttribute("src", filename);//文件的地址
            }
            else if (filetype == "css") { //判定文件类型
                var fileref = document.createElement("link");
                fileref.setAttribute("rel", "stylesheet");
                fileref.setAttribute("type", "text/css");
                fileref.setAttribute("href", filename);
            }
            if (typeof fileref != "undefined")
                document.getElementsByTagName("head")[0].appendChild(fileref);
        }

            function getQueryString(name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return decodeURI(r[2]);
            return null;
        }
    });


</script>
</body>
</html>
