<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">基本列表</e:section>

<e:section name="body">

	<p>包括一个可分页列表，支持排序/条件查询，操作按钮，列表链接。</p>
	<div style="margin:20px 0;"></div>
	
	<e:datagrid title="管理列表" style="width:100%;height:auto;" collapsible="true" pagination="true" pageSize="10" 
		singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get" idField="itemid"
		toolbar="#t1,#q1" >
		<e:columns>
			<e:column field="ck" checkbox="true" />
			<e:column field="itemid" width="80">Item ID</e:column>
			<e:column field="productid" width="100">Product</e:column>
			<e:column field="listprice" width="80" align="right">List Price</e:column>
			<e:column field="unitcost" width="80" align="right">Unit Cost</e:column>
			<e:column field="attr1" width="250">Attribute</e:column>
			<e:column field="status" width="60" align="center">Status</e:column>
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

<e:section name="code">&lt;e:datagrid title="管理列表" style="width:100%;height:auto;" collapsible="true" pagination="true" pageSize="10" 
	singleSelect="true" url="${root}/res/easyui/jsondata/datagrid_data1.json" method="get" idField="itemid"
	toolbar="#t1,#q1" &gt;
	&lt;e:columns&gt;
		&lt;e:column field="ck" checkbox="true" /&gt;
		&lt;e:column field="itemid" width="80"&gt;Item ID&lt;/e:column&gt;
		&lt;e:column field="productid" width="100"&gt;Product&lt;/e:column&gt;
		&lt;e:column field="listprice" width="80" align="right"&gt;List Price&lt;/e:column&gt;
		&lt;e:column field="unitcost" width="80" align="right"&gt;Unit Cost&lt;/e:column&gt;
		&lt;e:column field="attr1" width="250"&gt;Attribute&lt;/e:column&gt;
		&lt;e:column field="status" width="60" align="center"&gt;Status&lt;/e:column&gt;
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
</e:section>

<%@ include file="../shared/_content.jsp" %>