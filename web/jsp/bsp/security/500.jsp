<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>山东省综治委铁路护路联防平台</title>
		<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
		<meta name="renderer" content="ie-stand"> 
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="Simple" />
		<link rel="icon" href="${ctx}/resources/favicon.ico"></link>
		<style type="text/css">
			*{margin:0;padding:0;}
		</style>
	</head>
	
	<body>
	<div style="height:100px;"></div>
	<div id ="accessDenied" style="width:600px;margin:0 auto;height:300px;" >
		<div style="float:left;background: url('<%=request.getContextPath()%>/resources/image/main/404.png') 1px 1px no-repeat;height:150px;width:130px;">
			
		</div>
		<div style="width:450px;height:150px;padding-top:50px;float:left;font-weight:bold;font-family:微软雅黑; font-size:18px;">
			对不起，此功能权限暂不开放，请联系系统管理员！
		</div>
	</div>
	</body>
	
</html>