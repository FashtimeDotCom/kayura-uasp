<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">文件管理</k:section>

<k:section name="head">
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
					remove : false
				};
			
			function _initActions(){
				
				actions.addcompany = false;
				actions.adddepart = false;
				actions.addposition = false;
				actions.remove = false;
			}
			
			function _applyActions(_actions){

				$('#mm').menu((_actions.addcompany?'showItem':'hideItem'), $("#mmaddcompany"));
				$('#mm').menu((_actions.adddepart?'showItem':'hideItem'), $("#mmadddepart"));
				$('#mm').menu((_actions.addposition?'showItem':'hideItem'), $("#mmaddposition"));
				$('#mm').menu((_actions.remove?'showItem':'hideItem'), $("#mmremove"));

				$("#tbaddcompany").linkbutton(_actions.addcompany?'enable':'disable');
				$("#tbadddepart").linkbutton(_actions.adddepart?'enable':'disable');
				$("#tbaddposition").linkbutton(_actions.addposition?'enable':'disable');
			}

			function _clickNode(node) {

				selectNode = node;
				_initActions();

				// 0 根, 1 公司, 2 部门, 3 岗位;
				var type = node.attributes['type'];
				if (type == 0) {
					actions.addcompany = true;
				} else if (type == 1) {
					actions.addcompany = true;
					actions.adddepart = true;
				} else if (type == 2) {
					actions.adddepart = true;
					actions.addposition = true;
				}

				if (type != 0 && node.children.length == 0) {
					actions.remove = true;
				}

				_applyActions(actions);
				_findItems(node.id);
			}

			function _search(value) {

				searchvalue = value;
				_findItems(selectNode.id);
			}

			function _findItems(nodeId) {

				var id = nodeId;
				if (nodeId == "ROOT") {
					id = "";
				}

				if (isfirst) {

					$('#tg').datagrid({
						url : "${root}/org/find.json",
						queryParams : {
							"id" : id
						}
					});
				} else {

					$('#tg').datagrid('load', {
						"id" : id,
						"keyword" : searchvalue
					});
					$('#tg').datagrid('unselectAll');
				}

				isfirst = false;
			}

			function _addcompany(){
				
				var openUrl = "${root}/org/company/new?pid=" + selectNode.id + "&pname=" + selectNode.text;
				juasp.openWin({
					url : openUrl,
					width : "450px",
					height : "500px",
					title : "创建公司",
					onClose : function(r) {
						if (r.result == 1) {
							
						}
					}
				});
			}
			
			return {
				clicknode : _clickNode,
				search : _search,
				
				addcompany : _addcompany
			}

		}(window, jQuery));
	</script>
</k:section>

<k:section name="body">
	<k:dock region="center" border="false" style="padding: 2px;">
	<k:layout id="ctx" fit="true">
		<k:dock region="west" split="true" border="true" style="padding: 10px; width: 200px;">
			<k:tree id="tv" />
		</k:dock>
		<k:dock region="center" border="false">
			<k:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" singleSelect="true"
				pageSize="10" idField="orgId">
				<k:column field="ck" checkbox="true" />
				<k:column field="code" title="组织代码" width="100" />
				<k:column field="displayName" title="组织名称" width="180" />
				<k:column field="orgTypeName" title="组织类型" align="center" width="80" />
				<k:column field="serial" title="排序码" align="center" width="80" />
			</k:datagrid>
			<div id="tb">
				<k:linkbutton id="tbaddcompany" onClick="jctx.addcompany()" disabled="true" iconCls="icon-company" plain="true" text="添加公司" />
				<k:linkbutton id="tbadddepart" onClick="jctx.adddepart()" disabled="true" iconCls="icon-depart" plain="true" text="添加部门" />
				<k:linkbutton id="tbaddposition" onClick="jctx.addposition()" disabled="true" iconCls="icon-position" plain="true" text="添加岗位" />
				<div style="float:right;">
				<k:textbox id="search" prompt="搜索：代码、名称" style="width:250px;height:24px;" />
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
		</k:dock>
	</k:layout>
	</k:dock>
</k:section>

<%@ include file="/views/shared/_simple.jsp"%>