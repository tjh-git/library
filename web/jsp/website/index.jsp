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
		<div class="top">
			<div class="tpxw">
				<ul class="image_news">
					<c:forEach items="${tpxwList }" var="news" >
						<li>
							<a href="${ctx}/newsDetail?sno=${news.newsId}"><img src="${ctx}/${news.newsImageUrl}" width="300" height="250" /></a>
						</li>						
					</c:forEach>										
				</ul>
				<ul class="num_bar">
					<c:forEach items="${tpxwList }" var="news" varStatus="status">
						<li><a href="#">${status.count }</a></li>						
					</c:forEach>				
				</ul>
			</div>
			<div class="ldjh">
				<div class="ldjh_title"><a href="${ctx }/website/commonMore?perTypeCode=8&navIndex=3" style="float:right;font-size:12px;color:#000000;margin-right:420px;padding-top:0px;*padding-top:10px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>政务要闻</div>
				<div class="ldjh_content">
					<ul>
	               	<c:forEach items="${zwywList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 12}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 12)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 12)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 12)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>					
					</ul>
					<div class="ldjh_content_detail">
						<div class="ldjh_content_detail_title"><a href="${ctx}/newsDetail?sno=${zwywFirst.newsId}" title="${zwywFirst.newsTitle }" >${fn:substring(zwywFirst.newsTitle, 0, 25)}</a></div>
						<p>
	               		<c:choose>
	               			<c:when test="${fn:length(zwywFirst.newsContent) > 200}">
	               				${fn:substring(zwywFirst.newsContent, 0, 200)}...
	               			</c:when>
	               			<c:otherwise>
	               				${zwywFirst.newsContent}...
	               			</c:otherwise>
	               		</c:choose>                  	
	                  	<a href="${ctx}/newsDetail?sno=${zwywFirst.newsId}"  >【查看详细】</a>						
						</p>
					</div>
					<div class="cb"></div>
				</div>
			</div>			
			<div class="cb"></div>
		</div>
		<div class="middle">
			<div class="xwdt">
				<div class="xwdt_title"><a href="${ctx }/website/commonMore?perTypeCode=1&navIndex=4" style="float:right;font-size:12px;color:#000000;margin-right:10px;padding-top:0px;*padding-top:20px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>领导活动</div>
				<div class="xwdt_content">
					<c:forEach items="${ldhdTopList }" var="news">
					<div class="xwdt_content_first">
						<div>${news.newsTitle }</div>
						<p>
							${news.newsContent }<a href="${ctx}/newsDetail?sno=${news.newsId}">【查看详细】</a>
						</p>
					</div>						
					</c:forEach>
					<ul class="news">
	               	<c:forEach items="${ldhdList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 30}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>
					</ul>					
				</div>
			</div>
			<div class="gztz">
				<div class="xwdt_title"><a href="${ctx }/website/commonMore?perTypeCode=3&navIndex=5" style="float:right;font-size:12px;color:#000000;margin-right:10px;padding-top:0px;*padding-top:20px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>工作动态</div>
				<div class="xwdt_content">
					<c:forEach items="${gzdtTopList }" var="news">
					<div class="xwdt_content_first">
						<div>${news.newsTitle }</div>
						<p>
							${news.newsContent }<a href="${ctx}/newsDetail?sno=${news.newsId}">【查看详细】</a>
						</p>
					</div>						
					</c:forEach>				
					<ul class="news">
	               	<c:forEach items="${gzdtList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 30}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 30)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><span class="date">[${fn:substring(news.newsPublishTime, 0, 10)}]</span><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>
					</ul>					
				</div>				
			</div>
			<div class="cb"></div>
		</div>
		<div class="bottom">
			<div class="xxgk">
				<div class="common_title">
					<a href="${ctx }/website/commonMore?perTypeCode=4&navIndex=6" style="float:right;font-size:12px;color:#000000;margin-right:10px;padding-top:0px;*padding-top:10px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>媒体报道
				</div>
				<div class="common_content">
					<ul class="news">
	               	<c:forEach items="${mtbdList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 15}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li> <a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>
					</ul>				
				</div>
			</div>		
			<div class="xxgk">
				<div class="common_title">
					<a href="${ctx }/website/commonMore?perTypeCode=5&navIndex=7" style="float:right;font-size:12px;color:#000000;margin-right:10px;padding-top:0px;*padding-top:10px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>经验交流
				</div>
				<div class="common_content">
					<ul class="news">
	               	<c:forEach items="${jyjlList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 15}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>
					</ul>				
				</div>
			</div>
			<div class="zcfg">
				<div class="common_title">
					<a href="${ctx }/website/commonMore?perTypeCode=6&navIndex=8" style="float:right;font-size:12px;color:#000000;margin-right:10px;padding-top:0px;*padding-top:10px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>先进典型
				</div>
				<div class="common_content">
					<ul class="news">
	               	<c:forEach items="${xjdxList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 15}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>
					</ul>				
				</div>			
			</div>
			<div class="cyxz">
				<div class="common_title">
					<a href="${ctx }/website/commonMore?perTypeCode=9&navIndex=9" style="float:right;font-size:12px;color:#000000;margin-right:10px;padding-top:0px;*padding-top:10px!important;"><img src="${ctx}/resources/image/website/icon_more.png" /></a>护路文化
				</div>
				<div class="common_content">
					<ul class="news">
	               	<c:forEach items="${hlwhList }" var="news">
	               		<c:choose>
	               			<c:when test="${fn:length(news.newsTitle) > 15}">
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:when>
	               			<c:otherwise>
		           				<c:choose>
		           					<c:when test="${news.newsIsLinkTitle==1 }">
		           						<li><a href="${news.newsLinkUrl}" title="${news.newsTitle }" target="_blank">${news.newsTitle }</a></li>
		           					</c:when>
		           					<c:when test="${news.newsIsAccessory==1 }">
		           						<li><a href="${ctx}${news.newsAccessoryUrl}" title="${news.newsTitle }" target="_blank">${fn:substring(news.newsTitle, 0, 15)}...</a></li>
		           					</c:when>            					
		           					<c:otherwise>
		           						<li><a href="${ctx}/newsDetail?sno=${news.newsId}" title="${news.newsTitle }">${news.newsTitle }</a></li>
		           					</c:otherwise>
		           				</c:choose>               			
	               			</c:otherwise>
	               		</c:choose>
	               	</c:forEach>
					</ul>				
				</div>			
			</div>
			<div class="cb"></div>
		</div>
		<div class="xc">
			<img src="${ctx}/resources/image/website/img_tl.png" />
		</div>
		<div class="yqlj">
			<div class="yqlj_title"></div>
			<div class="yqlj_content">
				<p>
					<span>政府相关部门：</span>
					<a href="http://www.shandong.gov.cn/">山东省人民政府</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.jinan.gov.cn/">济南市人民政府</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.sdzfzz.cn/">山东政法综治网</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.sdga.gov.cn/">山东省公安厅</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.sdsft.gov.cn/">山东省司法行政网</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://sdfy.chinacourt.org/index.shtml">山东省高级人民法院</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.sdjcy.gov.cn/">山东省人民检察院</a>
				</p>
				<p>
					<span>铁路相关部门：</span>
					<a href="http://www.nra.gov.cn/gzhd/tsxx/">国家铁路局</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.rebcenter.com/">铁路建设工程网</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.sddtj.com/">山东省地方铁路局</a>&nbsp;&nbsp;
				</p>	
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
<script type="text/javascript">
	$(function(){
		var index=0;
		$(".num_bar li").bind({
			click:function(){
				//alert($(this).index())
				if($(this).index()!=index){
					$(".image_news li").slideUp();
					$($(".image_news li")[$(this).index()]).slideDown();
					index=$(this).index();
				}
			}
		});
	});
	var triggerIndex=0;
	setInterval("triggerPic()", 2000);
	function triggerPic(){
		$($(".num_bar li")[triggerIndex]).trigger('click');
		if(triggerIndex==3){
			triggerIndex=0	
		}else{
			triggerIndex++;
		}
		
	}
</script>