<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">Basic DataGrid</e:section>

<e:section name="body">
	<h2>DataGrid Complex Toolbar</h2>
	<p>The DataGrid toolbar can be defined from a &lt;div&gt; markup, so you can define the layout of toolbar easily.</p>
	<div style="margin:20px 0;"></div>
	
	<e:datagrid title="DataGrid Complex Toolbar" style="width:700px;height:250px" collapsible="true"
		singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get"
		toolbar="#tb" footer="#ft">
		<e:columns>
			<e:column field="itemid" width="80">Item ID</e:column>
			<e:column field="productid" width="100">Product</e:column>
			<e:column field="listprice" width="80" align="right">List Price</e:column>
			<e:column field="unitcost" width="80" align="right">Unit Cost</e:column>
			<e:column field="attr1" width="240">Attribute</e:column>
			<e:column field="status" width="60" align="center">Status</e:column>
		</e:columns>
	</e:datagrid>
	
	<div id="tb" style="padding:2px 5px;">
		Date From: <e:dateBox id="fromDate" style="110px" required="true" tipPosition="top"></e:dateBox>
		To: <e:dateBox id="toDate" style="110px"></e:dateBox>
		Language: <e:comboBox id=""></e:comboBox>
		<select class="easyui-combobox" panelHeight="auto" style="width:100px">
			<option value="java">Java</option>
			<option value="c">C</option>
			<option value="basic">Basic</option>
			<option value="perl">Perl</option>
			<option value="python">Python</option>
		</select>
		<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
	
	<div id="ft" style="padding:2px 5px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true"></a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"></a>
	</div>

</e:section>

<e:section name="code">&lt;e:datagrid title="Basic DataGrid" style="width:700px;height:200px" collapsible="true"
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
</e:section>

<%@ include file="../../shared/_content.jsp" %>