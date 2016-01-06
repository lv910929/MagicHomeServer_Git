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
		    $.ajax({
		        type: "post",
            	dataType: "json",
		        url:'${ctx}/MagicHomeServer/count_device',//请求数据的地址
		        success:function(data){
		        	
		            console.log(data);
		            chart.series[0].setData(data.onlineList);//数据填充到highcharts上面
		            chart.series[1].setData(data.offlineList);//数据填充到highcharts上面
		        },
		        error:function(e){
		        } 
		    });
		    var chart = new Highcharts.Chart({
		        chart:{
		            renderTo:'container',
		            type:'column' //显示类型 柱形
		        },
		        title: {
		            text: '中控数量统计'
		        },
		        xAxis: {
		            categories: [
		            	'星期日',
		                '星期一',
		                '星期二',
		                '星期三',
		                '星期四',
		                '星期五',
		                '星期六'
		            ],
		            crosshair: true
		        },
		        yAxis: {
		            min: 0,
		            title: {
		                text: '中控数量(个)'
		            }
		        },
		        tooltip: {
		            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                '<td style="padding:0"><b>{point.y:.0f}个</b></td></tr>',
		            footerFormat: '</table>',
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
		            column: {
		                pointPadding: 0.2,
		                borderWidth: 0
		            }
		        },
		        series: [{
		            name: '在线中控数',
		
		        }, {
		            name: '离线中控数',
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

