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
		Date From: <e:datebox id="fromDate" style="110px" required="true" tipPosition="top"></e:datebox>
		To: <e:datebox id="toDate" style="110px"></e:datebox>
		Language: <e:combobox id="" panelHeight="auto" style="width:100px">
			<e:selectitem label="java" value="java"/>
			<e:selectitem label="C" value="c"/>
			<e:selectitem label="Basic" value="basic"/>
			<e:selectitem label="Perl" value="perl"/>
			<e:selectitem label="Python" value="python"/>
		</e:combobox>
		<e:linkbutton iconCls="icon-search">Search</e:linkbutton>
	</div>
	
	<div id="ft" style="padding:2px 5px;">
		<e:linkbutton iconCls="icon-add" plain="true"></e:linkbutton>
		<e:linkbutton iconCls="icon-edit" plain="true"></e:linkbutton>
		<e:linkbutton iconCls="icon-save" plain="true"></e:linkbutton>
		<e:linkbutton iconCls="icon-cut" plain="true"></e:linkbutton>
		<e:linkbutton iconCls="icon-remove" plain="true"></e:linkbutton>
	</div>

</e:section>

<e:section name="code">
<pre><code class="html">&lt;e:datagrid title="DataGrid Complex Toolbar" style="width:700px;height:250px" collapsible="true"
	singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get"
	toolbar="#tb" footer="#ft"&gt;
	&lt;e:columns&gt;
		&lt;e:column field="itemid" width="80"&gt;Item ID&lt;/e:column&gt;
		&lt;e:column field="productid" width="100"&gt;Product&lt;/e:column&gt;
		&lt;e:column field="listprice" width="80" align="right"&gt;List Price&lt;/e:column&gt;
		&lt;e:column field="unitcost" width="80" align="right"&gt;Unit Cost&lt;/e:column&gt;
		&lt;e:column field="attr1" width="240"&gt;Attribute&lt;/e:column&gt;
		&lt;e:column field="status" width="60" align="center"&gt;Status&lt;/e:column&gt;
	&lt;/e:columns&gt;
&lt;/e:datagrid&gt;

&lt;div id="tb" style="padding:2px 5px;"&gt;
	Date From: &lt;e:datebox id="fromDate" style="110px" required="true" tipPosition="top"&gt;&lt;/e:datebox&gt;
	To: &lt;e:datebox id="toDate" style="110px"&gt;&lt;/e:datebox&gt;
	Language: &lt;e:combobox id="" panelHeight="auto" style="width:100px"&gt;
		&lt;e:selectitem label="java" value="java"/&gt;
		&lt;e:selectitem label="C" value="c"/&gt;
		&lt;e:selectitem label="Basic" value="basic"/&gt;
		&lt;e:selectitem label="Perl" value="perl"/&gt;
		&lt;e:selectitem label="Python" value="python"/&gt;
	&lt;/e:combobox&gt;
	&lt;e:linkbutton iconCls="icon-search"&gt;Search&lt;/e:linkbutton&gt;
&lt;/div&gt;

&lt;div id="ft" style="padding:2px 5px;"&gt;
	&lt;e:linkbutton iconCls="icon-add" plain="true"&gt;&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-edit" plain="true"&gt;&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-save" plain="true"&gt;&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-cut" plain="true"&gt;&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-remove" plain="true"&gt;&lt;/e:linkbutton&gt;
&lt;/div&gt;
</code></pre>
</e:section>

<%@ include file="../../shared/_content.jsp" %>