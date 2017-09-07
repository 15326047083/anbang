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
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>

<link rel="stylesheet" href="css/jquery-ui.css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/bootstrap.min.css">

<script type="text/javascript">
	var score;
	var confirmDialog;
	
	function checkSave(infoId){
		$.getJSON("selfcheck/isSaved.do?infoId="+infoId,function(data){
			if(data==false){
				confirmDialog.dialog("open");
				
			}
			else{
				window.location.href='selfcheck/commitCheck.do?infoId=${check.infoId}&epId=${sessionScope.epId}';
			}
			
		});
		
		
	}
	
	$(function(){
		confirmDialog=$( "#confirmDialog" ).dialog({
		        autoOpen: false,
		        height: 200,
		        width: 300,
		        modal: true,open: function (event, ui) {
		        	                $(".ui-dialog-titlebar-close", $(this).parent()).hide();
		        	         },
		        	         buttons: [
		        	        	    {
		        	        	      text: "返回",
		        	        	      
		        	        	      click: function() {
		        	        	        $( this ).dialog( "close" );}
		        	        	      }]
		      });
	});
	
	//准备单元数据
	$(function(){
		$("#toZero").val(1);
		$("#score").val(0);
		$.ajaxSettings.async = false;
		$.getJSON("check/getAllUnit.do?epid=${sessionScope.epId}",function(data){
			$("#subcheckitem option").remove();
			$.each(data,function(i,item){
					$("#checkitem").append("<option value='"+item.id+"'>"+item.unitName+"</option>");
			});
		});
		changeitem();
	
		
		
	});
	//根据单元调整项目
	function changeitem(){
		$.ajaxSettings.async = false;
		var unitId = $("#checkitem").val();
		$.getJSON("check/getItemByUnitId.do?unitId="+unitId,function(data){
			$("#subcheckitem option").remove();
			$.each(data,function(i,item){
				$("#subcheckitem").append("<option value='"+item.id+"'>"+item.itemNum+"</option>");
			});
		});
		showtips();
	}
	//显示项目提示
	function showtips(){
		var itemId=$("#subcheckitem").val();
		$("#tipslist").empty();
		$.getJSON("check/getItemById.do?id="+itemId,function(data){
			$("#tipslist").append("<tr><td>"+data.itemNum+"</td><td>"+data.itemContent+"</td><td>"+data.itemLaw+"</td><tr>");
		
			score = data.itemScore;
		});
	}
	
	//根据隐患情况决定是否要设置扣分或归零
	function setToZero(){
		//重大隐患本单元分数归0
		if($("#status").val()=="重大隐患"){
			$("#toZero").val(0);
		}else{
			$("#toZero").val(1);
		}	
		
		//符合项不扣分
		if($("#status").val()=="符合项"){
			$("#score").val(0);
		}else{
			$("#score").val(score);
		}
		
	}
	

</script>
</head>

<body>

	<div class="rightinfo">
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">录入信息</a></li> 
    <li><a href="#tab3">查看结果</a></li> 
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
		<div style="height:800px">
		<form:form  role="form" cssClass="form-inline" method="post" action="selfcheck/saveCheck.do" commandName="check">
			<form:hidden path="infoId"/>
			<form:hidden path="toZero"/>
			<div class="form-group">
				<label for="name">检查方式</label> <select name="checkType" class="form-control input-lg">
					<option value="查资料">查资料</option>
					<option value="查现场">查现场</option>
					<option value="现场询问">现场询问</option>
					<option value="现场考核">现场考核</option>
				</select>
		
				<label for="name">隐患分类</label> <select name="status" id="status" class="form-control input-lg" onchange="setToZero()">
					<option value="符合项">符合项</option>
					<option value="一般隐患">一般隐患</option>
					<option value="重大隐患">重大隐患</option>
				</select>
				<label for="name">单元</label> <select id="checkitem" name="unitId" onchange="changeitem()" class="form-control input-lg">
					
				</select>

				<label for="name">项目</label> <select id="subcheckitem" name="itemId" onchange="showtips()" class="form-control input-lg">
			
				</select>
	
			
			</div>
			<br>
			<div class="form-group">
			<label for="name">现场情况</label>
			<textarea name="live" style="min-width: 82%" rows="10"></textarea>
			</div>
				<br>
			<div class="form-group">
				<input type="hidden" id="score" name="score">
					<button type="submit" class="btn btn-default">暂存</button>
			<button type="button" onclick="checkSave('${check.infoId}')" class="btn btn-default">完成</button>
			<button type="button" onclick="javascript:window.location.href='selfcheck/cancel.do?infoId=${check.infoId}'"class="btn btn-default">取消</button>
			</div>
			<!-- javascript:window.location.href='check/commitCheck.do?infoId=${check.infoId}' -->
		
		</form:form>
		<div>
			<table class="c_tablelist">
				<tbody id="tipslist">
						
				</tbody>
			</table>
			
			
		</div>
	</div>
    
    </div> 
    
    
  
	<div id="tab3" class="tabson">
  		<iframe src="selfcheck/toCheckDetail.do?infoId=${check.infoId}" height="890px" width="100%" scrolling="false"></iframe>
	</div>
 
	
    <div id="confirmDialog" title="警告">
    	您没有暂存任何记录，点击返回重新录入。
    </div>
 
    
    
    
    
    
    
<script type="text/javascript"> 
     $("#usual1 ul").idTabs(); 
    </script>
</div> 
</div>
</body>

</html>
