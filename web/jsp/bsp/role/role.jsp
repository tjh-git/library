<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>多媒体发布系统</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
</head>

<body>

<style type="text/css">
.table{font-size:12px;}
</style>

<script type="text/javascript">
	
	jQuery.ajaxSetup({cache:false});//ajax不缓存
	
	jQuery(function($){
		
		//加载datagride
		$('#roleTable').datagrid({
			title:'角色列表', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:500, 					//高度
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"role/queryList", 			//数据来源
			sortName: 'roleId',				//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'roleId', 				//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'roleName',title:'角色名称',width:20,sortable:false,
						formatter:function(value,row,index){return row.roleName;}
					},
					{field:'roleDesc',title:'角色描述',width:20,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.roleDesc;}
					},
					{field:'roleLevel',title:'角色级别',width:20,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.roleLevel;}
					},
					{field:'enable',title:'是否可用',width:20,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
					},
					{field:'isSys',title:'是否系统角色',width:20,sortable:false,
						formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
					}
					]],
			toolbar:[
					{text:'新增', iconCls:'icon-add', 
						handler:function(){addRoleRow();}
					},'-',
					{text:'更新', iconCls:'icon-edit',
						handler:function(){updateRoleRow();}
					},'-',
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteRoleRow();}
					},'-',
					{text:'权限管理', iconCls:'icon-auth',
						handler:function(){updateRoleAuthRow();}
					},'-'
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#roleTable').datagrid('clearSelections');}
		});
		
	});
			
	//新增
	function addRoleRow(){
		showWindow({
			title:'增加角色信息',
			href:'role/addPopWin',
			width:500,
			height:320,
			onLoad: function(){$('#roleAddForm').form('reset');}
		});
	}
			
	//更新    
	function updateRoleRow(){
		var rows = $('#roleTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的角色",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个角色进行更新",'info');
			return;
		}
		showWindow({
			title:'更新角色信息',
			href:'role/updatePopWin',
			width:500,
			height:320,
			onLoad: function(){
				$("#roleUpForm").form('load', rows[0]);
			}
		});
	}
			
	//删除
	function deleteRoleRow(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
				var rows = $('#roleTable').datagrid('getSelections');
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?roleId="+n.uid;
					} else {
						ps += "&roleId="+n.uid;
					}	
				});
				$.post('role/delRoles'+ps,function(data){
					$('#roleTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}

	//编辑角色权限
	function updateRoleAuthRow(){
		var rows = $('#roleTable').datagrid('getSelections');
		//alert(rows[0].userId);
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择您要编辑权限的角色",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个角色进行编辑",'info');
			return;
		}
		showWindow({
			title:'编辑角色权限',
			href:'role/updateAuthPopWin?roleId='+rows[0].roleId,
			width:520,
			height:480,
			onLoad: function(){
				$("#roleAuthForm").form('load', rows[0]);
			}
		});
	}
			
	//表格查询
	function searchRole(){
		var params = $('#roleTable').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#roleQuForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert("["+field.name+":"+field.value+"]");
			params[field.name] = field.value; //设置查询参数
		});
		$('#roleTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
			
	//清空查询条件
	function clearRoleForm(){
		$('#roleQuForm').form('clear');
		//searchUser();
	}
	
	//弹出窗口
	function showWindow(options){
		jQuery("#myPopWindow").window(options);
	}
	//关闭弹出窗口
	function closeWindow(){
		$("#myPopWindow").window('close');
	}
	
</script>

<form id="roleQuForm" style="margin:0; text-align:center;">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;">
		<tr>
			<td width="70" align="right">角色名称：</td>
			<td width="160" align="left"><input name="roleName" style="width:250"></td>
			<td align="left">
				<a href="#" onclick="searchRole();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="clearRoleForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
			</td>
		</tr>
	</table>
	</div>
</form>
<div style="padding:5;margin:2;" id="tabdiv">
	<table id="roleTable"></table>
</div>

</body>

</html>