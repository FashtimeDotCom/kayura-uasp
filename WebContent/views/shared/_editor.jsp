<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><e:renderSection name="title" /></title>
	<e:resources location="res" theme="${theme}" />
	<e:renderSection name="head" />
</head>
<e:body full="true" style="overflow:hidden;padding: 10px;">
	<e:renderSection name="body"></e:renderSection>
</e:body>
</html>