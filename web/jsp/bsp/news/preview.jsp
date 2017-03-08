<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="ie-stand" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息发布</title>
</head>
<body  style="background-color: rgb(237, 242, 252);">
<div  align="center" style="margin-left: auto;margin-right: auto;width: 900px">
<h1 align="center">${news.newsTitle}</h1>
<h3 align="center">${news.newsSubTitle}</h3>
<h5 align="center">发表时间：${news.newsPublishTime}</h5>  
<div  style="background-color: rgb(237, 242, 252);text-align: left;font-size: 20px">
 &nbsp; &nbsp; &nbsp;${news.newsContent}
</div>
<div>
<br/>
<br/>
<br/>
<br/>
<INPUT name="btnClose"
	class="btn" style="border: none;" id="btnClose" onclick="javascript:window.opener=null;window.open('','_self');window.close();"
	type="button" value="关闭">
</div>
</div>
</body>
</html>