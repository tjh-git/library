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
	<div id="position">您的当前位置：<a href="${ctx}/website/index">首页</a> >> ${title }  </div>
	<div id="main">
		<div class="main_left">
			<div class="cylm">
				<div class="cylm_title">常用导航</div>
				<div style="height:20px;"></div>
				<ul class="cylm_sub_title">
					<li><a href="${ctx }/website/commonMore?perTypeCode=8&navIndex=3">政务要闻</a></li>
					<li><a href="${ctx }/website/commonMore?perTypeCode=1&navIndex=4">领导活动</a></li>
					<li><a href="${ctx }/website/commonMore?perTypeCode=3&navIndex=5">工作动态</a></li>
					<li><a class="mhlt" href="#">门户论坛</a></li>
				</ul>
			</div>
		</div>
		<div class="main_right">
			<div class="more_title">
				${title }
			</div>
			<ul class="more_news">
              	<c:forEach items="${pageBean.dispList }" var="news">
             		<c:choose>
             			<c:when test="${fn:length(news.newsTitle) > 50}">
             				<c:choose>
								<c:when test="${news.newsIsLinkTitle==1 }">
									<li><span>[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" target="_blank">${fn:substring(news.newsTitle, 0, 50)}...</a></li>
								</c:when>
								<c:when test="${news.newsIsAccessory==1 }">
									<li><span>[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" target="_blank">${fn:substring(news.newsTitle, 0, 50)}...</a></li>
								</c:when>            					
								<c:otherwise>
						        	<li><span>[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 50)}...</a></li>
								</c:otherwise>
							</c:choose> 
             			</c:when>
             			<c:otherwise>
             				<c:choose>
								<c:when test="${news.newsIsLinkTitle==1 }">
									<li><span>[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" target="_blank">${news.newsTitle }</a></li>
								</c:when>
								<c:when test="${news.newsIsAccessory==1 }">
									<li><span>[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" target="_blank">${news.newsTitle }</a></li>
								</c:when>            					
								<c:otherwise>
						        	<li><span>[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
								</c:otherwise>
							</c:choose> 
             			</c:otherwise>
             		</c:choose>             		
              	</c:forEach>			

				<li style="background:none;text-align:center;">
					<div style="text-align:center;">
						<a href="${ctx}/website/commonMore?perTypeCode=${perTypeCode}&currentPage=1">首页</a>&nbsp;&nbsp;
						<c:choose>
							<c:when test="${pageBean.currentPage==1}">
							<a href="${ctx}/website/commonMore?perTypeCode=${perTypeCode}&currentPage=1">上一页</a>&nbsp;&nbsp;					
							</c:when>
							<c:otherwise>
							<a href="${ctx}/website/commonMore?perTypeCode=${perTypeCode}&currentPage=${pageBean.currentPage-1}">上一页</a>&nbsp;&nbsp;
							</c:otherwise>
						</c:choose>						
						<a id="num" href="${ctx}/website/commonMore?perTypeCode=${perTypeCode }&currentPage=${pageBean.currentPage+1}">下一页</a>&nbsp;&nbsp;
						<a href="${ctx}/website/commonMore?perTypeCode=${perTypeCode }&currentPage=${pageBean.totalPages}">尾页</a>
					</div>				
				</li>				
			</ul>

		</div>
		<div class="cb"></div>
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
	$("#num").bind({
		click:function(){
			var n_=${pageBean.currentPage+1};
			var t_=${pageBean.totalPages};
			if(n_>t_){
				alert('已到尾页');
				return false;
			}
		}
	});
	$(".mhlt").bind({
		click:function(){
			alert('正在建设中...');
			return false;
		}
	});	
});
</script>