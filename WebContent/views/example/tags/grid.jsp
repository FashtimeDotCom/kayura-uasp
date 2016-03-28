<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">Panel 标签</k:section>

<k:section name="head">
</k:section>

<k:section name="body">
	<k:datagrid id="tg" title="表格" idField="id" fit="true"
		queryParams="{name:'fdf'}"
		url="${root}/example/general/order/find.json" remoteSort="true"
		customHeader="true">
		<k:columns>
			<k:column field="ck" checkbox="true" rowspan="2" />
			<k:column field="orderDate" sortable="true" rowspan="2">Order Date</k:column>
			<k:column field="shipViaName" sortable="true" rowspan="2">ShipVia</k:column>
			<k:column colspan="2" />
		</k:columns>
		<k:columns>
			<k:column field="shipName" sortable="true">Ship Name</k:column>
			<k:column field="shipAddress" sortable="true">Address</k:column>
		</k:columns>
	</k:datagrid>
</k:section>

<%@ include file="shared/_simple.jsp"%>