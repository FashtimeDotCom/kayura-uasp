<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>统一门户</title>
	<k:resource  id="themeLink" location="res" name="easyui/themes/${theme}/easyui.css"/>
	<k:resources location="res">
		easyui/themes/icon.css
		js/juasp.css
		js/jquery.min.js
		easyui/jquery.easyui.min.js
		easyui/easyui-lang-zh_CN.js
		js/juasp-core.js
		js/juasp-easyui.js
	</k:resources>
</head>
<body class="easyui-layout">
	<k:resource location="res" name="easyui/jquery.portal.js"/>
	<div region="center" border="false">
		<div id="pp" style="position: relative">
			<div style="width: 65%;">
				<div id="pgrid" title="任务中心" closable="true" style="height: 200px;">
					<table class="easyui-datagrid" style="width: 650px; height: auto" method="GET"
						fit="true" border="false" singleSelect="true" idField="itemid" fitColumns="true"
						url="${root}/res/easyui/jsondata/datagrid_data.json">
						<thead>
							<tr>
								<th field="itemid" width="60">Item ID</th>
								<th field="productid" width="60">Product ID</th>
								<th field="listprice" width="80" align="right">List Price</th>
								<th field="unitcost" width="80" align="right">Unit Cost</th>
								<th field="attr1" width="120">Attribute</th>
								<th field="status" width="50" align="center">Status</th>
							</tr>
						</thead>
					</table>
				</div>
				<div title="工作任务" closable="true" style="height: auto; text-align: center;">
					<iframe scrolling="no" frameborder="0" src="${root}/bpm/task/list" style="width:100%;height:100%;"></iframe>
				</div>
			</div>
			<div style="width: 35%;">
				<div title="消息提醒" style="text-align: center; height: 150px; padding: 5px;">
				</div>
				<div title="我的订阅" collapsible="true" closable="true" style="height: 200px; padding: 5px;">
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/datagrid/datagrid1.php">
							Build border layout for Web Pages
						</a>
					</div>
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/layout/panel.php">
							Complex layout on Panel
						</a>
					</div>
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/layout/accordion.php">Create
							Accordion</a>
					</div>
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/layout/tabs.php">Create
							Tabs</a>
					</div>
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/layout/tabs2.php">Dynamically
							add tabs</a>
					</div>
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/layout/panel2.php">Create
							XP style left panel</a>
					</div>
				</div>
				<div title="搜索中心" iconCls="icon-search" closable="true" style="height: 80px; padding: 10px;">
					<input class="easyui-searchbox" style="width: 80%">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#pp').portal({
				border : false,
				fit : true
			});
			add();
		});
		function add() {
			for (var i = 0; i < 2; i++) {
				var p = $('<div/>').appendTo('body');
				p.panel({
					title : 'Title' + i,
					content : '<div style="padding:5px;">Content' + (i + 1) + '</div>',
					height : 100,
					closable : true,
					collapsible : true
				});
				$('#pp').portal('add', {
					panel : p,
					columnIndex : i % 2
				});
			}
			$('#pp').portal('resize');
		}
		function remove() {
			$('#pp').portal('remove', $('#pgrid'));
			$('#pp').portal('resize');
		}
	</script>
</body>
</html>