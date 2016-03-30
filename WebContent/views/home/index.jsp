<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>统一应用支撑平台</title>
	<k:resources location="res">
		easyui/themes/${theme}/easyui.css
		easyui/themes/icon.css
		js/juasp.css
		js/jquery.min.js
		easyui/jquery.easyui.min.js
		easyui/easyui-lang-zh_CN.js
		js/juasp-core.js
		js/juasp-easyui.js
	</k:resources>
</head>
<k:body layout="true">
	<k:dock region="north" style="height: 60px">
		<h2 style="padding-left: 10px; float: left;">统一应用支撑平台</h2>
		<div style="padding: 15px; float: right;">
			欢迎：${loginName} <a href="${root}/logout" style="margin-left: 10px;">注销</a>		
		</div>
	</k:dock>
	<k:dock region="south" style="height: 35px">
		共 ${numUsers} 人在线.
	</k:dock>
	<k:dock region="west" split="true" title="导航栏"
		style="width: 160px;">
		<k:accordion fit="true" border="false">
			<k:sheet iconCls="icon-ok" title="常用模块">
				<ul>
					<li><a href="###" onclick="juasp.openTab('账号管理', '${root}/admin/user/list')" >账号管理</a></li>
					<li><a href="###" onclick="juasp.openTab('数据词典', '${root}/admin/dict/list')" >数据词典</a></li>
					<li><a href="###" onclick="juasp.openTab('组织机构', '${root}/org/manager')" >组织机构</a></li>
					<li><a href="###" onclick="juasp.openTab('文件管理', '${root}/file/manager')" >文件管理</a></li>
					<li><a href="###" onclick="juasp.openTab('文件上传', '${root}/mock/fileup')" >文件上传</a></li>
				</ul>
			</k:sheet>
		</k:accordion>
	</k:dock>
	<k:dock region="center">
		<k:tabs id="mainTabs" fit="true" border="false">
			<k:tabpage id="homePage" title="首页" iconCls="icon-home" style="padding: 0px;overflow:hidden;">
				<iframe frameborder="0" style="width:100%;height:100%;" src="" scrolling="auto" name="contentframe"></iframe>
			</k:tabpage>
		</k:tabs>
	</k:dock>
</k:body>
</html>