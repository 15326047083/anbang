<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
    int[] values = new int[] {0,1};
    pageContext.setAttribute("values", values);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
$(document).ready(function() {
	$(".btn").click(function() {
		if(this.value=="返回")
			window.history.go(-1);		
	});
});
</script>
</head>
<body>




<br>
<ul class = "forminfo">
<form:form action="item/update.do" method="post" commandName="it">

 <form:input path="unitId" name="" type="hidden" cssClass="dfinput" style="width:518px;"/>
 <form:input path="id" name="" type="hidden" cssClass="dfinput" style="width:518px;"/>
        <li><label>编号<b>*</b></label>
		<form:input path="itemNum"  cssClass="dfinput" style="width:518px;"/></li>
			<li><label>检查内容<b>*</b></label>
		<form:input path="itemContent"  cssClass="dfinput" style="width:518px;"/></li>
		<li><label>检查类型<b>*</b></label>
		<form:input path="itemType"  cssClass="dfinput" style="width:518px;"/></li>
			<li><label>检查依据<b>*</b></label>
		<form:input path="itemLaw"  cssClass="dfinput" style="width:518px;"/></li>
			<li><label>分数<b>*</b></label>
		<form:input path="itemScore"  cssClass="dfinput" style="width:518px;"/></li>
		<li><label>是否否决<b>*</b></label>
		<cite>
	   <form:radiobutton path="toNone" value="0"/> 否
	    <form:radiobutton path="toNone" value="1"/> 是
	    </cite>
    <li>
    <li><label>状态<b>*</b></label><cite>
	   <form:radiobutton path="expire" value="0"/> 可用
	    <form:radiobutton path="expire" value="1"/>过期
	    </cite>
	    
    <li>
    <label>&nbsp;</label><input name="" type="submit" class="btn" value="修改"/>
    <label>&nbsp;</label><input name="" type="button" class="btn" value="返回"></li>
 </form:form>
		</ul>
</body>
</html>