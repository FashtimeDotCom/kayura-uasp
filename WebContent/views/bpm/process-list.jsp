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
			
			return {
				
				init: _init,
				deploy: _deploy,
				search: _search
			};
			
		}(jQuery, window));
		
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
		<k:column field="resourceName" width="180" title="流程名" />
		<k:column field="diagramResourceName" width="180" title="流图名" />
		<k:column field="suspended" width="80" title="暂停" />
	</k:datagrid>
	<div id="tb">
		<k:linkbutton id="deploy" iconCls="icon-add" plain="true" text="上传流程" onClick="jctx.deploy()" />
		<div style="float: right;">
			<k:searchbox id="search" prompt="搜索：流程名称" width="220" height="25" searcher="jctx.search" />
		</div>
	</div>
</k:section>

<%@ include file="/shared/_list.jsp"%>