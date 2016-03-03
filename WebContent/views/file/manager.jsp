<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		var isfirst = true;
		var folderId = "", folderName = "";
		var nodeType = ""; // SYSFOLDER, MYFOLDER, MYGROUP, MYSHARE
		var hasRoot = ${hasRoot}, hasAdmin = ${hasAdmin};

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
					clickFolder(node);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$(this).tree('select', node.target);
					clickFolder(node);
					$('#mm').menu('show', { left: e.pageX, top: e.pageY });
				}
			});
		});

		function findFiles(id) {

			if(isfirst) {
				
				$('#tg').datagrid({
					url : "${root}/file/find.json",
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
						clickFile(row);
					},
					onDblClickRow : function(idx, row) {
	
					}
				});
			} else {

				$('#tg').datagrid('load', { "folderId" : id });
				$('#tg').datagrid('unselectAll');
			}
			
			isfirst = false;
		}
		
		function clickFolder(node){

			folderStatus(node);
			findFiles(folderId);

		}

		function folderStatus(node) {

			var root = node;
			while (true) {

				var p = $('#tv').tree("getParent", root.target);
				if (p == null ) {
					break;
				}
				root = $('#tv').tree("getParent", root.target);
			}
			
			nodeType = root.id;
			folderId = node.id;
			folderName = node.text;
			
			// 可操作节点区.
			var isEditArea = (nodeType == "MYFOLDER") || (hasRoot && nodeType == "SYSFOLDER") || (nodeType == "MYGROUP");
			// 是否为用户文件夹.
			var isUserFolder = isEditArea && folderId != "NOTCLASSIFIED";
			// 可上传节点.
			var uploadState = isEditArea && isUserFolder;
			// 可创建目录节点.
			var addfolderState = isUserFolder && folderId != "MYGROUP" && hasAdmin;
			// 可移除目录节点.
			var removefolderState = isEditArea && isUserFolder;
			// 可分享目录节点.
			var shareState = (nodeType == "MYFOLDER") && (folderId.length == 32);

			// 控制按钮状态.
			$('#mm').menu((removefolderState?'enableItem':'disableItem'), $("#removefolder"));
			$('#mm').menu((addfolderState?'enableItem':'disableItem'), $("#addfolder"));
			$('#mm').menu((shareState?'showItem':'hideItem'), $("#sharefolder"));
			
			$("#upload").linkbutton(uploadState?'enable':'disable');
			
			$('#downfile').linkbutton('disable');
			$('#movefile').linkbutton('disable');
			$('#copyfile').linkbutton('disable');
			$('#sharefile').linkbutton('disable');
		}

		function clickFile(row){
			
			var isEditArea = (nodeType == "MYFOLDER") || (hasRoot && nodeType == "SYSFOLDER") || (hasAdmin && nodeType == "MYGROUP");

			$('#downfile').linkbutton('enable');
			$("#copyfile").linkbutton('enable');
			
			$("#movefile").linkbutton(isEditArea?'enable':'disable');
			$("#sharefile").linkbutton(isEditArea?'enable':'disable');

			$("#downfile").attr("href", "${root}/file/get?id=" + row.frId);
		}
		
		function deleteFile() {

			var row = $("#tg").datagrid("getSelected");

			if (row != null) {
				juasp.confirm("是否删除名称为【 " + row.name + " 】的词典项。", function(r) {
					if (r == true) {
						juasp.post('${root}/file/delete.json', { id : row.id },
						{ success: function(r) {
										var idx = $("#tg").datagrid( "getRowIndex", row);
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

			var openUrl = "${root}/file/folder/new?pid=" + folderId + "&pname=" + folderName;
			
			juasp.openWin({
				url : openUrl,
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
	<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 200px;">
		<ul id="tv" class="easyui-tree"></ul>
	</e:layoutunit>
	<e:layoutunit region="center" border="false">
		<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" 
			pageSize="10" singleSelect="true" striped="true" idField="frId">
			<e:columns>
				<e:column field="fileName" title="文件名" width="450" />
				<e:column field="uploaderName" title="上传人" width="150" />
				<e:column field="fileSize" title="大小" width="150" />
				<e:column field="uploadTime" title="上传时间" width="200" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="upload" disabled="true" iconCls="icon-add" plain="true" text="上传文件" />
			<e:linkbutton id="downfile" disabled="true" iconCls="icon-download" plain="true" text="下载" />
			<e:linkbutton id="movefile" disabled="true" iconCls="icon-cut" plain="true" text="移动" />
			<e:linkbutton id="copyfile" disabled="true" iconCls="icon-copy" plain="true" text="复制" />
			<e:linkbutton id="sharefile" disabled="true" iconCls="icon-share" plain="true" text="分享" />
		</div>
		<div id="mm" class="easyui-menu" style="width: 120px;">
			<div id="addfolder" onclick="createFolder()" data-options="iconCls:'icon-addfolder'">添加</div>
			<div id="removefolder" onclick="removeit()" data-options="iconCls:'icon-remove'">移除</div>
			<div id="sharefolder" onclick="removeit()" data-options="iconCls:'icon-share'">分享</div>
			<div class="menu-sep"></div>
			<div onclick="expand()">展开</div>
			<div onclick="collapse()">收缩</div>
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>