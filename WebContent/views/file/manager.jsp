<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<e:resource location="res/js" name="webuploader.css" />
	<e:resource location="res/js" name="webuploader.js" />
	<e:resource location="res/js" name="juasp-uploader.js" />

	<script type="text/javascript">
	
		$(function() {
			$('#tv').tree({
				url : "${root}/file/folders.json",
				animate: true,
				onClick : function(node) {
					var root = $(this).tree("getRootNode", node);
					jctx.clicknode(root, node);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$(this).tree('select', node.target);
					var root = $(this).tree("getRootNode", node);
					jctx.clicknode(root, node);
					$('#mm').menu('show', { left: e.pageX, top: e.pageY });
				}
			});
		});
		
		jctx = (function(win, $) {

			var uploader = null;
			var isfirst = true;
			var selectRoot, selectNode;
	
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
						rowStyler: function(index, row){
							if (row.isBiz){
								return 'color:gray;';
							} else if (row.isUploader && selectRoot.id == "MYGROUP") {
								return 'color:blue;';
							}
						},
						onSelect : function(index, rows) {
							_clickRow(this);
						},
						onUnselect : function(index, rows) {
							_clickRow(this);
						},
						onSelectAll : function(rows) {
							_clickRow(this);
						},
						onUnselectAll : function(rows) {
							_clickRow(this);
						}
					});
				} else {
	
					$('#tg').datagrid('load', { "folderId" : nodeId });
					$('#tg').datagrid('unselectAll');
				}
				
				isfirst = false;
			}
			
			function _formatterFileName(index, row){
				
				var icon = juasp.getIconName(row.postfix);
				return '<img class="webuploader-fileitem-icon" src="${root}/res/images/types/' + icon + '.png">' + row.fileName;
			}

			function selectFileIds(){

				var rows = $('#tg').datagrid("getSelections");
				var names = [];
				$.each(rows, function(index, item){
					names.push(item.frId);
				});
				return names.join(",");
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
				<c:if test="${hasRoot == false}">
				$('#mm').menu((_actions.sharefolder?'showItem':'hideItem'), $("#sharefolder"));
				</c:if>
				
				// 按钮.
				$("#upload").linkbutton(_actions.upload?'enable':'disable');
				$("#downfile").linkbutton(_actions.downfile?'enable':'disable');
				$("#movefile").linkbutton(_actions.movefile?'enable':'disable');
				$("#copyfile").linkbutton(_actions.copyfile?'enable':'disable');
				$("#removefile").linkbutton(_actions.removefile?'enable':'disable');
				<c:if test="${hasRoot == false}">
				$("#sharefile").linkbutton(_actions.sharefile?'enable':'disable');
				</c:if>
			}
			
			function _initUploader() {
				
				if(uploader == null) {
					
					$("#upload").uploader({
						formData : {
							folderId: selectNode.id
						},
						onFinished : function (){
							_findFiles(selectNode.id);
						}
					});
					
					uploader = 1;
					
/* 					uploader = WebUploader.create({
			            swf: '${root}/res/webuploader/Uploader.swf',
			            server: '${root}/file/upload.json',
						pick: '#upload',
						auto: true,
						formData:{
							folderId: selectNode.id
						}
					});
					
			        uploader.on('uploadFinished', function (file, response) {
						_findFiles(selectNode.id);
			        }); */
			        
				} else {
					
					$("#upload").uploader('setFormData', {
						folderId : selectNode.id
					});
				}
			}

			function _clickNode(root, node) {

				selectRoot = root;
				selectNode = node;

				_initActions();

				<c:choose>
				<c:when test="${hasRoot}">
				actions.addfolder = node.id != "NOTCLASSIFIED";
				actions.removefolder = node.children.length == 0
						&& node.id.length == 32;
				actions.upload = node.id.length == 32;
				</c:when>
				<c:otherwise>
				<c:choose>
				<c:when test="${hasAdmin}">
				actions.removefolder = (root.id == "MYFOLDER" || root.id == "MYGROUP")
						&& node.children.length == 0 && node.id.length == 32;
				</c:when>
				<c:otherwise>
				actions.removefolder = (root.id == "MYFOLDER")
						&& node.children.length == 0 && node.id.length == 32;
				</c:otherwise>
				</c:choose>
				actions.addfolder = (root.id == "MYFOLDER" && node.id != "NOTCLASSIFIED")
						|| (root.id == "MYGROUP" && node.id != "MYGROUP");
				actions.upload = (root.id == "MYFOLDER" || root.id == "MYGROUP")
						&& node.id.length == 32;
				</c:otherwise>
				</c:choose>

				_findFiles(node.id);
				_applyActions(actions);
				_initUploader();
			}

			function _clickRow(t) {

				var ids = selectFileIds();
				var i = ids.length;
				actions.downfile = i > 0;
				actions.copyfile = i > 0;
				<c:if test="${hasRoot}">
				actions.sharefile = false;
				actions.movefile = i > 0;
				actions.copyfile = i > 0;
				actions.removefile = i > 0;
				</c:if>
				<c:if test="${hasRoot==false}">
				var myarea = (selectRoot.id == "MYFOLDER" || selectRoot.id == "MYGROUP");
				actions.sharefile = (selectRoot.id == "MYFOLDER") && (i > 0);
				actions.movefile = myarea && (i > 0);
				actions.removefile = myarea && (i > 0);
				</c:if>

				_applyActions(actions);
			}

			function _createFolder() {
				var openUrl = "${root}/file/folder/new?pid=" + selectNode.id
						+ "&pname=" + selectNode.text;
				juasp.openWin({
					url : openUrl,
					width : "450px",
					height : "200px",
					title : "创建文件夹",
					onClose : function(r) {
						if (r.result == 1) {
							$('#tv').tree('append', {
								parent : selectNode.target,
								data : [ {
									id : r.id,
									iconCls : 'icon-folder',
									text : r.text,
									children : []
								} ]
							});
						}
					}
				});
			}

			function _removeFolder() {

				juasp.confirm("是否删除【 " + selectNode.text + " 】文件夹。",
						function(r) {
							if (r == true) {
								juasp.post('${root}/file/folder/remove.json', {
									id : selectNode.id
								}, {
									success : function(r) {
										$("#tv").tree("remove", selectNode.target);
										_initActions();
										_applyActions(actions);
									}
								});
							}
						});
			}

			function _shareFolder() {

			}

			function _uploadFile() {

			}

			function _downfile() {

				var ids = selectFileIds();
				var url = "${root}/file/get?id=" + ids;
				win.open(url);
			}

			function _removeFile() {

				var rows = $('#tg').datagrid("getSelections");
				var ids = [], names = [];
				$.each(rows, function(index, item) {
					ids.push(item.frId);
					names.push("[<span color='blue'>" + item.fileName
							+ "</span>]");
				});

				juasp.confirm("<b>是否确认删除</b> " + names.join(",") + "<b> "
						+ ids.length + " 个文件。</b>", function(r) {
					if (r == true) {
						juasp.post('${root}/file/remove.json', {
							id : ids.join(",")
						}, {
							success : function(r) {
								_findFiles(selectNode.id);
							}
						});
					}
				});
			}

			function _selectFolder(action) {

				var openUrl = "${root}/file/folder/select?sid=" + selectNode.id;
				juasp.openWin({
					url : openUrl,
					width : "350px",
					height : "400px",
					title : "移动至文件夹",
					onClose : function(r) {
						if (r.result == 1) {
							if (typeof action == 'function') {
								action(r);
							}
						}
					}
				});
			}

			function _moveFile() {

				var ids = selectFileIds();
				_selectFolder(function(r) {
					juasp.post('${root}/file/folder/move.json', {
						id : ids,
						folderId : r.data
					}, {
						success : function(r) {
							_findFiles(selectNode.id);
						}
					});
				});
			}

			function _copyFile() {

				var ids = selectFileIds();
				_selectFolder(function(r) {
					juasp.post('${root}/file/folder/copy.json', {
						id : ids,
						folderId : r.data
					}, {
						success : function(r) {
							_findFiles(selectNode.id);
						}
					});
				});
			}

			function _shareFile() {

			}

			return {
				clicknode : _clickNode,
				createfolder : _createFolder,
				removefolder : _removeFolder,
				sharefolder : _shareFolder,

				uploadfile : _uploadFile,
				downfile : _downfile,
				removefile : _removeFile,
				movefile : _moveFile,
				copyfile : _copyFile,
				sharefile : _shareFile,
				
				formatterFileName : _formatterFileName 
			}

		}(window, jQuery));

		function expand() {

			var node = $('#tv').tree('getSelected');
			$('#tv').tree('expand', (node ? node.target : null))
		}

		function collapse() {

			var node = $('#tv').tree('getSelected');
			$('#tv').tree('collapse', (node ? node.target : null))
		}
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 2px;">
	<e:layout id="ctx" fit="true">
		<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 200px;">
			<ul id="tv" class="easyui-tree"></ul>
		</e:layoutunit>
		<e:layoutunit region="center" border="false">
			<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" 
				pageSize="10" idField="frId" >
				<e:columns>
					<e:column field="ck" checkbox="true" />
					<e:column field="fileName" formatter="jctx.formatterFileName" title="文件名" width="450" />
					<e:column field="uploaderName" title="上传人" align="center" width="90" />
					<e:column field="fileSize" title="大小" align="center" width="80" />
					<e:column field="downloads" title="下载次数" align="center" hidden="true" width="70" />
					<e:column field="allowChange" title="允许更改" hidden="true" width="80" />
					<e:column field="isEncrypted" title="数据加密" hidden="true" width="80" />
					<e:column field="uploadTime" title="上传时间" align="center" width="150" />
				</e:columns>
			</e:datagrid>
			<div id="tb">
				<e:linkbutton id="upload" disabled="true" iconCls="icon-add" plain="true" text="上传文件" />
				<e:linkbutton id="downfile" onclick="jctx.downfile()" disabled="true" iconCls="icon-download" plain="true" text="下载" />
				<e:linkbutton id="movefile" onclick="jctx.movefile()" disabled="true" iconCls="icon-cut" plain="true" text="移动" />
				<e:linkbutton id="copyfile" onclick="jctx.copyfile()" disabled="true" iconCls="icon-copy" plain="true" text="复制" />
				<e:linkbutton id="removefile" onclick="jctx.removefile()" disabled="true" iconCls="icon-remove" plain="true" text="删除" />
				<c:if test="${hasRoot == false}">
				<e:linkbutton id="sharefile" onclick="jctx.sharefile()" disabled="true" iconCls="icon-share" plain="true" text="分享" />
				</c:if>
			</div>
			<div id="mm" class="easyui-menu" style="width: 120px;">
				<div id="addfolder" onclick="jctx.createfolder()" data-options="iconCls:'icon-addfolder'">添加</div>
				<div id="removefolder" onclick="jctx.removefolder()" data-options="iconCls:'icon-remove'">移除</div>
				<c:if test="${hasRoot == false}">
				<div id="sharefolder" onclick="jctx.sharefolder()" data-options="iconCls:'icon-share'">分享</div>
				</c:if>
				<div class="menu-sep"></div>
				<div onclick="expand()">展开</div>
				<div onclick="collapse()">收缩</div>
			</div>
		</e:layoutunit>
	</e:layout>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>