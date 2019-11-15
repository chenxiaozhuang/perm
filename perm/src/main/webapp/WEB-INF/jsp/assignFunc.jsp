<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色授权页面</title>
<%@ include file="script.html" %>
<script type="text/javascript">
	$(function(){
		//加载所有权限
		$("#functionList").treegrid({
			url : "findAllFunction.do",
			fitColumns : true,
			idField : "funcId",
			treeField: "funcName",
			rownumbers : true,
			checkbox:true,
			cascadeCheck:false,
			singleSelect : false,
			columns : [[
				
				{field:"funcName",title:"权限名称",sortable:true,width:10},
				{field:"funcType",title:"权限类型",width:5,formatter:function(value,rowData,index){
					if(value == 1){
						return "菜单";
					}else if(value == 0){
						return "按钮";
					}else{
						return "其他";
					}
				}},
				{field:"funcUrl",title:"权限URL",width:10},
				{field:"status",title:"权限状态",formatter:function(value,rowData,index){
					if(value == 1){
						return "可用";
					}else if(value == 0){
						return "禁用";
					}else if(value == 2){
						return "已删除";
					}else{
						return "";
					}
				}}
				
			]],
			loadFilter:function(data){//"_parentId"
				
				if(data && data.data){
					$.each(data.data ,function(index,obj){
						if(obj.parentId){
							data.data[index]._parentId = obj.parentId; 
						}
					});
					return {"rows":data.data};
				}
				return null;
			},
			//当前表格中所有的数据加载完毕之后
			onLoadSuccess : function(){
				//根据角色的id获取到当前角色的所有权限信息
				$.get("findFunctionByRoleId.do",
					{"id":  $("#role_id").val()},
					function(data){
						//判断用户是否已经有了角色，如果有，就将对应角色在表格中选中
						if(data && data.data.length>0){
							$.each(data.data,function(index,obj){
								$("#functionList").treegrid("checkNode",obj.funcId);
							});
						}
					},"json");
			}
			
		});
		
		$("#assignBtn").click(function(){
			var rows = $("#functionList").treegrid("getCheckedNodes");
			if(rows.length <= 0){
				$.messager.alert("警告","必须选择一条记录！");
				return;
			}
			if(rows.length == 1 && rows[0].funcId == 1){
				$.messager.alert("警告","请至少选择一个子菜单");
				return;
			}
			var ids = new Array();
			$.each(rows,function(index,obj){
				ids.push(obj.funcId);
			});
			ids = ids.join(",");
			//提交修改的菜单选项信息
			$.get(
				"save_roleFunction.do",
				{"roleId":$("#role_id").val(),"ids":ids},
				function(data){
					alert(data.msg);
					if(data.success){
						parent.closeTopWindow();
					}
				},
				"json"
			);
		});
	})
</script>
</head>
<body>
	<div style="text-align: center;margin-top:10px;">
		<input type="hidden" name="roleId" value="${roleId}" id="role_id"/>
    	<a  class="easyui-linkbutton" id="assignBtn" data-options="iconCls:'icon-man'">分配</a>
    </div>
	<table id="functionList"></table>
	
</body>
</html>