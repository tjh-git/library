<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>
    
    <script type="text/javascript">
    
  	jQuery(function($){
  		//加载机构树
  		$(document).ready(function() {
  			var jqueryObj=$("#updateTreeName");
	 		makeOrgTree2(jqueryObj);
  		});

	});

	//更新用户
	function updateTree(){
		var r = $('#treeUpForm').form('validate');
		if(!r) {
			return false;
		}
		var parentId=$('#updateTreeName').combotree("getValue");
		
		var treeSelected = $('#updateTreeName').combotree("tree").tree('getSelected');
		if(null!= treeSelected){
			$("#parentId").val(parentId);
		}
		var rows = $('#treeTable').datagrid('getSelections');
		var oldParentId=rows[0].parentId;
		//更新
		$.post("tree/updateTree?oldParentId="+oldParentId, $("#treeUpForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$('#treeTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
	<form id="treeUpForm" method="post" style="margin:0;text-align:center;">
	<input  type="hidden" id="id" name="id" >
		<table style="width:100%; font-size:12px; border:0px solid #f0f0f0;">
			<tr>
				<td width="30%" align="right">名称：</td>
				<td width="70%" align="left">
					<input id="treeName" name="treeName" style="width:200" class="easyui-validatebox" 
					required="true" validType="chkAccount[2,32]" >
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">所属分类：</td>
				<td width="70%" align="left">
					<input type="hidden" name="parentId" id="parentId" >
					<input id="updateTreeName" type="text" name="parentName" style="width:200"><a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="updateTree();" class="easyui-linkbutton" iconCls="icon-save">修改</a>
				</td>
			</tr>
		</table>
	</form>
	</body>
	
</html>