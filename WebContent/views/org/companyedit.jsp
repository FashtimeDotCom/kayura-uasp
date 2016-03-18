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
	<e:panel id="p1" border="false" style="padding: 10px 40px 10px 40px;">
		<e:form id="ff">
			<input type="hidden" name="id" value="${model.id}" />
			<table cellpadding="5">
				<tr>
					<td>公司代码:</td>
					<td>${model.code}</td>
				</tr>
				<tr>
					<td>公司名称:</td>
					<td><e:textbox id="name" value="${model.name}" validType="length[1,32]" required="true" /></td>
				</tr>
				<tr>
					<td>排序值:</td>
					<td><e:number id="serial" min="0" precision="0" value="${model.serial}"></e:number> </td>
				</tr>
			</table>
		</e:form>
		<div style="text-align: center; padding: 5px">
			<e:linkbutton style="width: 75px" onclick="submitForm()"></e:linkbutton>
			<e:linkbutton style="width: 75px" onclick="clearForm()" ></e:linkbutton>
		</div>
	</e:panel>
</e:section>

<%@ include file="/views/shared/_editor.jsp"%>