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
    	//校验参数值
		$.extend($.fn.validatebox.defaults.rules, {
		    chkValue: {
		        validator: function(value,param){
	        		if(value.length < param[0]){
	        			$.fn.validatebox.defaults.rules.chkValue.message = '参数值必须为'+param[0]+'位';
		        	}else{
		        		if(!/^[0-9a-fA-F]*$/g.test(value)){
							$.fn.validatebox.defaults.rules.chkValue.message = '参数值只能是数字[0-9]和字母[a-f/A-F]的组合';
							return false;
						}else{
							if(value.length > param[1]){
								$.fn.validatebox.defaults.rules.chkAccount.message = '参数值必须为'+param[1]+'位';
							}else{
								return true;
							}
						}
			        }
		        }
		    }
		});
    });

	//添加系统参数
	function addSysParam(){
		var r = $('#sysParamAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("sysparam/addSysParam",$("#sysParamAddForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$('#sysParamTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="sysParamAddForm" method="post" style="margin:0;text-align: center;">
		<table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="25%" align="right">参数编码：</td>
				<td width="75%" align="left">
					<input name="paramCode" style="width:200" class="easyui-validatebox" required="true" validType="length[1,32]">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">参数名称：</td>
				<td width="75%" align="left">
					<input name="paramName" style="width:200" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">参数值：</td>
				<td width="75%" align="left">
					<!-- <input name="paramValue" style="width:250" maxlength="32" class="easyui-validatebox" 
						required="true" validType="chkValue[32,32]"> -->
					<input name="paramValue" style="width:200" class="easyui-validatebox" required="true" validType="length[1,32]">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">参数状态：</td>
				<td width="75%" align="left">
					<input type="radio" name="paramStatus" value="1" checked/>使用
					<input type="radio" name="paramStatus" value="0" />禁用
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="addSysParam();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
		
	</form>
	
	</body>
	
</html> 