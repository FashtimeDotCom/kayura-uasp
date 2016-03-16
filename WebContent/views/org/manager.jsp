<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		$(function() {
			
			$('#tv').tree({
				url : "${root}/org/tree.json",
				animate: true,
				onClick : function(node) {

				},
				onBeforeLoad : function(node, data){
					$(this).tree("collapseAll");
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#mm').menu('show', { left: e.pageX, top: e.pageY });
				}
			});
		});
		
		jctx = (function(win, $) {

			return {

			}
			
		}(window, jQuery));
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
				pageSize="10" idField="orgId" >
				<e:columns>
					<e:column field="ck" checkbox="true" />
					<e:column field="code" title="编号" width="150" />
					<e:column field="displayName" title="显示名" align="center" width="120" />
					<e:column field="orgType" title="组织类型" align="center" width="80" />
				</e:columns>
			</e:datagrid>
			<div id="tb">
				<e:linkbutton id="upload" disabled="true" iconCls="icon-add" plain="true" text="上传文件" />
			</div>
			<div id="mm" class="easyui-menu" style="width: 120px;">
				<div id="addfolder" onclick="jctx.createfolder()" data-options="iconCls:'icon-addfolder'">添加</div>
				<div id="removefolder" onclick="jctx.removefolder()" data-options="iconCls:'icon-remove'">移除</div>
				<div class="menu-sep"></div>
				<div onclick="expand()">展开</div>
				<div onclick="collapse()">收缩</div>
			</div>
		</e:layoutunit>
	</e:layout>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>