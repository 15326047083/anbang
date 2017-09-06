
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/lib/pager-taglib-2.0.jar" prefix="pg" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/page.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript">
 	
	$(document).ready(function() {
		 var scores = $( ".scores" ).spinner();
		 var kis = $( ".kis" ).spinner({
		      step: 0.01});
	});
	
</script>

</head>


<body>
 
 <%! double total=0;%>

	<div class="rightinfo">

	
		<form action="unit/updateKiAndScore.do">
		
		<table class="tablelist">
			<thead>
				<tr>
					
					<th>单元编号</th>
					<th>单元名称</th>
					<th>分数</th>
					<th>权重</th>
	
				</tr>
			</thead>	
			<tbody>
				<c:set var="total" value="0.0"></c:set>
				<c:forEach items="${infos.datas}" var="info">
				<c:set var="total" value="${info.score*info.ki+total}"></c:set>
				<tr>
					<input type="hidden" name="ids" value="${info.id}"/>
					<td>${info.unitNum}</td>
					<td>${info.unitName}</td>
					<td><input class="scores" type="text" name="scores"  value="${info.score}" readonly="readonly"></td>
					<td><input class="kis" type="text" name="kis"  value="${info.ki}"readonly="readonly"></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<table class="tablelist">
		<thead>
		<tr>
			<th>总分</th>
				<td>${total}</td>
			<td><input name="" type="submit" class="btn" value="修改"/></td>
		</tr>
	
		</thead>
	
		</table>
		</form>
	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>