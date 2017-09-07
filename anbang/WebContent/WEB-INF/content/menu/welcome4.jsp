<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
    <meta charset="utf-8">  
    <title>ECharts</title>  
    <!-- 引入 echarts.js -->  

    <script src="js/jquery.js"></script>  
		<script type="text/javascript" src="js/jquery.corner.js"></script>

    <style type="text/css">
    h1 {
    color: #292929;
    font-family: "Trebuchet MS",Lucida,Verdana,sans-serif;
    font-weight: normal;
    text-align: center;
}

    a {
    color: #292929;
    font-family: "Trebuchet MS",Lucida,Verdana,sans-serif;
    font-weight: normal;
    text-align: center;
	font-size:20px
}
	body{ 
		
  height:100%;
  width:100%;
  overflow: hidden;
  background-size:cover;
}
.tab_box{ width:595px; margin:20px auto;}
.lyz_tab_left{ background:url(images/left3.png) no-repeat top right; float: left; width: 159px; height: auto !important; min-height:500px; height:500px;}
.pro_con1111{ float: left; overflow: hidden; width: 149px; height: auto;}
.pro_con111 UL{ padding: 0px; width: 159px; list-style-type: none;}
.pro_con111 UL LI{ padding-right: 5px; display: inline; font-weight: bold; font-size: 14px; background: url(images/hongbj.png) no-repeat left center;	float: right; margin-bottom: 5px; width: 119px; cursor: pointer; color: #fff; line-height: 34px; height: 34px; text-align: center;}
.pro_con111 UL LI.hover{display: inline; font-weight: bold; font-size: 14px; background: url(images/baibj.jpg) no-repeat right 50%;	float: right; margin-bottom: 5px; width: 129px; cursor: pointer; color: #3c3c3c; line-height: 34px; height: 34px; text-align: center;}
.lyz_tab_right{	padding-right: 10px; display: inline; padding-left: 20px; background: url(images/right4.png) no-repeat left top; float: left; padding-bottom: 20px; width: 375px; padding-top: 20px; height:500px;}
.clear{ clear:both}
    </style>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		$.ajaxSettings.async = false;
		$.getJSON("enterprise/getEpByDeptid.do?deptId=8a80bd945bb398f9015bb398fcb80001",function(data){
			$.each(data,function(i,item){
				
				switch(item.areaId){
				case "402880e75bb72498015bb7249e3b0001":
					
					$("#con_one_1").append("<a href='menu/toDeptCheckMain.do?epId="+item.id+"'>"+item.ename+"</a>   <br>");
					break;
				case "402880e75bb726b3015bb726b88f0001":
					
					$("#con_one_2").append("<a href='menu/toDeptCheckMain.do?epId="+item.id+"'>"+item.ename+"</a>   <br>");
					break;
				
				}
				
			});
			
		});
		
		
		
	
	})

</script>
</head>  
<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">  
	 <div class="logintop">    
	<span>欢迎</span>  
    <ul>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    </ul>    
    </div>
	 <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
	<br>
	<br>
	<br>
	<div width="100%" style="text-align:center;margin-bottom:40px;">
		<h1 style="color:white;font-size:30px">准格尔旗工业园区分布图</h1> 
	</div>
	<table style="margin:auto;">
		<tr>
			<td>
   
		<div class="tab_box">
<div class="lyz_tab_left">
<div class="pro_con111">
<ul>
<li class="hover" id="one1" onclick="setTab('one',1,5)">大路工业园区</li>

<li id="one2" onclick="setTab('one',2,5)">其他开发区</li>
</ul>
 </div>
<script language="javascript"> 
<!--
function setTab(name,cursel,n){
for(i=1;i<=n;i++){
var menu=document.getElementById(name+i);
var con=document.getElementById("con_"+name+"_"+i);
menu.className=i==cursel?"hover":"";
con.style.display=i==cursel?"block":"none";
}
}
//-->
</script>
</div>
<div class="lyz_tab_right">
<div class="hover" id="con_one_1">
	
	</div>

<div class="hover" id="con_one_2" style="display: none">
	
		
</div>

<div class="clear"></div>
	
	</td>
	<td style="vertical-align:top">
	<img src="images/indexPage02Back.png"/>
		
	</td>
	</tr>
	</table>

	
   
   
</body>  
</html>  