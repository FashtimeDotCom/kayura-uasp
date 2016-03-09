<%@page import="org.kayura.utils.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">选择文件夹</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		$(function() {
			$('#tv').tree({
				url : "${root}/file/folders.json?t=select",
				animate : true,
				onClick : function(node) {
					jctx.buttonState(node.id);
				}
			});
		});

		jctx = (function(win, $) {

			var currentFolderId = '${sid}';
			var selectedNodeId = null;

			function _confirm() {

				if ((selectedNodeId.length == 32 || selectedNodeId == "NOTCLASSIFIED")
						&& selectedNodeId != currentFolderId) {
					juasp.closeWin({
						result : 1,
						data : selectedNodeId
					});
				}
			}

			function _buttonState(nodeId) {

				selectedNodeId = nodeId;

				var isOk = false;
				if ((nodeId.length == 32 || nodeId == "NOTCLASSIFIED")
						&& nodeId != currentFolderId) {
					isOk = true;
				}

				$("#ok").linkbutton(isOk ? 'enable' : 'disable');
			}

			return {
				confirm : _confirm,
				buttonState : _buttonState
			};

		}(window, jQuery));
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 5px">
		<ul id="tv" class="easyui-tree"></ul>
	</e:layoutunit>
	<e:layoutunit region="south" border="false" style="height: 35px; padding: 5px;">
		<div style="text-align: right;">
			<e:linkbutton id='ok' onclick="jctx.confirm();" iconCls="icon-ok"
				style="width:75px" text="确认" />
			<e:linkbutton id='cancel' onclick="juasp.closeWin({result:0});"
				iconCls="icon-cancel" style="width:75px; margin-left: 5px" text="取消" />
		</div>
	</e:layoutunit>
</e:section>

<%@ include file="/shared/_simple.jsp"%>