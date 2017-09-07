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


<script type="text/javascript">
	KE.show({
		id : 'content7',
		cssPath : './index.css'
	});
</script>


</head>
<body>
	<br>
	<ul class="forminfo">
		<form:form action="enterprise/addDangerInfo.do" method="post"
			commandName="di">
			<form:hidden path="epId" value="${requestScope.epid}"/>
			<li><label>重点监管技术<b>*</b></label> 
			<form:input path="tech"
					cssClass="dfinput"/></li>
			<li><label>重大危险源<b>*</b></label> 
			<form:input path="source"
					cssClass="dfinput"/></li>

			<li><label>&nbsp;</label>
			<input type="submit"
				class="btn" value="提交" /></li>
		</form:form>
	</ul>
</body>
</html>