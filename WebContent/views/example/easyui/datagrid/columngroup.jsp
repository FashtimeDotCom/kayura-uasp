<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">Basic DataGrid</e:section>

<e:section name="body">

	<h2>Column Group</h2>
	<p>The header cells can be merged. Useful to group columns under a category.</p>
	<div style="margin:20px 0;"></div>

	<e:datagrid title="Column Group" style="width:700px;height:250px"
		collapsible="true" singleSelect="true" rownumbers="true"
		url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get">
		<thead>
			<tr>
				<e:column field="itemid" width="80" rowspan="2">Item ID</e:column>
				<e:column field="productid" width="100" rowspan="2">Product</e:column>
				<th colspan="4">Item Details</th>
			</tr>
			<tr>
				<e:column field="listprice" width="80" align="right">List Price</e:column>
				<e:column field="unitcost" width="80" align="right">Unit Cost</e:column>
				<e:column field="attr1" width="240">Attribute</e:column>
				<e:column field="status" width="60" align="center">Status</e:column>
			</tr>
		</thead>
	</e:datagrid>
</e:section>

<e:section name="code">
<pre><code class="html">&lt;e:datagrid title="Column Group" style="width:700px;height:250px"
	collapsible="true" singleSelect="true" rownumbers="true"
	url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get"&gt;
	&lt;thead&gt;
		&lt;tr&gt;
			&lt;e:column field="itemid" width="80" rowspan="2"&gt;Item ID&lt;/e:column&gt;
			&lt;e:column field="productid" width="100" rowspan="2"&gt;Product&lt;/e:column&gt;
			&lt;th colspan="4"&gt;Item Details&lt;/th&gt;
		&lt;/tr&gt;
		&lt;tr&gt;
			&lt;e:column field="listprice" width="80" align="right"&gt;List Price&lt;/e:column&gt;
			&lt;e:column field="unitcost" width="80" align="right"&gt;Unit Cost&lt;/e:column&gt;
			&lt;e:column field="attr1" width="240"&gt;Attribute&lt;/e:column&gt;
			&lt;e:column field="status" width="60" align="center"&gt;Status&lt;/e:column&gt;
		&lt;/tr&gt;
	&lt;/thead&gt;
&lt;/e:datagrid&gt;
</code></pre>
</e:section>

<%@ include file="/views/shared/_example.jsp" %>