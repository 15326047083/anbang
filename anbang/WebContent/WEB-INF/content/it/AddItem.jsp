<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
</script>
</head>
<body>
<br>
<ul class = "forminfo">
<form:form action="item/add.do" method="post" commandName="it">
<li>
<form:input path="unitId" name="" type="hidden" class="dfinput" value="${requestScope.unitId}"/></li>

		<li><label>编号<b>*</b></label>
		<form:input path="itemNum" name="" type="text" class="dfinput" value=" "  style="width:518px;"/></li>
			<li><label>检查内容<b>*</b></label>
		<form:input path="itemContent" name="" type="text" class="dfinput" value=" "  style="width:518px;"/></li>
			<li><label>检查依据<b>*</b></label>
		<form:input path="itemLaw" name="" type="text" class="dfinput" value=" "  style="width:518px;"/></li>
			<li><label>分数<b>*</b></label>
		<form:input path="itemScore" name="" type="text" class="dfinput" value=" "  style="width:518px;"/></li>
		<li><label>是否否决<b>*</b></label>
	    <input type="radio" name="toNone" value="1"/>是
	    <input type="radio" name="toNone" value="0"/>否
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/></li>
 </form:form>
		</ul>
</body>
</html>
