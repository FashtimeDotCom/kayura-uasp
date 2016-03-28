<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">数据词典管理</k:section>

<k:section name="head">

</k:section>

<k:section name="body">
	<div class="easyui-panel" border="false" style="padding: 10px 40px 10px 40px; ">
		<k:form id="ff" url="${root}/admin/dict/save.json" ajax="true" success="function(data){ juasp.closeWin(1) }">
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
					<td><input class="easyui-textbox textbox" name="name" style="width:180px" value="${model.name}" data-options="required:true,validType:'length[1,32]'"></input></td>
				</tr>
				<tr>
					<td>词典值:</td>
					<td><input class="easyui-textbox textbox" name="value" style="width:180px" value="${model.value}" data-options="required:true,validType:'length[1,1024]'"></input></td>
				</tr>
				<tr>
					<td>排序值:</td>
					<td><input class="easyui-numberbox textbox" name="serial" style="width:180px" value="${model.serial}" data-options="required:true,min:0,precision:0"></input></td>
				</tr>
			</table>
		</k:form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px" onclick="$('#ff').form('submit')">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px" onclick="juasp.closeWin(0)">取消</a>
		</div>
	</div>
</k:section>

<%@ include file="/views/shared/_editor.jsp"%>