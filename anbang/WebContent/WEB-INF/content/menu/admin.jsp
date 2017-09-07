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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/font-awesome.min.css"/>
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});	

</script>
<script type="text/javascript">
var visible=true;
function hide(){
		
		if(visible)
		{
		parent.document.getElementById('frame1').cols="12,*";
		
		$("#hide").css('background','orange');
		visible=false;
		}else{
		parent.document.getElementById('frame1').cols="187,*";
		$("#hide").css('background','');
		visible=true;
		}
		
	}
</script>
</head>

<body style="background:#f0f9fd;">

    
    <dl class="leftmenu">
     <dd>
         <div class="title">
    <span><img src="images/leftico01.png" /></span>评分信息管理
    </div>
    	<ul class="menuson">
    	<!-- <li><cite></cite><a href="menu/toSafetyCheckVisual.do" target="rightFrame">评估信息</a><i></i></li>
		 -->
		<li class="active"><cite></cite><a href="menu/toUnitEps.do?pager.offset=0" target="rightFrame">条目管理</a><i></i></li>
		
        </ul>    
    </dd>
    
        
		 <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>企业信息管理
    </div>
    	<ul class="menuson">

		<li class="active"><cite></cite><a href="menu/toEnterpriseList.do?pager.offset=0" target="rightFrame">企业管理</a><i></i></li>
        </ul>    
    </dd>
    
    
   
   
    
    </dl>

   
</body>
</html>

