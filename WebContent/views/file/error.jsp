<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<h2>文件上传失败。</h2>
	<c:if test="${exception != null}">
		<h3>Exception: ${exception.message}</h3>
	</c:if>
</body>
</html>