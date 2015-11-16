<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">基本列表</e:section>

<e:section name="head">
	<script type="text/javascript">

		var cmenu;
	
		$(function() {
			$('#dg').datagrid({
				url : "${root}/example/general/order/find.json",
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!cmenu){
						cmenu = jeasyui.createColumnMenu('#dg');
					}
					cmenu.menu('show', { left:e.pageX, top:e.pageY });
				}
			});
		});
		
		function search1() {
			var kw = $("#keyword").val();			
			$("#dg").datagrid('reload', { keyword : kw });
		}
		
		function addOrder() {

			juasp.openWin({
				title : "新增表单",
				url : "${root}/example/general/basicedit",
				iconCls : 'icon-edit',
				width : 800,
				height : 500,
				onClose : function(result) {
					juasp.info("<b>关闭啦。</b>返回值为：" + result.value);
				}
			});
		}
		
	</script>
</e:section>

<e:section name="body">

	<div id="t1" style="padding: 2px;">
		<e:linkbutton iconCls="icon-add" onclick="addOrder()" plain="true">创建表单</e:linkbutton>
		<e:linkbutton iconCls="icon-edit" plain="true">编辑表单</e:linkbutton>
		<e:linkbutton iconCls="icon-remove" plain="true">删除表单</e:linkbutton>
		<div id="q1" style="float: right;">
		关键字：<e:textbox id="keyword" style="width:220px" prompt="搜索：Order Date,ShipVia,Ship Name" />
		<e:linkbutton iconCls="icon-search" style="margin-left:5px" plain="true" onclick="search1()">搜索</e:linkbutton>
		</div>
	</div>
	
	<e:datagrid id="dg" style="width:100%;height:auto;" collapsible="true" rownumbers="true"
		pagination="true" pageSize="10"  singleSelect="true" url="" method="get" idField="id" 
		remoteSort="true" striped="true" toolbar="#t1" fitColumns="true">
		<e:columns>
			<e:column field="ck" checkbox="true" />
			<e:column field="orderDate" width="120" sortable="true">Order Date</e:column>
			<e:column field="shipViaName" width="120" sortable="true">ShipVia</e:column>
			<e:column field="shipName" width="200" sortable="true">Ship Name</e:column>
			<e:column field="shipAddress" width="200" sortable="true">Address</e:column>
			<e:column field="shipCity" width="120" sortable="true">City</e:column>
			<e:column field="shipRegion" width="120" sortable="true">Region</e:column>
			<e:column field="shipPostalCode" width="120" sortable="true">Postal Code</e:column>
			<e:column field="shipCountry" width="120" sortable="true">Country</e:column>
		</e:columns>
	</e:datagrid>

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

<%@ include file="/shared/_content.jsp" %>