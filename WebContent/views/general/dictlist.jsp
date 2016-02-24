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
					var id = node.id;
					findItems(node.id);
					if(id == 'ROOT' || id == 'CATEGORY') {
						$('#add').linkbutton('disable');
						$('#edit').linkbutton('disable');
						$('#delete').linkbutton('disable');
					} else {
						$('#add').linkbutton('enable');
						$('#edit').linkbutton('enable');
						$('#delete').linkbutton('enable');
					}
				}
			});
		});

		function findItems(id) {
			
			if(dictId == "") {

				$('#tg').datagrid({
					url: "${root}/gm/dict/load.json",
					method : "post",
					queryParams: {
						"dictId": id
					},
					loadFilter: function(r){
						if(r.type == juasp.SUCCESS) {
							if (r.data && r.data.items){
								return r.data.items;
							}
						}
					},
					onDblClickRow : function(idx, row){
						editDict(row);
					}
				});
				
			} else {
				
				$('#tg').datagrid('load', { "dictId": id });
			}
			//else {

				//$('#tg').datagrid('unselectAll');
				//$('#tg').datagrid('load');
			//}
			
			dictId = id;
		}
		
		function newDict(){
			juasp.openWin({
				url: "${root}/gm/dict/new?id=" + dictId,
				width: "450px",
				height: "300px",
				title: "创建词典项",
				onClose : function(result){
					if(result == 1){
						findItems(dictId);
					}
				}
			});
		}
		
		function editDict(row){
			
			if(row == null){
				row = $("#tg").datagrid("getSelected");
			}
			
			if(row != null) {
				juasp.openWin({
					url: "${root}/gm/dict/edit?id=" + row.id,
					width: "500px",
					height: "300px",
					title: "修改词典项",
					onClose : function(result){
						if(result == 1){
							findItems(dictId);
						}
					}
				});
			} else {
				juasp.info("编辑", "请选择要编辑的记录。");
			}
		}
		
		function delDict() {

			var row = $("#tg").datagrid("getSelected");
			
			if(row != null) {
				juasp.confirm("是否删除名称为【 " + row.name + " 】的词典项。", function(r) {
					if(r == true) {
						juasp.post('${root}/gm/dict/del.json', { id : row.id},
								{ success: function(r){
									var idx = $("#tg").datagrid("getRowIndex", row);
									$("#tg").datagrid('deleteRow', idx);
								}
						});
					}
				});
			} else {
				juasp.info("删除", "请选择要删除的记录。");
			}
		}
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 160px;">
		<ul id="tv" class="easyui-tree">   
	</e:layoutunit>
	<e:layoutunit region="center" border="false" >
		<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" 
			pageSize="10" singleSelect="true" striped="true" idField="itemId" >
			<e:columns>
				<e:column field="name" title="词典名" width="200" />
				<e:column field="value" title="词典值" width="150" />
				<e:column field="serial" title="排序值" width="150" />
				<e:column field="isFixedName" width="80" title="保留数据" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="add" iconCls="icon-add" disabled="true" plain="true" text="新增" onclick="newDict()" />
			<e:linkbutton id="edit" iconCls="icon-edit" disabled="true" plain="true" text="编辑" onclick="editDict()" />
			<e:linkbutton id="delete" iconCls="icon-remove" disabled="true" plain="true" text="删除" onclick="delDict()" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>