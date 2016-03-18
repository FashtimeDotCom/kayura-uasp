<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		$(function() {
			
			$('#tv').tree({
				url : "${root}/org/tree.json",
				onClick : function(node) {
					jctx.clicknode(node);
				},
				onLoadSuccess : function(node, data){
					$(this).tree("collapseAll");
					
					var root = $(this).tree('find', "ROOT");
					$(this).tree("expand", root.target);
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$(this).tree('select', node.target);
					jctx.clicknode(node);
					$('#mm').menu('show', { left: e.pageX, top: e.pageY });
				}
			});
			
			$("#search").textbox({
				iconCls:'icon-search',
				onChange : function(n, o){
					jctx.search(n);
				}
			});
			$("#search").textbox('addClearBtn');
		});
		
		jctx = (function(win, $) {
			
			var isfirst = true;
			var searchvalue = "";
			var actions = {
					addcompany : false,
					adddepart : false,
					addposition : false,
				};

			function _clickNode(node) {

				selectNode = node;

				_findItems(node.id);
			}
			
			function _search(value){
				
				searchvalue = value;
				_findItems(selectNode.id);
			}
			
			function _findItems(nodeId) {

				var id = nodeId;
				if(nodeId == "ROOT"){
					id = "";
				}
				
				if(isfirst) {
					
					$('#tg').datagrid({
						url : "${root}/org/find.json",
						queryParams : { "id" : id }
					});
				} else {
	
					$('#tg').datagrid('load', { "id" : id, "keyword" : searchvalue });
					$('#tg').datagrid('unselectAll');
				}
				
				isfirst = false;
			}
			
			return {
				clicknode : _clickNode,
				search : _search
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
			<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" singleSelect="true"
				pageSize="10" idField="orgId">
				<e:columns>
					<e:column field="ck" checkbox="true" />
					<e:column field="code" title="组织代码" width="100" />
					<e:column field="displayName" title="组织名称" width="180" />
					<e:column field="orgTypeName" title="组织类型" align="center" width="80" />
					<e:column field="serial" title="排序码" align="center" width="80" />
				</e:columns>
			</e:datagrid>
			<div id="tb">
				<e:linkbutton id="tbaddcompany" disabled="true" iconCls="icon-company" plain="true" text="添加公司" />
				<e:linkbutton id="tbadddepart" disabled="true" iconCls="icon-depart" plain="true" text="添加部门" />
				<e:linkbutton id="tbaddposition" disabled="true" iconCls="icon-position" plain="true" text="添加岗位" />
				<div style="float:right;">
				<e:textbox id="search" prompt="搜索：代码、名称" style="width:250px;height:24px;" />
				</div>
			</div>
			<div id="mm" class="easyui-menu" style="width: 120px;">
				<div id="mmaddcompany" onclick="jctx.addcompany()" data-options="iconCls:'icon-company'">添加公司</div>
				<div id="mmadddepart" onclick="jctx.adddepart()" data-options="iconCls:'icon-depart'">添加部门</div>
				<div id="mmaddposition" onclick="jctx.addposition()" data-options="iconCls:'icon-position'">添加岗位</div>
				<div id="mmremove" onclick="jctx.remove()" data-options="iconCls:'icon-remove'">删除</div>
				<div class="menu-sep"></div>
				<div onclick="expand()">展开</div>
				<div onclick="collapse()">收缩</div>
			</div>
		</e:layoutunit>
	</e:layout>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>