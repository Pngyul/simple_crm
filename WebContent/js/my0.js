//使用ajax加载数据字典,生成select
//参数1: 数据字典类型 (dict_type_code)
//参数2: 将下啦选放入的标签id
//参数3: 生成下拉选时,select标签的name属性值
//参数4: 需要回显时,选中哪个option

function loadDropdownSelecct(typeCode,positionId,selectName,selectedId){
	//1.创建select对象
	var $select = $("<select name="+selectName+" id="+positionId+"1></select>");
	//2.添加默认选项
	$select.append("<option>--请选择--</option>");
	//3.通过Ajax访问Action
	$.post("${pageContext.request.contextPath}/BaseDictAction", { dict_type_code:typeCode},
    function(data){
		//4 返回json数组对象,对其遍历
    	$.each(data,function(i, json){
    		 var $option = $("<option value='"+json['dict_id']+"'>"+json["dict_item_name"]+"</option>");
    		 //判断是否需要回显
    		 if(json['dict_id'] == selectedId){
    			 $option.attr("selected","selected");
    		 }
    		//并添加到select对象
			$select.append($option);
    		});
  },"json");
	
	//5.将select对象添加到指定位置
	$("#"+positionId).append($select);
}