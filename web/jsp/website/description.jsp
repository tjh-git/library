<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>                                       
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>山东省综治委铁路护路联防办公室</title>
    <link rel="icon" href="${ctx}/resources/favicon.ico"></link>
	<link rel="stylesheet" href="${ctx }/resources/css/website.css" type="text/css" />
	<script type="text/javascript" src="${ctx}/resources/jquery-easyui-1.4.2/jquery.min.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="wrap">
  <div id="wrap_inner">
    <div id="position">您的当前位置：<a href="${ctx}/website/index">首页</a> </div>
    <div id="show_main">
      <div id="show_title">机构设置</div>
      <div id="show_subtitle">${commonNews.newsSubTitle}</div>
      <div id="show_info">发布日期：${commonNews.newsPublishTime }&nbsp;&nbsp;&nbsp;&nbsp;浏览次数：${commonNews.newsTotalAccessCount }次&nbsp;&nbsp;&nbsp;&nbsp;[<a href="#" onclick="javascript:history.back();">后退</a>]&nbsp;&nbsp;&nbsp;&nbsp;[<a href="${ctx}/website/index">返回首页</a>] </div>
      <div id="show_content" style="text-indent:2em">
		${news.newsContent }
      </div>
      <div id="show_editor">
      	<c:choose>
      		<c:when test="${fn:contains(commonNews.newsAuthor, '发布人')}">
      		 ${commonNews.newsAuthor }
      		</c:when>
      		<c:otherwise>
      		发布人：&nbsp; ${commonNews.newsAuthor }  
      		</c:otherwise>
      	</c:choose>
      </div>
    </div>
  </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
<script type="text/javascript">
$(function(){
	var navIndex='${navIndex}';
	$(".nav li").removeClass('active');
	$(".nav li").eq(navIndex).addClass('active');
});
</script>