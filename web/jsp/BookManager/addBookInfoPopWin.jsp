<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en">

<html>
	<head>
	</head>
    
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

  
	//添加
	function submitBookLoginInfo(){
		$.post("${ctx}/book/addBookInfo",$("#BookInfoAddForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info',function(){
				$("#edit_box").dialog("destroy");
				$('#BookTable').datagrid('reload');  				
			});
		});	

	}



	
	</script>
	<div id='edit_box' style='padding:5px;width: 800px;height: 440px'>
		<form id="BookInfoAddForm" method="post" style="margin:0;text-align: center;">
			<table style="width:100%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
				<tr>
					<td width="15%" align="right">图书名称：</td>
					<td width="35%" align="left">
						<input name="bookname" id="bookname" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" >
					</td>
					<td width="15%" align="right">图书条码：</td>
					<td width="35%" align="left">
						<input name="bookcode" id="bookcode" style="width:100px;height:20px;"  class="easyui-numberbox h25"  required="true" >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">书名简写：</td>
					<td width="35%" align="left">
						<input name="bookab" id="bookab" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true" >
					</td>
					<td width="15%" align="right">有无分卷：</td>
					<td width="35%" align="left">
						<select id="have_vol" name="have_vol" class="easyui-combobox" style="width:175px;height:20px;">
							<option value="0">请选择</option>
							<option value="1">有</option>
							<option value="-1">无</option>
						</select>
					</td>

				</tr>
				<tr>
					<td width="15%" align="right">丛书名称：</td>
					<td width="35%" align="left">
						<input name="seriesname" id="seriesname" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true" >
					</td>
					<td width="15%" align="right">编著作者：</td>
					<td width="35%" align="left" >
						<input id="writer" name="writer" type="text" class="easyui-validatebox textbox" style="width:175px;height:20px;" required="true" >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">翻译作者：</td>
					<td width="35%" align="left">
						<input name="translator" id="translator" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true" >
					</td>
					<td width="15%" align="right">图书isbn：</td>
					<td width="35%" align="left" >
						<input id="isbn" name="isbn" type="text" class="easyui-numberbox" style="width:175px;height:20px;" required="true"  >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">出版社：</td>
					<td width="35%" align="left">
						<%--出版社可选--%>
						<input name="publishcom" id="publishcom" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  >
					</td>
					<td width="15%" align="right">图书语言：</td>
					<td width="35%" align="left" >
						<input id="booklanguage" name="booklanguage" type="text" class="easyui-validatebox textbox" style="width:175px;height:20px;" required="true" >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">图书类型：</td>
					<td width="35%" align="left">
						<select id="booktype" name="booktype" class="easyui-combobox" style="width:175px;height:20px;">
							<option value="1">种类1</option>
							<option value="2">种类2</option>
							<option value="3">种类3</option>
						</select>
					</td>
					<td width="15%" align="right">出版日期：</td>
					<td width="35%" align="left" >
						<input id="publishdate" name="publishdate" type="text" class="easyui-datebox" style="width:175px;height:20px;" required="true"  >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">图书版次：</td>
					<td width="35%" align="left">
						<input name="editiontimes" id="editiontimes" style="width:175px;height:20px;"  class="easyui-numberbox" required="true"  >
					</td>
					<td width="15%" align="right">图书印次：</td>
					<td width="35%" align="left" >
						<input id="printtimes" name="printtimes" type="text" class="easyui-numberbox" style="width:175px;height:20px;"  >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">图书开本：</td>
					<td width="35%" align="left">
						<input name="booksize" id="booksize" style="width:175px;height:20px;"  class="easyui-numberbox" required="true">
					</td>
					<td width="15%" align="right">图书装帧：</td>
					<td width="35%" align="left" >
						<input id="bookbind" name="bookbind" type="text" class="easyui-validatebox textbox" style="width:175px;height:20px;" required="true"  >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">图书页数：</td>
					<td width="35%" align="left">
						<input name="bookpagenum" id="bookpagenum" style="width:175px;height:20px;"  class="easyui-numberbox" required="true">
					</td>
					<td width="15%" align="right">定价：</td>
					<td width="35%" align="left" >
						￥<input id="bookprice" name="bookprice" type="text" class="easyui-numberbox" style="width:175px;height:20px;" required="true"  >
					</td>
				</tr>
				<tr>
					<td width="15%" align="right">是否为工具书:</td>
					<td width="35%" align="left">
						<select id="is_refbook" name="is_refbook" class="easyui-combobox" style="width:175px;height:20px;">
							<option value="0">请选择</option>
							<option value="1">是</option>
							<option value="-1">否</option>
						</select>
					</td>
					<td width="15%" align="right">是否期刊：</td>
					<td width="35%" align="left" >
						<select id="is_journal" name="is_journal" class="easyui-combobox" style="width:175px;height:20px;">
							<option value="0">请选择</option>
							<option value="1">是</option>
							<option value="-1">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="#" id="btn-back" onclick="closeDialog('edit_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
						<a href="#" id="btn-add" onclick="submitBookLoginInfo();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
  	<!-- 验证使用jquery-validation -->

	</body>
</html> 