<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../bsp/layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>游泳报名系统</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Swimming Registration System">
</head>

<body>

<style type="text/css">
</style>

<script type="text/javascript">
	
	jQuery.ajaxSetup({cache:false});//ajax不缓存

	//校验密码
	$.extend($.fn.validatebox.defaults.rules, {
	    chkPwd: {
	        validator: function(value,param){
        		if(value.length < param[0]){
        			$.fn.validatebox.defaults.rules.chkPwd.message = '密码不能少于'+param[0]+'位';
	        	}else{
	        		if(RegExp("[\\u4E00-\\u9FFF]+", "g").test(value)){
						$.fn.validatebox.defaults.rules.chkPwd.message = '密码不能出现汉字';
						return false;
					}else{
						if(value.length > param[1]){
							$.fn.validatebox.defaults.rules.chkPwd.message = '密码长度不能超过'+param[1]+'位';
						}else{
							return true;
						}
					}
		        }
	        }
	    }
	});

	//判断密码输入是否一致
	$.extend($.fn.validatebox.defaults.rules, {
	    equalTo: {
	        validator: function(value,param){
	            return value == $(param[0]).val();
	        },
	        message: '新密码不一致'
	    }
	});

	//校验原始密码是否正确
	function chkOldPwd(){
		var oldPwd = $("#oldPassword");
		if(oldPwd.val().length > 0){
			var result = $.ajax({
				url: '${ctx}/user/chkOldPwd',
                data: 'oldPassword='+oldPwd.val(),  
                type: 'post',  
                dataType: 'json',  
                async: false,  
                cache: false
				}).responseText;
			if(result == 0){
				$.messager.alert('提示',"原始密码错误，请重新输入",'info');
				oldPwd.val("");
				oldPwd.focus();
			}
		}
	}
	
	//修改密码
	function updatePwd(){
		var newPwd = $("#reUserPassword");
		if(newPwd.val().length > 0){
			var result = $.ajax({
				url: '${ctx}/user/updateNewPwd',
                data: 'newPassword='+newPwd.val(),  
                type: 'post',  
                dataType: 'json',  
                async: false,  
                cache: false
				}).responseText;
			if(result == 1){
				$.messager.alert('提示',"密码修改成功",'info');
			}else{
				$.messager.alert('提示',"密码修改失败",'info');
			}
		}
	}
	
</script>

<div>
<form id="pwdUpForm" style="margin:10px 5px; text-align:center;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
		<tr>
			<td width="10%" align="left">原始密码：</td>
			<td width="90%" align="left">
				<input id="oldPassword" name="oldPassword" type="password" style="width:200" class="easyui-validatebox" 
					required="true" validType="chkPwd[1,32]" onblur="chkOldPwd()">
			</td>
		</tr>
		<tr>
			<td width="10%" align="left">新密码：</td>
			<td width="90%" align="left">
				<input id="userPassword" name="userPassword" type="password" style="width:200" class="easyui-validatebox" 
					required="true" validType="chkPwd[1,32]">
			</td>
		</tr>
		<tr>
			<td width="10%" align="left">新密码重复：</td>
			<td width="90%" align="left">
				<input id="reUserPassword" name="reUserPassword" type="password" style="width:200" class="easyui-validatebox" 
					required="true" validType="equalTo['#userPassword']">
			</td>
		</tr>
		<tr>
			<td width="10%" align="left"></td>
			<td width="90%" align="left">
				<a href="#" id="btn-add" onclick="updatePwd();" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			</td>
		</tr>
	</table>
</form>
</div>

</body>

</html>