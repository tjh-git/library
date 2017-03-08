<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>
    
    <style type="text/css">
    	.invisibleButton
    	{
    		width:200px;
        	border:none;
        	font-size:15px;
        	font-weight:bold;
        	background:#ffffff;
    	}
    	div#content {
			margin:0 5;
			width: 750px;
		}
    	div#rMenu {
			background-color: #F0F0F0;
			text-align: left;
			padding: 0px;
			width: 120px;
			position: absolute;
			display: none;
			border:1px solid #1C7887;
		}
		div#rMenu ul {
			margin: 0px 0;
			padding: 0px 0px;
			list-style: none outside none;
			display: none;
		}
		div#rMenu ul li {
			cursor: pointer;
			background-color: #F0F0F0;
		}
		div#rMenu ul li a{
			padding: 5px 5px;
			display:block;
			text-decoration:none;
			background-color: #F0F0F0;
		}
		div#rMenu ul li a:hover{
			padding: 5px 5px;
			display:block;
			text-decoration:none;
			background-color: #FAFAFA;
		}
    </style>
    
    <script type="text/javascript">

    jQuery.ajaxSetup({cache:false});//ajax不缓存
    
    var mTree;	//菜单树变量

    var mSetting = {
  		async : {
  			enable : true, 			//设置 zTree是否开启异步加载模式
  			url : "menu/getMenu", 	//Ajax 获取数据的 URL 地址
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
  			onRightClick : function(event, treeId, treeNode) {
			mTree.selectNode(treeNode);
				var offsetX = 0;	//横向（左）偏移值，定义弹出层的横向起点
				var offsetY = 0;	//纵向（上）偏移值，定义弹出层的纵向起点
				if(navigator.userAgent.indexOf("MSIE")>0) {	//IE浏览器
					offsetX = 180;
					offsetY = 80;
				}else{	//非IE浏览器
				   	offsetX = 180;
				   	offsetY = 60;
				}
				showRightMenu(treeNode, {
					x : event.clientX - offsetX,
					y : event.clientY - offsetY
				});
			},
  			onClick : function(event, treeId, treeNode, clickFlag) {
  				//alert("treeId自动编号：" + treeNode.tId + ", 节点id是：" + treeNode.id + ", 节点文本是：" + treeNode.name);
  			},
  			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
  			onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
  				alert("加载错误：" + XMLHttpRequest);  
  			},
  			onAsyncSuccess : function(event, treeId, treeNode, msg){
  				//alert("加载成功!");
  				mTree.expandAll(true);//展开全部菜单
  			}
  		}
  	};
    
  	jQuery(function($){
  		$(document).ready(function() {
  		    //加载菜单树
  			mTree = $.fn.zTree.init($("#myMenuTree"), mSetting);
  		});
	});

  	//显示右键菜单
  	function  showRightMenu(treeNode,postionJson){
		//设置右键菜单的位置
        $("#rMenu").css({
            top:postionJson.y + "px",
            left:postionJson.x + "px",
            display:"block"
        });
        cancelMenu();	//隐藏menuForm
        showItem(["#r_addMenu","#r_upMenu","#r_delMenu"]);
        $("body").bind("mousedown", onBodyMouseDown);
    }

	//显示指定域
	function showItem(itemArray){
        for(var i = 0; i<itemArray.length; i++){
            $(itemArray[i]).show();
        }
    }

	//隐藏指定域
	function hideItem(itemArray) {
		if(itemArray==undefined){//如果为传入值，则禁用缺省的域
        	hideItem(["#rMenu","#r_addMenu","#r_upMenu","#r_delMenu"]);
        }else {
        	for(var i = 0; i<itemArray.length; i++){
                $(itemArray[i]).hide();
            }
        }
     	$("body").unbind("mousedown", onBodyMouseDown);
	 }

	//鼠标点击别处时，隐藏右键菜单
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			hideItem();
		}
	}

	//显示菜单增加页面
	function addMenuNode(){
		var sNode = mTree.getSelectedNodes()[0];//获取选中节点
		
		var title = $("#title");
		var mark = $("#mark");		//标记当前menuForm用作菜单添加
		var pid = $("#menuPid");

		title.attr("value", "添加菜单"); //提示当前为新增页面
		pid.attr("value", sNode.id);	//将选中节点的menuId保存到隐藏域中
		mark.attr("value", "add");		//'add'为添加标志
		
		showItem(["#menuDiv"]);//显示menuForm
		hideItem(["#rMenu"]);  //隐藏右键菜单
	}

	//显示菜单修改页面
	function editMenuNode(){
		var sNode = mTree.getSelectedNodes()[0];
		

		var title = $("#title");
		var mark = $("#mark");		//标记当前menuForm用作菜单修改
		var menuId = $("#menuPid");
		var menuName = $("#menuName");
		var menuUrl = $("#menuUrl");
		var menuDesc = $("#menuDesc");
		var openType = $("#openType");
		
		
		showItem(["#menuDiv"]);	//显示menuForm

		title.val("修改菜单");		//提示当前为修改页面
		mark.val("update");		//'update'为修改标志
		menuId.val(sNode.id);		//设置menuId
		menuName.val(sNode.name);	//设置menuName
		menuUrl.val(sNode.title);	//设置menuUrl
		menuDesc.val(sNode.mdesc);//设置菜单排列顺序
		openType.val(sNode.otype);//设置菜单打开方式
		
		hideItem(["#rMenu"]);//隐藏右键菜单
	}

	//保存新增菜单
	function saveOrUpdateMenu(){
		var r = $('#menuForm').form('validate');
		if(!r) {
			return false;
		}

		var menuName = $("#menuName").val();
		if(menuName == ""){
			alert("[菜单名称]不能为空");
			return false;
		}
		var menuUrl = $("#menuUrl").val();
		var openVal = $("#openType").val();
		if(menuUrl != "" && openVal == ""){
			alert("[打开方式]不能为空");
			return false;
		}
		var mark = $("#mark").val();
		if(mark == "add"){
			$.post("menu/addMenu",$("#menuForm").serializeArray(),function(data){
				reloadTree();			//重新加载树
				clearMenuForm();		//清空menuForm
				hideItem(["#menuDiv"]);	//隐藏menuForm
				$.messager.alert('提示',data.mes,'info');
			});
		}
		if(mark == "update"){
			$.post("menu/updateMenu",$("#menuForm").serializeArray(),function(data){
				reloadTree();			//重新加载树
				clearMenuForm();		//清空menuForm
				hideItem(["#menuDiv"]);	//隐藏menuForm
				$.messager.alert('提示',data.mes,'info');
			});
		}
	}

	//删除菜单
	function delMenuNode(){
		if(confirm("确定要删除该菜单吗?")){
			var sNode = mTree.getSelectedNodes()[0];
			$.post("menu/delMenu?menuId="+sNode.id, function(data){
				reloadTree();			//重新加载树
				hideItem(["#rMenu"]);	//隐藏右键菜单
				$.messager.alert('提示',data.mes,'info');
			});
		}else{
			hideItem(["#rMenu"]);	//隐藏右键菜单
		}
	}

	//清空当前菜单form
	function clearMenuForm(){
		$('#menuForm').form('clear');
	}

	//取消操作
	function cancelMenu(){
		clearMenuForm();
		hideItem(["#menuDiv"]);
	}

	//重新加载菜单树
	function reloadTree(){
		mTree = $.fn.zTree.init($("#myMenuTree"), mSetting);
	}
	
	</script>
	
	<div id="content">
		<div style="z-index:0; width:350px; margin:0; height:550px;overflow:scroll;float:left;">
			<ul id="myMenuTree" class="ztree"></ul>
		</div>
	
		<div id="menuDiv" style="display:none; border:0px solid #1C7887; float:right; width:380px; height:550px;">
	    	<form id="menuForm" style="text-align:center;">
				<table style="font-size:12px;">
					<tr>
						<td width="40%" align="left"></td>
						<td width="60%" align="left">
							<input id="title" name="title" class="invisibleButton" readonly >
						</td>
					</tr>
					<tr>
						<td width="40%" align="left">菜单名称：</td>
						<td width="60%" align="left">
							<input id="mark" name="mark" type="hidden" >
							<input id="menuPid" name="menuPid" type="hidden" >
							<input id="menuName" name="menuName" maxlength="20" style="width:300">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td width="40%" align="left">URL：</td>
						<td width="60%" align="left">
							<input id="menuUrl" maxlength="40" name="menuUrl" style="width:300">
							<a href="#" style="color:red; text-decoration:none;">菜单目录URL为空</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td width="40%" align="left">菜单排序：</td>
						<td width="60%" align="left">
							<input id="menuDesc" name="menuDesc" maxlength="16" style="width:150">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td width="30%" align="left">打开方式：</td>
						<td width="70%" align="left">
							<input id="openType" name="openType" maxlength="1" style="width:50">
							<a href="#" style="color:red; text-decoration:none;">[a]代表ajax , [i]代表iframe</a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<a href="#" onclick="saveOrUpdateMenu();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="cancelMenu();" class="easyui-linkbutton" iconCls="icon-undo">取消</a>
						</td>
					</tr>
				</table>
			</form>
   		</div>
	</div>
	
	<div id="rMenu">
		<ul id="r_addMenu" onclick="addMenuNode();">
			<li><a href="#" title="">增加菜单</a></li>
		</ul>
		<ul id="r_upMenu" onclick="editMenuNode();">
			<li><a href="#" title="">修改菜单</a></li>
		</ul>
		<ul id="r_delMenu" onclick="delMenuNode();">
			<li><a href="#" title="">删除菜单</a></li>
		</ul>
	</div>
	
</body>
	
</html> 