<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<script language="javascript">
$(function(){
//搜索框效果
$("#submitspan").click(function(){
if($('#key').val()==''||$('#key').val()=='请输入搜索关键字') 
{alert('请输入搜索关键字，再点击搜索。');
$("#key").focus();}
else
{$("#sch").submit();}
});

$("#key").focus(function(){if($(this).val()=="请输入搜索关键字"){$(this).val("");}});
$("#key").blur(function(){if($(this).val()==""){$(this).val("请输入搜索关键字")}})
$("#mhlt").bind({
	click:function(){
		alert('正在建设中...');
		return false;
	}
}); 
})
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>                                         
	<div id="header">
		<div style="position:relative;width:1100px;margin:0 auto;height:205px;">
<div style="position:absolute;">		
<object width="1000" height="205" 
codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0" 
classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"> 
<param value="${ctx }/resources/banner.swf" name="movie"> 
<param value="high" name="quality"> 
<param value="transparent" name="wmode"> 
<param value="exactfit" name="SCALE">

<embed width="1000" height="205" wmode="transparent" type="application/x-shockwave-flash" 
pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" quality="high" src="${ctx }/resources/banner.swf"> 
</object>
</div>		
			<ul class="nav">
				<li style="width:50px;" class="bg_nav_left"></li>
				<li class="active" style="width:91px;"><a href="${ctx}/website/index">首页</a></li>
				<li><a href="${ctx }/website/netDescription?navIndex=2">机构概况</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=8&navIndex=3">政务要闻</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=1&navIndex=4">领导活动</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=3&navIndex=5">工作动态</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=4&navIndex=6">媒体报道</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=5&navIndex=7">经验交流</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=6&navIndex=8">先进典型</a></li>
				<li><a href="${ctx }/website/commonMore?perTypeCode=9&navIndex=9">护路文化</a></li>
				<li><a id="mhlt" href="#">门户论坛</a></li>
				<li style="width:55px;" class="bg_nav_right"></li>
			</ul>
		</div>
		<div class="bg_znss">
			<div class="search">
		       <form id="sch" action="${ctx}/website/znss">
		         <table width="260" border="0" cellpadding="0" cellspacing="0" class="search_input">
		           <tr>
		           		<td width="80">站内搜索：</td>
		            	<td width="145" align="right"><input type="text" name="key" class="input" style="background:#F5F5F5" value="请输入搜索关键字" id="key" /></td>
		            	<td width="35" align="center"><input type="image" src="${ctx}/resources/image/website/img_search.png" width="15" height="15" border="0"  id="button" value="搜索" /></td>
		           </tr>
		         </table>
		       </form>		
			</div>	
		</div>
	</div>	