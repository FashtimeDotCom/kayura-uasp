<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>EasyUI 示例库</title>
<e:resources location="res" theme="${theme}" />
</head>
<e:body>
	<e:layoutUnit region="north" style="height: 60px">
		<h2 style="padding-left: 10px; float: left;">EasyUI 示例</h2>
	</e:layoutUnit>
	<e:layoutUnit region="south" style="height: 35px"></e:layoutUnit>
	<e:layoutUnit region="east" split="true" collapsible="true" title="快捷工具" style="width: 180px">
	</e:layoutUnit>
	<e:layoutUnit region="west" split="true" title="导航栏" style="width: 200px;">
		<e:accordion fit="true" border="false">
			<e:tab iconCls="icon-ok" title="datagrid">
				<ul>
					<li><a href="###" onclick="juasp.openTab('Basic-DataGrid', '${root}/easyui/datagrid/basic')" >basic</a></li>
				</ul>
			</e:tab>
			<e:tab iconCls="icon-ok" title="accordion">
				<ul>
					<li><a href="###" onclick="juasp.openTab('Basic-Accordion', '${root}/easyui/accordion/basic')" >basic</a></li>
				</ul>
			</e:tab>
		</e:accordion>
	</e:layoutUnit>
	<e:layoutUnit region="center">
		<e:tabs id="mainTabs" fit="true" border="false">
			<e:tab id="homePage" title="首页" iconCls="icon-home" style="padding: 0px;overflow:hidden;"></e:tab>
		</e:tabs>
	</e:layoutUnit>
</e:body>
</html>