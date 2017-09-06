<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<ul class="forminfo">
			<form:form action="enterprise/addProduct.do" method="post"
			commandName="p">
			<form:hidden path="dInfoId" value="${requestScope.dangerId}"/>
			<li><label>项目名称<b>*</b></label> <form:input path="pName" name=""
					type="text"  cssClass="dfinput" style="width:518px;" /></li>
			<li><label>生产能力<b>*</b></label> <form:textarea path="pCapacity"
					cssClass="dfinput" style="width:518px; height:80px;" /></li>
			<li><label>存储能力<b>*</b></label> <form:textarea path="loadCapacity"
					cssClass="dfinput" style="width:518px; height:80px;" /></li>
			<li><label>涉危化学品<b>*</b></label> <form:input path="pType" cssClass="dfinput" style="width:518px;" /></li>
			<li><label>重点监管品<b>*</b></label><form:input path="pSur" cssClass="dfinput" style="width:518px;" /></li>

			<li><label>&nbsp;</label><input name="" type="submit"
				class="btn" value="保存" />
				<input name="" type="button"
				class="btn" value="返回" /></li>
		</form:form>
	</ul>
</body>
</html>