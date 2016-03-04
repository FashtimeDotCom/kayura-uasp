<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<script type="text/javascript">
			
		$(function() {

			$('#tv').tree({
				url : "${root}/file/folders.json",
				method : "post",
				onClick : function(node) {
					var root = $(this).tree("getRootNode", node);
					jctx.clickNode(root, node);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$(this).tree('select', node.target);
					var root = $(this).tree("getRootNode", node);
					jctx.clickNode(root, node);
					$('#mm').menu('show', { left: e.pageX, top: e.pageY });
				}
			});
		});

		jctx = (function(win, $) {
			
			var selectRoot, selectNode;
			var hasRoot = ${hasRoot}, hasAdmin = ${hasAdmin};
			var isfirst = true;
			var folderId = "", folderName = "";
	
			// 页面的动作状态.
			var actions = {
				addfolder : false,
				removefolder : false,
				sharefolder : false,
				upload : false,
				downfile : false,
				sharefile : false,
				movefile : false,
				copyfile : false,
				removefile : false
			};
			
			function _findFiles(nodeId) {
				if(isfirst) {
					$('#tg').datagrid({
						url : "${root}/file/find.json",
						queryParams : { "folderId" : nodeId },
						onClickRow : function(idx, row) {
							_clickRow(this);
						},
						onSelectAll : function(rows) {
							_clickRow(this);
						},
						onUnselectAll : function(rows) {
							_clickRow(this);
						},
					});
				} else {
	
					$('#tg').datagrid('load', { "folderId" : nodeId });
					$('#tg').datagrid('unselectAll');
				}
				
				isfirst = false;
			}
			
			function _initActions() {
				
				 actions.addfolder = false;
				 actions.removefolder =false;
				 actions.sharefolder = false;
				 actions.upload = false;
				 actions.downfile = false;
				 actions.sharefile = false;
				 actions.movefile = false;
				 actions.copyfile = false;
				 actions.removefile = false;
			}
			
			function _applyActions(_actions) {
				
				// 菜单.
				$('#mm').menu((_actions.addfolder?'enableItem':'disableItem'), $("#addfolder"));
				$('#mm').menu((_actions.removefolder?'enableItem':'disableItem'), $("#removefolder"));
				$('#mm').menu((_actions.sharefolder?'showItem':'hideItem'), $("#sharefolder"));
				
				// 按钮.
				$("#upload").linkbutton(_actions.upload?'enable':'disable');
				$("#downfile").linkbutton(_actions.downfile?'enable':'disable');
				$("#movefile").linkbutton(_actions.movefile?'enable':'disable');
				$("#copyfile").linkbutton(_actions.copyfile?'enable':'disable');
				$("#removefile").linkbutton(_actions.removefile?'enable':'disable');
				$("#sharefile").linkbutton(_actions.sharefile?'enable':'disable');
			}
			
			function _clickNode(root, node){
				
				selectRoot = root;
				selectNode = node;
				
				_initActions();
				
				<c:if test="${hasRoot}">
				actions.addfolder = true;
				actions.removefolder = node.children.length == 0 && node.id.length == 32;
				actions.upload = node.id.length == 32;
				</c:if>
				
				_findFiles(node.id);
				_applyActions(actions);
			}
	
			function _clickRow(t) {

				var rows = $(t).datagrid("getSelections");
				var i = rows.length;
				<c:if test="${hasRoot}">
				actions.downfile = i > 0;
				actions.sharefile = false;
				actions.movefile = i > 0;
				actions.copyfile = i > 0;
				actions.removefile = i > 0;
				</c:if>
				_applyActions(actions);
			}
			
			function _createFolder() {
	
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
			
			function _removeFolder(){
				
			}

			function _shareFolder(){
				
			}
			
			function _uploadFile(){
				
			}
			
			function _downfile() {

				var rows = $('#tg').datagrid("getSelections");
				if(rows.length > 0 ) {
					var url = "${root}/file/get?id=" + rows[0].frId;
					win.open(url);
				}
			}
			
			function _deleteFile(){
				
			}
			
			function _moveFile(){
				
			}
			
			function _copyFile(){
				
			}
			
			function _shareFile(){
				
			}
			
			return {
				clickNode : _clickNode,
				createFolder : _createFolder,
				removeFolder : _removeFolder,
				shareFolder : _shareFolder,

				uploadFile : _uploadFile,
				downfile : _downfile,
				deleteFile : _deleteFile,
				moveFile : _moveFile,
				copyFile : _copyFile,
				shareFile : _shareFile
			}
			
		}(window,jQuery));
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 200px;">
		<ul id="tv" class="easyui-tree"></ul>
	</e:layoutunit>
	<e:layoutunit region="center" border="false">
		<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" 
			pageSize="10" idField="frId" >
			<e:columns>
				<e:column field="ck" checkbox="true" />
				<e:column field="fileName" title="文件名" width="450" />
				<e:column field="uploaderName" title="上传人" width="90" />
				<e:column field="fileSize" title="大小" width="80" />
				<e:column field="uploadTime" title="上传时间" width="150" />
			</e:columns>
		</e:datagrid>
		<div id="tb">
			<e:linkbutton id="upload" onclick="jctx.upload()" disabled="true" iconCls="icon-add" plain="true" text="上传文件" />
			<e:linkbutton id="downfile" onclick="jctx.downfile()" disabled="true" iconCls="icon-download" plain="true" text="下载" />
			<e:linkbutton id="movefile" onclick="jctx.movefile()" disabled="true" iconCls="icon-cut" plain="true" text="移动" />
			<e:linkbutton id="copyfile" onclick="jctx.copyfile()" disabled="true" iconCls="icon-copy" plain="true" text="复制" />
			<e:linkbutton id="removefile" onclick="jctx.removefile()" disabled="true" iconCls="icon-remove" plain="true" text="删除" />
			<e:linkbutton id="sharefile" onclick="jctx.sharefile()" disabled="true" iconCls="icon-share" plain="true" text="分享" />
		</div>
		<div id="mm" class="easyui-menu" style="width: 120px;">
			<div id="addfolder" onclick="jctx.createFolder()" data-options="iconCls:'icon-addfolder'">添加</div>
			<div id="removefolder" onclick="jctx.removeFolder()" data-options="iconCls:'icon-remove'">移除</div>
			<div id="sharefolder" onclick="jctx.shareFolder()" data-options="iconCls:'icon-share'">分享</div>
			<div class="menu-sep"></div>
			<div onclick="expand()">展开</div>
			<div onclick="collapse()">收缩</div>
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>