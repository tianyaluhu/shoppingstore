<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-gl/dist/echarts-gl.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts-stat/dist/ecStat.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/dataTool.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/china.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/map/js/world.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>

</head>
<body style="height: 100%; margin: 0">
<h1 align="center">网站每月访问量数据表</h1>
<div id="container" style="height: 100%"></div>

<script type="text/javascript">
    var datax=[];
    var datay=[];
    $(function(){


        $.ajax({
            url:"getWebData",
            type:"post",
            success:function(data){
                for(var i=0;i<data.length;i++){
                    datax.push(data[i].visittime);
                    datay.push(data[i].visittimes);
                }

                var dom = document.getElementById("container");
                var myChart = echarts.init(dom);
                var app = {};
                option = null;
                option = {

                    xAxis : [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: datax,
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#ffffff'
                                }
                            }
                        }
                    ],

                    yAxis : [
                        {
                            type : 'value',
                            name : '',
                            axisLabel : {
                                textStyle: {
                                    color: '#ffffff'
                                }
                            }
                        }
                    ],
                    legend: {
                        y: '55%',
                        textStyle: {
                            fontSize: 18,//字体大小
                            color: '#ffffff'//字体颜色
                        },
                        data: []
                    },
                    series: [{
                        data: datay,
                        type: 'line',
                        areaStyle: {},
                    }]
                };

                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }

            }
        });




    });


</script>

</body>
</html>
