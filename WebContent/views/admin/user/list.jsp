<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">账号管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			$('#tg').datagrid({
				url: "${root}/admin/user/find.json",
				queryParams: {
					keyword : $('#keyword').val(),
					status : $('#status').val()
				},
				onDblClickRow : function(idx, row){
					editUser(row);
				}
			});
		});

		function doSearch() {
			$('#tg').datagrid('load', {
				keyword : $('#keyword').val(),
				status : $('#status').val()
			});
		}
		
		function editUser(row){
			
			if(row == null){
				row = $("#tg").datagrid("getSelected");
			}
			
			if(row != null) {
				juasp.openWin({
					url: "${root}/admin/user/edit?id=" + row.userId,
					width: "600px",
					height: "500px",
					title: "修改用户资料",
					onClose : function(result){
						if(result == 1){
							//doSearch();
						}
					}
				});
			} else {
				juasp.info("请选择要编辑的记录。");
			}
		}
		
		function newUser(){
			juasp.openWin({
				url: "${root}/admin/user/new",
				width: "600px",
				height: "500px",
				title: "创建用户资料",
				onClose : function(result){
					if(result == 1){
						doSearch();
					}
				}
			});
		}
		
	</script>
</e:section>

<e:section name="body">
	<e:datagrid id="tg" fit="true" rownumbers="true" 
		pagination="true" pageSize="10" singleSelect="true" striped="true"
		toolbar="#tb,#tq" idField="userId">
		<e:columns>
			<e:column field="userName" width="100" title="用户名" />
			<e:column field="displayName" width="180" title="显示名" />
			<e:column field="email" width="280" title="电子邮件" />
			<e:column field="mobileNo" width="340" title="手机号" />
			<e:column field="status" width="60" align="center" title="状态" />
		</e:columns>
	</e:datagrid>
	<div id="tb">
		<e:linkbutton id="add" iconCls="icon-add" plain="true" text="新增账号" onclick="newUser()" />
		<e:linkbutton id="edit" iconCls="icon-edit" plain="true" text="编辑账号" onclick="editUser()" />
	</div>
	<div id="tq" style="padding-left: 8px">
		关键字：
		<e:textbox id="keyword" style="width:150px" />
		状态：
		<e:combobox id="status">
			<e:selectitem label="所有" value="" />
			<e:selectitem label="申请中" value="1" />
			<e:selectitem label="使用中" value="2" />
			<e:selectitem label="已停用" value="3" />
		</e:combobox>
		<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
	</div>
</e:section>

<%@ include file="/shared/_list.jsp"%>