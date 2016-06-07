<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<k:section name="title">任务列表</k:section>
<k:section name="head">
	<script type="text/javascript">
	
		$(function(){ 
			
			$('#tg').datagrid({
				url: "${root}/bpm/task/find.json",
				queryParams: {
					keyword : $('#search').val()
				}
			});
			
		});
		
		function search(){
			
			$('#tg').datagrid('load', {
				keyword : $('#search').val()
			});
		}
		
		function taskclaim(id){
			
			juasp.post("${root}/bpm/task/claim.json", { "id": id },
			{
				success: function(r){
					juasp.infoTips("任务签收完成.");
					search();
				}
			});
		}
		
		function taskread(id){
			
			juasp.openWin({
				url: "${root}/bpm/task/read?id=" + id,
				width: "550px",
				height: "600px",
				title: "处理任务",
				onClose : function(result){
					if(result == 1){
						search();
					}
				}
			});
		}
		
		function formaterActions(value, row, index){
			
 			if(juasp.isEmpty(row.assignee)){
				return "<a href='###' onclick=\"taskclaim('"+ row.id + "')\" >签收</a>";
			} else {
				return "<a href='###' onclick=\"taskread('"+ row.id + "')\" >处理</a>";
			}
		}
		
	</script>
</k:section>

<k:section name="body">
	<k:datagrid id="tg" fit="true" rownumbers="true" border="false"
		pagination="true" pageSize="10" singleSelect="true" striped="true"
		toolbar="#tb" idField="id">
		<k:column field="ck" checkbox="true" />
		<k:column field="name" title="任务名称" />
		<k:column field="processInstanceId" title="过程实例Id" />
		<k:column field="processDefinitionId" title="过程定义Id" />
		<k:column field="createTime" title="创建时间" />
		<k:column field="assignee" title="处理人" />
		<k:column field="id" width="80" title="操作项" align="center" formatter="formaterActions"/>
	</k:datagrid>
		<div id="tb">
			<k:linkbutton id="deploy" iconCls="icon-add" plain="true" text="上传流程" onClick="jctx.deploy()" />
			<div style="float: right;">
				<k:searchbox id="search" prompt="搜索：任务名称" width="220" height="25" searcher="search" />
			</div>
		</div>
	</div>
</k:section>

<%@ include file="/shared/_simple.jsp"%>