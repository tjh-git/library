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

	//更新资源
	function updateResource(){
		
		var r = $('#resourceUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("resource/updateResource", $("#resourceUpForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$('#resourceTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="resourceUpForm" method="post" style="margin:0;text-align: center;">
		<!-- 隐藏域 -->
		<input type="hidden" name="resourceId">
		<table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="25%" align="right">资源名称：</td>
				<td width="75%" align="left">
					<input name="resourceName" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="resourceType" value="b"/>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源URL：</td>
				<td width="75%" align="left">
					<textarea style="font-size:12px;" name="resourceString" cols="25" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源描述：</td>
				<td width="75%" align="left">
					<textarea style="font-size:12px;" name="resourceDesc" cols="25" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">是否可用：</td>
				<td width="75%" align="left">
					<input type="radio" name="enable" value="1" />是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="updateResource();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>

	</body>
	
</html>