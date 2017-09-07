<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<link href="css/style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="css/font-awesome.min.css" />
<link rel="stylesheet" href="css/jquery-ui.css" />
<!-- 引入 echarts.js -->
<script src="js/echarts.min.js"></script>
<script src="js/jquery-1.8.3.js"></script>
<script src="js/highcharts.js"></script>
<script src="js/highcharts-3d.js"></script>
<script src="js/modules/exporting.js"></script>
<script type="text/javascript" src="js/jquery.corner.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/template-web.js"></script>
<script src="js/datepiker_cn.js"></script>
<script src="js/jquery.blockUI.js"></script>

<script type="text/javascript">
  $.ajaxSettings.async = false;//必须保持同步，否则数据混乱无法渲染图片
  var infoData;//饼图，信息图json信息
  var xax=new Array();//横坐标信息
  var score=new Array();//各点分数信息
  var infoMap=new Map();//map 对应点的map 格式为<点  infoID>
  var dialog,pieDialog,checkInfoDialog;
  var currentInfoId;
  var templateData=new Object();
  //$.blockUI();
  $( function() {

	    var dateFormat = "yy-MM-dd",
	      from = $( "#from" )
	        .datepicker({
	        	  changeMonth: true,
	              changeYear: true,
	              defaultDate: '2017-01-01',
	              dateFormat:"yy-mm-dd"
	        });
	      to = $( "#to" ).datepicker({
	    	  changeMonth: true,
	          changeYear: true,
	          defaultDate: '2017-12-31',
              dateFormat:"yy-mm-dd"
	      });
	      
	 
	    function getDate( element ) {
	      var date;
	      try {
	        date = $.datepicker.parseDate( dateFormat, element.value );
	      } catch( error ) {
	        date = null;
	      }
	 
	      return date;
	    }
	    $( "#from" ).val("2017-01-01");
	    $( "#to" ).val("2017-21-31");
	    
	    
	  } );
  	//点击饼图
  	function getPieDetail(checkType){
  	  $.ajaxSettings.async = false;//必须保持同步
  	  switch(checkType){
  	  case "一般隐患":
  		 $.getJSON("check/getExChecks.do?infoId="+currentInfoId,function(data){
  			 templateData.list=data;
  			 var tbodys=template('pies', templateData);
  			 $("#tbodys").html(tbodys);
  			 pieDialog.dialog("open");
  			 
  		 });
  		  break;
  	case "重大隐患":
 		 $.getJSON("check/getErrorChecks.do?infoId="+currentInfoId,function(data){
 			 templateData.list=data;
 			 var tbodys=template('pies', templateData);
 			 $("#tbodys").html(tbodys);
 			 pieDialog.dialog("open");
 			 
 		 });
 		  break;
  	  
  	  }
  	 
  		
  	}
  
  	function getCheckInfo(info){
  		$("#checkFrame").attr("src",info);
  		$(checkInfoDialog.dialog("open"));
  	}
	//准备信息栏内容
    function loadInfo(data,index) {
    var result="";
	$.each(data,function(i,item){
		if(item.index==index){
			if(item.exception!=null){
			$.each(item.exception,function(i,item){
			var temp1=item.split(":");
			result=result+"<a class='fa fa-exclamation-triangle' href='javascript:void()' onclick=showMessage("+"'"+temp1[0]+"'"+")>&nbsp;&nbsp;"+temp1[1]+"</a>";
			});
			}
			if(item.error!=null){
				
			$.each(item.error,function(i,item){
				var temp2=item.split(":");
				result=result+"<a class='fa fa-times-circle' href='javascript:void()' onclick=showMessage("+"'"+temp2[0]+"'"+")>&nbsp;&nbsp;"+temp2[1]+"</a>";
			});
			}
			
		}

	});
	 
    $("#info").html("");
    $("#info").html(result);

  }
  
  function showMessage(checkId){

	  $.ajaxSettings.async = false;//必须保持同步
	  $.getJSON("check/getCheckVo.do?checkId="+checkId,function(data){
		  $.each(data,function(i,item){
			  switch(i){
			  case "itemNum":
				  $("#itemNumber").val(item);
				  break;
			  case "itemContent":
				  $("#itemContent").val(item);
				  break;
			  case "itemLaw":
				  $("#itemLaw").val(item);
				  break;
			  case "checkLive":
				  $("#itemLive").val(item);
				  break;
			  
			  }
			
		  });
		
		  
	  });
	  dialog.dialog("open");
	  
  }
  
  //获取检测json数据
  function getInfoData(){
	 
	 $.ajaxSettings.async = false;//必须保持同步，否则数据混乱无法渲染图片
	 infoData="";
	 
	 $.getJSON("charts/getSafetyCheckDate.do?epid=${sessionScope.epId}&start=${param.start}&end=${param.end}&chartType=dept",function(data){
	 		$.each(data,function(i,item){
	   			xax.push(item);
	 		});
	 		
	 	});
	 
	$.getJSON("charts/getChartsVisual.do?epid=${sessionScope.epId}&start=${param.start}&end=${param.end}&type=dept",function(data){
		infoData=data;
		$.each(data,function(i,item){
			infoMap.set(item.index,item.infoId);
			
				/**var zero={
					value:0,
					symbol:'circle',
					symbolSize:16,
					itemStyle: { //折线样式
				          normal: {
				            color: 'red'}
					}//拐点颜色
				}**/

				var tempData = new Array();
   				tempData.push(item.index);
   				tempData.push(item.score);
				score.push(tempData);
				//xax.push(item.index);
	
			
		});
		
	});
	

  }


  function getPieData(index){
		var pieData= new Array();
	$.each(infoData,function(i,item){
		
		if(item.index==index){
			pieData[0]=item.stat[0];
			pieData[1]=item.stat[1];
			pieData[2]=item.stat[2];
			return false;
			
				
		}
	
	});
	 return pieData;
	 
  }
