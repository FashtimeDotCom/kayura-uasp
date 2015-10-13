<head><%@ tag language="java" pageEncoding="UTF-8"%>
	<%@ attribute name="title" type="java.lang.String" required="true" %><title>${title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<link rel="stylesheet" type="text/css" href="${root}/res/easyui/themes/${theme}/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${root}/res/easyui/themes/icon.css" />
	<script type="text/javascript" src="${root}/res/js/jquery.min.js"></script>
	<script type="text/javascript" src="${root}/res/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${root}/res/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${root}/res/js/juasp-core.js"></script>
	<script type="text/javascript">
		juasp.siteUrl = "${root}";
		juasp.loginName = "${loginName}";
	</script>
	<jsp:doBody></jsp:doBody></head>
