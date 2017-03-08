<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>山东省综治委铁路护路联防平台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="Simple">
<link rel="icon" href="${ctx}/resources/favicon.ico" />
<%@ include file="jsp/bsp/layout/taglibnew.jsp"%>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	list-style-type: none;
}

a.mya, a.mya:visited {
	color: #5e5e5e;
	text-decoration: none;
}

a.mya:hover {
	color: red;
	text-decoration: underline;
}

.content {
	width: 100%;
	margin: 0 auto;
	height: 364px;
	background: url(${ctx}/resources/image/login/header_bean.png) no-repeat;
	position: relative;
	background-size: contain;
}

#login_table {
	position: absolute;
	font-size: 12px;
	margin-left: 600px;
	margin-top: 115px;
	height: 190px;
}

.ipt {
	padding: 5px 5px;
	width: 220px;
	outline: none;
	border-radius: 3px;
	background-color: #fff;
}

.ipt:focus {
	background-color: #fefefe;
	box-shadow: 0 0 3px #aaa;
}

#submit_btn {
	background-color: #55b8ed;
	border: none;
	border-radius: 3px;
	box-shadow: 0 0 3px #aaa;
	width: 90px;
	height: 40px;
	color: #fff;
	font-size: 14px;
	font-weight: bold;
	cursor: pointer;
	outline: none;
	text-shadow: 0 1px 1px #777;
}

#submit_btn:hover {
	background-color: #f0f0f0;
	color: #666;
	text-shadow: 1px 1px 1px #fff;
}

.zt {
	font-size: 1.9em;
	text-align: center;
	line-height: 64px;
	text-align: center;
	overflow: hidden;
	letter-spacing: 30px;
	color: white;
	font-family: "microsoft yahei", "Times New Roman", "宋体", Times, serif;
	width: 1000px;
	margin: 0 auto;
}

.section_all {
	width: 100%;
	height: 323px;
	background: url(${ctx}/resources/image/login/header_bean.png) no-repeat;
	background-size: 100%;
}

.section_all_div {
	width: 1000px;
	height: auto;
	overflow: hidden;
	margin: 0 auto;
}

.section_all_div_left {
	width: 500px;
	height: 400px;
	float: left;
	margin-top: -30px;
}

.section_all_div_left img {
	width: 100%;
	height: 100%;
}

.section_all_div_right {
	width: 318px;
	height: 229px;
	overflow: hidden;
	margin-top: 70px;
	border-radius: 15px;
	margin-left: 550px;
}

.section_all_div_header {
	text-align: center;
	height: 33px;
	width: 100%;
	background: #51aef0;
	letter-spacing: 20px;
	font-family: "SimHei";
	font-size: 1.4em;
	color: white;
	line-height: 35px;
}

.table {
	width: 100%;
	height: 100%
}

#submit_btn {
	width: 80%;
	letter-spacing: 10px;
	font-size: 1.3em;
	border-radius: 10px;
}

body {
	background-size: 100%;
	background-image: url(${ctx}/resources/image/login/tsgl.png);
	width: 100%;
	height: 100%;
}

.div_section {
	height: 435px;
	overflow: hidden;
	margin: auto;
	position: absolute;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
}

.td_input {
	width: 170px;
	height: 25px;
	border-radius: 5px;
	border: 1px solid #929090;
	outline: none;
	color: #929090;
}

.input_td_first {
	width: 26px;
	height: 25px;
	background: #51aef0;
	float: left;
	position: absolute;
	margin-left: 145px;
	border-radius: 0px 5px 5px 0px;
	margin-top: 1px;
}

.input_td_first img {
	width: 80%;
	height: 80%;
	padding: 2px;
}

.input_td_two {
	width: 26px;
	height: 25px;
	background: #51aef0;
	float: left;
	position: absolute;
	margin-left: 145px;
	border-radius: 0px 5px 5px 0px;
	margin-top: 1px;
}

.input_td_two img {
	width: 80%;
	height: 80%;
	padding: 2px;
}
</style>
<script type="text/javascript">
	jQuery.ajaxSetup({
		cache : false
	});//ajax不缓存
	//支持Enter键登录
	document.onkeydown = function(e) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			var obtnLogin = document.getElementById("submit_btn");
			obtnLogin.focus();
		}
	};
	$(function() {
		//提交表单
		$('#submit_btn').click(function() {
			if ($('.username').val() == '') {
				$.messager.alert('提示', "请填写用户名！", 'info');
				$('.username').focus();
			} else if ($('.password').val() == '') {
				$.messager.alert('提示', "请填写登录密码！", 'info');
				$('.password').focus();
			} else {
				$('#loginForm').submit();
			}
		});
	});

	//返回首页
	function goBack() {
		window.location.href = "${ctx}/index.jsp";
	}
</script>
</head>
<body>
	<div
		style="background-image:url(${ctx}/resources/image/login/right.png)"></div>

	<div class="div_section">
		<p class="zt">图书馆管理系统</p>
		<div class="section_all">
			<div class="section_all_div">
				<div class="section_all_div_left">
					<img src="${ctx}/resources/image/login/headerr.png">
				</div>
				<div class="section_all_div_right">
					<div class="section_all_div_header">
						<p>登录</p>
					</div>
					<form id="loginForm" action="${ctx}/j_spring_security_check"
						method="post" style="height: 100%;">
						<table cellpadding="0" cellspacing="0" class="table">
							<tbody style="background: white; width: 100%; height: 100%;">
								
								<tr>
									<td
										style="padding-left: 60px; position: absolute; padding-top:15px;">
										<div class="input_td_first">
											<img src="${ctx}/resources/image/login/right.png">
										</div> <input type="text" name="j_username" class="td_input"
										value="请输入用户名"
										data-options="prompt:'输入用户名',iconCls:'icon-man',iconWidth:38"
										onfocus="if(value=='请输入用户名') {value=''}"
										onblur="if (value=='') {value='请输入用户名'}" />
									</td>
								</tr>
								<div style="clear:both"></div>
								<tr>
									<td style="padding-left: 60px; padding-top:15px;position: absolute;">
										<div class="input_td_two">
											<img src="${ctx}/resources/image/login/right2.png">
										</div> <input type="password" name="j_password" class="td_input"
										value="请输入密码"
										data-options="prompt:'输入密码',iconCls:'icon-lock',iconWidth:38"
										onfocus="if(value=='请输入密码') {value=''}"
										onblur="if (value=='') {value='请输入密码'}">
									</td>
								</tr>

								<tr>
									<td colspan="2" style="text-align: center; padding-bottom:25px;">
										<button id="submit_btn" type="button">登录</button>
									</td>
								</tr>
								 <tr>
									<td colspan="2" style="color: red; ">${message}</td>
								</tr> 
							</tbody>
							
						</table>

					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>