//获取饼图百分比数据
  function loadPie(data) {
    $('#container').highcharts({
      chart: {
        type: 'pie',
        options3d: {
          enabled: true,
          alpha: 65,
          beta: 0
        }
      },
      title: {
        text: '占比'
      },

      tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
      },
      plotOptions: {
        pie: {
		  size:'200%',
          allowPointSelect: true,
          cursor: 'pointer',
          depth: 30,
          dataLabels: {
            enabled: true,
            format: '{point.name}'
          },
          showInLegend: true
        }
      },
      series: [{
        type: 'pie',
        name: '各项占比',
        data: [
      {name:'符合项',color:'#065279',y:data[0]},
      {name:'一般隐患',color:'#FF7500',y:data[1]},
      {name:'重大隐患',color:'#990033',y:data[2]}],
        events: {
          click: function(e) {
        	  getPieDetail(e.point.name);

          }
        }
      }]
    });
  }



  //准备提示框数据
  function getTipData(index){
		var res="";
		$.each(infoData,function(i,item){
			if(item.index==index){
				res=res+'<div><p>符合:' + item.stat[0] + '</p><p>一般隐患:' + item.stat[1] + '<p>重大隐患:' + item.stat[2] + '</p></div>';
				return false;
			}
		});
		return res;
  }
  
  function loadLine() {
    var chart = echarts.init(document.getElementById('main'));

    option = { //图标题
      title: {
        text: '安全生产监督检查信息趋势图', //标题文字
        x: 'center' //标题位置
      },
      tooltip: { //弹出框样式
        triger: 'axis',
        formatter: function(params) { //定义弹出框样式
        return getTipData(params.value[0]);
        },


      },
      xAxis: { //横向设置
        minInterval: 1,
            data:xax,//横轴坐标

      },
      yAxis: {
        splitArea: { //纵向分割色带
          show: true, //是否显示
          areaStyle: { //色带样式
            color: ['#FFCC99', '#FFCC99', '#FFCC99', '#FFFFCC', 'rgba(135,200,250,0.3)'] //色带颜色，由下至上
          }
        },
        splitLine: { //纵向分割线
          show: false, //是否显示

        },
        min: 0, //纵坐标最小值
        max: 100,
        axisLabel: false //纵坐标最大值
      },
      series: [{ //图形数据
        name: '评分',
        type: 'line', //图形类型
        data: score, //数据
        showAllSymbol: true, //显示所有拐点标
        symbol: 'emptyDiamond',
        symbolSize: 12,
        itemStyle: { //折线样式
          normal: {
            color: '#003366', //拐点颜色
            width: 5,
            lineStyle: { //线条样式
              color: '#003366', //颜色
              width: 3, //宽度
              shadowColor: 'rgba(0,0,0,0.5)', //阴影颜色
              shadowBlur: 10, //阴影属性
              shadowOffsetX: 8, //阴影位置
              shadowOffsetY: 8
            }
          },

        },
      }],

      dataZoom: [{ // 这个dataZoom组件，默认控制x轴。
        type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
        start: 0, // 左边在 10% 的位置。
        end: 100 // 右边在 60% 的位置。
      }, { // 这个dataZoom组件，也控制x轴。
        type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
        start: 0, // 左边在 10% 的位置。
        end: 100 // 右边在 60% 的位置。
      }]

    };


    chart.setOption(option);

    chart.on('click', function(params) {
      
      var res="check/toCheckDetail.do?infoId="+infoMap.get(params.value[0]);
      getCheckInfo(res);

    });
    chart.on('mouseover', function(params) {

      currentInfoId=infoMap.get(params.value[0]);
      loadPie(getPieData(params.value[0]));
      loadInfo(infoData,params.value[0]);
    });

  }


