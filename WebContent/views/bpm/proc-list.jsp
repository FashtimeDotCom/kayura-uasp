<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">账号管理</k:section>

<k:section name="head">
	<k:resource location="res/js" name="webuploader.css" />
	<k:resource location="res/js" name="webuploader.min.js" />
	<k:resource location="res/js" name="juasp-uploader.js" />
	<script type="text/javascript">
	
		$(function(){ 
			jctx.init(); 
		});
		
		var jctx = (function($, win){
			
			var selectedNode = null;
			
			function _init() {
				
				$('#tv').tree({
					url : "${root}/bpm/biz/navtree.json",
					onClick : function(node) {
						_clickNode(node);
					}
				});
				
				$('#tg').datagrid({
					url: "${root}/bpm/proc/find.json",
					queryParams: {
						keyword : $('#search').val()
					},
					rowStyler: function(index, row){
						
					},
					onDblClickRow : function(idx, row){
						
					}
				});
				
				$("#import").uploader({
					innerOptions : {
						server: '${root}/bpm/proc/import.json',
					},
					onFinished : function (){
						_search();
					}
				});
			}
			
			function _clickNode(node){
				
				selectedNode = node;
				_search();
			}
			
			function _search(){
				
				var key = "", type="";
				if(selectedNode != null){
					key = selectedNode.attributes['key'];
					type = selectedNode.attributes['type'];
				}
				
				$("#import").uploader("setFormData", { "key" : key });
						
				$('#tg').datagrid('load', {
					"key" : key,
					"t" : type,
					"keyword" : $('#search').val()
				});
			}
			
			function _edit(){
				
				var row = $('#tg').datagrid("getSelected");
				if(row != null){
					
					win.open("${root}/modeler?modelId=" + row.id);
					
/* 					juasp.openWin({
						url: "${root}//bpm/model?id=" + row.id,
						width: "1250px",
						height: "700px",
						title: "编辑流程",
						maximized: true,
						onClose : function(result){
							if(result == 1){
		
							}
						}
					}); */
				}
			}
			
			function _remove(){
				
				var rows = $('#tg').datagrid("getSelections");
				if(rows != null){
					
					var ids = [], names = [];
					$.each(rows, function(index, item) {
						ids.push(item.deploymentId);
						names.push(item.name + ":" + item.version);
					});

					juasp.confirm("<b>是否确认流程</b> 【" + names.join(", ") + "】<b> " + ids.length + " 个文件。</b>", 
						function(r) {
							if (r == true) {
								juasp.post('${root}/bpm/proc/remove.json', 
										{ ids : ids.join(",") },
										{ success : function(r) { _search(); } }
								);
							}
					});
				}
			}
			
			function _start(){
				
				var row = $('#tg').datagrid("getSelected");
				if(row != null){
					
					juasp.openWin({
						url: "${root}/bpm/proc/form/start?id=" + row.id,
						width: "550px",
						height: "600px",
						title: "启动流程",
						onClose : function(result){
							if(result == 1){
		
							}
						}
					});
				}
			}
			
			return {
				
				init: _init,
				edit: _edit,
				remove: _remove,
				search: _search,
				start: _start
			};
			
		}(jQuery, window));
		
		function formaterProcess(value, row, index){
			
			return "<a href='${root}/bpm/proc/res?t=1&id=" + row.id + "' target='_blank'>"+row.resourceName + "</a>";
		}
		
		function formaterDiagram(value, row, index){

			return "<a href='${root}/bpm/proc/res?t=2&id=" + row.id + "' target='_blank'>"+row.diagramResourceName + "</a>";
		}
		
	</script>
</k:section>

<k:section name="body">
	<k:dock region="center" border="false" style="padding: 2px;">
	<k:layout id="ctx" fit="true">
		<k:dock region="west" split="true" border="true" style="padding: 10px; width: 200px;">
			<k:tree id="tv" />
		</k:dock>
		<k:dock region="center" border="false">
			<k:datagrid id="tg" fit="true" rownumbers="true"
				pagination="true" pageSize="10" singleSelect="true" striped="true"
				toolbar="#tb" idField="id">
				<k:column field="ck" checkbox="true" />
				<k:column field="name" title="流程名称" />
				<k:column field="key" title="流程键" />
				<k:column field="category" title="流程类别" />
				<k:column field="version" title="版本号" />
			</k:datagrid>
			<div id="tb">
				<k:linkbutton id="import" iconCls="icon-add" plain="true" text="导入流程" />
				<k:linkbutton id="edit" iconCls="icon-edit" plain="true" text="编辑流程" onClick="jctx.edit()" />
				<k:linkbutton id="remove" iconCls="icon-remove" plain="true" text="删除流程" onClick="jctx.remove()" />
				<k:linkbutton id="deploy" iconCls="icon-add" plain="true" text="启动流程" onClick="jctx.start()" />
				<div style="float: right;">
					<k:searchbox id="search" prompt="搜索：流程名称" width="220" height="25" searcher="jctx.search" />
				</div>
			</div>
		</k:dock>
	</k:layout>
	</k:dock>
</k:section>

<%@ include file="/views/shared/_simple.jsp"%>