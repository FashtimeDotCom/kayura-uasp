<%@page import="org.kayura.utils.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件上传</e:section>

<e:section name="head">
	<e:resource location="res/js" name="webuploader.css" />
	<e:resource location="res/js" name="webuploader.js" />
	<e:resource location="res/js" name="juasp-uploader.js" />
	<script type="text/javascript">
		$(function(){
			$("#fileUpload1").uploader({
				showicon : true,
				actions : {
					upload : true,
					remove : false
				},
				formData : {
					category: '合同',
					bizId: $("#bizId").val()
				}
			});
			$("#fileUpload2").uploader({
				formData : {
					category: '归档',
					bizId: $("#bizId").val()
				}
			});
		});
	</script>
</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 5px">
		<h2>文件上传</h2>
		<p>此页模拟表单中多文件上传，大小限制，MD5校验，简单及表格显示列表。</p>
		<input type="hidden" id="bizId" name="bizId" value="8F6528BEEAB411E5AD4E10BF48BBBEC9" />
		<e:panel id="p1" title="列表上传(合同)" style="width: 400px; padding: 40px">
			<e:linkbutton id="fileUpload1" iconCls="icon-add" text="上传文件" />
		</e:panel>
		<e:panel id="p2" title="列表上传(归档)" style="width: 400px; padding: 40px">
			<e:linkbutton id="fileUpload2" iconCls="icon-add" text="上传文件" />
		</e:panel>
	</e:layoutunit>
</e:section>

<%@ include file="/shared/_simple.jsp"%>