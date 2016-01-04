<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件上传</e:section>

<e:section name="body">
	<h2>Basic FileBox</h2>
	<p>The filebox component represents a file field of the forms.</p>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="Upload File"
		style="width: 400px; padding: 30px 70px 50px 70px">
		<form id="ff" method="post" action="${root}/file/upload.json" enctype="multipart/form-data">
			<div style="margin-bottom: 20px">
				<div>File:</div>
				<input class="easyui-filebox" name="file"
					data-options="prompt:'Choose a file...'" style="width: 100%">
			</div>
			<div>
				<input type="submit" value="提交">
			</div>
		</form>
	</div>
</e:section>

<%@ include file="/shared/_simple.jsp"%>