
// Cloud Float...
var option1="<option>1.1 危险化学品生产许可证</option>" +
		"<option>1.2 危险化学品经营许可证</option>";
var option2="<option>2.1 职业健康管理机构设置及人员配备情况</option>" +
		"<option>2.2 职业危害防治制度和规程建立、落实和公布情况</option>" +
		"<option>2.3 职业健康管理机构设置及人员配备情况</option>";

var option3="<option>3.1 制定安全检查制度</option>" +
		"<option>3.2 制定各工种操作规范</option>" +
		"<option>3.3制定安全生产档案</option>";


function changeItem(){
	//主选项
	var select1 = document.getElementById("select1").value;
	//联动选项
	var select2 =document.getElementById("select2");
	select2.options.length = 0; 
	
	switch(select1){
	case 'check01':

		select2.innerHTML=option1
		break;
	case 'check02':
		
		select2.innerHTML=option2
		break;
	case 'check03':
	
		select2.innerHTML=option3
		break;
	
	}
	
	
}