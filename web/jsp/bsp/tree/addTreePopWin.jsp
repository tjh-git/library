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
  			var jqueryObj=$("#addTreeName");
	 		makeOrgTree2(jqueryObj);
  		});

	});
	//添加用户
	function addTree(){
		var tree = $('#addTreeName').combotree("tree");
		var treeSelected = tree.tree('getSelected');
		if(null== treeSelected){
			alert("所属机构不能为空！");
			return false;
		}
		var r = $('#treeAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("tree/addTree",$("#treeAddForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$('#treeTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
	<form id="treeAddForm" method="post" style="margin:0;text-align: center;">
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
				
					<input id="addTreeName" type="text" name="parentId" style="width:200"><a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="addTree();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
	
	<div id="addTreeContent" style="display:none; position: absolute; z-index:1; background: #ffffff; border: 1px solid #808080; 
		height:200px; overflow-y:auto;">
		<ul id="addOrgTree" class="ztree" style="margin-top:0; width:198px;"></ul>
	</div>
	
	</body>
	
</html> 