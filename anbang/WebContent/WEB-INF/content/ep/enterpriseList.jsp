
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
 <script src="js/XCheck.js" type="text/javascript"></script>
<script type="text/javascript">
	var xcheck1;
	$(document).ready(function() {

		$(".clickA").click(function() {
			window.location.href = 'enterprise/toAdd.do';
		});
		 
		xcheck1=$.XCheck({
             groupClass:".xcheckgroup2"
         });
		
		$(".clickB").click(function() {
			//alert(xcheck1.val());
			if(xcheck1.val()==undefined){
				alert("没有选择任何企业");
			}
			else{if(confirm("确认删除吗？"))
			
			window.location.href = 'enterprise/deleteEps1.do?ids='+xcheck1.val();
			
			}
		});
			
	});
</script>

</head>


<body>
 
 	<!-- 隐藏 checkbox的值 -->
    <div class="xcheckgroup2 xcheckValue" style="visibility:hidden"></div>
	<div class="rightinfo">

		<div class="tools">
			<ul class="toolbar">
		
			
				<li class="clickA"><span><img src="images/t01.png"  /></span>添加</li>
				<li class="clickB"><span><img src="images/t03.png"  /></span>删除</li>
			</ul>



		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th><input name="" type="checkbox" value="" class="xcheckgroup2 checkAllCurrent" /></th>
					<th>序号</th>
					<th>企业名称</th>
					<th>注册地址</th>
					<th>USCC</th>
					<th>详细信息</th>
					<th>用户信息</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach items="${infos.datas}" var="info" varStatus="status">
				<tr>
					<td><input type="checkbox" class="xcheckgroup2 checkItem" value="${info.id}"/></td>
					<td>${status.index+1}</td>
					<td>${info.ename}</td>
					<td>${info.address}</td>
					<td>${info.ecode}</td>
					
					
					<td><a href="enterprise/toChangeEnterprise.do?epId=${info.id}">查看</a></td>
					<td><a href="user/new.do?epId=${info.id}">查看</a></td>
					<%-- <td><a href="check/toCheckDetail.do?infoId=${info.id}">查看</a></td> --%>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><div class="pages">
	
		<pg:pager url="menu/toEnterpriseList.do"
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