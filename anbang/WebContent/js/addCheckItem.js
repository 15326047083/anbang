//添加检查条目
var checkUnit="<ul class='forminfo'>" +
		"<li><label>大项</label>" +
		"<div class='vocation'>" +
		"<select name='' class='select1' id='select1' onchange='changeItem()'>" +
				"<option value='check01'>一，安全生产行政许可情况</option>" +
				"<option value='check02'>二，作业场所职业危害防治情况</option>" +
				"<option value='check03'>三，安全生产规章制度和操作规程</option>" +
				"</select>" +
				"</div></li><li><label>小项</label>" +
				"<div class='vocation'><select name='' class='select1'  id=''select2'>" +
				"<option>--请选择--</option>" +
				"<option>1.1 危险化学品生产许可证</option>" +
				"<option>1.2 危险化学品经营许可证</option>" +
				"</select></div></li></ul>"


function addUnit(id){
	var inner=document.getElementById(id).innerHTML;
	document.getElementById(id).innerHTML=inner+checkUnit;
}