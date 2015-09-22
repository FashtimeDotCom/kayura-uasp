<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<u:head title="账号管理">
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</u:head>
<u:body padding="5">
<table class="easyui-datagrid" title="已注册账号列表"
	   data-options="fit:true,rownumbers:true,pagination:true,border:true,
				pageSize:10,singleSelect:false,url:'datagrid_data1.json',
				method:'get',toolbar:'#ft,#tb',footer:''">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100">Product</th>
				<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
				<th data-options="field:'attr1',width:240">Attribute</th>
				<th data-options="field:'status',width:60,align:'center'">Status</th>
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