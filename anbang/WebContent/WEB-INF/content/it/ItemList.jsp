
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$(".click").click(function() {
			window.location.href = 'item/toAdd.do?unitId=${requestScope.unitId}';
		});
		
		$("#returnA").click(function() {
			window.history.go(-1);
		});
	});
</script>

</head>


<body>
 
	

	<div class="rightinfo">

		<div class="tools">
			<ul class="toolbar">
				
				<li class="click"><span><img src="images/t01.png"  /></span>添加</li>
				<li class="click" id="returnA"><span></span>返回</li>
			</ul>



		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>编号</th>
					<th>分数</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach items="${infos}" var="info">
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>${info.itemNum}</td>
					<td>${info.itemScore}</td>
					<td>
					<c:choose>
						<c:when test="${info.expire==0}">
							可用
						</c:when>
						<c:otherwise>
							过期
						</c:otherwise>
					</c:choose>
					</td>
					<td><a href="item/toChangeItem.do?itemId=${info.id}">查看</a></td>
					<%-- <td><a href="check/toCheckDetail.do?infoId=${info.id}">查看</a></td> --%>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>