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
  			var jqueryObj=$("#addUserOrg");
	 		makeOrgTree(jqueryObj);
  		});

		//校验账号
		$.extend($.fn.validatebox.defaults.rules, {
		    chkAccount: {
		        validator: function(value,param){
	        		if(value.length < param[0]){
	        			$.fn.validatebox.defaults.rules.chkAccount.message = '账号不能少于'+param[0]+'位';
		        	}else{
		        		if(!/^[\w]+$/.test(value)){
							$.fn.validatebox.defaults.rules.chkAccount.message = '账号只能是英文字母、数字及下划线的组合';
							return false;
						}else{
							if(value.length > param[1]){
								$.fn.validatebox.defaults.rules.chkAccount.message = '账号长度不能超过'+param[1]+'位';
							}else{
								return true;
							}
						}
			        }
		        }
		    }
		});

		//校验密码
		$.extend($.fn.validatebox.defaults.rules, {
		    chkPwd: {
		        validator: function(value,param){
	        		if(value.length < param[0]){
	        			$.fn.validatebox.defaults.rules.chkPwd.message = '密码不能少于'+param[0]+'位';
		        	}else{
		        		if(RegExp("[\\u4E00-\\u9FFF]+", "g").test(value)){
							$.fn.validatebox.defaults.rules.chkPwd.message = '密码不能出现汉字';
							return false;
						}else{
							if(value.length > param[1]){
								$.fn.validatebox.defaults.rules.chkPwd.message = '密码长度不能超过'+param[1]+'位';
							}else{
								return true;
							}
						}
			        }
		        }
		    }
		});

		//判断密码输入是否一致
		$.extend($.fn.validatebox.defaults.rules, {
		    equalTo: {
		        validator: function(value,param){
		            return value == $(param[0]).val();
		        },
		        message: '密码不一致'
		    }
		});
		
	});

  	//判断用户账号是否存在
	function chkExist(){
		var uAcc = $("#userAccount");
		if(uAcc[0].value.length > 0){
			var result = $.ajax({
				url: 'user/chkExist',  
                data: 'userAccount='+uAcc[0].value,  
                type: 'post',  
                dataType: 'json',  
                async: false,  
                cache: false
				}).responseText;
			if(result == 'true'){
				$.messager.alert('提示',"["+uAcc[0].value+"]已经存在,请更换新的账号",'info');
				uAcc.val("");
				uAcc.focus();
			}
		}
	}

	//添加用户
	function addUser(){
		var r = $('#userAddForm').form('validate');
		if(!r) {
			return false;
		}
		var orgTree = $('#addUserOrg').combotree("tree");
		var orgSelected = orgTree.tree('getSelected');
		if(null== orgSelected){
			alert("所属机构不能为空！");
			return false;
		}		
		$.post("user/addUser",$("#userAddForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info',function(){
				$("#edit_box").dialog("destroy");
				$('#userTable').datagrid('reload');  				
			});
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="userAddForm" method="post" style="margin:0;text-align: center;">
		<table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
			<tr>
				<td width="30%" align="right">账号：</td>
				<td width="70%" align="left">
					<input id="userAccount" name="userAccount" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[2,32]" onblur="chkExist()">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">姓名：</td>
				<td width="70%" align="left">
					<input name="userName" style="width:150px;height:20px;"  class="easyui-validatebox textbox" required="true" validType="length[2,16]">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">密码：</td>
				<td width="70%" align="left">
					<input id="userPassword" name="userPassword" type="password" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkPwd[1,32]">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">密码重复：</td>
				<td width="70%" align="left">
					<input id="reUserPassword" name="reUserPassword" type="password" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="equalTo['#userPassword']">
				</td>
			</tr>
			<!--
			<tr>
				<td width="30%" align="right">性别：</td>
				<td width="70%" align="left">
					<input type="radio" name="userGender" value="1" checked/>男
					<input type="radio" name="userGender" value="2" />女
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">出生日期：</td>
				<td width="70%" align="left">
					<input name="userBirthday" class="Wdate" type="text" style="width:200" onclick="WdatePicker()">
				</td>
			</tr>
			-->
			<tr>
				<td width="30%" align="right">所属机构：</td>
				<td width="70%" align="left">
					<input id="addUserOrg" type="text" name="userOrg" style="width:250px;height:20px;"><a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">职务：</td>
				<td width="70%" align="left">
					<input name="userDuty" class="easyui-textbox" style="width:150px;height:20px;" maxlength="16">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">联系电话：</td>
				<td width="70%" align="left">
					<input name="userTelephone" class="easyui-textbox" style="width:150px;height:20px;" maxlength="16">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">邮件地址：</td>
				<td width="70%" align="left">
					<input name="mail" class="easyui-textbox" style="width:150px;height:20px;" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">QQ或微信：</td>
				<td width="70%" align="left">
					<input name="qqWeixin" class="easyui-textbox" style="width:150px;height:20px;" maxlength="16">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">用户描述：</td>
				<td width="70%" align="left">
					<input name="userDesc" style="height:50px;width:150px;" class="easyui-textbox" data-options="multiline:true" />
					
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">用户状态：</td>
				<td width="70%" align="left">
					<input type="radio" name="enable" value="1" checked />正常
					<input type="radio" name="enable" value="0" />禁用
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否系统用户：</td>
				<td width="70%" align="left">
					<input type="radio" name="isSys" value="1" checked/>是&nbsp;&nbsp;
					<input type="radio" name="isSys" value="0" />否
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" id="btn-back" onclick="closeDialog('edit_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="addUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
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