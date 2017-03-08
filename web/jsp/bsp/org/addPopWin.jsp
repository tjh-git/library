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
			        var zTree = $.fn.zTree.getZTreeObj("addOrgTree"),
					nodes = zTree.getSelectedNodes(),
					v = "";
					nodes.sort(function compare(a,b){return a.id-b.id;});
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);

					var orgId = $("#addOrgId");
					var orgName = $("#addOrgName");
					orgId.attr("value", treeNode.id); //id值放在隐藏区addOrgId里
					orgName.attr("value", v);		  //文本(中文)放在addOrgName里
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
  				$.fn.zTree.init($("#addOrgTree"), setting);
  			});
  			//----------------------------------------加载机构树结束----------------------------------------//
  		
		});

  	
  	//----------------------------------------机构树弹出层控制函数----------------------------------------//
	//弹出机构树
	function showAddTree() {
		var userOrg = $("#addOrgName");
		var orgLen = userOrg.width();
		var myOrg = $("#addOrgTree");
		//动态设置弹出层的宽度
		myOrg.css("width", (orgLen-8));
		
		var orgPos = userOrg.position(); //弹出层与输入框的相对位置
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
		$("#addTreeContent").css({left:orgPos.left-leftOffset + "px", top:orgPos.top-topOffset + "px"}).slideDown("fast");
		$("body").bind("mousedown", onAddBodyDown);
	}

	//响应鼠标事件（点击弹出树以外区域）关闭弹出树
	function onAddBodyDown(event) {
		if (!(event.target.id == "addTreeBtn" || event.target.id == "addTreeContent" || $(event.target).parents("#addTreeContent").length>0)) {
			addHideTree();
		}
	}

	//隐藏弹出机构树
	function addHideTree() {
		$("#addTreeContent").fadeOut("fast");
		$("body").unbind("mousedown", onAddBodyDown);
	}
	//----------------------------------------机构树弹出层控制函数----------------------------------------//

	
	//添加机构
	function addOrg(){
		var org = $("#addOrgName");
		if(org.val() == ""){
			alert("上级机构不能为空！");
			org.focus();
			return false;
		}
		var r = $('#orgAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("org/addOrg",$("#orgAddForm").serializeArray(),function(data){
			
			
			

			$.messager.alert('提示',data.mes,'info',function(){
				//机构增加后重新加载查询条件中的机构树
				oTree = $.fn.zTree.init($("#myOrgTree"), orgTreeSetting);
				$('#orgTable').datagrid('reload');
				//$('#myPopWindow').window('close');
				$("#edit_box").dialog("destroy");
				
			});
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="orgAddForm" method="post" style="margin:0;text-align: center;">
		<table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="25%" align="right">机构名称：</td>
				<td width="75%" align="left">
					<input name="orgName" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right"><a style="color:red">*</a>上级机构：</td>
				<td width="75%" align="left">
					<!-- 隐藏区域存放机构树id -->
					<input id="addOrgId"  name="orgId" type="hidden">
					<!-- 存放机构树中文名称（后台保存不需要） -->
					<input id="addOrgName" type="text" name="addOrgName" style="width:250px" readOnly>
					<a href="#"><img id="addTreeBtn" border="0" src="${ctx}/resources/image/icon/org.png" 
						onclick="showAddTree();" align="bottom"/></a>
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
					<input type="radio" name="enable" value="1" checked/>是
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
			<tr >
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeDialog('edit_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="addOrg();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
		
	</form>
	
	<div id="addTreeContent" style="display:none; position: absolute; z-index:1; background: #ffffff; border: 1px solid #808080; 
		height:200px; overflow-y:auto;">
		<ul id="addOrgTree" class="ztree" style="margin-top:0; width:198px;"></ul>
	</div>
	
	</body>
	
</html> 