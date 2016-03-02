<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		var folderId = "", id = "", hasRoot = ${hasRoot}, hasAdmin = ${hasAdmin};

		$(function() {

			$('#tv').tree({
				url : "${root}/file/folders.json",
				method : "post",
				loadFilter : function(r) {
					if (r.data && r.data.items) {
						return r.data.items;
					}
				},
				onClick : function(node) {
					buttonStatus(node);
				},
				onContextMenu: function(e,node){
					e.preventDefault();
					$(this).tree('select',node.target);
					buttonStatus(node);
					$('#mm').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				}
			});
		});

		function findItems(id) {

			if (folderId == "") {

				$('#tg').datagrid({
					url : "${root}/file/list.json",
					method : "post",
					queryParams : {
						"folderId" : id
					},
					loadFilter : function(r) {
						if (r.type == juasp.SUCCESS) {
							if (r.data && r.data.items) {
								return r.data.items;
							}
						}
					},
					onClickRow : function(idx, row) {

					},
					onDblClickRow : function(idx, row) {

					}
				});

			} else {

				$('#tg').datagrid('unselectAll');
				$('#tg').datagrid('load', {
					"folderId" : id
				});
			}

			folderId = id;
		}

		function buttonStatus(node) {

			var root = node;
			while (true) {

				var p = $('#tv').tree("getParent", root.target);
				if (p == null ) {
					break;
				}
				root = $('#tv').tree("getParent", root.target);
			}
			
			if ((root.id == "MYFOLDER") || 
				(hasRoot && root.id == "SYSFOLDER") ||
				(hasAdmin && root.id == "MYGROUP" && node.id != "MYGROUP")) {
				
				$('#mm').menu('enableItem', $("#addfolder"));
			} else {
				$('#mm').menu('disableItem', $("#addfolder"));
			}
			
			if ((root.id == "MYFOLDER") || (root.id == "MYGROUP") ||
				(hasRoot && root.id == "SYSFOLDER")) {
				
				if (node.id.length == 32 || node.id == "NOTCLASSIFIED") {
					
					$('#upload').linkbutton('enable');
				} else {
					$("#upload").linkbutton('disable');
				}
				
				if (node.id.length == 32 && node.id != "NOTCLASSIFIED") {
					
					$('#mm').menu('enableItem', $("#removefolder"));
				} else {
					$('#mm').menu('disableItem', $("#removefolder"));
				}
				
			} else {
				$("#upload").linkbutton('disable');
				$('#mm').menu('disableItem', $("#removefolder"));
			}
		}

		function deleteFile() {

			var row = $("#tg").datagrid("getSelected");

			if (row != null) {
				juasp.confirm("是否删除名称为【 " + row.name + " 】的词典项。", function(r) {
					if (r == true) {
						juasp.post('${root}/file/delete.json', {
							id : row.id
						},
								{
									success : function(r) {
										var idx = $("#tg").datagrid(
												"getRowIndex", row);
										$("#tg").datagrid('deleteRow', idx);
									}
								});
					}
				});
			} else {
				juasp.info("删除", "请选择要删除的记录。");
			}
		}

		function createFolder() {

			juasp.openWin({
				url : "${root}/file/folder/new?pid=" + folderId,
				width : "450px",
				height : "200px",
				title : "创建文件夹",
				onClose : function(result) {
					if (result == 1) {
					}
				}
			});
		}
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="west" split="true" border="true"
		style="padding: 10px; width: 200px;">
		<ul id="tv" class="easyui-tree"></ul>
	</e:layoutunit>
	<e:layoutunit region="center" border="false">
		<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb"
			pagination="true" pageSize="10" singleSelect="true" striped="true"
			idField="frId">
			<e:columns>
				<e:column field="fileName" title="文件名" width="200" />
				<e:column field="uploaderName" title="上传人" width="150" />
				<e:column field="fileSize" title="大小" width="150" />
				<e:column field="uploadTime" title="上传时间" width="80" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="upload" iconCls="icon-add" plain="true" text="上传文件" />
		</div>
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div id="addfolder" onclick="createFolder()" data-options="iconCls:'icon-addfolder'">添加</div>
			<div id="removefolder" onclick="removeit()" data-options="iconCls:'icon-remove'">移除</div>
			<div class="menu-sep"></div>
			<div onclick="expand()">展开</div>
			<div onclick="collapse()">收缩</div>
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>