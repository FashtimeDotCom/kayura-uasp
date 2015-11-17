<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">EasyUI DataGrid</e:section>

<e:section name="body">
	<e:layoutunit region="west" split="true" border="false" style="width: 160px;">
		<ul>
			<li><a href="${root}/example/easyui/datagridbasic" target="iframe1">Basic</a></li>
			<li><a href="${root}/example/easyui/datagridcacheeditor" target="iframe1">Cache Editor</a></li>
			<li><a href="${root}/example/easyui/datagridcellediting" target="iframe1">Cell Editing</a></li>
			<li><a href="${root}/example/easyui/datagridcellstyle" target="iframe1">Cell style</a></li>
			<li><a href="${root}/example/easyui/datagridcheckbox" target="iframe1">CheckBox</a></li>
			<li><a href="${root}/example/easyui/datagridcolumngroup" target="iframe1">Column Group</a></li>
			<li><a href="${root}/example/easyui/datagridcomplextoolbar" target="iframe1">Complex Toolbar</a></li>
		</ul>
	</e:layoutunit>
	<e:layoutunit region="center" border="false" style="overflow:hidden;" >
		<iframe id="iframe1" name="iframe1" scrolling="yes" frameborder="0" src="" style="width:100%;height:100%;"></iframe>
	</e:layoutunit>
</e:section>

<%@ include file="/views/shared/_simple.jsp"%>