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
    <span><img src="images/leftico01.png" /></span>企业自查信息
    </div>
    	<ul class="menuson">

        
		<li class="active"><cite></cite><a href="menu/toSelfCheckVisual.do?start=2017-01-01&end=2017-12-31" target="rightFrame">企业数据</a><i></i></li>
        <li><cite></cite><a href="selfcheck/toCheckList.do?pager.offset=0" target="rightFrame">隐患整改</a><i></i></li>
  

        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>监督管理信息
    </div>
    	<ul class="menuson">
    	<li><cite></cite><a href="menu/toSafetyCheckVisual.do?start=2017-01-01&end=2017-12-31" target="rightFrame">评估信息</a><i></i></li>
    	<li><cite></cite><a href="menu/toDoubleVisual.do?start=2017-01-01&end=2017-12-31" target="rightFrame">信息对比</a><i></i></li>
        </ul>    
    </dd>
     <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>企业管理信息
    </div>
    	<ul class="menuson">
    	<li><cite></cite><a href="enterprise/toShow.do?epid=${sessionScope.epId}" target="rightFrame">基本信息</a><i></i></li>
		<li><cite></cite><a href="enterprise/getProductList.do?pager.offset=0&dangerid=${sessionScope.epId}" target="rightFrame">项目信息</a><i></i></li>
		<li><cite></cite><a href="enterprise/toExport.do?epid=${sessionScope.epId}" target="rightFrame">信息导出</a><i></i></li>
        </ul>    
    </dd>
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>在线操作
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="kaoshi.html" target="rightFrame">在线考核</a><i></i></li>
        <li><cite></cite><a href="help.html" target="rightFrame">在线服务</a><i></i></li>
       
        </ul>     
    </dd> 

       <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>选择报警
    </div>
	  <ul class="menuson">
      <li><cite></cite><a href="alert.html" target="rightFrame">选择报警</a><i></i></li>
	    </ul>  
    </dd> 
	
    <dd>
	</dd> 
    </dl>
    <div><i type="button" id="hide" class="fa fa-chevron-left" onclick='hide()'></i></div>
</body>
</html>
