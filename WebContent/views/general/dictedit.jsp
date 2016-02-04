<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">数据词典管理</e:section>

<e:section name="head">
	<script type="text/javascript">
		function submitForm() {
			$('#ff').form('submit', {
				url : '${root}/gm/dict/save.json',
				onSubmit : function() {
					
				},
				success : function(data) {
					alert(data)
				}
			});
		}
		
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</e:section>

<e:section name="body">
	<div class="easyui-panel" border="false" style="padding: 10px 40px 10px 40px; ">
		<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
			<input type="hidden" name="id" value="${model.id}" />
			<input type="hidden" name="dictId" value="${model.dictId}" />
			<input type="hidden" name="parentId" value="${model.parentId}" />
			<table cellpadding="5">
				<tr>
					<td>所属词典:</td>
					<td>${model.dictName}</td>
				</tr>
				<c:if test="${treeType}">
				<tr>
					<td>上级词典:</td>
					<td>${model.parentName}</td>
				</tr>
				</c:if>
				<tr>
					<td>词典名称:</td>
					<td><input class="easyui-textbox" type="text" name="name" style="width:250px" value="${model.name}" data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>词典值:</td>
					<td><input class="easyui-textbox" type="text" name="value" style="width:250px" value="${model.value}" data-options="required:true"></input></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px" onclick="submitForm()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px" onclick="clearForm()">取消</a>
		</div>
	</div>
</e:section>

<%@ include file="/views/shared/_editor.jsp"%>