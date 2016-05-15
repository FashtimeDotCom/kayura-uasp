<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">任务列表</k:section>
<k:section name="head">
</k:section>

<k:section name="body">
	<table class="easyui-datagrid" style="width: 650px;"
		method="GET" fit="true" border="false" singleSelect="true"
		idField="itemid" fitColumns="true"
		url="${root}/res/easyui/jsondata/datagrid_data.json">
		<thead>
			<tr>
				<th field="itemid" width="60">Item ID</th>
				<th field="productid" width="60">Product ID</th>
				<th field="listprice" width="80" align="right">List Price</th>
				<th field="unitcost" width="80" align="right">Unit Cost</th>
				<th field="attr1" width="120">Attribute</th>
				<th field="status" width="50" align="center">Status</th>
			</tr>
		</thead>
	</table>
</k:section>

<%@ include file="/shared/_simple.jsp"%>