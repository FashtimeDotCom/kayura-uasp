<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">Panel 标签</k:section>

<k:section name="head">
	<k:resource location="res" name="js/juasp-uploader.js"></k:resource>
	
	<script type="text/javascript">
		$(function(){
			
			$("#panel2").bind('contextmenu',function(e){
				e.preventDefault();
				$('#mm2').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			});
			
			$('#mm2').menu().menu('enableNav');
		});
		
		function menuHandler(item){
			if(!juasp.isEmpty(item.name)) {
				alert(item.name);
			}
		}
	</script>
	
</k:section>

<k:section name="body">
	<k:panel title="搜索框" width="450px" height="auto" padding="10px">
		<p>搜索框（HTML菜单）</p>
		<k:searchbox menu="#mm" width="300px" searcher="function(value,name){ alert(value+name); }" />
		<div id="mm" style="width: 150px">
			<div data-options="name:'item1',iconCls:'icon-ok'">Search Item1</div>
			<div data-options="name:'item2',selected:true">Search Item2</div>
			<div data-options="name:'item3'">Search Item3</div>
		</div>
		<p>搜索框（代码构造菜单）</p>
		<k:searchbox menus="${menus}" width="300px" searcher="function(value,name){ alert(value+name); }" />
	</k:panel>
	<div style="margin: 5px"></div>
	<k:panel id="panel2" title="弹出式菜单" width="450px" height="auto" padding="10px">
		<p>点击右键，显示菜单</p>
		<k:menu id="mm2" style="width:150px" menus="${menus}" onClick="menuHandler" />
	</k:panel>
	<div style="margin: 5px"></div>
	<k:panel title="各种按钮" width="450px" height="auto" padding="10px">
		<k:linkbutton text="我是按钮" iconCls="icon-add" onClick="alert('中华人民共和国') "/>
		<k:linkbutton text="左" toggle="true" group="align"/>
		<k:linkbutton text="中" toggle="true" group="align"/>
		<k:linkbutton text="右" toggle="true" group="align"/>
		<k:menubutton text="我是下拉菜单" iconCls="icon-edit" plain="false" menus="${menus}" />
		<k:splitbutton text="我是分割菜单" iconCls="icon-edit" plain="false" menus="${menus}" onClick="alert('分割菜单')" />
		
	</k:panel>
</k:section>

<%@ include file="shared/_simple.jsp"%>
