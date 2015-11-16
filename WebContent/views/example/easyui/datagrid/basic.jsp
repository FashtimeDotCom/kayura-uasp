<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">Basic DataGrid</e:section>

<e:section name="body">
	<h2>Basic DataGrid</h2>
	<p>The DataGrid is created from markup, no JavaScript code needed.</p>
	<div style="margin:20px 0;"></div>
	<e:datagrid title="Basic DataGrid" style="width:700px;height:auto" collapsible="true"
		singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get">
		<e:columns>
			<e:column field="itemid" width="80">Item ID</e:column>
			<e:column field="productid" width="100">Product</e:column>
			<e:column field="listprice" width="80" align="right">List Price</e:column>
			<e:column field="unitcost" width="80" align="right">Unit Cost</e:column>
			<e:column field="attr1" width="250">Attribute</e:column>
			<e:column field="status" width="60" align="center">Status</e:column>
		</e:columns>
	</e:datagrid>
</e:section>

<e:section name="code">
<pre><code class="html">&lt;e:datagrid title="Basic DataGrid" style="width:700px;height:200px" collapsible="true"
	singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get"&gt;
	&lt;e:columns&gt;
		&lt;e:column field="itemid" width="80"&gt;Item ID&lt;/e:column&gt;
		&lt;e:column field="productid" width="100"&gt;Product&lt;/e:column&gt;
		&lt;e:column field="listprice" width="80" align="right"&gt;List Price&lt;/e:column&gt;
		&lt;e:column field="unitcost" width="80" align="right"&gt;Unit Cost&lt;/e:column&gt;
		&lt;e:column field="attr1" width="250"&gt;Attribute&lt;/e:column&gt;
		&lt;e:column field="status" width="60" align="center"&gt;Status&lt;/e:column&gt;
	&lt;/e:columns&gt;
&lt;/e:datagrid&gt;
</code></pre>
</e:section>

<%@ include file="../../shared/_content.jsp" %>