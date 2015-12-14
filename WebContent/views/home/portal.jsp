<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>统一门户</title>
<link rel="stylesheet" type="text/css" href="${root}/res/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${root}/res/easyui/themes/icon.css">
<script type="text/javascript" src="${root}/res/js/jquery.min.js"></script>
<script type="text/javascript" src="${root}/res/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${root}/res/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${root}/res/easyui/plugins/jquery.portal.js"></script>
<style type="text/css">
.t-list { padding: 5px; }
</style>
<script type="text/javascript">
	$(function() {
		$('#pp').portal({
			border : false,
			fit : true
		});
		//add();
	});
	function add() {
		for (var i = 0; i < 5; i++) {
			var p = $('<div/>').appendTo('body');
			p.panel({
				title : 'Title' + i,
				content : '<div style="padding:5px;">Content' + (i + 1)
						+ '</div>',
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
</head>
<body class="easyui-layout">
	<div region="center" border="false">
		<div id="pp" style="position: relative">
			<div style="width: 65%;">
				<div id="pgrid" title="DataGrid" closable="true"
					style="height: 200px;">
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
				<div title="Graph" closable="true"
					style="height: 200px; text-align: center;"></div>
			</div>
			<div style="width: 35%;">
				<div title="Clock"
					style="text-align: center; background: #f3eeaf; height: 150px; padding: 5px;">
				</div>
				<div title="Tutorials" collapsible="true" closable="true"
					style="height: 200px; padding: 5px;">
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/datagrid/datagrid1.php">Build
							border layout for Web Pages</a>
					</div>
					<div class="t-list">
						<a href="http://www.jeasyui.com/tutorial/layout/panel.php">Complex
							layout on Panel</a>
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
				<div title="Searching" iconCls="icon-search" closable="true"
					style="height: 80px; padding: 10px;">
					<input class="easyui-searchbox">
				</div>
			</div>
		</div>
	</div>
</body>
</html>