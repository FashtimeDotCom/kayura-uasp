<%@page import="org.kayura.utils.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">选择文件夹</e:section>

<e:section name="head">
	<script type="text/javascript">
		$(function() {
			$('#tv').tree({
				url : "${root}/file/folders.json",
				animate : true,
				onClick : function(node) {

				}
			});
		});
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 5px">
		<ul id="tv" class="easyui-tree"></ul>
	</e:layoutunit>
	<e:layoutunit region="south" border="false" style="height: 40px;">
		<div style="text-align: right; margin-top: 5px; margin-right: 10px">
			<e:linkbutton iconCls="icon-ok" style="width:75px" text="确认" />
			<e:linkbutton iconCls="icon-cancel" style="width:75px; margin-left: 5px" text="取消" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/shared/_simple.jsp"%>