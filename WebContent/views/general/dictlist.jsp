<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">数据词典管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		$(function() {

			$('#tv').tree({
				url : "${root}/gm/dicts.json",
				method : "post",
				loadFilter : function(r) {
					if (r.data && r.data.items) {
						return r.data.items;
					} else {
						return r;
					}
				},
				onClick: function(node){
					findDictItems(node.id);
				}
			});
			
		});

		function findDictItems(dictId) {
			$('#tg').datagrid({
				url: "${root}/gm/dictItems.json",
				method : "post",
				queryParams: {
					"dictId": dictId
				},
				loadFilter: function(d){
					if (d.data && d.data.items){
						return d.data.items;
					} else {
						return d;
					}
				}
			});
		}
	</script>
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 160px;">
		<ul id="tv" class="easyui-tree">   
	</e:layoutunit>
	<e:layoutunit region="center" border="false" >
		<e:datagrid id="tg" fit="true" rownumbers="true" fitColumns="true"
			toolbar="#tb" pagination="true" pageSize="10" singleSelect="true"
			striped="true" url="" method="post" idField="itemId" >
			<e:columns>
				<e:column field="ck" checkbox="true" />
				<e:column field="name" title="词典名" />
				<e:column field="value" title="词典值" />
				<e:column field="isFixed" width="280" title="保留数据" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="add" iconCls="icon-add" plain="true" text="新增账号" />
			<e:linkbutton id="edit" iconCls="icon-edit" plain="true" text="编辑账号" />
			<e:linkbutton id="cancel" iconCls="icon-cancel" plain="true"
				text="删除账号" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>