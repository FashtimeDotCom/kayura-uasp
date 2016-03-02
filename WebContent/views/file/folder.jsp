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
			<input type="hidden" name="id" value="${model.folderId}" />
			<input type="hidden" name="parentId" value="${model.parentId}" />
			<table cellpadding="5">
				<tr>
					<td>上级文件夹:</td>
					<td>${model.parentName}</td>
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