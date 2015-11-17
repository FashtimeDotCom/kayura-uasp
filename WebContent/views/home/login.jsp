<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<e:section name="title">登录页</e:section>

<e:section name="head">
	<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
	overflow: hidden;
	font-size: 12px;
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

.login_window dl dt {
	margin-top: 20px;
	margin-left: 20px;
	line-height: 26px;
}

.login_mar_left {
	padding-left: 40px;
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
					<dl>
						<dt>
							用 户：<e:textbox id="j_username" style="width:180px"></e:textbox>
						</dt>
						<dt>
							密 码：<e:textbox id="j_password" style="width:180px"></e:textbox>
						</dt>
						<dt class="login_mar_left" style="text-align: left;"
							id="btn_login">
							<input name="" type="checkbox" value="记住用户名" id="chk_rember" />记住用户名
							<input type="checkbox" name="chkIsAdmin" id="chkIsAdmin" /> 管理员
						</dt>
						<dt class="login_mar_left" style="text-align: left;">
							<input type="submit" value="登录" /> <input type="reset"
								value="重置">
						</dt>
					</dl>
					<div id="login-error" style="height: 40px;">${error}</div>

					<a href="${root}/example/">EasyUI 示例</a>
				</div>
			</div>
		</div>
	</form>
</e:section>

<%@ include file="/shared/_simple.jsp"%>