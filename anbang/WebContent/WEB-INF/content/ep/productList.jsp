<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/lib/pager-taglib-2.0.jar" prefix="pg"%>
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
<link href="css/page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/excellentexport.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
	
		$(".clickA").click(function() {
			window.location.href="enterprise/newProduct.do?dangerId=${sessionScope.epId}"
		});
		
	});
</script>
</head>
<body>
	<br>
	
	<div class="rightinfo">
	
	
	<div class="tools">
			<ul class="toolbar">
			<c:if test="${sessionScope.user.userType ne 'dept'}">
				<li class="clickA"><span><img src="images/t01.png"  /></span>添加</li>
				</c:if>
				 <a download="checktable.xls" href="#" onclick="return ExcellentExport.excel(this, 'datatable', 'Sheet Name Here');"><li class="click"><span><img src="images/excel_exp.png"/></span> 
			导出</li></a>
			</ul>
				</div>



	
		<table id="datatable" class="tablelist">
			<thead>
				<tr>
					
					
					<th>项目名称</th>
					<th>生产能力</th>
					<th>存储能力</th>
					<th>涉危化学品</th>
					<th>重点监管化学品</th>
					<c:if test="${sessionScope.user.userType ne 'dept'}"><th>操作</th></c:if>
				</tr>
			</thead>	
			<tbody>
				<c:forEach items="${infos.datas}" var="info">
				<tr>
				
					<td>${info.pName}</td>
					<td>${info.pCapacity}</td>
					<td>${info.loadCapacity}</td>
					<td>${info.pType}</td>
					<td>${info.pSur}</td>
					
					<c:if test="${sessionScope.user.userType ne 'dept'}">
					<td><a href="javascript:if(confirm('确实要删除该内容吗？'))location='enterprise/deleteProduct.do?id=${info.id}'">删除</a>
					<a href="enterprise/getProduct.do?id=${info.id}">修改</a></td>
					</c:if>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><div class="pages">
	
		<pg:pager url="enterprise/getProductList.do"
					items="${info.total}" export="currentPageNumber=pageNumber"
					maxPageItems="15" maxIndexPages="5">
					<pg:param name="dangerid" value="${infos.datas[0].dInfoId}"/>
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