<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<u:head title="账号管理">
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</u:head>

<u:body padding="10">
<table class="easyui-datagrid" title="已注册账号列表"
	data-options="
		fit:true,
		rownumbers:true,
		pagination:true,
		border:true,
		pageSize:10,
		singleSelect:true,
		striped:true,
		url:'${siteUrl}/admin/user/find.json',
		method:'get',
		toolbar:'#ft,#tb',
		idField:'userId'
	">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'userName',width:100">用户名</th>
				<th data-options="field:'displayName',width:180">显示名</th>
				<th data-options="field:'email',width:280">电子邮件</th>
				<th data-options="field:'mobileNo',width:340">手机号</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th>
			</tr>
		</thead>
	</table>
	<div id="ft" style="padding:2px 5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增账号</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑账号</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除账号</a>
	</div>
	<div id="tb" style="padding:2px 5px;">
		关键字：<input name="keyword" class="easyui-textbox" style="width:150px;">
		状态：
		<select name="status" class="easyui-combobox" panelHeight="auto" style="width:150px">
			<option value="0">申请中</option>
			<option value="1">使用中</option>
			<option value="2">已停用</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">查询</a>
	</div>
</u:body>
</html>