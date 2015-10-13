<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Basic DataGrid - jQuery EasyUI Demo</title>
	<e:resources location="res" theme="${theme}" />
	<link rel="stylesheet" href="${root}/res/highlight/styles/default.css">
	<script src="${root}/res/highlight/highlight.pack.js"></script>
	<script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
	<p>The DataGrid is created from markup, no JavaScript code needed.</p>
	<div style="margin: 20px 0;"></div>

	<e:datagrid title="Basic DataGrid" style="width:700px;height:200px" collapsible="true"
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

	<pre><code class="html">
	
	</code></pre>

	<div style="margin-top: 10px">
		Source : <%= request.getRequestURI() %>
	</div>
</body>
</html>