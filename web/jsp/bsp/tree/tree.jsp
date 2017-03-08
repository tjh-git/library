<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>管理列表</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
</head>

<body>

<script type="text/javascript">
	
	jQuery.ajaxSetup({cache:false});//ajax不缓存
	jQuery(function($){
		//加载datagride
		$('#treeTable').datagrid({
			title:'数据列表', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:366, 					//高度
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"tree/queryTreeList", 			//数据来源
			sortName: 'id',				//排序的列
			sortOrder: 'asc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'id', 				//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'treeName',title:'分类名称',width:20,sortable:false,
						formatter:function(value,row,index){
							return row.treeName;
							}                          
					},
					{field:'parentName',title:'所属类别',width:20,sortable:false,
						formatter:function(value,row,index){
							return row.parentName;
							}                          
					},
					{field:'parentId',title:'父节点Id',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){
							return row.parentId;
							}                          
					}
					]],
			toolbar:[
					{text:'新增', iconCls:'icon-add', 
						handler:function(){addTreeRow();}
					},'-',
					{text:'更新', iconCls:'icon-edit',
						handler:function(){updateTreeRow();}
					},'-',
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteTreeRow();}
					},'-',
					{text:'查看', iconCls:'icon-otree',
						handler:function(){showTree();}
					},'-'
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#treeTable').datagrid('clearSelections');}
		});
	});


	//查看机构数
	function showTree(){
		showWindow({
			title:'查看分类',
			href:'tree/treePopWin',
			width:350,
			height:480,
			onLoad: function(){}
		});
	}
	//新增
	function addTreeRow(){
		showWindow({
			title:'增加分类信息',
			href:'tree/addTreePopWin',
			width:400,
			height:235,
			onLoad: function(){
				$('#treeAddForm').form('reset');
			}
		});
	}
			
	//更新    
	function updateTreeRow(){
		var rows = $('#treeTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新记录",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条记录进行更新",'info');
			return;
		}
		showWindow({
			title:'更新分类信息',
			href:'tree/updatePopWin',
			width:400,
			height:235,
			onLoad: function(){
				$("#treeUpForm").form('load', rows[0]);
			}
		});
	}
			
	//删除
	function deleteTreeRow(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
				var rows = $('#treeTable').datagrid('getSelections');
				var ps = "";
				var isRoot = 0;
				$.each(rows,function(i,n){
					if(n.parentId == '0'){
						$.messager.alert('提示', '分类根节点不能删除', 'info');
						isRoot = 1;
					}else{
						if(i==0){
							ps += "?id="+n.id;
						} else {
							ps += "&id="+n.id;
						}
					}
				});
				//不包含组织机构根节点时进行删除
				if(isRoot == 0){
					$.post('tree/delTrees'+ps,function(data){
						$('#treeTable').datagrid('reload');
						//机构删除后重新加载查询条件中的机构树
				//		oTree = $.fn.zTree.init($("#myOrgTree"), orgTreeSetting);
						$.messager.alert('提示',data.mes,'info');
					});
				}
			}
		});
	}
	//表格查询
	function searchOrg(){
		var params = $('#treeTable').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#treeQuForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert("["+field.name+":"+field.value+"]");
			params[field.name] = field.value; //设置查询参数
		});
		$('#treeTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
			
	//清空查询条件
	function clearTreeForm(){
		$('#treeQuForm').form('clear');
	}
</script>
<div style="padding:0px 5px;" id="tabdiv">
	<table id="treeTable"></table>
</div>
</body>
</html>