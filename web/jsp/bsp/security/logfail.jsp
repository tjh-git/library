<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
	<%@ include file="../layout/taglibnew.jsp" %>
	<style type="text/css">
	*{margin:0;padding:0;list-style-type:none;}
	a.mya,a.mya:visited{color:#5e5e5e; text-decoration:none;}
	a.mya:hover{color:red;text-decoration:underline;}
	.content {width:741px;margin:100px auto;height:364px;background:url(${ctx}/resources/image/login/bg_header.png) left top no-repeat;position:relative;}
	#login_table{position:absolute;top:130px;right:70px;font-size:12px;}
	.ipt {border:1px solid #ddd;padding:5px 5px;width:220px;outline:none;border-radius:3px;background-color:#fff;}
	.ipt:focus {background-color:#fefefe;box-shadow:0 0 3px #aaa;}
	#login_table th,#login_table td {height:40px;}
	#submit_btn {background-color:#55b8ed;border:none;border-radius:3px;box-shadow:0 0 3px #aaa;width:90px;height:40px;color:#fff;font-size:14px;font-weight:bold;cursor:pointer;outline:none;text-shadow:0 1px 1px #777;}
	#submit_btn:hover {background-color:#f0f0f0;color:#666;text-shadow:1px 1px 1px #fff;}
    </style>
    <script type="text/javascript">
	jQuery.ajaxSetup({cache:false});//ajax不缓存
	//支持Enter键登录
	document.onkeydown = function(e){
		if(!e) e = window.event;
		if((e.keyCode || e.which) == 13){
			var obtnLogin=document.getElementById("submit_btn");
			obtnLogin.focus();
		}
	};
	$(function(){
		//提交表单
		$('#submit_btn').click(function(){
			if($('.username').val() == ''){
				$.messager.alert('提示',"请填写用户名！",'info');
				$('.username').focus();
			}else if($('.password').val() == ''){
				$.messager.alert('提示',"请填写登录密码！",'info');
				$('.password').focus();
			}else{
				$('#loginForm').submit();
			}
		});
	});
	
	//返回首页
	function goBack(){
		window.location.href="${ctx}/index.jsp";
	}
	</script>
</head>
<body style="background:#9bd3ff; ">
	<div class="content">
		<form id="loginForm" action="${ctx}/j_spring_security_check" method="post">
			<table id="login_table" cellpadding="0" cellspacing="0">
				<tbody>
				<tr>
						<th width="20%" align="left">用户名：</th>
						<td width="60%" >
						<input type="text" name="j_username" class="easyui-textbox" style="width:150px;" data-options="prompt:'输入用户名',iconCls:'icon-man',iconWidth:38" />
						</td>
					</tr>
					<tr>
						<th align="left">密&nbsp;&nbsp;&nbsp;码：</th>
						<td ><input type="password" name="j_password" class="easyui-textbox" style="width:150px;" data-options="prompt:'输入密码',iconCls:'icon-lock',iconWidth:38"></td>
					</tr>					
					<tr>
						<td colspan="2" style="text-align:center;">
							<button id="submit_btn" type="button">登录</button>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="color:red;">用户名或密码错误</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>