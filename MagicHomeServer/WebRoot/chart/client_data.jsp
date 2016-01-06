<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>智能家居管理系统</title>
    
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">

	<c:set var="ctx" value="<%=request.getContextPath() %>"/>
	<link rel="stylesheet" href="../comm/css/ace-rtl.min.css" type="text/css" />
	<link rel="stylesheet" href="../comm/css/ace-skins.min.css" type="text/css" />
	<link rel="stylesheet" href="../comm/css/ace.min.css" type="text/css" />
	<link rel="stylesheet" href="../comm/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" href="../comm/css/font-awesome.min.css" type="text/css" />
	<script type="text/javascript" src="../comm/js/jquery-1.8.0.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
		    var x = [];//X轴
		    var y = [];//Y轴
		    var xtext = [];//X轴TEXT
		    $.ajax({
		        type: "post",
            	dataType: "json",
		        url:'${ctx}/MagicHomeServer/count_city',//请求数据的地址
		        success:function(data){
		        	
		            for(var key in data.list){
		                data.list[key].y = data.list[key].num; //给Y轴赋值
		                xtext.push(data.list[key].cityName);//给X轴TEXT赋值
		            };
		            console.log(data);
		            chart.series[0].setData(data.list);//数据填充到highcharts上面
		        },
		        error:function(e){
		        } 
		    });
		    var chart = new Highcharts.Chart({
		        chart:{
		            renderTo:'container',
		            type:'column', //显示类型 柱形
		            margin: 60,
		            options3d: {
		                enabled: true,
		                alpha: 10,
		                beta: 25,
		                depth: 70
		            }
		        },
		        title:{
		            text:'中控IP归属地分布图' //图表的标题
		        },
		         plotOptions: {
		            column: {
		                depth: 25
		            }
		        },
		        xAxis:{
		            categories:xtext
		        },
		        yAxis:{
		            title:{
		                text:'统计' //Y轴的名称
		            },
		            opposite: true
		        },
		        series:[{
		            name:"数量"
		        }]
		    });
		});
	</script>
</head>

<body>
	<script type="text/javascript" src="../assets/js/exporting.js"></script>
	<script type="text/javascript" src="../assets/js/highcharts.js"></script>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>
