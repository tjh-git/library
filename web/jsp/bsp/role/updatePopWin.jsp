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

    jQuery(function($){

  		//加载机构树
  		$(document).ready(function() {
  			var jqueryObj=$("#roleLevel");
	 		makeOrgTree(jqueryObj);
  		});
  		
    });

	//更新角色
	function updateRole(){
		
		var r = $('#roleUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("role/updateRole", $("#roleUpForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$('#roleTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="roleUpForm" method="post" style="margin:0;text-align: center;">
		<!-- 隐藏域 -->
		<input type="hidden" name="roleId">
		<table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="30%" align="right">角色名称：</td>
				<td width="70%" align="left">
					<input name="roleName" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">角色级别：</td>
				<td width="70%" align="left">
					<input id="roleLevel" type="text" name="roleLevel" style="width:270px"><a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">角色描述：</td>
				<td width="70%" align="left">
					<textarea style="font-size:12px;" name="roleDesc" cols="25" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否可用：</td>
				<td width="70%" align="left">
					<input type="radio" name="enable" value="1" />是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否系统角色：</td>
				<td width="70%" align="left">
					<input type="radio" name="isSys" value="1" />是
					<input type="radio" name="isSys" value="0" />否
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="updateRole();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>

	</body>
	
</html>