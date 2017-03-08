<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>护路办系统</title>
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
		$('#dictionnaryTable').datagrid({
			title:'密钥参数列表', 			//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:500, 					//高度
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"dictionnary/queryList", 		//数据来源
			sortName: 'id',			//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'id', 				//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'name',title:'名称',width:20,sortable:false,
						formatter:function(value,row,index){return row.name;}
					},
					{field:'num',title:'编码',width:20,sortable:false,
						formatter:function(value,row,index){return row.num;}
					},
					{field:'orderNum',title:'排序',width:20,sortable:false,
						formatter:function(value,row,index){return row.orderNum;}
					},
					{field:'parentName',title:'父节点名称',width:20,sortable:false,
						formatter:function(value,row,index){return row.parentName;}
					},
					{field:'parentNum',title:'父节点编码',width:20,sortable:false,
						formatter:function(value,row,index){return row.parentNum;}
					},
					{field:'remarks',title:'备注',width:20,sortable:false,
						formatter:function(value,row,index){return row.remarks;}                          
					},
					{field:'enable',title:'是否启用',width:20,sortable:false,
						formatter:function(value,row,index){return row.enable == '0'?'禁用':'使用';}                          
					}
					]],
			toolbar:[
					{text:'新增', iconCls:'icon-add', 
						handler:function(){addDictionaryRow();}
					},'-',
					{text:'更新', iconCls:'icon-edit',
						handler:function(){updateDictionaryRow();}
					},'-',
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteDictionaryRow();}
					},'-',
					{text:'刷新数据字典', iconCls:'icon-reload',
						handler:function(){reloadDictionnary();}
					},'-'
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#dictionaryTable').datagrid('clearSelections');}
		});
		
	});


			
	//新增
	function addDictionaryRow(){
		showWindow({
			title:'增加数据字典信息',
			href:'dictionnary/addPopWin',
			width:350,
			height:400,
			onLoad: function(){$('#dictionaryAddForm').form('reset');}
		});
	}
			
	//更新    
	function updateDictionaryRow(){
		var rows = $('#dictionnaryTable').datagrid('getSelections');
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的数据",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一项数据进行更新",'info');
			return;
		}
		showWindow({
			title:'更新数据信息',
			href:'dictionnary/updatePopWin',
			width:350,
			height:400,
			onLoad: function(){
				$("#dictionnaryUpForm").form('load', rows[0]);
			}
		});
	}

	//删除
	function deleteDictionaryRow(){
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
				var rows = $('#dictionnaryTable').datagrid('getSelections');
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?id="+n.id;
					} else {
						ps += "&id="+n.id;
					}	
				});
				$.post('dictionnary/delDictionnary'+ps,function(data){
					$('#dictionnaryTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}
		
	//刷新数据字典
	function reloadDictionnary(){
		$.post('dictionnary/reloadDictionnary',function(data){
			$.messager.alert('提示',data.mes,'info');
		});
	}
	
	//表格查询
	function searchDictionnary(){
		var params = $('#dictionnaryTable').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#dictionnaryQuForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		});
		$('#dictionnaryTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
			
	//清空查询条件
	function clearDictionnaryForm(){
		$('#dictionnaryQuForm').form('clear');
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

<form id="dictionnaryQuForm" style="margin:0; text-align:center; font-size:12px">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;">
		<tr>
			<td width="50" align="right">名称：</td>
			<td width="80" align="left"><input name="name" style="width:80px;"></td>
			<td width="40" align="right">编码：</td>
			<td width="80" align="left"><input name="num" style="width:80px"></td>
			<td width="90" align="right">父节点名称：</td>
			<td width="80" align="left"><input name="pname" style="width:80px"></td>
			<td width="90" align="right">父节点编码：</td>
			<td width="80" align="left"><input name="pnum" style="width:80px"></td>
			<td align="left">
				<a href="#" onclick="searchDictionnary();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="clearDictionnaryForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
			</td>  
		</tr>
	</table>
	</div>
</form>

<div style="padding:5" id="tabdiv">
	<table id="dictionnaryTable"></table>
</div>



</body>

</html>