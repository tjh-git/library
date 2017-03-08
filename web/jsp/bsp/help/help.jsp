<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
	<title>水厂管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
	<%@ include file="../layout/taglibnew.jsp" %>
	
		
</head>
<body class="easyui-layout">
	<script type="text/javascript">
		
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		jQuery(function($){

			//加载datagride
			$('#HelpInfoTable').datagrid({
				title:'通用帮助', 				//标题
				method:'post',
				fit:true,
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:true,				//可折叠
				url:"${ctx}/help/dataList?id="+'${id}&param1='+'${param1}&param2='+"${param2}", 			//数据来源
				sortName: 'id',				//排序的列
				sortOrder: 'desc', 				//倒序
				remoteSort: true, 				//服务器端排序
				idField:'id', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:5}, //显示复选框
						
						${easyUiString}
						
						]],
				toolbar:[
						{text:'确定', iconCls:'icon-add', 
							handler:function(){
								getHelpInfoRow();
								}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#HelpInfoTable').datagrid('clearSelections');}
			});
			
		});

		//
		function getHelpInfoRow(){
			var rows = $('#HelpInfoTable').datagrid('getSelections');
			//alert(rows[0].userId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择一条记录！",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条记录！",'info');
				return;
			}

			returnDataRow_${id}(rows);
			
			//doCallback('returnDataRow_${id}',rows);
			//eval('returnDataRow_${id}'+'${id}('+rows+')');

		}
	
		function doCallback(fn,args)
		{
			fn.apply(this, args);
		}
		
		//表格查询
		function searchHelpInfo(){
			var params = $('#HelpInfoTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#helpInfoForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#HelpInfoTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}

		//清空查询条件
		function clearHelpInfoForm(){
			$('#helpInfoForm').form('clear');
			//searchHelpInfo();
		}
		
		//关闭窗口
		function closeDialog(id){
			$("#"+id).dialog("destroy");
		}		
		//弹出窗口
		function showWindow(options){
			jQuery("#myPopWindow").window(options);
		}
		
		
	</script>
		<form id="helpInfoForm">
			<table style="font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
				<tr>
					<td width="300" align="right">
                        <select class="easyui-combobox" name="queryKey" style="width:120px;" data-options="editable:false">
						    <c:forEach items="${queryParamList}" var="helpPo">
						       <option value="${helpPo.key}"><c:out value="${helpPo.value}" /></option>
						    </c:forEach>
						    <input name="queryValue" class="easyui-textbox" style="width:120px;" />
		    			</select>
					</td>
					<td align="right" width="150">
						<a href="#" onclick="searchHelpInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="clearHelpInfoForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
					</td>
				</tr>
			</table>
		</form>	
	<table id="HelpInfoTable"></table>
</body>
</html>