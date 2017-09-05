
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
/* 	$(document).ready(function() {
		$(".click").click(function() {
			window.location.href = 'check/toSaveInfo.do?deptId=8a80bd945bb398f9015bb398fcb80001';
		});
	}); */
	$(document).ready(function() {
		$(".clickS").click(function() {
			window.location.href = 'enterprise/toSearchById.do';
		});
		$(".clickA").click(function() {
			window.location.href = 'enterprise/toAdd.do';
		});
		$(".clickD").click(function() {
			window.location.href = 'enterprise/deleteEps.do?';
		});
	});
</script>

</head>


<body>
 


	<div class="rightinfo">


		<table class="tablelist">
			<thead>
				<tr>
					
					<th>序号</th>
					<th>企业名称</th>
					<th>注册地址</th>
					<th>USCC</th>
					<th>单元明细</th>
					<th>单元分值</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach items="${infos.datas}" var="info" varStatus="status">
				<tr>
					
					<td>${status.index+1}</td>
					<td>${info.ename}</td>
					<td>${info.address}</td>
					<td>${info.ecode}</td>
					
					<td><a href="menu/toUnitList.do?epId=${info.id}">查看</a></td>
					<td><a href="unit/toScoreList.do?epId=${info.id}">查看</a></td>
					<%-- <td><a href="check/toCheckDetail.do?infoId=${info.id}">查看</a></td> --%>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><div class="pages">
	
		<pg:pager url="menu/toUnitEps.do"
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