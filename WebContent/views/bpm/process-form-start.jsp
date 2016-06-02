<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">启动流程表单</k:section>
<k:section name="head">
</k:section>

<k:section name="body">
	<k:form id="ff" url="${root}/bpm/process/start.json" success="function(data){ juasp.closeWin(1) }">
		<k:hidden id="id" value="${model.processDefinition.id}" />
		<table cellpadding="5">
		<c:forEach items="${model.formProperties}" var="fp">
			<tr>
				<td>${fp.name}:</td>
				<td>
				<c:if test="${fp.type.name == 'string' }">
					<input class="easyui-textbox" name="${fp.id}" style="width:200px" value="${fp.value}" />
				</c:if>
				<c:if test="${fp.type.name == 'date' }">
					<input class="easyui-datebox" name="${fp.id}" style="width:200px" value="${fp.value}" />
				</c:if>
				</td>
			</tr>
		</c:forEach>
		</table>
	</k:form>
</k:section>

<k:section name="tool">
	<k:linkbutton style="width:80px" iconCls="icon-ok" onClick="$('#ff').form('submit')" text="启动流程" /><span />
	<k:linkbutton style="width:80px" iconCls="icon-cancel" onClick="juasp.closeWin(0)" text="取消" />
</k:section>

<%@ include file="/views/shared/_dialog.jsp"%>