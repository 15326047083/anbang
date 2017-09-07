<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <title>无标题文档</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/bootstrap.min.css">

  
  <script type="text/javascript">	
	function showPersonInfo(){
		if(times%2==0){
			$("#personInfo").html('安监局人员，从业10年，现任职科员，于2010年取得**资格');
		}else{
			$("#personInfo").html('安监局人员，从业15年，现任职副科长，于2005年取得**资格，2010年取得**资格');
		}
		times++;
	}
</script>
</head>

<body>
  
  
 <div class="formbody">
  <div class="formtitle">
      <span>检查基本信息</span>
    </div>
 <form:form method="post" action="selfcheck/saveInfo.do" commandName="info" cssClass="form-horizontal" role="form" cssStyle="padding-top:4px">
   

	<div class="form-group">
  		 <label for="name" class="col-sm-2 control-label">检查机构</label>
  		 <div class="col-sm-10">
  		<form:input path="checkEP" cssClass="form-control input-lg" value="本公司"/>
   		<form:hidden path="checkEPId"/>
   		</div>
   	</div>
   	<br>
   	<div class="form-group">
   		<label for="name" class="col-sm-2 control-label">检查类型</label>
   			<div class="col-sm-10">
   		<select name="checkType" class="form-control input-lg">
					<option value="自查自报">自查自报</option>
					<option value="隐患整改">隐患整改</option>
				</select>
				</div>
  	</div>
     <br>
	<div class="form-group">
   		<label for="name"  class="col-sm-2 control-label">检查人</label>
   		<div class="col-sm-10">
   		 <form:input path="checkStaff" items="${staffs}" itemValue="name" itemLabel="name" delimiter="&nbsp" cssClass="form-control input-lg"/>
  		<button type="submit" class="btn btn-default">开始录入</button>
  		</div>
  	</div>
      </form:form>
  </div>

</body>
</html>
