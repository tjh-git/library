<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>

    <script type="text/javascript">
	//加载机构树
	$(document).ready(function() {
		
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

  
	//修改
	function updateRefundInfo(){
		var r = $('#refundInfoUpForm').form('validate');
		if(!r) {
			return false;
		}
		if($("#type").combotree('getValue')==''){
			$.messager.alert('提示','请选择退费类型','info');
			return;
		}
	
		$.post("${ctx}/finance/updateRefundInfo",$("#refundInfoUpForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info',function(){
				$("#edit_box").dialog("destroy");
				$('#RefundTable').datagrid('reload');  				
			});
		});	

	}	
	
	
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="refundInfoUpForm" method="post" style="margin:0;text-align: center;">
		<input type="hidden" name="id" id="id"/>
		<table style="width:100%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="15%" align="right">学员姓名：</td>
				<td width="35%" align="left">
					<input name="studentId" id="studentId" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,128]">
				</td>
			    <td width="15%" align="right">退费类型：</td>
				<td width="35%" align="left">
					<select id="type" name="type" class="easyui-combobox" style="width:175px;height:20px;">
						<option value="">请选择</option>
						<c:forEach items="${typeList }" var="type">
							<option value="${type.key }">${type.value }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">退费金额：</td>
				<td width="35%" align="left">
				    <input name="money" id="money" style="width:175px;height:20px;"  class="easyui-numberbox  h25" required="true"  validType="length[1,64]">
				</td>
			    <td width="15%" align="right">退费日期：</td>
				<td width="35%" align="left" >
                    <input id="date" name="date" type="text" class="easyui-datebox" style="width:175px;height:20px;" required="true" value="${date }" >
				</td>				
			</tr>
			<tr>
			    <td width="15%" align="right">备注：</td>
				<td width="85%" align="left" colspan="3" >
					<input name="remarks" style="height:50px;width:475px;" class="easyui-textbox" data-options="multiline:true" />
				</td>								
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a href="#" id="btn-back" onclick="closeDialog('edit_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="updateRefundInfo();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>																								
		</table>
	</form>
	</body>
</html> 