<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">公司资料</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		function submitForm() {
			
			$('#ff').form('submit', {
				url : '${root}/org/save.json',
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
	<e:panel id="p1" border="false" style="padding: 10px 30px 10px 30px;">
		<e:form id="ff">
			<input type="hidden" name="companyId" value="${model.companyId}" />
			<table cellpadding="5">
				<tr>
					<td>上级公司:</td>
					<td>${model.parentName}</td>
				</tr>
				<tr>
					<td>公司代码:</td>
					<td>${model.code}</td>
				</tr>
				<tr>
					<td>公司简称:</td>
					<td><e:textbox id="shortName" value="${model.shortName}" style="width:180px;" validType="length[1,32]" required="true" /></td>
				</tr>
				<tr>
					<td>公司全称:</td>
					<td><e:textbox id="fullName" value="${model.fullName}" style="width:250px;" validType="length[1,64]" required="true" /></td>
				</tr>
				<tr>
					<td>公司描述:</td>
					<td><e:textbox id="Description" value="${model.Description}" multiline="true" style="width:250px;" validType="length[1,512]" /></td>
				</tr>
				<tr>
					<td>行业类型:</td>
					<td><e:textbox id="tndustryId" value="${model.tndustryId}" validType="length[1,512]" /></td>
				</tr>
				
				
				<tr>
					<td>排序值:</td>
					<td><e:number id="serial" min="0" precision="0" value="${model.serial}"></e:number> </td>
				</tr>
			</table>
		</e:form>
		<div style="text-align: center;  padding: 5px">
			<e:linkbutton style="width: 75px; margin:10px;" onclick="submitForm()" text="提交" />
			<e:linkbutton style="width: 75px; margin:10px;" onclick="clearForm()" text="取消" />
		</div>
	</e:panel>
</e:section>

<%@ include file="/views/shared/_editor.jsp"%>