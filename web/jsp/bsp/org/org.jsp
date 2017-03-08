<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
	<title>山东综治委护路办管理平台</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="山东综治委护路办,管理平台,山东综治委护路办管理平台">
	<meta http-equiv="description" content="Simple">
	<%@ include file="../layout/taglibnew.jsp" %>	
	<script type="text/javascript"   src="${ctx}/resources/js/jquery-migrate-1.2.1.min.js"></script>
	<script type="text/javascript">
		
		jQuery.ajaxSetup({cache:false});//ajax不缓存
	
		//----------------------------------------机构树参数设定----------------------------------------//
		var oTree;	//查询条件中的机构树对象
		
		//参数定义
	    var orgTreeSetting = {
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
	    		onClick : function (event, treeId, treeNode, clickFlag){
	    		//判断是否父节点(这里不进行判断)
	    		//if(!treeNode.isParent){}
	    		//alert("treeId自动编号：" + treeNode.tId + ", 节点id是：" + treeNode.id + ", 节点文本是：" + treeNode.name);
	        	var zTree = $.fn.zTree.getZTreeObj("myOrgTree"),
	    		nodes = zTree.getSelectedNodes(),
	    		v = "";
	    		nodes.sort(function compare(a,b){return a.id-b.id;});
	    		for (var i=0, l=nodes.length; i<l; i++) {
	    			v += nodes[i].name + ",";
	    		}
	    		if (v.length > 0 ) v = v.substring(0, v.length-1);
	    		var orgId = $("#myOrgId");
	    		var orgName = $("#myOrgName");
	    		orgId.attr("value", treeNode.id); //id值放在隐藏区userOrgId里
	    		orgName.attr("value", v);		  //文本(中文)放在userOrgName里
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
		    $(document).ready(function(){
			    oTree = $.fn.zTree.init($("#myOrgTree"), orgTreeSetting);
			});
		  	//----------------------------------------加载机构树结束----------------------------------------//
			
			//加载datagride
			$('#orgTable').datagrid({
				title:'机构列表', 				//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				autoRowHeight:false,
				fit:true,
				//height:800, 					//高度
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:true,				//可折叠
				url:"org/queryList", 			//数据来源
				sortName: 'orgId',				//排序的列
				sortOrder: 'asc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'orgId', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'orgName',title:'机构名称',width:20,sortable:false,
							formatter:function(value,row,index){return row.orgName;}                          
						},
						{field:'orgCode',title:'机构代码',width:20,sortable:true,hidden:false,
							formatter:function(value,row,index){return row.orgCode;} //需要formatter一下才能显示正确的数据                                
						},
						{field:'enable',title:'是否可用',width:20,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.enable;}                          
						},
						{field:'orgAddress',title:'机构地址',width:20,sortable:false,                              
							formatter:function(value,row,index){return row.orgAddress;}
						},
						{field:'orgDesc',title:'机构描述',width:30,sortable:true,
							formatter:function(value,row,index){return row.orgDesc;}
						},
						{field:'orgReserve1',title:'预留属性1',width:30,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.orgReserve1;}
						},
						{field:'orgReserve2',title:'预留属性2',width:30,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.orgReserve2;}
						},
						{field:'id',title:'机构描述ID',width:30,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.id;}
						},
						{field:'orgLevel',title:'机构级别',width:30,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.orgLevel;}
						},
						{field:'pId',title:'机构描述PID',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.pId;}
						},
						{field:'isParent',title:'是否父节点',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.isParent;}
						},
						{field:'open',title:'是否展开',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.open;}
						}
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addOrgRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateOrgRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteOrgRow();}
						},'-',
						{text:'查看机构树', iconCls:'icon-otree',
							handler:function(){showOrgTree();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#orgTable').datagrid('clearSelections');}
			});
			
		});
		//----------------------------------------机构树弹出层控制函数----------------------------------------//
		//弹出机构树
		function showMyTree(){
			//计算机构输入框的宽度
			var userOrg = $("#myOrgName");
			var orgLen = userOrg.width();
			var myOrg = $("#myOrgTree");
			//动态设置弹出层的宽度
			myOrg.css("width", (orgLen-8));
			
			var orgPos = userOrg.position(); //弹出层与输入框的相对位置
			var leftOffset = 0;	//横向（左）偏移值，定义弹出层的横向起点
			var topOffset = 0;	//纵向（上）偏移值，定义弹出层的纵向起点
			if(navigator.userAgent.indexOf("MSIE")>0) {	//IE浏览器
				leftOffset = 1;
				topOffset = 6;
		   	}else{	//非IE浏览器
		   		leftOffset = 0;
		   		topOffset = -23;
			}
			//alert(orgOffset.left+"-"+orgOffset.top+"-"+userOrg.outerHeight());
			//$("#myTreeContent").css({left:orgPos.left-leftOffset + "px", top:orgPos.top-topOffset + 9 + "px"}).slideDown("fast");
			$("#myTreeContent").slideDown("fast");
			$("body").bind("mousedown", onMyBodyDown);
		}
	
		//响应鼠标事件（点击弹出树以外区域）关闭弹出树
		function onMyBodyDown(event) {
			if (!(event.target.id == "myTreeBtn" || event.target.id == "myTreeContent" || $(event.target).parents("#myTreeContent").length>0)) {
				hideMyTree();
			}
		}
	
		//隐藏弹出机构树
		function hideMyTree() {
			$("#myTreeContent").fadeOut("fast");
			$("body").unbind("mousedown", onMyBodyDown);
		}
		//----------------------------------------机构树弹出层控制函数----------------------------------------//
	
		
		//新增
		function addOrgRow(){
			$("<div id='edit_box' style='padding:5px'></div>").show().dialog({
				title:'增加机构信息',
				href:'org/addPopWin',
				width:500,
				height:435,
				onLoad: function(){
					$('#orgAddForm').form('reset');
				},
				onClose : function() {
		               $(this).dialog('destroy');
		        }				
			});
		}
				
		//更新    
		function updateOrgRow(){
			var rows = $('#orgTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的机构",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个机构进行更新",'info');
				return;
			}
			$("<div id='edit_box' style='padding:5px'></div>").show().dialog({
				title:'更新机构信息',
				href:'org/updatePopWin?pId='+rows[0].pId,
				width:500,
				height:435,
				onLoad: function(){
					$("#orgUpForm").form('load', rows[0]);
				},
				onClose : function() {
		               $(this).dialog('destroy');
		        }
			});
		}
				
		//删除
		function deleteOrgRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#orgTable').datagrid('getSelections');
					var ps = "";
					var isRoot = 0;
					$.each(rows,function(i,n){
						if(n.pId == '0'){
							$.messager.alert('提示', '组织机构根节点不能删除', 'info');
							isRoot = 1;
						}else{
							if(i==0){
								ps += "?orgId="+n.uid;
							} else {
								ps += "&orgId="+n.uid;
							}
						}
					});
					//不包含组织机构根节点时进行删除
					if(isRoot == 0){
						$.post('org/delOrgs'+ps,function(data){
							$('#orgTable').datagrid('reload');
							
							//机构删除后重新加载查询条件中的机构树
							oTree = $.fn.zTree.init($("#myOrgTree"), orgTreeSetting);
							$.messager.alert('提示',data.mes,'info');
						});
					}
				}
			});
		}
	
		//查看机构数
		function showOrgTree(){
			$("<div id='edit_box' style='padding:5px'></div>").show().dialog({
				title:'查看机构机树',
				href:'org/treePopWin',
				width:350,
				height:480,
				onLoad: function(){
					
				},
				onClose : function() {
		               $(this).dialog('destroy');
		        }
			});
		}
				
		//表格查询
		function searchOrg(){
			var params = $('#orgTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#orgQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#orgTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearOrgForm(){
			$('#orgQuForm').form('clear');
			//searchUser();
		}
		//弹出窗口
		function showWindow(options){
			jQuery("#myPopWindow").window(options);
		}
		
		//关闭窗口
		function closeDialog(id){
			$("#"+id).dialog("destroy");
		}
	</script>		
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height: 60px;">
		<form id="orgQuForm" style="margin:0; text-align:center;">
			<div style="margin:10px 0px; padding:5px 0px; border:0px solid #f0f0f0;">
			<table style="width:100%; font-size:12px; border:0px solid #f0f0f0;">
				<tr>
					<td width="70" align="right">上级机构：</td>
					<td width="300" align="left" style="position:relative;">
						<!-- 隐藏区域存放机构树id -->
						<input id="myOrgId"  name="orgId" type="hidden">
						<!-- 存放机构树中文名称 -->
						<input id="myOrgName" type="text" name="orgName" style="width:250px" readOnly>
						<a href="#"><img id="myTreeBtn" border="0" src="${ctx}/resources/image/icon/org.png" 
							onclick="showMyTree();" align="bottom"/></a>
					</td>
					<td width="70" align="left">机构名称：</td>
					<td width="200" align="left"><input name="orgName" style="width:250"></td>
					
					<td  align="left">
						<a href="#" onclick="searchOrg();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="clearOrgForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
					</td>  
				</tr>
			</table>
			</div>
		</form>	
	</div>
	<div region="center" border="false" >
		<table id="orgTable"></table>
	</div>
	<div id="myTreeContent" style="width:320px; position:absolute; z-index:9999; background: #ffffff; border: 1px solid #808080; border-top:none;
		height:200px; overflow-x:hidden;top:37px;left:132px;display:none;">
		<ul id="myOrgTree" class="ztree" style="margin-top:0;"></ul>
	</div>
</body>
</html>