<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件上传</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 5px">
		<h2>Basic FileBox</h2>
		<p>The filebox component represents a file field of the forms.</p>
		<div style="margin: 20px 0;"></div>
		<div class="easyui-panel" title="Upload File"
			style="width: 400px; padding: 30px 70px 50px 70px">
			<form id="ff" method="post" action="${root}/file/upload"
				enctype="multipart/form-data">
				<div style="margin-bottom: 20px">
					<div>File1:</div>
					<input class="easyui-filebox" name="file"
						data-options="prompt:'选择一个文件...',buttonText:'浏览文件……'" style="width: 100%">
				</div>
				<div style="margin-bottom: 20px">
					<div>File2:</div>
					<input class="easyui-filebox" name="file"
						data-options="prompt:'选择一个文件...',buttonText:'浏览文件……'" style="width: 100%">
				</div>
				<div>
					<input type="submit" value="提交">
				</div>
			</form>
		</div>
		<h2>${message}</h2>
	</e:layoutunit>
</e:section>

<%@ include file="/shared/_simple.jsp"%>