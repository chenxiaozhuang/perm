<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
<%@ include file="script.html"%>
<script type="text/javascript">
	$(function(){
		$("#userList").datagrid({
			url : "findByPage.do",
			pagination : true,
			toolbar : "#toolbar",
			fitColumns : true,
			idField : "userId",
			rownumbers : true,
			//singleSelect:true,
			columns : [[
				{field:"userId",title:"选择",checkbox:true},
				{field:"userName",title:"用户名",sortable:true,width:20},
				{field:"phone",title:"联系电话",width:15},
				{field:"email",title:"电子邮箱",width:20},
				{field:"createTime",title:"创建时间",width:15},
				{field:"updateTime",title:"修改时间",width:15},
				{field:"status",title:"用户状态",formatter:function(value,rowData,index){
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
			loadFilter:function(data){
				//data是服务器返回的数据,将服务器端返回的数据转换为datagrid需要的格式
				return {"total":data.total,"rows":data.rows};
			}
		});
	})
	
	function add(url){
		//调用父页面的弹出页面的方法
		parent.openTopWindow({
			width:700,
			height:500,
			title:"添加用户",
			url:url,
			close:function(){
				$("#userList").datagrid("reload");
			}
		});
	}
	
	function del(url){
		//获取选中的记录的条数数组
		var rows = $("#userList").datagrid("getChecked");
		if(rows.length == 0){
			$.messager.alert("警告","必须选中一条要删除的记录！");
			return;
		}
		if(rows.length >= 1){
			$.messager.confirm("警告","数据删除将无法恢复，确认删除选中的信息吗?",function(b){
				if(b){
					var ids = new Array();
					$.each(rows,function(index,obj){
						ids.push(obj.userId);
					});
					//以逗号进行分割
					ids = ids.join(",");
					//通过ajax提交删除操作
					$.post(
						url,
						{"ids":ids},
						function(data){
							alert(data.msg);
							//删除成功之后，刷新列表页面
							if(data.success){
								$("#userList").datagrid("reload");
								return;
							}
						},
						"json"
					);
				}
			});
		}
	}
	
	function edit(url){
		//选中修改的记录
		var rows = $("#userList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录修改！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录修改！");
			return;
		}else{
			var id = rows[0].userId;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"添加用户",
				url:url+"?id="+id,
				close:function(){
					$("#userList").datagrid("reload");
				}
			});
		}
	}
	
	function assign(url){
		//选中修改的记录
		var rows = $("#userList").datagrid("getChecked");
		if(rows.length <= 0 ){
			$.messager.alert("警告","必须选中一条记录！");
			return;
		} else if(rows.length > 1){
			$.messager.alert("警告","只能选中一条记录！");
			return;
		}else{
			var id = rows[0].userId;
			//调用父页面的弹出页面的方法
			parent.openTopWindow({
				width:700,
				height:500,
				title:"分配角色",
				url:url+"?id="+id,
				close:function(){
					$("#userList").datagrid("reload");
				},
				isScrolling:"yes"
			});
		}
	}
</script>
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0);" onclick="return add('addUser.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true">添加</a> <a
			href="javascript:void(0);"
			onclick="return del('delUser.do')"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true">删除</a> <a
			href="javascript:void(0);"
			onclick="return edit('updateUser.do');"
			id="editBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true">修改</a> <a
			href="javascript:void(0);"
			onclick="return assign('assignRole.do')"
			id="setBtn" class="easyui-linkbutton"
			data-options="iconCls:'icon-man',plain:true">分配角色</a>
	</div>
	<table id="userList"></table>
</body>
</html>