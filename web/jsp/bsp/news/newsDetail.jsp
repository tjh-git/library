<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../bsp/layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>                                       
<!doctype html>

<html>
<head>
	<title>信息发布明细</title>
    <meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css" />
	<meta name="renderer" content="ie-stand" />
</head>

<body>

 	<style type="text/css" >
 		.table2{width:100%;}
    	.table2 td{border:solid 1px #000000; text-align:center; font-size:12px;padding:5px 0 5px 0;}
    </style>
<form id="newsDetailForm"  method="post" style="">
	<table class="table2" width="95%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="25%" >标题：</td>
			<td width="75%" colspan="3">
				<input type="text" id="newsTitle" readonly="readonly" name="newsTitle" style="width: 90%;height: 25px" />
			</td>
		</tr>
		<tr>
			<td width="25%" >副标题：</td>
			<td width="75%" colspan="3">
				<input type="text" id="newsSubTitle" name="newsSubTitle" readonly="readonly" style="width: 90%;height: 25px" />
			</td>
		</tr>
		<tr style="display: none;">
			<td width="100%" colspan="4">
				置顶：<input type="radio" name="newsIsTop"  value="0" checked="checked" />否
				<input type="radio" name="newsIsTop" value="1" />是
			</td>
		</tr>
		<tr>
			<td width="25%" align="left">信息类型：
			<select id="newsType" name="newsType" style="width: 50%;height: 30px">
				<c:forEach items="${newsTypeList}" var="nType">
					<option value="${nType.newsTypeCode}" ><c:out value="${nType.newsTypeName}" /></option>
				</c:forEach>
				</select>
			</td>
			<td width="25%" align="left">
			作者：<input id="newsAuthor" name="newsAuthor" readonly="readonly" />
			</td>
			<td width="25%" align="left">
			发布日期：<input id="newsPublishTime" name="newsPublishTime" readonly="readonly" />
			</td>
			<td width="25%" align="left">
			单位：<input id="newsCompany" name="newsCompany" />
			</td>
		</tr>
		<tr>
			<td width="25%" align="right">链接标题：</td>
			<td  align="right" colspan="3">
			<c:if test="${news.newsIsLinkTitle==0}">
				否
			</c:if>
			<c:if test="${news.newsIsLinkTitle==1}">
			<input type="text"  id="newsLinkUrl" name="newsLinkUrl" readonly="readonly" value="${news.newsLinkUrl}" /> 
			</c:if>
			</td>
		</tr>
		<tr>
			<td width="25%" align="right">图片信息</td>
			<td width="25%" align="left">

				<input type="radio" name="newsIsImageNews"  value="0" checked="checked" onchange="chnewsImage()" />否
				<input type="radio" name="newsIsImageNews" value="1"   onchange="chnewsImage()"/>是
			</td>
			<td width="25%" align="left"  >
				<div id="newsImageQueue"  style="width: 300px; height: 30px;overflow: auto;display: none;"></div>
			</td>
			<td width="25%">
			<input type="file" style="display: none"  name="newsImage" id="newsImage">
			<input type="hidden" name="newsImageUrl" id="newsImageUrl">
			</td>
		</tr>
		<tr>
			<td width="25%" align="right">附件信息</td>
			<td width="25%" align="left">
				<input type="radio" name="newsIsAccessory"  value="0" checked="checked" onchange="chnewsAccessory()" />否
				<input type="radio" name="newsIsAccessory" value="1"   onchange="chnewsAccessory()"/>是
			</td>
			<td width="25%" align="left"  >
				<div id="newsAccessoryQueue"  style="width: 300px; height: 30px;overflow: auto;display: none;"></div>
			</td>
			<td width="25%">
			<input type="file" style="display: none"  name="newsAccessory" id="newsAccessory">
			<input type="hidden" name="newsAccessoryUrl" id="newsAccessoryUrl">
			</td>
		</tr>
		<tr>
			<td  align="center" colspan="4">
				<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
			</td>
		</tr>
	</table>
	
</form>
</body>

</html>