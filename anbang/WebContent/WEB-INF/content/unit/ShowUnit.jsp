<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript" src="editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
</head>

<body>
<br>
<form>
  			<ul class="forminfo">
    <li><label>单元编号</label><input name="" type="text" class="dfinput" value="${ut.unitNum}"/></li>
    <li><label>单元名称</label><input name="" type="text" class="dfinput" value="${ut.unitName }"/></li>
    <li><label>分数</label><input name="" type="text" class="dfinput" value="${ut.score }"/></li>
    <li><label>权重</label><input name="" type="text" class="dfinput" value="${ut.ki }"/></li>
   	 <li><label>对应企业</label><input name="" type="text" class="dfinput" value="${ut.epid }"/></li>
   	
    </ul>
    	</form>

</body>
</html>