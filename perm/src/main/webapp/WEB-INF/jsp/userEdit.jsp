<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户编辑页面</title>
<%@ include file="script.html"%>
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			//校验输入的信息是否合法
			var result = $("#form").form("validate");
			//通过ajax进行数据的提交
			if(result){
				$.post(
					"saveUser.do",
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
	})

</script>
</head>
<body>
	<section class="info-section">
		<form id="form" method="post">
			<table>
				<tr>
					<td class="text-title">用户名称：</td>
					<td class="text-content">
						<input type="hidden" name="userId" value=""> 
						<input type="text" name="userName" value="" 
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,20]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">用户密码：</td>
					<td class="text-content">
						<input type="password" name="password" value=""
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'length[6,20]'">
					</td>
				</tr>

				<tr>
					<td class="text-title">联系电话：</td>
					<td class="text-content">
						<input type="text" name="phone" value=""
							class="easyui-textbox theme-textbox-radius" data-options="required:true">
					</td>
				</tr>
				<tr>
					<td class="text-title">电子邮箱：</td>
					<td class="text-content">
						<input type="text" name="email" value=""
							class="easyui-textbox theme-textbox-radius" data-options="required:true,validType:'email'"></td>
				</tr>
				<tr>
					<td class="text-title">用户状态：</td>
					<td class="text-content">
						<select name="status" class="easyui-combobox theme-textbox-radius">
							<option value="1">正常</option>
							<option value="0">禁用</option>
							<option value="2">已删除</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="text-title">描述信息：</td>
					<td class="text-content">
						<textarea class="theme-textbox-radius" rows="5"  cols="20" name="note"></textarea></td>
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