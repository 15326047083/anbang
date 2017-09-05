<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>ECharts</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<!-- 引入 echarts.js -->
<script src="js/echarts.min.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/datepiker_cn.js"></script>
<script type="text/javascript">  
        $( function() {

    	    var dateFormat = "yy-MM-dd",
    	      from = $( "#from" )
    	        .datepicker({
    	        	  changeMonth: true,
    	              changeYear: true,
    	              defaultDate: '2017-01-01',
    	              dateFormat:"yy-mm-dd"
    	        });
    	       
    	      to = $( "#to" ).datepicker({
    	    	  changeMonth: true,
    	          changeYear: true,
    	          defaultDate: '2017-12-31',
                  dateFormat:"yy-mm-dd"
    	      });
    	 
    	    function getDate( element ) {
    	      var date;
    	      try {
    	        date = $.datepicker.parseDate( dateFormat, element.value );
    	      } catch( error ) {
    	        date = null;
    	      }
    	 
    	      return date;
    	    }
    	    $( "#from" ).val("2017-01-01");
    	    $( "#to" ).val("2017-21-31");
    	    
    	    
    	  } );
        var xax=new Array();//横坐标信息
        var score1=new Array();//检查机构分数信息
        var score2=new Array();//企业分数信息
        function getInfoData(){
       	
       	$.getJSON("charts/getDoubleCheckDate.do?epid=${sessionScope.epId}&start=${param.start}&end=${param.end}",function(data){
       		$.each(data,function(i,item){
         			xax.push(item.substring(0,item.indexOf('日')+1));
       			
       		});
       		
       	});
         }
        
        function getEpData(){
     
        		$.getJSON("charts/getDoubleChartsVo.do?epid=${sessionScope.epId}&chartsType=ep&start=${param.start}&end=${param.end}",function(data){
               		$.each(data,function(i,item){
               			
               				var tempData = new Array();
               				
               				tempData.push(item.index.substring(0,item.index.indexOf('日')+1));
               				tempData.push(item.score);
                 			score1.push(tempData);
	
               		});	
               	});
        	
        }
        
        function getDeptData(){

       		$.getJSON("charts/getDoubleChartsVo.do?epid=${sessionScope.epId}&chartsType=dept&start=${param.start}&end=${param.end}",function(data){
              		$.each(data,function(i,item){
              	
              				var tempData = new Array();

              				tempData.push(item.index.substring(0,item.index.indexOf('日')+1));
              				tempData.push(item.score);
                			score2.push(tempData);
              				
              			
              		});
              		
              	});
       	
       }
        
        
    	$(function(){
    		 $.ajaxSettings.async = false;//必须保持同步，否则数据混乱无法渲染图片
    		getInfoData();
    		getEpData();
    		getDeptData();
    		var chart = echarts.init(document.getElementById('main')); 
    		
    		  option = {
    				    title: {
    				        text: '数据对比图',
    				        x: 'center'
    				    },
						color:['#87cefa','#87cefa','#87cefa','#87cefa'],
						 legend: {
							 x:'top',
							data:['监督检查数据', '自评自报数据']
							
						},
    				    tooltip: {
							
						},
    			    	xAxis: {
    						minInterval:1,
    				        data:xax
    				    },
    				   yAxis: {
						 splitArea : {//纵向分割色带
							show: true,//是否显示
							areaStyle:{//色带样式
									color:['#FFCC99','#FFCC99','#FFCC99','#FFFFCC','rgba(135,200,250,0.3)']//色带颜色，由下至上
							}
							}, 
							splitLine : {//纵向分割线
									show:false,//是否显示
               
							},
							min:0,//纵坐标最小值
							max:100,//纵坐标最大值,
							axisLabel:false
    				    },
    				    series: [{
    				        name: '监督检查数据',
    				        type: 'line',
    				        data: score2,
							showAllSymbol: true,//显示所有拐点标
							symbol:'emptyDiamond',
							symbolSize: 12,
							symbolColor:'#003366',
							itemStyle : {  //折线样式
                                normal : { 
									    color:'#003366',//拐点颜色
										width:5,
                                    lineStyle:{  //线条样式
                                       
										width:3,//宽度
										shadowColor : 'rgba(0,0,0,0.5)',//阴影颜色
										shadowBlur: 10,//阴影属性
										shadowOffsetX: 8,//阴影位置
										shadowOffsetY: 8
                                    }  
                                } ,
					
                            }, 
    				    },
						{
    				        name: '自评自报数据',
    				        type: 'line',
    				        data: score1,
							showAllSymbol: true,//显示所有拐点标
							symbol:'emptyDiamond',
							symbolSize: 12,
							color:'red',
							itemStyle : {  //折线样式
                                normal : { 
									    color:'red',//拐点颜色
										width:5,
                                    lineStyle:{  //线条样式
                                        color:'red',//颜色
										width:3,//宽度
										shadowColor : 'rgba(0,0,0,0.5)',//阴影颜色
										shadowBlur: 10,//阴影属性
										shadowOffsetX: 8,//阴影位置
										shadowOffsetY: 8
                                    }  
                                } ,
					
                            }, 
    				    }],
    				    dataZoom: [
    				        {   // 这个dataZoom组件，默认控制x轴。
    				            type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
    				            start: 0,      // 左边在 10% 的位置。
    				            end: 100         // 右边在 60% 的位置。
    				        },
    				        {   // 这个dataZoom组件，也控制x轴。
    				            type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
    				            start: 0,      // 左边在 10% 的位置。
    				            end: 100         // 右边在 60% 的位置。
    				        }
    				    ],
						animationDuration:3000,
    				   
    				};
    		 
    		  chart.setOption(option);  
    
    
    	  	 
    	});
     
    </script>
</head>
<body>
	<div class="rightinfo">
		<div id="datarange" style="width: 80%; margin: auto">

			<form method="get" action="menu/toDoubleVisual.do">
				<label for="from">开始日期</label> <input type="text" id="from"
					name="start" class="dfinput"> <label for="to">结束日期</label>
				<input type="text" id="to" name="end" class="dfinput"> <input
					type="hidden" id="type" name="type" value="dept" class="dfinput">
				<input type="hidden" name='epid' value="${sessionScope.epId}" /> <input
					type="submit" class="btn" value="查询" />

			</form>
		</div>
		<div id="main"
			style="min-width: 1000px; min-height: 600px; float: none"></div>
	</div>

</body>
</html>
