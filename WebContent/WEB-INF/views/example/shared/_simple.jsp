<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><e:renderSection name="title"/></title>
	<e:resources location="res" theme="${theme}" />
	<e:renderSection name="head" />
</head>
<body>
	<!-- Body -->
	<e:renderSection name="body"></e:renderSection>
</body>
</html>