<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">部门资料</k:section>

<k:section name="head">
	<script type="text/javascript">
	function submitForm() {
		$('#ff').form('submit', {
			url : '${root}/org/depart/save.json',
			onlySuccess : function(r) {
				var t = $("#name").textbox("getValue");
				juasp.closeWin({result: 1, 'id': r.data.id, text: t});
			}
		});
	}
	</script>
</k:section>

<!-- 编辑内容区域 body -->
<k:section name="body">
	<k:form id="ff">
		<k:hidden id="departmentId" value="${model.departmentId}"/>
		<k:hidden id="companyId" value="${model.companyId}"/>
		<k:hidden id="parentId" value="${model.parentId}"/>
		<table cellpadding="5">
			<c:if test="${model.companyName != null}">
			<tr>
				<td>所属公司:</td>
				<td>${model.companyName}</td>
			</tr>
			</c:if>
			<c:if test="${model.parentName != null}">
			<tr>
				<td>上级部门:</td>
				<td>${model.parentName}</td>
			</tr>
			</c:if>
			<tr>
				<td>部门代码:</td>
				<td><k:textbox id="code" value="${model.code}" style="width:180px;" validType="length[1,32]" required="true" /></td>
			</tr>
			<tr>
				<td>部门名称:</td>
				<td><k:textbox id="name" value="${model.name}" style="width:180px;" validType="length[1,32]" required="true" /></td>
			</tr>
			<tr>
				<td>部门描述:</td>
				<td><k:textbox id="description" value="${model.description}" multiline="true" style="height:50px;width:250px;" validType="length[1,512]" /></td>
			</tr>
			<tr>
				<td>排序值:</td>
				<td><k:numberbox id="serial" min="0" precision="0" value="${model.serial}"></k:numberbox></td>
			</tr>
			<tr>
				<td>是否启用:</td>
				<td>
					<k:combobox id="status" value="${model.status}" panelHeight="50px">
						<k:option label="启用" value="1" />
						<k:option label="禁用" value="0" />
					</k:combobox>
				</td>
			</tr>
		</table>
	</k:form>
</k:section>

<!-- 工具栏区域 tool -->
<k:section name="tool">
	<k:linkbutton style="width:80px" iconCls="icon-ok" onClick="submitForm()" text="提交" />
	<k:linkbutton style="width:80px" iconCls="icon-cancel" onClick="juasp.closeWin(0)" text="取消" />
</k:section>

<%@ include file="/views/shared/_dialog.jsp"%>