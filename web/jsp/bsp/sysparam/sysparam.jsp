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
		$('#sysParamTable').datagrid({
			title:'密钥参数列表', 			//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:500, 					//高度
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"sysparam/queryList", 		//数据来源
			sortName: 'paramId',			//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'paramId', 				//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'paramCode',title:'参数编码',width:20,sortable:false,
						formatter:function(value,row,index){return row.paramCode;}
					},
					{field:'paramName',title:'参数名称',width:20,sortable:false,
						formatter:function(value,row,index){return row.paramName;}
					},
					{field:'paramValue',title:'参数值',width:20,sortable:false,
						formatter:function(value,row,index){return row.paramValue;}
					},
					{field:'paramStatus',title:'参数状态',width:20,sortable:false,
						formatter:function(value,row,index){return row.paramStatus == '0'?'禁用':'使用';}                          
					}
					]],
			toolbar:[
					{text:'新增', iconCls:'icon-add', 
						handler:function(){addSysParamRow();}
					},'-',
					{text:'更新', iconCls:'icon-edit',
						handler:function(){updateSysParamRow();}
					},'-',
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteSysParamRow();}
					},'-'
					,
					{text:'刷新系统参数', iconCls:'icon-reload',
						handler:function(){reloadSysParam();}
					},'-'
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#sysParamTable').datagrid('clearSelections');}
		});
		
	});

	//刷新系统参数
	function reloadSysParam(){
		$.post('sysparam/reloadSysParam',function(data){
			$('#sysParamTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
		});
	}
			
	//新增
	function addSysParamRow(){
		showWindow({
			title:'增加系统参数信息',
			href:'sysparam/addPopWin',
			width:350,
			height:260,
			onLoad: function(){$('#sysParamAddForm').form('reset');}
		});
	}
			
	//更新    
	function updateSysParamRow(){
		var rows = $('#sysParamTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的系统参数",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一项系统参数进行更新",'info');
			return;
		}
		showWindow({
			title:'更新系统参数信息',
			href:'sysparam/updatePopWin',
			width:350,
			height:260,
			onLoad: function(){
				$("#sysParamUpForm").form('load', rows[0]);
			}
		});
	}

	//删除
	function deleteSysParamRow(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
				var rows = $('#sysParamTable').datagrid('getSelections');
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?paramId="+n.uid;
					} else {
						ps += "&paramId="+n.uid;
					}	
				});
				$.post('sysparam/delSysParams'+ps,function(data){
					$('#sysParamTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}
			
	//表格查询
	function searchSysParam(){
		var params = $('#sysParamTable').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#sysParamQuForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert("["+field.name+":"+field.value+"]");
			params[field.name] = field.value; //设置查询参数
		});
		$('#sysParamTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
			
	//清空查询条件
	function clearSysParamForm(){
		$('#sysParamQuForm').form('clear');
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

<form id="sysParamQuForm" style="margin:0; text-align:center; font-size:12px">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;">
		<tr>
			<td width="70" align="right">参数编码：</td>
			<td width="180" align="left"><input name="paramCode" style="width:250"></td>
			<td width="70" align="left">参数名称：</td>
			<td width="180" align="left"><input name="paramName" style="width:250"></td>
			<td  align="left">
				<a href="#" onclick="searchSysParam();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="clearSysParamForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
			</td>  
		</tr>
	</table>
	</div>
</form>

<div style="padding:5" id="tabdiv">
	<table id="sysParamTable"></table>
</div>



</body>

</html>