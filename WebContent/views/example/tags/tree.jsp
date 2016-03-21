<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>标签测试</title>
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
<body>
	<k:tree id="tree1" url="${root}/example/tags/treedata.json" queryParams="${query}"></k:tree>
	<k:tree id="tree2" data="${data}"></k:tree>
</body>
</html>