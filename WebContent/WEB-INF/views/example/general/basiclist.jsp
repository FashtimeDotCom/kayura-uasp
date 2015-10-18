<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">基本列表</e:section>

<e:section name="body">

	<p>包括一个可分页列表，支持排序/条件查询，操作按钮，列表链接。</p>
	<div style="margin:20px 0;"></div>
	
	<e:datagrid title="管理列表" style="width:100%;height:auto;" collapsible="true" pagination="true" pageSize="10" 
		singleSelect="true" url="${root}/example/general/order/find.json" method="get" idField="id"
		toolbar="#t1,#q1">
		<e:columns>
			<e:column field="ck" checkbox="true" />
			<e:column field="orderDate" width="80">Order Date</e:column>
			<e:column field="customerName" width="150">Customer</e:column>
			<e:column field="shipViaName" width="120" >ShipVia</e:column>
			<e:column field="shipName" width="200" >Ship Name</e:column>
			<e:column field="shipAddress" width="200">Ship Address</e:column>
			<e:column field="shipCity" width="120" >Ship City</e:column>
		</e:columns>
	</e:datagrid>

	<div id="t1" style="padding:2px 5px;">
		<e:linkbutton iconCls="icon-add" plain="true">新增</e:linkbutton>
		<e:linkbutton iconCls="icon-edit" plain="true">编辑</e:linkbutton>
		<e:linkbutton iconCls="icon-save" plain="true">保存</e:linkbutton>
		<e:linkbutton iconCls="icon-cut" plain="true">剪切</e:linkbutton>
		<e:linkbutton iconCls="icon-remove" plain="true">移除</e:linkbutton>
	</div>
	
	<div id="q1" style="padding:2px 5px;">
		开始: <e:datebox id="fromDate" style="110px" required="true" tipPosition="top"></e:datebox>
		至: <e:datebox id="toDate" style="110px"></e:datebox>
		语言: <e:combobox id="" panelHeight="auto" style="width:100px">
			<e:selectitem label="java" value="java"/>
			<e:selectitem label="C" value="c"/>
			<e:selectitem label="Basic" value="basic"/>
			<e:selectitem label="Perl" value="perl"/>
			<e:selectitem label="Python" value="python"/>
		</e:combobox>
		<e:linkbutton iconCls="icon-search" style="margin-left:5px">搜索</e:linkbutton>
	</div>
	
</e:section>

<e:section name="code">
<pre><code class="html">&lt;e:datagrid title="管理列表" style="width:100%;height:auto;" collapsible="true" pagination="true" pageSize="10" 
	singleSelect="true" url="${root}/example/general/order/find.json" method="get" idField="id"
	toolbar="#t1,#q1"&gt;
	&lt;e:columns&gt;
		&lt;e:column field="ck" checkbox="true" /&gt;
		&lt;e:column field="orderDate" width="80"&gt;Order Date&lt;/e:column&gt;
		&lt;e:column field="customerName" width="150"&gt;Customer&lt;/e:column&gt;
		&lt;e:column field="shipViaName" width="120" &gt;ShipVia&lt;/e:column&gt;
		&lt;e:column field="shipName" width="200" &gt;Ship Name&lt;/e:column&gt;
		&lt;e:column field="shipAddress" width="200"&gt;Ship Address&lt;/e:column&gt;
		&lt;e:column field="shipCity" width="120" &gt;Ship City&lt;/e:column&gt;
	&lt;/e:columns&gt;
&lt;/e:datagrid&gt;

&lt;div id="t1" style="padding:2px 5px;"&gt;
	&lt;e:linkbutton iconCls="icon-add" plain="true"&gt;新增&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-edit" plain="true"&gt;编辑&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-save" plain="true"&gt;保存&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-cut" plain="true"&gt;剪切&lt;/e:linkbutton&gt;
	&lt;e:linkbutton iconCls="icon-remove" plain="true"&gt;移除&lt;/e:linkbutton&gt;
&lt;/div&gt;

&lt;div id="q1" style="padding:2px 5px;"&gt;
	开始: &lt;e:datebox id="fromDate" style="110px" required="true" tipPosition="top"&gt;&lt;/e:datebox&gt;
	至: &lt;e:datebox id="toDate" style="110px"&gt;&lt;/e:datebox&gt;
	语言: &lt;e:combobox id="" panelHeight="auto" style="width:100px"&gt;
		&lt;e:selectitem label="java" value="java"/&gt;
		&lt;e:selectitem label="C" value="c"/&gt;
		&lt;e:selectitem label="Basic" value="basic"/&gt;
		&lt;e:selectitem label="Perl" value="perl"/&gt;
		&lt;e:selectitem label="Python" value="python"/&gt;
	&lt;/e:combobox&gt;
	&lt;e:linkbutton iconCls="icon-search" style="margin-left:5px"&gt;搜索&lt;/e:linkbutton&gt;
&lt;/div&gt;
</code></pre>
</e:section>

<%@ include file="../shared/_content.jsp" %>