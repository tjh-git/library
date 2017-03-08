<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>
    
  	<style type="text/css">
    </style>
    
    <script type="text/javascript">

  	//----------------------------------------机构树参数设定----------------------------------------//
	var setting = {
		async : {
			enable : true, 			//设置 zTree是否开启异步加载模式
			url : "org/getUserOrg", //Ajax 获取数据的 URL 地址
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
			    //判断是否父节点(这里不进行判断)
			    //if(!treeNode.isParent){}
			  	//alert("treeId自动编号：" + treeNode.tId + ", 节点id是：" + treeNode.id + ", 节点文本是：" + treeNode.name);
		        var zTree = $.fn.zTree.getZTreeObj("upOrgTree"),
				nodes = zTree.getSelectedNodes(),
				v = "";
				nodes.sort(function compare(a,b){return a.id-b.id;});
				for (var i=0, l=nodes.length; i<l; i++) {
					v += nodes[i].name + ",";
				}
				if (v.length > 0 ) v = v.substring(0, v.length-1);

				var orgDescPId = $("#upOrgPId");
				var orgId = $("#upOrgId");
				var orgPName = $("#upOrgName");
				orgDescPId.attr("value", treeNode.id);	//id值放在隐藏区upOrgPId里
				orgId.attr("value", treeNode.id);		//id值放在隐藏区upOrgId里
				orgPName.attr("value", v);				//文本(中文)放在upOrgName里
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
	//----------------------------------------机构树参数设定----------------------------------------//

  	jQuery(function($){
  		//----------------------------------------加载机构树开始----------------------------------------//
		$(document).ready(function() {
			$.fn.zTree.init($("#upOrgTree"), setting);
		});
		//----------------------------------------加载机构树结束----------------------------------------//
	});

  	//----------------------------------------机构树弹出层控制函数----------------------------------------//
  	//弹出机构树
	function showUpTree() {
		var upOrg = $("#upOrgName");
		var orgLen = upOrg.width();
		var myOrg = $("#upOrgTree");
		//动态设置弹出层的宽度
		myOrg.css("width", (orgLen-8));
		
		var orgPos = upOrg.position(); //弹出层与输入框的相对位置
		var leftOffset = 0;	//横向（左）偏移值，定义弹出层的横向起点
		var topOffset = 0;	//纵向（上）偏移值，定义弹出层的纵向起点
		if(navigator.userAgent.indexOf("MSIE")>0) {	//IE浏览器
			leftOffset = 6;
			topOffset = 6;
	   	}else{	//非IE浏览器
	   		leftOffset = 0;
	   		topOffset = -23;
		}
		//alert(orgOffset.left+"-"+orgOffset.top+"-"+userOrg.outerHeight());
		$("#upTreeContent").css({left:orgPos.left-leftOffset + "px", top:orgPos.top-topOffset + "px"}).slideDown("fast");
		$("body").bind("mousedown", onUpBodyDown);

		//根据隐藏的id展开其所有的父节点
		var orgDescPId = $("#orgDescPId").val();
		var orgTree = $.fn.zTree.getZTreeObj("upOrgTree");
		//展开指定的节点
		var treenode = orgTree.getNodeByParam("id", orgDescPId, null);
		orgTree.expandNode(treenode, false, false, false);
		//选中该节点
		orgTree.selectNode(treenode);
		
	}		

	//响应鼠标事件（点击弹出树以外区域）关闭弹出树
	function onUpBodyDown(event) {
		if (!(event.target.id == "upTreeBtn" || event.target.id == "upTreeContent" || $(event.target).parents("#upTreeContent").length>0)) {
			upHideTree();
		}
	}

	//隐藏弹出树
	function upHideTree() {
		$("#upTreeContent").fadeOut("fast");
		$("body").unbind("mousedown", onUpBodyDown);
	}
	//----------------------------------------机构树弹出层控制函数----------------------------------------//

	//更新机构
	function updateOrg(){
		
		var oldPId = $("#orgDescPId").val();	//隐藏域中的上级机构id，即当前机构的pId
		var newPId = $("#upOrgPId").val();		//通过机构树获取的上级机构id
		var orgId = $("#orgId").val();			//机构orgId
		var id = $("#orgDescId").val();			//机构id
		
		var upDesc = -1;
		//[上级机构]没有修改
		if(newPId == oldPId){
			upDesc = 0;
		}else{
			upDesc = 1;
		}
		
		var r = $('#orgUpForm').form('validate');
		if(!r) {
			return false;
		}
		//更新
		$.post("org/updateOrg?updesc="+upDesc+"&orgid="+orgId+"&id="+id+"&oldPid="+oldPId, $("#orgUpForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$('#orgTable').datagrid('reload');
			//机构更新后重新加载查询条件中的机构树
			oTree = $.fn.zTree.init($("#myOrgTree"), setting);
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="orgUpForm" method="post" style="margin:0;text-align: center;">
		<!-- 隐藏域 -->
		<input id="orgId" type="hidden" name="orgId">
		<input id="orgDescId" type="hidden" name="id">
		<input id="orgDescPId" type="hidden" name="pId">
		<table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="25%" align="right">机构名称：</td>
				<td width="75%" align="left">
					<input name="orgName" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">上级机构：</td>
				<td width="75%" align="left">
					<!-- 隐藏区域存放机构树pId(用来比较机构是否修改了)和orgId(用来向后台传值) -->
					<input id="upOrgPId"  name="pId" type="hidden">
					<input id="upOrgId"  name="orgId" type="hidden">
					<!-- 存放机构树中文名称（后台保存不需要） -->
					<input id="upOrgName" type="text" name="upOrgName" style="width:250px" value="${upOrgName}" readOnly>
					<a href="#"><img id="upTreeBtn" border="0" src="${ctx}/resources/image/icon/org.png" 
						onclick="showUpTree();" align="bottom"/></a>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构代码：</td>
				<td width="75%" align="left">
					<input name="orgCode" style="width:200" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构描述：</td>
				<td width="75%" align="left">
					<textarea style="font-size:12px;" name="orgDesc" cols="25" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构地址：</td>
				<td width="75%" align="left">
					<textarea style="font-size:12px;" name="orgAddress" cols="25" rows="4"></textarea>
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">是否可用：</td>
				<td width="75%" align="left">
					<input type="radio" name="enable" value="1" />是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">预留1：</td>
				<td width="75%" align="left">
					<input name="orgReserve1" style="width:200" maxlength="32">
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">预留2：</td>
				<td width="75%" align="left">
					<input name="orgReserve2" style="width:200" maxlength="32">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeDialog('edit_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="updateOrg();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
	
	<div id="upTreeContent" style="display:none; position: absolute; z-index:1; background: #ffffff; border: 1px solid #808080; 
		height:200px; overflow-y:auto;">
		<ul id="upOrgTree" class="ztree" style="margin-left:5; margin-top:0; width:193px;"></ul>
	</div>

	</body>
	
</html>