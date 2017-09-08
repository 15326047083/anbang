
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
		$("#new").click(function() {
			window.location.href = 'unit/toAdd.do?epId=${requestScope.epId}';
		});
		$("#returnA").click(function() {
			window.history.go(-1);
		});
		
		$("#export").click(function(){
			window.location.href = 'unit/exportUnit.do?epId=${requestScope.epId}';
		});
		$("#backups").click(function(){
			window.location.href = 'unit/backupsUnit.do?epId=${requestScope.epId}';
		});
	});
	
</script>

</head>


<body>
 

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click" id="new"><span><img src="images/t01.png"  /></span>添加</li>
				<li class="click" id="returnA"><span></span>返回</li>
				<li class="click" id="export"><span></span>导出</li>
				<li class="click" id="backups"><span></span>备份</li>
			</ul>



		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>单元编号</th>
					<th>单元名称</th>
					<th>分数</th>
					<th>权重</th>
			
					<th>操作</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach items="${infos.datas}" var="info">
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>${info.unitNum}</td>
					<td>${info.unitName}</td>
					<td>${info.score}</td>
					<td>${info.ki}</td>
					
					
					<td><a href="unit/toChangeUnit.do?unitId=${info.id}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="menu/toItemList.do?unitId=${info.id}">查看</a></td>
					<%-- <td><a href="check/toCheckDetail.do?infoId=${info.id}">查看</a></td> --%>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><div class="pages">
	
		<pg:pager url="unit/toList.do"
					items="${infos.total}" export="currentPageNumber=pageNumber"
					maxPageItems="15" maxIndexPages="5">
					<pg:first>
						<a href="${pageUrl}" class="page">首页</a>
					</pg:first>
					<pg:prev>
						<a href="${pageUrl}"  class="page">上一页</a>
					</pg:prev>
					<pg:pages>
						<c:choose>
							<c:when test="${currentPageNumber eq pageNumber}">
								${pageNumber }
							</c:when>
							<c:otherwise>
								<a href="${pageUrl }">${pageNumber}</a>
							</c:otherwise>
						</c:choose>
					</pg:pages>
					<pg:next>
						<a href="${pageUrl}"  class="page">下一页</a>
					</pg:next>
					<pg:last>
						<a href="${pageUrl }"  class="page">尾页</a>
					</pg:last>
				</pg:pager>
				</div>

	<script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
