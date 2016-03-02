<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件夹</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		function submitForm() {
			
			$('#ff').form('submit', {
				url : '${root}/file/folder/save.json',
				onSubmit : function() {
					return $('#ff').form('validate');
				},
				success : function(data) {
					juasp.closeWin(1);
				}
			});
		}
		
		function clearForm() {
			juasp.closeWin(0);
		}
	</script>
</e:section>

<e:section name="body">
	<div class="easyui-panel" border="false" style="padding: 10px 40px 10px 40px; ">
		<form id="ff" class="easyui-form" method="post">
			<input type="hidden" name="folderId" value="${model.folderId}" />
			<input type="hidden" name="parentId" value="${model.parentId}" />
			<input type="hidden" name="parentId" value="${model.groupId}" />
			<table cellpadding="5">
				<tr>
					<td>上级文件夹:</td>
					<c:if test="${model.parentId != null }">
					<td>${model.parentName}</td>
					</c:if>
					<c:if test="${model.groupId != null }">
					<td>${model.groupName}</td>
					</c:if>
				</tr>
				<tr>
					<td>文件夹名称:</td>
					<td><input class="easyui-textbox" name="name" style="width:180px" value="${model.name}" data-options="required:true,validType:'length[1,32]'"></input></td>
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