$(function(){
	
	getInfoData();
	loadLine();
	
  
  });

  $(function() {
    $("#info").corner('15px');
    $("#container").corner('15px');
    
    dialog=$( "#dialog" ).dialog({
        autoOpen: false,
        height: 380,
        width: 400,
        modal: true,open: function (event, ui) {
        	                $(".ui-dialog-titlebar-close", $(this).parent()).hide();
        	         },
        	         buttons: [
        	        	    {
        	        	      text: "确认",
        	        	      
        	        	      click: function() {
        	        	        $( this ).dialog( "close" );}
        	        	      }]
      });
    pieDialog=$( "#pieDialog" ).dialog({
        autoOpen: false,
        height: 500,
        width: 950,
        modal: true,open: function (event, ui) {
        	                $(".ui-dialog-titlebar-close", $(this).parent()).hide();
        	         },
        	         buttons: [
        	        	    {
        	        	      text: "确认",
        	        	      
        	        	      click: function() {
        	        	        $( this ).dialog( "close" );}
        	        	      }]
      });
    
    checkInfoDialog=$( "#checkInfo" ).dialog({
        autoOpen: false,
        height: 700,
        width: 1020,
        modal: true,open: function (event, ui) {
        	                $(".ui-dialog-titlebar-close", $(this).parent()).hide();
        	         },
        	         buttons: [
        	        	    {
        	        	      text: "确认",
        	        	      
        	        	      click: function() {
        	        	        $( this ).dialog( "close" );}
        	        	      }]
      });

  });


  $(document).ready(function(){
	  $.unblockUI();
  });


  window.onresize = function() {
    loadLine();
  }
  </script>
<style>
</style>
</head>

<body>
	<script type="text/javascript">
 	
	$.blockUI({message:  '<h1>请稍等...</h1>',  css: { backgroundColor: '#f00', color: '#fff'} });
	</script>
	<div class="rightinfo">

		<div id="datarange" style="width: 80%; margin: auto">

			<form method="get" action="menu/toSafetyCheckVisual.do">
				<label for="from">开始日期</label> <input type="text" id="from"
					name="start" class="dfinput"> <label for="to">结束日期</label>
				<input type="text" id="to" name="end" class="dfinput"> <input
					type="hidden" id="type" name="type" value="dept" class="dfinput">
				<input type="hidden" name='epid' value="${sessionScope.epId}" /> <input
					type="submit" class="btn" value="查询" />

			</form>
		</div>
		<div id="main"
			style="min-width: 1000px; min-height: 350px; float: none"></div>

		<div>





			<table style="width: 80%; margin: auto">
				<tr>
					<td style="height: 250px; width: 50%;">
						<div id="container" class="shadow"
							style="height: 250px; padding-top: 15px; overflow: hidden;"></div>
					</td>
					<td style="height: 250px; width: 50%;">
						<div id="info" class="shadow"
							style="height: 250px; margin-left: 15px; text-align: center; overflow: auto; background-color: #f0ffff">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!-- 模态框，显示隐患信息 -->
	<div id="dialog" title="隐患信息">

		<label>编号</label><br> <input type="text" id="itemNumber"
			class="text ui-widget-content ui-corner-all" /><br> <label>内容</label><br>
		<textarea id="itemContent" cols="60"
			class="text ui-widget-content ui-corner-all"></textarea>
		<br> <label>依据</label><br>
		<textarea id="itemLaw" cols="60"
			class="text ui-widget-content ui-corner-all"></textarea>
		<br> <label>现场情况</label><br>
		<textarea id="itemLive" cols="60"
			class="text ui-widget-content ui-corner-all"></textarea>

	</div>

	<div id="checkInfo" title="检查明细">
		<iframe id="checkFrame" style="width: 100%; height: 100%;"></iframe>
	</div>
	<!-- 模态框，显示饼图表示详细隐患信息列表 -->
	<div id="pieDialog" title="隐患信息列表">
		<table class="tablelist">
			<thead>
				<tr>
					<th>编号</th>
					<th>内容</th>
					<th>依据</th>
					<th>情况</th>
				</tr>
			</thead>
			<tbody id="tbodys">
				<script id="pies" type="text/html">
			{{each list as value}}    
				
				<tr>
				<td width="40">{{value.itemNum}}</td>
				<td>{{value.itemContent}}</td>
				<td>{{value.itemLaw}}</td>
				<td>{{value.checkLive}}</td>
			</tr>
			{{/each}}
		
		</script>
			</tbody>
		</table>
	</div>

</body>

</html>
