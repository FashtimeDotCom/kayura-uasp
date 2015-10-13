<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>账号管理</title>
	<e:resources location="res" />
	<script type="text/javascript">
		$(document).ready(function() {
		});
		
		function doSearch(){
			$('#tt').datagrid('load',{
				keyword: $('#keyword').val(),
				status: $('#status').val()
			});
		}
	</script>
</head>

<e:body style="padding:10px">
	<e:datagrid id="tt" title="注册账号列表" fit="true" rownumbers="true" pagination="true" pageSize="10" singleSelect="true" 
		striped="true" url="${root}/admin/user/find.json" method="get" idField="userId" >
		
		<e:columns>
			<e:column field="ck" checkbox="true" />
			<e:column field="userName" width="100" title="用户名" />
			<e:column field="displayName" width="180" title="显示名"/>
			<e:column field="email" width="280" title="电子邮件"/>
			<e:column field="mobileNo" width="340" title="手机号"/>
			<e:column field="status" width="60" align="center" title="状态" />
		</e:columns>

		<e:facet name="toolbar">
		
			关键字：<e:inputText id="keyword" style="width:150px" />
			状态： 
			<select id="status" class="easyui-combobox" panelHeight="auto" style="width: 150px">
				<option value="">所有</option>
				<option value="0">申请中</option>
				<option value="1">使用中</option>
				<option value="2">已停用</option>
			</select><a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
			
			<div style="float: right;">
				<e:button id="add" iconCls="icon-add" plain="true" text="新增账号" />
				<e:button id="edit" iconCls="icon-edit" plain="true" text="编辑账号" />
				<e:button id="cancel" iconCls="icon-cancel" plain="true" text="删除账号" />
			</div>
			
		</e:facet>
	
<%-- 		<e:facet name="tb">
			关键字：<e:inputText id="keyword" style="width:150px" />
			状态： <select id="status" class="easyui-combobox" panelHeight="auto" style="width: 150px">
				<option value="">所有</option>
				<option value="0">申请中</option>
				<option value="1">使用中</option>
				<option value="2">已停用</option>
			</select> <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		</e:facet> --%>
	</e:datagrid>
</e:body>
</html>