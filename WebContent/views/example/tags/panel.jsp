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
	<k:resource location="res" name="js/juasp-uploader.js"></k:resource>
</head>
<k:body full="true" padding="5px">
	<k:panel title="我是标题" fit="true" iconCls="icon-save" padding="10px" width="400px" height="250px" closable="true"
		tools='[{iconCls:"icon-add",handler:function(){alert("添加")}},{iconCls:"icon-edit",handler:function(){alert("编辑")}}]'
		onClose='function(){ alert("close"); }'>
		<p>我是内容。</p>
	</k:panel>
</k:body>
</html>