<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限菜单修改加页面</title>
<%@ include file="script.html"%>
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			//校验输入的信息是否合法
			var result = $("#form").form("validate");
			//通过ajax进行数据的提交
			if(result){
				$.post(
					"updatePermById.do",
					$("#form").serialize(),
					function(data){
						alert(data.msg);
						if(data.success){
							//关掉当前弹出窗口，刷新父页面
							parent.closeTopWindow();
						}
						return;
					},
					"json"
				);
			}
		});
		//获取树形菜单		
		$("#parent").combotree({
			url:"findTree.do",
			method:"get",
			loadFilter : function(data){
				if(data.success){
					return data.data;
				}else{
					return null;
				}
			}
		});
	})

	
</script>
</head>
<body>
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">权限名称：</td>
					<td class="text-content">
						<input type="hidden" name="funcId" value="${function.funcId}">
						<input type="text" name="funcName" value="${function.funcName}" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,20]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">权限Url：</td>
					<td class="text-content">
						<input type="text" name="funcUrl" value="${function.funcUrl}"
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,64]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">权限编码：</td>
					<td class="text-content">
						<input type="text" name="funcCode" value="${function.funcCode}"
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">权限父ID：</td>
					<td class="text-content">
						<input type="text" name="parentId" value="${function.parentId}" id="parent"
							class="easyui-combotree" data-options="required:true"></td>
				</tr>
				<tr>
					<td class="text-title">权限类型：</td>
					<td class="text-content">
						<select name="funcType" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${function.funcType==1? "selected":"" }>菜单</option>
							<option value="0" ${function.funcType==0? "selected":"" }>按钮</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">权限状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1" ${function.status==1? "selected":"" }>正常</option>
							<option value="0" ${function.status==0? "selected":"" }>禁用</option>
							<option value="2" ${function.status==2? "selected":"" }>已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">权限序号：</td>
					<td class="text-content">
						<input type="text" name="sortNum" value="${function.sortNum}" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true"></td>
				</tr>
			
				<tr>
					<td colspan="2" style="text-align: center;margin-top: 10px;">
						<a href="javascript:void(0);" id="saveBtn" class="easyui-linkbutton button-primary">保存</a> 
						<a href="javascript:void(0);" id="resetBtn" class="easyui-linkbutton button-danger">重置</a>
					</td>
				</tr>
				</table>
		</form>
	</section>
</body>
</html>