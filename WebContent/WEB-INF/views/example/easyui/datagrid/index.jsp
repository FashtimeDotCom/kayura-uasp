<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">EasyUI DataGrid</e:section>

<e:section name="head">
	<script type="text/javascript">
	
	</script>
</e:section>

<e:section name="body">
	<e:tabs id="tt" fit="true" border="false" plain="true" tabPosition="left">
		<e:tab title="Base" style="overflow:hidden;" iframe="true" href="${root}/example/easyui/datagridbasic"></e:tab>
		<e:tab title="Cache Editor" style="overflow:hidden;" iframe="true" href="${root}/example/easyui/datagridcacheeditor"></e:tab>
		<e:tab title="Cell Editing"></e:tab>
		<e:tab title="Cell style"></e:tab>
	</e:tabs>
</e:section>

<%@ include file="../../shared/_simple.jsp"%>