<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>统一应用支撑平台</title>
<e:resources location="res" theme="${theme}" />
</head>
<e:body>
	<e:layoutunit region="north" style="height: 60px">
		<h2 style="padding-left: 10px; float: left;">统一应用支撑平台</h2>
		<div style="padding: 15px; float: right;">
			欢迎：${loginName} <a href="${root}/logout" style="margin-left: 10px;">注销</a>		
		</div>
	</e:layoutunit>
	<e:layoutunit region="south" style="height: 35px">
		共 ${numUsers} 人在线.
	</e:layoutunit>
	<e:layoutunit region="west" split="true" title="导航栏"
		style="width: 160px;">
		<e:accordion fit="true" border="false">
			<e:tab iconCls="icon-ok" title="常用模块">
				<ul>	
					<li><a href="###" onclick="juasp.openTab('账号管理', '${root}/admin/user/list')" >账号管理</a></li>
					<li><a href="###" onclick="juasp.openTab('数据词典', '${root}/admin/dict/list')" >数据词典</a></li>
					<li><a href="###" onclick="juasp.openTab('文件上传', '${root}/file/upload')" >文件上传</a></li>
					<li><a href="###" onclick="juasp.openTab('文件管理', '${root}/file/manager')" >文件管理</a></li>
				</ul>
			</e:tab>
		</e:accordion>
	</e:layoutunit>
	<e:layoutunit region="center">
		<e:tabs id="mainTabs" fit="true" border="false">
			<e:tab id="homePage" title="首页" iconCls="icon-home" style="padding: 0px;overflow:hidden;">
				<iframe frameborder="0" style="width:100%;height:100%;" src="" 
						scrolling="auto" name="contentframe"></iframe>
			</e:tab>
		</e:tabs>
	</e:layoutunit>
</e:body>
</html>