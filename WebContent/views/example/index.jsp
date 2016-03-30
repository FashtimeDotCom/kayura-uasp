<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>统一应用支撑平台-开发示例</title>
<k:resources location="res" theme="${theme}" />
</head>
<k:body>
	<k:dock region="north" style="height: 60px">
		<h2 style="padding-left: 10px; float: left;">统一应用支撑平台-开发示例</h2>
	</k:dock>
	<k:dock region="south" style="height: 35px"></k:dock>
	<k:dock region="east" split="true" collapsible="true"
		title="快捷工具" style="width: 180px">
		<ul>
			<li><a href="###"
				onclick="juasp.openTab('Html-Convert', '${root}/example/htmlconvert')">Html语义转换</a></li>
		</ul>
	</k:dock>
	<k:dock region="west" split="true" title="导航栏" style="width: 160px;">
		<k:accordion fit="true" border="false" multiple="true">
			<k:sheet iconCls="icon-ok" title="EasyUI" collapsed="false" collapsible="false">
				<ul>
					<li><a href="###" onclick="juasp.openTab('EasyUI-DataGrid', '${root}/example/easyui/datagrid')">DataGrid</a></li>
					<li><a href="###" onclick="juasp.openTab('EasyUI-Accordion', '${root}/example/easyui/accordion')">Accordion</a></li>
				</ul>
			</k:sheet>
			<k:sheet iconCls="icon-ok" title="JSP标签" collapsed="false" collapsible="false">
				<ul>
					<li><a href="###" onclick="juasp.openTab('Base Tag', '${root}/example/tags/base')">BaseTag</a></li>
					<li><a href="###" onclick="juasp.openTab('Form Tag', '${root}/example/tags/form')">FormTag</a></li>
					<li><a href="###" onclick="juasp.openTab('WindowTag', '${root}/example/tags/win')">Window</a></li>
					<li><a href="###" onclick="juasp.openTab('DataGrid Tag', '${root}/example/tags/grid')">DataGridTag</a></li>
					<li><a href="###" onclick="juasp.openTab('Accordion Tag', '${root}/example/tags/acc')">AccordionTag</a></li>
					<li><a href="###" onclick="juasp.openTab('Tree Tag', '${root}/example/tags/tree')">TreeTag</a></li>
					<li><a href="###" onclick="juasp.openTab('Panel Tag', '${root}/example/tags/panel')">PanelTag</a></li>
					<li><a href="###" onclick="juasp.openTab('Tabs Tag', '${root}/example/tags/tabs')">TabsTag</a></li>
				</ul>
			</k:sheet>
			<k:sheet iconCls="icon-ok" title="常用示例" collapsed="false" collapsible="false">
				<ul>
					<li><a href="###" onclick="juasp.openTab('List-Basic', '${root}/example/general/basiclist')">基本列表</a></li>
				</ul>
			</k:sheet>
		</k:accordion>
	</k:dock>
	<k:dock region="center">
		<k:tabs id="mainTabs" fit="true" border="false">
			<k:tabpage id="homePage" title="首页" iconCls="icon-home"
				style="padding: 0px;overflow:hidden;"></k:tabpage>
		</k:tabs>
	</k:dock>
</k:body>
</html>