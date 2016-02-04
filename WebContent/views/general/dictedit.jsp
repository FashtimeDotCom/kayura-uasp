<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">数据词典管理</e:section>

<e:section name="head">
</e:section>

<e:section name="body">
	<div class="easyui-panel" border="false" style="padding: 10px 50px 10px 50px; ">
		<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
			<table cellpadding="5">
				<tr>
					<td>Name:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input class="easyui-textbox" type="text" name="email"
						data-options="required:true,validType:'email'"></input></td>
				</tr>
				<tr>
					<td>Subject:</td>
					<td><input class="easyui-textbox" type="text" name="subject"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>Message:</td>
					<td><input class="easyui-textbox" name="message"
						data-options="multiline:true" style="height: 60px"></input></td>
				</tr>
				<tr>
					<td>Language:</td>
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