<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>
    
    <style type="text/css">
    
    .content
    {
    	width:100%;
    	height:452px;
    	overflow:hidden;
    	border-top:0px solid #E1E9DC;
        border-bottom:0px solid #E1E9DC;
        border-left:0px solid #E1E9DC;
        border-right:0px solid #E1E9DC;
    }
    .top
    {
    	align:left;
    	width:100%;
    	height:25px;
    	text-align:left;
    	padding-left:5px;
    	padding-bottom:5px;
    	font-size:13px;
        border-bottom:0px solid #E1E9DC;
    }
    .invisibleButton
    {
    	padding-top:10px;
    	width:100px;
        height:25px;
        border:none;
        background:#ffffff;
    }
    .bottom
    {
    	padding-top:395px;
    	width:100%;
    	height:30px;
        border-top:0px solid #E1E9DC;
    }
    
    </style>
    
    <script type="text/javascript">
    
    jQuery(function($){
  		var setting = {
  		  	async : {
  		  		enable : true, 				//设置 zTree是否开启异步加载模式
  		  		url : "menu/getMenuAndRes", //Ajax 获取数据的 URL 地址
  		  		autoParam : [ "id" ]		//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
  		  	},
  		  	check: {
				enable: true,
				chkboxType: { "Y" : "ps", "N" : "ps"},
				chkStyle: "checkbox"
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
  		  			//加载选中节点
  		  			$(function(){
  		  				$.ajax({
  		  					type:'POST',
  		  					url:"authority/getMyRes",
  		  					success:function(data){
  		  						var arTree = $.fn.zTree.getZTreeObj("authResTree");
  		  						$.each(data, function(n, val) {
  	  		  						arTree.checkNode(arTree.getNodeByParam("id", val.id, null), true, true);
  		              			});
  		  					}
  		  				});
  		  			});
  		  		}
  		  	}
  		};
		  	
  		$(document).ready(function() {
  		    //加载（菜单+资源）树
  			$.fn.zTree.init($("#authResTree"), setting);
  		});
	});

    //保存[权限-资源]对应关系
    function upAuthRes(){
        var authId = $("#authorityId").val();

        var arTree = $.fn.zTree.getZTreeObj("authResTree");
        var nodes = arTree.getCheckedNodes(true);
        var resIds = "";
        for (var i = 0; i < nodes.length; i++) {
            if(nodes[i].id.length > 3 && nodes[i].id.substring(0,3) == "res"){
            	resIds += nodes[i].id + ",";
            }
        }
        $.post('authority/updateAuthRes?authorityIds='+resIds, $("#authResForm").serializeArray(), function(data){
        	$('#myPopWindow').window('close');
			$.messager.alert('提示',data.mes,'info');
		});
    }
	
	</script>
	
	<form id="authResForm" method="post" style="margin:0;text-align: center;">
		
    	<div class="content">
    		<div class="top">
    			当前权限：<input class="invisibleButton" name="authorityName"><input id="authorityId" name="authorityId" type="hidden">
    		</div>
    		
    		<div style="position: absolute; z-index:1; background: #ffffff; border: 1px solid #808080; height:390px; overflow-y:auto;">
				<ul id="authResTree" class="ztree" style="margin-top:0; width:320px;"></ul>
			</div>
    		
    		<div class="bottom">
    			<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
				<a href="#" id="btn-add" onclick="upAuthRes();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    		</div>
		</div>
	</form>
	
	</body>
	
</html> 