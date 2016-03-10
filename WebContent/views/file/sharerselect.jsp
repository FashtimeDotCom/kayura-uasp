<%@page import="org.kayura.utils.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">选择文件夹</e:section>

<e:section name="head">
	<script type="text/javascript">
		$(function() {

			$('#tg').datagrid({
				url : "${root}/file/sharer/find.json",
				queryParams : {
					keyword : $('#keyword').val()
				}
			});
		});

		jctx = (function(win, $) {

			function _confirm() {

			}

			return {
				confirm : _confirm
			};

		}(window, jQuery));
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 5px">
		<e:datagrid id="tg" fit="true" rownumbers="true" pagination="true"
			pageSize="20" singleSelect="true" striped="true" toolbar="#tq" idField="userId">
			<e:columns>
				<e:column field="ck" checkbox="true" />
				<e:column field="displayName" width="120" title="显示名" />
			</e:columns>
		</e:datagrid>
		<div id="tq" style="padding-left: 8px">
			关键字：
			<e:textbox id="keyword" style="width:150px" />
			<a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		</div>
	</e:layoutunit>
	<e:layoutunit region="south" border="false" style="height: 35px; padding: 5px;">
		<div style="text-align: right;">
			<e:linkbutton id='ok' onclick="jctx.confirm();" iconCls="icon-ok" style="width:75px" text="确认" />
			<e:linkbutton id='cancel' onclick="juasp.closeWin({result:0});" iconCls="icon-cancel" style="width:75px; margin-left: 5px" text="取消" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/shared/_simple.jsp"%>