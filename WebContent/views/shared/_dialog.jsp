<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><k:renderSection name="title"/></title>
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
	<k:renderSection name="head"/>
</head>
<k:body full="true" padding="5px">
	<k:layout fit="true">
		<k:dock region="center" style="padding: 10px 30px 10px 30px;">
			<k:renderSection name="body"/>
		</k:dock>
		<k:dock region="south" border="false" style="text-align:right;padding:5px 0 0;">
			<k:renderSection name="tool"/>
		</k:dock>
	</k:layout>
</k:body>
</html>