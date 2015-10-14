<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">Basic DataGrid</e:section>

<e:section name="body">
	<h2>DataGrid Cell Style</h2>
	<p>The cells which listprice value is less than 30 are highlighted.</p>
	<div style="margin:20px 0;"></div>
	
<e:datagrid title="DataGrid Cell Style" style="width:700px;height:auto" collapsible="true"
	singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get">
	<e:columns>
		<e:column field="itemid" width="80">Item ID</e:column>
		<e:column field="productid" width="100">Product</e:column>
		<e:column field="listprice" width="80" align="right" styler="cellStyler">List Price</e:column>
		<e:column field="unitcost" width="80" align="right">Unit Cost</e:column>
		<e:column field="attr1" width="250">Attribute</e:column>
		<e:column field="status" width="60" align="center">Status</e:column>
	</e:columns>
</e:datagrid>
</e:section>

<e:section name="code">&lt;e:datagrid title="DataGrid Cell Style" style="width:700px;height:auto" collapsible="true"
	singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get"&gt;
	&lt;e:columns&gt;
		&lt;e:column field="itemid" width="80"&gt;Item ID&lt;/e:column&gt;
		&lt;e:column field="productid" width="100"&gt;Product&lt;/e:column&gt;
		&lt;e:column field="listprice" width="80" align="right" styler="cellStyler"&gt;List Price&lt;/e:column&gt;
		&lt;e:column field="unitcost" width="80" align="right"&gt;Unit Cost&lt;/e:column&gt;
		&lt;e:column field="attr1" width="250"&gt;Attribute&lt;/e:column&gt;
		&lt;e:column field="status" width="60" align="center"&gt;Status&lt;/e:column&gt;
	&lt;/e:columns&gt;
&lt;/e:datagrid&gt;

&lt;script type="text/javascript"&gt;
	function cellStyler(value,row,index){
		if (value &lt; 30){
			return 'background-color:#ffee00;color:red;';
		}
	}
&lt;/script&gt;
</e:section>

<e:section name="footer">
<script type="text/javascript">
	function cellStyler(value,row,index){
		if (value < 30){
			return 'background-color:#ffee00;color:red;';
		}
	}
</script>
</e:section>

<%@ include file="../../shared/_content.jsp" %>