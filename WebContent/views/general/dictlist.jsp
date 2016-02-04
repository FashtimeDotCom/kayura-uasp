<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">数据词典管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		var dictId = "", id = "";
		
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
					dictId = node.id;
					findItems(dictId);
				}
			});
		});

		function findItems(dictId) {
			$('#tg').datagrid({
				url: "${root}/gm/dict/load.json",
				method : "post",
				queryParams: {
					"dictId": dictId
				},
				loadFilter: function(r){
					if(r.type == juasp.SUCCESS) {
						if (r.data && r.data.items){
							return r.data.items;
						}
					}
				}
			});
		}
		
		function newDict(){
			juasp.openWin({
				url: "${root}/gm/dict/new?id=" + dictId,
				width: "450px",
				height: "300px",
				title: "创建词典项",
				onClose : function(result){
					
				}
			});
		}
		
		function editDict(){
			juasp.openWin({
				url: "${root}/gm/dict/edit?id=" + id,
				width: "500px",
				height: "300px",
				title: "修改词典项",
				onClose : function(result){
					
				}
			});
		}
		
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 160px;">
		<ul id="tv" class="easyui-tree">   
	</e:layoutunit>
	<e:layoutunit region="center" border="false" >
		<e:datagrid id="tg" fit="true" rownumbers="true" 
			toolbar="#tb" pagination="true" pageSize="10" singleSelect="true"
			striped="true" url="" method="post" idField="itemId" >
			<e:columns>
				<e:column field="ck" checkbox="true" />
				<e:column field="name" title="词典名" width="200" />
				<e:column field="value" title="词典值" width="150" />
				<e:column field="isFixedName" width="80" title="保留数据" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="add" iconCls="icon-add" plain="true" text="新增账号" onclick="newDict()" />
			<e:linkbutton id="cancel" iconCls="icon-cancel" plain="true" text="删除账号" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>