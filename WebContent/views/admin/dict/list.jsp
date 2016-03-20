<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">数据词典管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		var dictId = "", id = "";
		
		$(function() {
			
			$('#tv').tree({
				url : "${root}/admin/dict/define.json",
				onClick: function(node){
					var id = node.id;
					findItems(node.id);
					if(id == 'ROOT' || id == 'CATEGORY') {
						$('#add').linkbutton('disable');
						$('#edit').linkbutton('disable');
						$('#delete').linkbutton('disable');
					} else {
						$('#add').linkbutton('enable');
						$('#edit').linkbutton('enable');
						$('#delete').linkbutton('enable');
					}
				}
			});
		});

		function findItems(id) {
			
			if(dictId == "") {
				
				$('#tg').datagrid({
					url: "${root}/admin/dict/load.json",
					queryParams: {
						"dictId": id
					},
					onClickRow : function(idx, row){
						<c:if test="${ISROOT==false}">
						if(row.isFixed){
							$('#delete').linkbutton('disable');
							$('#edit').linkbutton('disable');
						}else {
							$('#delete').linkbutton('enable');
							$('#edit').linkbutton('enable');
						}
						</c:if>
					},
					onDblClickRow : function(idx, row){
						editDict(row);
					}
				});
			} else {

				$('#tg').datagrid('unselectAll');
				$('#tg').datagrid('load', { "dictId": id });
			}
			
			dictId = id;
		}
		
		function newDict(){
			juasp.openWin({
				url: "${root}/admin/dict/new?id=" + dictId,
				width: "450px",
				height: "300px",
				title: "创建词典项",
				onClose : function(result){
					if(result == 1){
						findItems(dictId);
					}
				}
			});
		}
		
		function editDict(row){
			
			if(row == null){
				row = $("#tg").datagrid("getSelected");
			}
			
			if(row != null) {
				<c:if test="${ISROOT==false}">
				if(!row.isFixed){
				</c:if>
					juasp.openWin({
						url: "${root}/admin/dict/edit?id=" + row.id,
						width: "500px",
						height: "300px",
						title: "修改词典项",
						onClose : function(result){
							if(result == 1){
								findItems(dictId);
							}
						}
					});
				<c:if test="${ISROOT==false}">
				}
				</c:if>
			} else {
				juasp.warntips("请在表格中点击要<b>编辑</b>的记录。");
			}
		}
		
		function delDict() {

			var row = $("#tg").datagrid("getSelected");
			
			if(row != null) {
				<c:if test="${ISROOT==false}">
				if(!row.isFixed){
				</c:if>
					juasp.confirm("是否删除名称为【 " + row.name + " 】的词典项。", function(r) {
						if(r == true) {
							juasp.post('${root}/admin/dict/del.json', { id : row.id},
									{ success: function(r){
										var idx = $("#tg").datagrid("getRowIndex", row);
										$("#tg").datagrid('deleteRow', idx);
									}
							});
						}
					});
				<c:if test="${ISROOT==false}">
				}
				</c:if>
			} else {
				juasp.warntips("请在表格中点击要<b>删除</b>的记录。");
			}
		}
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 2px;">
	<e:layout id="ctx" fit="true">
		<e:layoutunit region="west" split="true" border="true" style="padding: 10px; width: 160px;">
			<ul id="tv" class="easyui-tree"></ul>
		</e:layoutunit>
		<e:layoutunit region="center" border="false" >
			<e:datagrid id="tg" fit="true" rownumbers="true" toolbar="#tb" pagination="true" 
				pageSize="10" singleSelect="true" striped="true" idField="itemId" >
				<e:columns>
					<e:column field="name" title="词典名" width="200" />
					<e:column field="value" title="词典值" width="150" />
					<e:column field="serial" title="排序值" width="150" />
					<e:column field="isFixedName" width="80" title="保留数据" />
				</e:columns>
			</e:datagrid>
			<div id="tb">
				<e:linkbutton id="add" iconCls="icon-add" disabled="true" plain="true" text="新增" onclick="newDict()" />
				<e:linkbutton id="edit" iconCls="icon-edit" disabled="true" plain="true" text="编辑" onclick="editDict()" />
				<e:linkbutton id="delete" iconCls="icon-remove" disabled="true" plain="true" text="删除" onclick="delDict()" />
			</div>
		</e:layoutunit>
	</e:layout>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>