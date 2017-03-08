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

</style>

<script type="text/javascript">
	
	jQuery.ajaxSetup({cache:false});//ajax不缓存
	
	jQuery(function($){
		
		//加载datagride
		$('#authorityTable').datagrid({
			title:'权限列表', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:500, 					//高度
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"authority/queryList", 		//数据来源
			sortName: 'authorityId',		//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'authorityId', 			//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'authorityName',title:'权限名称',width:20,sortable:false,
						formatter:function(value,row,index){return row.authorityName;}
					},
					{field:'authorityDesc',title:'权限描述',width:20,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.authorityDesc;}
					},
					{field:'enable',title:'是否可用',width:20,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
					},
					{field:'isSys',title:'是否系统权限',width:20,sortable:false,
						formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
					}
					]],
			toolbar:[
					{text:'新增', iconCls:'icon-add', 
						handler:function(){addAuthorityRow();}
					},'-',
					{text:'更新', iconCls:'icon-edit',
						handler:function(){updateAuthorityRow();}
					},'-',
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteAuthorityRow();}
					},'-',
					{text:'资源管理', iconCls:'icon-resource',
						handler:function(){updateAuthResRow();}
					},'-'
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#authorityTable').datagrid('clearSelections');}
		});
		
	});
			
	//新增
	function addAuthorityRow(){
		showWindow({
			title:'增加权限信息',
			href:'authority/addPopWin',
			width:420,
			height:315,
			onLoad: function(){$('#authorityAddForm').form('reset');}
		});
	}
			
	//更新    
	function updateAuthorityRow(){
		var rows = $('#authorityTable').datagrid('getSelections');
		//alert(rows[0].userId);
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的权限",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个权限进行更新",'info');
			return;
		}
		showWindow({
			title:'更新权限信息',
			href:'authority/updatePopWin',
			width:420,
			height:315,
			onLoad: function(){
				$("#authorityUpForm").form('load', rows[0]);
			}
		});
	}
			
	//删除
	function deleteAuthorityRow(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
				var rows = $('#authorityTable').datagrid('getSelections');
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?authorityId="+n.uid;
					} else {
						ps += "&authorityId="+n.uid;
					}	
				});
				$.post('authority/delAuthorities'+ps,function(data){
					$('#authorityTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}

	//编辑权限资源
	function updateAuthResRow(){
		var rows = $('#authorityTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择您要编辑资源的权限",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个权限进行编辑",'info');
			return;
		}
		showWindow({
			title:'编辑权限资源',
			href:'authority/updateResPopWin?authorityId='+rows[0].authorityId,
			width:350,
			height:490,
			onLoad: function(){
				$("#authResForm").form('load', rows[0]);
			}
		});
	}
			
	//表格查询
	function searchAuthority(){
		var params = $('#authorityTable').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#authorityQuForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert("["+field.name+":"+field.value+"]");
			params[field.name] = field.value; //设置查询参数
		});
		$('#authorityTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
			
	//清空查询条件
	function clearAuthorityForm(){
		$('#authorityQuForm').form('clear');
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

<form id="authorityQuForm" style="margin:0; text-align:center;">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;">
		<tr>
			<td width="70" align="right">权限名称：</td>
			<td width="180" align="left"><input name="authorityName" style="width:250"></td>
			<td align="left">
				<a href="#" onclick="searchAuthority();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="clearAuthorityForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
			</td>
			
		</tr>
	</table>
	</div>
</form>

<div style="padding:5" id="tabdiv">
	<table id="authorityTable"></table>
</div>

</body>

</html>