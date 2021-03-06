<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    <body>
    <style type="text/css">
   	 .table{font-size:12px;}
    </style>
    
    <script type="text/javascript">
    
  	jQuery(function($){

  		var setting = {
  			async : {
  				enable : true, 			//设置 zTree是否开启异步加载模式
  				url : "tree/getTree", //Ajax 获取数据的 URL 地址
  				autoParam : [ "id" ]	//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
  			},
  			data:{ //必须使用data
  				simpleData : {
  				    enable : true,
  				    idKey : "id", 	//id编号命名 默认
  				    pIdKey : "pId", //父id编号命名 默认
  				    rootPId : 0 	//用于修正根节点父节点数据，即 pIdKey 指定的属性值
  				}
  			},
  			//回调函数
  			callback : {
  				onClick : function(event, treeId, treeNode, clickFlag) {
  				   	
  				},
  				//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
  				onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
  					alert("加载错误：" + XMLHttpRequest);  
  				},
  				onAsyncSuccess : function(event, treeId, treeNode, msg){
  					//alert("加载成功!");
  				}
  			}
  		};

  		//渲染
  		$(document).ready(function() {
  		    //加载机构树
  			$.fn.zTree.init($("#loadOrgTree2"), setting);
  		});
	});
	</script>
	
	<div style="position: absolute; z-index:1; background: #ffffff; border: 1px solid #808080; 
		height:440px; overflow-y:auto;">
		<ul id="loadOrgTree2" class="ztree" style="margin-top:0; width:320px;"></ul>
	</div>
	
	</body>
	
</html> 