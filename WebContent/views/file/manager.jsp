<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		var folderId = "", id = "";
		
		$(function() {
			
			$('#tv').tree({
				url : "${root}/file/folders.json",
				method : "post",
				loadFilter : function(r) {
					if (r.data && r.data.items) {
						return r.data.items;
					}
				},
				onClick: function(node){
					
				}
			});
		});

		function findItems(id) {
			
			if(folderId == "") {

				$('#tg').datagrid({
					url: "${root}/file/list.json",
					method : "post",
					queryParams: {
						"folderId": id
					},
					loadFilter: function(r){
						if(r.type == juasp.SUCCESS) {
							if (r.data && r.data.items){
								return r.data.items;
							}
						}
					},
					onClickRow : function(idx, row){
						
					},
					onDblClickRow : function(idx, row){
						
					}
				});
				
			} else {

				$('#tg').datagrid('unselectAll');
				$('#tg').datagrid('load', { "folderId": id });
			}
			
			folderId = id;
		}
		
		function deleteFile() {

			var row = $("#tg").datagrid("getSelected");
			
			if(row != null) {
				juasp.confirm("是否删除名称为【 " + row.name + " 】的词典项。", function(r) {
					if(r == true) {
						juasp.post('${root}/file/delete.json', { id : row.id},
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
		<ul id="tv" class="easyui-tree"></ul>
	</e:layoutunit>
	<e:layoutunit region="center" border="false" >
		<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" 
			pageSize="10" singleSelect="true" striped="true" idField="frId" >
			<e:columns>
				<e:column field="fileName" title="文件名" width="200" />
				<e:column field="uploaderName" title="上传人" width="150" />
				<e:column field="fileSize" title="大小" width="150" />
				<e:column field="uploadTime" title="上传时间" width="80" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="upload" iconCls="icon-add" disabled="true" plain="true" text="上传" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>