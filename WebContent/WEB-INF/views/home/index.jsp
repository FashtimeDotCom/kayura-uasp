<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<u:head title="统一应用支撑平台">
	<script type="text/javascript">	
		$(document).ready(function () {
			$('#homePage').append("<iframe name='contentframe' scrolling='auto' frameborder='0' src='${siteUrl}/portal' style='width:100%;height:100%;'></iframe>");
		});
	</script>
</u:head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 60px">
		<h2 style="padding-left: 10px">统一应用支撑平台</h2>
	</div>
	<div data-options="region:'south'" style="height: 35px;"></div>
	<div data-options="region:'east',split:true,collapsed:true" title="快捷菜单" style="width: 180px;"></div>
	<div data-options="region:'west',split:true" title="导航栏" style="width: 200px;">
		<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="系统管理" data-options="iconCls:'icon-ok'" style="padding: 0px;">
				<ul>
					<li><a href="###" onclick="juasp.openTab('账号管理', '${siteUrl}/account/list')" >账号管理</a></li>
				</ul>
			</div>
			<div title="组织机构" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				content2
			</div>
			<div title="工作流" data-options="iconCls:'icon-ok'" style="padding: 10px">
				content3
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">
 			<div id="homePage" title="首页" style="padding: 0px;overflow:hidden;">
			</div>
		</div>
	</div>
</body>
</html>