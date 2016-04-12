<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">公司资料</k:section>

<k:section name="head">
	<script type="text/javascript">
	</script>
</k:section>

<!-- 编辑内容区域 body -->
<k:section name="body">
	<k:form id="ff" url="${root}/org/save.json" success="juasp.closeWin(1)">
		<k:hidden id="companyId" value="${model.companyId}"/>
		<table cellpadding="5">
			<tr>
				<td>上级公司:</td>
				<td>${model.parentName}</td>
			</tr>
			<tr>
				<td>公司代码:</td>
				<td><k:textbox id="code" value="${model.code}" style="width:180px;" validType="length[1,32]" required="true" /></td>
			</tr>
			<tr>
				<td>公司简称:</td>
				<td><k:textbox id="shortName" value="${model.shortName}" style="width:180px;" validType="length[1,32]" required="true" /></td>
			</tr>
			<tr>
				<td>公司全称:</td>
				<td><k:textbox id="fullName" value="${model.fullName}" style="width:250px;" validType="length[1,64]" required="true" /></td>
			</tr>
			<tr>
				<td>公司描述:</td>
				<td><k:textbox id="description" value="${model.description}" multiline="true" style="height:50px;width:250px;" validType="length[1,512]" /></td>
			</tr>
			<tr>
				<td>行业类型:</td>
				<td><k:textbox id="tndustryId" value="${model.industryTypeId}" validType="length[1,512]" /></td>
			</tr>
			<tr>
				<td>排序值:</td>
				<td><k:numberbox id="serial" min="0" precision="0" value="${model.serial}"></k:numberbox></td>
			</tr>
		</table>
	</k:form>
</k:section>

<!-- 工具栏区域 tool -->
<k:section name="tool">
	<k:linkbutton style="width:80px" iconCls="icon-ok" onClick="$('#ff').form('submit')" text="提交" />
	<k:linkbutton style="width:80px" iconCls="icon-cancel" onClick="juasp.closeWin(0)" text="取消" />
</k:section>

<%@ include file="/views/shared/_dialog.jsp"%>