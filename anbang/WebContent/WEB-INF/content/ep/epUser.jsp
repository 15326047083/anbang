<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
$(document).ready(function() {
	$(".btn").click(function() {
		if(this.value=="返回")
			window.location.href = 'enterprise/toList.do?pager.offset=0';		
	});
});
</script>


</head>
<body>
	<br>
	<ul class="forminfo">
		<form:form action="user/save.do" method="post"
			commandName="user">
			<c:if test="${not empty requestScope.user.id}">
				<form:hidden path="id"/>
			</c:if>
		
			<form:hidden path="epId" value="${param.epId}"/>
			<form:hidden path="userType" value="ep"/>
			
		
			<li><label>用户名<b>*</b></label> 
			<form:input path="username"
					cssClass="dfinput"/></li>
			<li><label>密码<b>*</b></label> 
			<form:password path="password"
					cssClass="dfinput"/></li>

			<li><label>&nbsp;</label>
			<input type="submit"
				class="btn" value="提交" />
					<input name="" type="button"
				class="btn" value="返回" /></li>
		</form:form>
	</ul>
</body>
</html>