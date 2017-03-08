<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../bsp/layout/taglibnew.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>山东省综治委铁路护路联防平台</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
</head>

<body>

<style type="text/css">
</style>

<script type="text/javascript">
	
	jQuery.ajaxSetup({cache:false});//ajax不缓存

	
	//修改个人信息
	function updateMsg(){
		var result = $.ajax({
			url: '${ctx}/user/updateUserMsg',
            data: $("#msgUpForm").serializeArray(),  
            type: 'post',  
            dataType: 'json',  
            async: false,  
            cache: false
			}).responseText;
		if(result == 1){
			$.messager.alert('提示',"用户修改成功",'info');
		}else{
			$.messager.alert('提示',"用户修改失败",'info');
		}
	}
	
</script>

<form id="msgUpForm" style="margin:10px 5px; text-align:center; font-size:12px">
	<input id="gender" name="gender" value="${userGender}" type="hidden">
	<input name="userAccount" type="hidden" value="${userAccount}">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
		<tr>
			<td width="10%" align="left">姓名：</td>
			<td width="90%" align="left">
				<input name="userName" value="${row.userName}" style="width:200" class="easyui-validatebox" 
				required="true" validType="length[2,16]">
			</td>
		</tr>
		<tr>
			<td width="10%" align="left">性别：</td>
			<td width="90%" align="left">
				<c:choose>
					<c:when  test="${userGender==1}" >
						<input type="radio" name="userGender" value="1" checked/>男
						<input type="radio" name="userGender" value="2" />女
					</c:when >
					<c:when  test="${userGender==2}" >
						<input type="radio" name="userGender" value="1" />男
						<input type="radio" name="userGender" value="2" checked/>女
					</c:when >
					<c:otherwise>
						<input type="radio" name="userGender" value="1" />男
						<input type="radio" name="userGender" value="2" />女
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td width="10%" align="left">联系电话：</td>
			<td width="90%" align="left">
				<input name="userTelephone" value="${row.userTelephone}" style="width:200" maxlength="16">
			</td>
		</tr>
		<tr>
		<td width="10%" align="left">邮件地址：</td>
			<td width="90%" align="left">
				<input name="mail" value="${row.mail}" style="width:200" maxlength="32">
			</td>
		</tr>
		<tr>
			<td width="10%" align="left">QQ或微信：</td>
			<td width="90%" align="left">
				<input name="qqWeixin" value="${row.qqWeixin}" style="width:200" maxlength="16">
			</td>
		</tr>
		<tr>
			<td width="10%" align="left">备注：</td>
			<td width="90%" align="left">
				<textarea style="font-size:12px;" name="userDesc" cols="30" rows="5">${row.userDesc}</textarea>
			</td>
		</tr>
		<tr>
			<td width="10%" align="left"></td>
			<td width="90%" align="left">
				<a href="#" id="btn-add" onclick="updateMsg();" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
			</td>
		</tr>
	</table>
</form>

</body>

</html>