<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link href="css/style.css" rel="stylesheet" type="text/css" />
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
       <th colspan=2>检查时间</th>
       <th colspan=2>检查机构</th>
       <th>检查形式</th>
       <th>检查人员 </th>
	   
     </tr>

      <tr>
	    <td colspan=2> <fmt:formatDate value="${info.checkDate}" dateStyle="full" /></td>
       	<td colspan=2>${info.checkEP} </td>
        <td>${info.checkType}</td>
        <td>${info.checkStaff}</td>
     
    	</tr>
     <tr>
  <td colspan="6"> 
	<br/>
	</td>

   
	</tr>
	 <tr>
       <th width="50">条款</th>
	   <th width="28%">检查内容</th>
       <th width="20%">检查依据</th>
       <th width="80">检查方式</th>
       <th>现场实际情况</th>
	   <th width="68">隐患分类</th>
    
     </tr>
	<c:forEach items="${checks}" var="cv">
	<tr>
	    <td>${cv.itemNum}</td>
       <td>${cv.itemContent} </td>
	   <td>${cv.itemLaw}  </td>
	    <td>${cv.checkType}  </td>
       <td style="word-wrap:break-word; word-break:break-all;">${cv.checkLive}</td>
	   <td>${cv.checkStatus}</td>
      
     </tr>
     
     </c:forEach>
	 
    
    </table>

</div>
</body>
</html>