<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<e:section name="title">用户管理</e:section>

<e:section name="head">
	<script type="text/javascript">
	
		function submitForm() {
			
			$('#ff').form('submit', {
				url : '${root}/admin/user/save.json',
				onSubmit : function() {
					return $('#ff').form('validate');
				},
				success : function(data) {
					juasp.closeWin(1);
				}
			});
		}
		
		function clearForm() {
			juasp.closeWin(0);
		}
	</script>
</e:section>

<e:section name="body">
	<div class="easyui-panel" border="false" style="padding: 10px 30px 10px 30px; ">
		<form id="ff" class="easyui-form" method="post">
			<input type="hidden" name="userId" value="${model.userId}" />
			<table cellpadding="5">
				<tr>
					<td>登录名</td>
					<td><input class="easyui-textbox" name="userName" style="width:180px" value="${model.userName}" data-options="required:true"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>显示名:</td>
					<td><input class="easyui-textbox" name="displayName" style="width:180px" value="${model.displayName}" data-options="required:true"></input></td>
					<td></td>
				</tr>
				<c:if test="${empty model.userId}">
				<tr>
					<td>初始密码:</td>
					<td><input class="easyui-textbox" name="password" style="width:180px" data-options="required:true"></input></td>
					<td></td>
				</tr>
				</c:if>
				<tr>
					<td>电子邮件:</td>
					<td><input class="easyui-textbox" name="email" style="width:180px" value="${model.email}" data-options="required:true,validType:'email'"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>手机号:</td>
					<td><input class="easyui-textbox" name="mobileNo" style="width:180px" value="${model.mobileNo}" data-options="required:true,validType:'length[11,11]'"></input></td>
					<td></td>
				</tr>
				<tr>
					<td>关键字:</td>
					<td><input class="easyui-textbox" name="keyword" style="width:180px" value="${model.keyword}" data-options=""></input></td>
					<td></td>
				</tr>
				<tr>
					<td>过期时间:</td>
					<td><input class="easyui-textbox" name="expireTime" style="width:180px" value='<fmt:formatDate value="${model.expireTime}" pattern="yyyy-MM-dd"/>' data-options=""></input></td>
					<td></td>
				</tr>
				<tr>
					<td>所属角色:</td>
					<td><input class="easyui-textbox" name="roles" style="width:180px" value="${model.roles}" data-options="required:true"></input></td>
					<td>可选：ROOT,ADMIN,USER。默认为USER</td>
				</tr>
				<tr>
					<td>是否启用:</td>
					<td><input class="easyui-textbox" name="isEnabled" style="width:180px" value="${model.isEnabled}" data-options=""></input></td>
					<td></td>
				</tr>
				<tr>
					<td>是否锁定:</td>
					<td><input class="easyui-textbox" name="isLocked" style="width:180px" value="${model.isLocked}" data-options=""></input></td>
					<td></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px" onclick="submitForm()">提交</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" style="width: 75px" onclick="clearForm()">取消</a>
		</div>
	</div>
</e:section>

<%@ include file="/views/shared/_editor.jsp"%>