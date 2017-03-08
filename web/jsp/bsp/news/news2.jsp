<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../bsp/layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>                                       
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>信息发布系统</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta name="renderer" content="ie-stand" />
</head>

<body style="overflow-x:hidden;background:#f0f0f0">

<script type="text/javascript">
	
	jQuery.ajaxSetup({cache:false});//ajax不缓存
	jQuery(function($){
		//加载datagride
		$('#newsTable2').datagrid({
			title:'会议纪要', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//单选
			height:370, 					//高度
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"${ctx}/news/queryNewsList2", 			//数据来源
			sortName: 'newsId',				//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'newsId', 					//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'sno',title:'id',width:2,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.newsId;}
					},
					{field:'newsTitle',title:'标题',width:74,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.newsTitle;}
					},

					{field:'newsAuthor',title:'发布人',width:12,sortable:false,
						formatter:function(value,row,index){return row.newsAuthor;}
					},
					{field:'newsPublishTime',title:'发布时间',width:12,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.newsPublishTime;}
					}
					]],
			toolbar:[
					{text:'预览', iconCls:'icon-edit',
						handler:function(){preview2();}
					}
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#newsTable2').datagrid('clearSelections');}
		});
		
	});

	//预览
		function preview2(){
			var rows = $('#newsTable2').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要预览的记录",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条记录进行预览",'info');
				return;
			}
			var sno=rows[0].newsId;
			window.open('${ctx}/newsDetail?sno='+sno);
			}

	//表格查询
	function searchNews2(){
		var params = $('#newsTable2').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#searchNewsForm2').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		});
		$('#newsTable2').datagrid('reload'); //设置好查询参数 reload一下就可以了
	}
	
	//弹出窗口
	function showWindow(options){
		jQuery("#myPopWindow").window(options);
	}
	
	//关闭弹出窗口
	function closeWindow(){
		$("#myPopWindow").window('close');
	}
	//清空查询条件
	function clearNewsForm2(){
		$('#searchNewsForm2').form('clear');
	}
	
</script>

<form id="searchNewsForm2" name="searchNewsForm2">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;">
		<tr>
			<td width="13%" align="left">会议纪要：</td>
			<td width="17%" align="left">
			<select id="newsType"  name="newsType" style=" width:90%; height: 30px">
				<option value="10001" >会议纪要</option>
				<%--  
				<c:forEach items="${newsTypeList}" var="nType">
					<option value="${nType.newsTypeCode}" ><c:out value="${nType.newsTypeName}" /></option>
				</c:forEach>
				--%>
			</select>
			</td>
			<td width="13%" align="left">会议标题：</td>
			<td width="17%" align="left">
				<input style="width: 90%;height: 20px" name="newsTitle">
			</td>
			<td width="38%" align="left">
			<%--
				置顶状态：
				<select  name="newsIsTop" style="width: 40%;height: 30px">
					<option value="" selected="selected" >全部</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				--%>
			</td>
			</tr>
			<tr style="display:none">
				<td width="30%" align="left">
					图片信息：
					<select  name="newsIsImageNews" style="width: 50%;height: 30px">
						<option value="" selected="selected" >全部</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
				<td width="30%" align="left">
					审核状态：
					<select  name="newsIsChecked" style="width: 50%;height: 30px">
						<option value="0" selected="selected" >未审核</option>
						<option value="1">核准</option>
						<option value="2">驳回</option>
						<option value="3">全部</option>
					</select>
				</td>
				<td width="38%" align="left">
				链接标题：
				<select  name="newsIsLinkTitle" style="width: 40%;height: 30px">
					<option value="" selected="selected" >全部</option>
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				</td>
				
			</tr>
			<tr>
			<td width="13%" align="left">
			发布时间（开始日期）：</td>
			<td width="17%" align="left"><input id="publishStartTime" name="publishStartTime" class="Wdate" type="text"
						style="width: 90%;height: 25px" data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
			</td>
			<td width="13%" align="left">
			发布时间（结束日期）：</td>
			<td width="17%" align="left">
				<input id="publishEndTime" name="publishEndTime" class="Wdate" type="text"
						style="width: 90%;height: 25px" data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
			</td>
			<td  width="38%" align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="searchNews2();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
					&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="clearNewsForm2()" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
			</td>  
			</tr>
	</table>
	</div>
</form>
<div style="padding:5" id="tabdiv">

	<table id="newsTable2"></table>
</div>
</body>

</html>