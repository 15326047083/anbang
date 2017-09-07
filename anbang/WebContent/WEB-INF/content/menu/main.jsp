<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <title>危险化学品企业安全生产风险预警信息系统</title>
</head>

<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.html" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />

  <frameset frameborder="no" border="0" framespacing="0">
    <frame src="mainPagea.html" name="main" scrolling="no" noresize="noresize" id="main" title="main" />
  </frameset>

  <noframes>
    <body>
    </body>
  </noframes>
</frameset>
</html>
