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
			
			function _init() {
				
				$('#tg').datagrid({
					url: "${root}/bpm/process/find.json",
					queryParams: {
						keyword : $('#search').val()
					},
					rowStyler: function(index, row){
						
					},
					onDblClickRow : function(idx, row){
						
					}
				});
				
				$("#deploy").uploader({
					innerOptions : {
						server: '${root}/bpm/process/deploy.json',
					},
					onFinished : function (){
						_search();
					}
				});
			}
			
			function _deploy(){
				

			}
			
			function _search(){
				
				$('#tg').datagrid('load', {
					keyword : $('#search').val()
				});
			}
			
			function _edit(){
				
				var row = $('#tg').datagrid("getSelected");
				if(row != null){
					
					juasp.openWin({
						url: "${root}/bpm/process/edit?id=" + row.id,
						width: "1250px",
						height: "700px",
						title: "编辑流程",
						maximized: true,
						onClose : function(result){
							if(result == 1){
		
							}
						}
					});
				}
			}
			
			function _start(){
				
				var row = $('#tg').datagrid("getSelected");
				if(row != null){
					
					juasp.openWin({
						url: "${root}/bpm/process/form/start?id=" + row.id,
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
				deploy: _deploy,
				edit: _edit,
				search: _search,
				start: _start
			};
			
		}(jQuery, window));
		
		function formaterProcess(value, row, index){
			
			return "<a href='${root}/bpm/process/res?t=1&id=" + row.id + "' target='_blank'>"+row.resourceName + "</a>";
		}
		
		function formaterDiagram(value, row, index){

			return "<a href='${root}/bpm/process/res?t=2&id=" + row.id + "' target='_blank'>"+row.diagramResourceName + "</a>";
		}
		
	</script>
</k:section>

<k:section name="body">
	<k:datagrid id="tg" fit="true" rownumbers="true"
		pagination="true" pageSize="10" singleSelect="true" striped="true"
		toolbar="#tb" idField="id">
		<k:column field="ck" checkbox="true" />
		<k:column field="name" width="240" title="流程名称" />
		<k:column field="category" width="180" title="流程分类" />
		<k:column field="version" width="180" title="版本号" />
		<k:column field="resourceName" width="180" title="流程名" formatter="formaterProcess" />
		<k:column field="diagramResourceName" width="180" title="流图名" formatter="formaterDiagram" />
		<k:column field="suspended" width="80" title="暂停" />
	</k:datagrid>
	<div id="tb">
		<k:linkbutton id="deploy" iconCls="icon-add" plain="true" text="上传流程" onClick="jctx.deploy()" />
		<k:linkbutton id="edit" iconCls="icon-edit" plain="true" text="编辑流程" onClick="jctx.edit()" />
		<k:linkbutton id="deploy" iconCls="icon-add" plain="true" text="启动流程" onClick="jctx.start()" />
		<div style="float: right;">
			<k:searchbox id="search" prompt="搜索：流程名称" width="220" height="25" searcher="jctx.search" />
		</div>
	</div>
</k:section>

<%@ include file="/shared/_list.jsp"%>