<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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



	

	<div class="formbody">


		<div id="usual1" class="usual">

			<div class="itab">
				<ul>
					<li><a href="#tab1" class="selected">基本信息</a></li>
					<li><a href="#tab2">人员信息</a></li>
					<li><a href="#tab3">其他信息</a></li>
				</ul>
			</div>

			<div id="tab1" class="tabson">


				<ul class="forminfo">
					<form:form action="enterprise/update.do" method="post"
						commandName="ep">
						<form:hidden path="id"/>
						<form:hidden path="deptId"/>
						<li><label>企业名称<b>*</b></label> <form:input path="ename"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>
						
					
						<li><label>统一信用代码<b>*</b></label> <form:input path="ecode"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>
						<li><label>地址<b>*</b></label> <form:input path="address"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>
						<li><label>企业性质<b>*</b></label> <form:input path="etype"
								name="" type="text" cssClass="dfinput" value=""
								style="width:518px;" /></li>
						<li><label>法人代表姓名<b>*</b></label> <form:input
								path="corporation" cssClass="dfinput"
								style="width:518px;" /></li>
						<li><label>电话号码<b>*</b></label> <form:input path="tel"
								cssClass="dfinput" 
								style="width:518px;" /></li>
						<li><label>传真号码<b>*</b></label> <form:input path="fax"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>

						<li><label>电邮<b>*</b></label> <form:input path="email"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>
						<li><label>生产许可<b>*</b></label> <form:input path="permision"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>
						<li><label>企业人数<b>*</b></label> <form:input path="count"
								name="" type="text" cssClass="dfinput" style="width:518px;" /></li>
						<li><label>企业简介<b>*</b></label> <form:textarea path="desc"
								cssClass="dfinput" style="width:518px;" rows="6"/></li>
						<li><label>企业类别<b>*</b></label> <form:input path="especial"
								cssClass="dfinput" style="width:518px;" /></li>


						<li><label>所属区域<b>*</b></label>
							<div class="vocation">
								<form:select cssClass="select1" path="areaId" items="${areas}"
									itemLabel="aname" itemValue="id" />


							</div></li>
							<form:hidden path="leaderCount"/>
							<form:hidden path="middleCount"/>
							<form:hidden path="safetyCount"/>
							<form:hidden path="techCount"/>
							<form:hidden path="worker"/>
							<form:hidden path="liveWorker"/>
								<c:if test="${sessionScope.user.userType ne 'dept'}">
			
			
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="修改"/></li>
</c:if>
					</form:form>

				</ul>



			</div>

		</div>
		<div id="tab2" class="tabson">
		<ul class="forminfo">
		<form:form action="enterprise/update.do" method="post"
						commandName="ep">
						<li><label>主要负责人数<b>*</b></label>
						<form:hidden path="id"/>
		<form:input path="leaderCount" name="" type="text" class="dfinput"   style="width:518px;"/></li>
			<li><label>中层人员数量<b>*</b></label>
		<form:input path="middleCount" name="" type="text" class="dfinput"   style="width:518px;"/></li>
			<li><label>安全人员数量<b>*</b></label>
		<form:input path="safetyCount" name="" type="text" class="dfinput"   style="width:518px;"/></li>
			<li><label>技术人员<b>*</b></label>
		<form:input path="techCount" name="" type="text" class="dfinput"   style="width:518px;"/></li>
			<li><label>工作人员<b>*</b></label>
		<form:input path="worker" name="" type="text" class="dfinput"   style="width:518px;"/></li>
			<li><label>现场操作人员<b>*</b></label>
		<form:input path="liveWorker" name="" type="text" class="dfinput"   style="width:518px;"/></li>
		 <form:hidden path="deptId"/>
		 <form:hidden path="ename"/>
		 <form:hidden path="ecode"/>
		 <form:hidden path="address"/>
		 <form:hidden path="etype"/>
		 <form:hidden path="corporation"/>
		 <form:hidden path="tel"/>
		 <form:hidden path="fax"/>
		 <form:hidden path="email"/>
		 <form:hidden path="permision"/>
		 <form:hidden path="count"/>
		 <form:hidden path="desc"/>
		 <form:hidden path="especial"/>
		 <form:hidden path="areaId"/>
		 	<c:if test="${sessionScope.user.userType ne 'dept'}">
    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="修改"/></li>
    </c:if>
 	</form:form>
 	</ul>
 	
		</div>

		<div id="tab3" class="tabson">
			<iframe src="enterprise//getDangerInfoList.do?epid=${ep.id}"
				height="890px" width="100%" scrolling="no"></iframe>
		</div>

		<script type="text/javascript">
			$("#usual1 ul").idTabs();
		</script>

		<script type="text/javascript">
			$('.tablelist tbody tr:odd').addClass('odd');
		</script>





	</div>

	

</body>
</html>