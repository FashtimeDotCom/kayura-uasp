<%@page import="org.kayura.utils.JsonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">文件上传</e:section>

<e:section name="body">
	<e:layoutunit region="center" border="false" style="padding: 5px">
		<h2>Basic FileBox</h2>
		<p>The filebox component represents a file field of the forms.</p>
		<div style="margin: 20px 0;"></div>
		<div class="easyui-panel" title="Upload File"
			style="width: 400px; padding: 30px 70px 50px 70px">
			<form id="ff" method="post" action="${root}/file/upload" enctype="multipart/form-data">
				<input type="hidden" name="bizId" value="C3CD06B2-1AE5-481F-A3BF-ADC177CD586F" />
				<input type="hidden" name="uploaderId" value="BD817FA7-716E-11E5-86C6-D8CB8A43F8DD" />
				<input type="hidden" name="uploaderName" value="管理员" />

				<div style="margin-bottom: 20px">
					<div style="margin-bottom: 5px">
					上传分类: <e:textbox id="category"></e:textbox>
					</div>
					<div style="margin-bottom: 5px">
						<e:selectBooleanCheckbox id="allowChange">允许修改</e:selectBooleanCheckbox><br>
					</div>
					<div style="margin-bottom: 5px">
						<e:selectBooleanCheckbox id="isEncrypt">加密文件</e:selectBooleanCheckbox><br>
					</div>
					<div style="margin-bottom: 5px">
						排序号：<e:numberspinner id="serial" value="0"></e:numberspinner><br>
					</div>
					<div style="margin-bottom: 5px">
						自定义标签：<e:textbox id="tags"></e:textbox>
					</div>
				</div>

				<div style="margin-bottom: 10px">
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
		<c:if test="${type != null}">
			<h2>Type: ${type}</h2>
			<h2>Message: ${message}</h2>
			<h2>Data: <%=JsonUtils.fromObject(request.getAttribute("data"))%></h2>
			
			<c:forEach var="m" items="${data}">
				<a target="_blank" href="${root}/file/get?id=<c:out value="${m.value.frId}"></c:out>"><c:out value="${m.value.fileName}"></c:out></a>
				<br>
			</c:forEach>
		</c:if>
		<c:if test="${exception != null}">
			<h2>Exception: ${exception}</h2>
		</c:if>
	</e:layoutunit>
</e:section>

<%@ include file="/shared/_simple.jsp"%>