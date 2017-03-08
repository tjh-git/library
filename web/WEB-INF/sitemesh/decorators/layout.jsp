<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/bsp/layout/taglib.jsp" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title><decorator:title default="多媒体发布系统(MMPS)"/></title>
    
    <SCRIPT type="text/javascript">
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"系统管理"},
			{ id:11, pId:1, name:"机构管理",url:"/mmps/user/home",target:"_self"},
			{ id:12, pId:1, name:"用户管理"},
			{ id:13, pId:1, name:"角色管理"},
			{ id:14, pId:1, name:"权限管理"},
			{ id:2, pId:0, name:"媒体管理"},
			{ id:21, pId:2, name:"分组管理"},
			{ id:22, pId:2, name:"资源管理"},
			{ id:221, pId:22, name:"视频资源"},
			{ id:222, pId:22, name:"图片资源"},
			{ id:223, pId:22, name:"文字资源"},
			{ id:23, pId:2, name:"资源审核"},
			{ id:231, pId:23, name:"视频审核"},
			{ id:232, pId:23, name:"图片审核"},
			{ id:233, pId:23, name:"文字审核"},
			{ id:3, pId:0, name:"节目管理"},
			{ id:31, pId:3, name:"模板管理"},
			{ id:32, pId:3, name:"节目编排"},
			{ id:33, pId:3, name:"节目审核"},
			{ id:34, pId:3, name:"节目发布"}
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</SCRIPT>
    
</head>
<body class="easyui-layout">
    <div data-options="region:'north'" style="height:64px">
    	<table width="100%" height="100%" border="0px">
    		<tr>
     			<td width="50%" valign="bottom">
     				<h2>多媒体发布系统(MMPS)</h2>
     			</td>
     			<td width="50%" align="right" valign="middle">
     				<a href="#">Quit</a>
     			</td>
     		</tr>
     	</table>
    </div>
    <div data-options="region:'west',split:true" title="导航菜单" style="width:180px;">
    	<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
    </div>
    
    <div data-options="region:'center'">
    	<decorator:body/><!-- 这里的内容由引用模板的子页面来替换 -->
    </div>
</body>
</html>