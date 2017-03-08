<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">
<html>
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
	</head>
	
	<body>
	<style type="text/css">
		#accessDenied
		{
            margin-top: 50px;
            margin-left: 50px;
			padding: 15px 50px;
			width: 550px;
			border: 1px solid green;
			background-color: yellow;
			text-align: center;
		}
    </style>

	<div id ="accessDenied">
		<a style="font-weight:bold;font-family:微软雅黑; font-size:18px;">对不起，您没有此功能的访问权限！</a>
	</div>
	
	</body>
	
</html>