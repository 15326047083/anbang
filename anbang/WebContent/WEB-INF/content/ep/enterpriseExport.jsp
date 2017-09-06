
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
		<script type="text/javascript" src="js/excellentexport.js"></script>
	
	</head>
    

<body>
 
 
	<div class="rightinfo">

	<div class="tools">


			<ul class="toolbar">
					 <a download="checktable.xls" href="#" onclick="return ExcellentExport.excel(this, 'datatable', 'Sheet Name Here');"><li class="click"><span><img src="images/excel_exp.png"/></span> 
			导出</li></a>
				      
				
			</ul>



		</div>
	 <table id="datatable" class="tablelist1">
				<tr>
					<th>企业名称</th><td>${ep.ename}</td>
				</tr>
				<tr>
					<th>统一信用代码</th><td>${ep.ecode}</td>
				</tr>
				<tr>
					<th>地址</th><td>${ep.address}</td>
				</tr>
				<tr>
					<th>企业性质</th><td>${ep.etype}</td>
				</tr>
				<tr>
					<th>法人代表姓名</th><td>${ep.corporation}</td>
				</tr>
				<tr>
					<th>电话号码</th><td>${ep.tel}</td>
				</tr>
				<tr>
					<th>传真号码</th><td>${ep.fax}</td>
				</tr>
				<tr>
					<th>电子邮箱</th><td>${ep.email}</td>
				</tr>
				<tr>
					<th>生产许可</th><td>${ep.permision}</td>
				</tr>
				<tr>
					<th>企业简介</th><td>${ep.desc}</td>
				</tr>
				<tr>
					<th>企业类别</th><td>${ep.especial}</td>
				</tr>
				<tr>
					<th>所属区域</th>
				
					<td>${ep.area}</td>
				
				</tr>
				<tr>
					<th>主要负责人数量</th><td>${ep.leaderCount}</td>
				</tr>
				<tr>
					<th>中层人员数量</th><td>${ep.middleCount}</td>
				</tr>
				<tr>
					<th>安全人员数量</th><td>${ep.safetyCount}</td>
				</tr>
				<tr>
					<th>工作人员</th><td>${ep.worker}</td>
				</tr>
				<tr>
					<th>现场操作人员</th><td>${ep.liveWorker}</td>
				</tr>
				<tr>
					<th>重点监管技术</th><td>${ep.tech}</td>
				</tr>
				<tr>
					<th>重大危险源</th><td>${ep.source}</td>
				</tr>
			
			</table>
			</div>

</body>

</html>