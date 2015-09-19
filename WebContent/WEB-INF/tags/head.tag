<head><%@ tag language="java" pageEncoding="UTF-8"%>
	<%@ attribute name="title" type="java.lang.String" required="true" %><title>${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<link rel="stylesheet" type="text/css" href="${siteUrl}/res/easyui/themes/${theme}/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${siteUrl}/res/easyui/themes/icon.css" />
	<script type="text/javascript" src="${siteUrl}/res/js/jquery.min.js"></script>
	<script type="text/javascript" src="${siteUrl}/res/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${siteUrl}/res/js/juasp-core.js"></script>
	<script type="text/javascript">
		window.siteUrl = "${siteUrl}";
	</script>
	<jsp:doBody></jsp:doBody></head>
