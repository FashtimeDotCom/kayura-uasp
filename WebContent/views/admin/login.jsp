<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">登录页</e:section>

<e:section name="head">
	<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden;
	font-size: 14px;
}

.login_negative {
	width: 910px;
	height: 420px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -480px;
	margin-top: -310px;
}

.login_header {
	height: 100px;
	overflow: hidden;
	margin: 0;
	padding: 0;
}

.login_shadow {
	height: 416px;
	padding: 1px;
	background: #fff;
	border: #888888 1px solid;
	-moz-box-shadow: 3px 3px 4px #888888;
	-webkit-box-shadow: 3px 3px 4px #888888;
	box-shadow: 3px 3px 4px #888888;
	/* For IE 8 */
	-ms-filter:
		"progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135, Color='#888888')";
	/* For IE 5.5 - 7 */
	filter: progid:DXImageTransform.Microsoft.Shadow(Strength=4, Direction=135,
		Color='#888888');
}

.login_imgshow {
	float: left;
	width: 600px;
	height: 416px;
	overflow: hidden;
}

.login_window {
	float: left;
	width: 300px;
}
</style>

</e:section>

<e:section name="body">
	<form action="${root}/j_spring_security_check" method="post">
		<div class="login_negative">
			<div class="login_header" style="height: 100px; position: relative;">
				<img src='${root}/res/images/login/logo.png'
					style="position: absolute; left: -90px; top: 0;" alt="" />
			</div>
			<div class="login_shadow">
				<div class="login_imgshow">
					<img src="${root}/res/images/login/img1.jpg" alt="" />
				</div>
				<div class="login_window">
					<img src="${root}/res/images/login/icon1.png" alt="" />

					<div style="padding: 30px 30px 10px 30px">
						<div style="margin-bottom: 10px">
							<e:textbox id="j_username"
								style="width:100%;height:30px;padding:8px" iconCls="icon-man"
								iconWidth="30" prompt="用户名/手机号"></e:textbox>
						</div>
						<div style="margin-bottom: 10px">
							<e:textbox id="j_password"
								style="width:100%;height:30px;padding:8px" iconCls="icon-lock"
								iconWidth="30" prompt="确认密码"></e:textbox>
						</div>
						<div style="margin-bottom: 20px">
							<label><input name="" type="checkbox" />记住用户名</label>
						</div>
						<div style="margin-bottom: 10px">
							<e:linkbutton text="登录" iconCls="icon-ok"
								style="width:100%;height:30px;"></e:linkbutton>
						</div>
						<div style="margin-bottom: 20px">
							<a href="javascript:void(0)">忘记登录密码？</a> <a
								href="javascript:void(0)"
								style="float: right; margin-right: 5px">自助注册</a>
						</div>
						<div id="login-error" style="height: 35px;">${error}</div>
					</div>
				</div>
			</div>
			<div style="float: right; margin: 15px">
				<c:if test="${runMode == 'dev'}">
					<a href="${root}/example/" target="_blank">开发示例库</a>
				</c:if>
			</div>
		</div>
	</form>
</e:section>

<%@ include file="/shared/_simple.jsp"%>