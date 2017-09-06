<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".click").click(function() {
			window.location.href = 'selfcheck/toSaveInfo.do?epId=${sessionScope.epId}';
		});
	});
</script>

</head>


<body>

	

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<li class="click"><span><img src="images/t01.png"  /></span>添加</li>

			</ul>



		</div>


		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" checked="checked" /></th>
					<th>编号</th>
					<th>检查时间</th>
					<th>检查机构</th>
					<th>检查形式</th>
					<th>检查人姓名</th>
					<th>详细信息</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${infos.datas}" var="info" varStatus="status">
				<tr>
					<td><input name="" type="checkbox" value="" /></td>
					<td>${status.index+1}</td>
					<td>${info.checkDate}</td>
					<td>${info.checkEP}</td>
					<td>${info.checkType}</td>
					<td>${info.checkStaff}</td>
					<td><a href="selfcheck/toCheckDetail.do?infoId=${info.id}">查看</a></td>

				</tr>
				</c:forEach>
				

			</tbody>
		</table>
	</div>
	<div class="pages">
		<pg:pager url="selfcheck/toCheckList.do"
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