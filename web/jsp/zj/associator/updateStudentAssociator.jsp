<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
       <script type="text/javascript">
       
       function submitAssociator(){
   		var r = $('#associatorUpStudent').form('validate');
   		if(!r) {
   			return false;
   		}
   		
   		if($("#cardtype").combotree('getValue')==''){
 		   
 		   $.messager.alert('提示','请选择会员卡类型','info');
 		   return;
 	   }
 	   
        if($("#state").combotree('getValue')==''){
 		   
 		   $.messager.alert('提示','请选择会员卡类型','info');
 		   return;
 	   }
   	
   	   $.post("${ctx}/associator/updateStudent",$("#associatorUpStudent").serializeArray(),function(data){
   			$.messager.alert('提示',data.mes,'info',function(){
   				$('#student_box').dialog('destroy');
   				$('#AssociatorTable').datagrid('reload');  				
   			});
   		});	

   	}	  
       
       </script>

       <form id="associatorUpStudent" method="post" style="margin:0;text-align: center;">
             <input type="hidden" name="id" id="id"/>
             <table style="width:100%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
                    <tr>
                        <td width="15%" align="right">学号：</td>
                        <td width="35%" colspan="3" align="left">
				            <input name="stuNo" id="stuNo" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,64]">
				        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">姓名：</td>
                        <td width="35%" align="left">
                             <input type="text" name="name" id="name" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,32]">
                        </td>
                        <td width="15%" align="right">身份证号：</td>
                        <td width="35%" align="left">
				            <input name="idcard" id="idcard" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,64]">
				        </td>
                    </tr>
                    
                     <tr>
                        <td width="15%" align="right">性别：</td>
                        <td width="35%" align="left">
                             <input type="radio" name="gender" id="gender" value="男" required="true" validType="chkAccount[1,4]"/>男&nbsp;&nbsp;&nbsp;
                             <input type="radio" name="gender" id="gender" value="女" required="true" validType="chkAccount[1,4]"/>女
                        </td>
                        <td width="15%" align="right">借阅卡号：</td>
                        <td width="35%" align="left">
				             <input name="cardno" id="cardno" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,64]">
				        </td>
                    </tr>
                    
                     <tr>
                        <td width="15%" align="right">借阅证有效期：</td>
                        <td width="35%" align="left">
				            <input name="expiryDate" id="expiryDate" style="width:175px;height:20px;"  class="easyui-numberbox  h25" required="true"  validType="length[1,64]">
				        </td>
                        <td width="15%" align="right">会员卡类型：</td>
                        <td width="35%" align="left">
					        <select id="cardtype" name="cardtype" class="easyui-combobox" style="width:175px;height:20px;">
						        <option value="">请选择</option>
						        <c:forEach items="${typelist }" var="cardtype">
							    <option value="${cardtype.key }">${cardtype.value }</option>
						        </c:forEach>
					        </select>
				        </td>
                        
                    </tr>
                    
                     <tr>
                        <td width="15%" align="right">加入时间：</td>
                        <td width="35%" align="left" >
                             <input id="joinDate" name="joinDate" type="text" class="easyui-datebox" style="width:175px;height:20px;" required="true" value="${joinDate }" >
				        </td>
                        <td width="15%" align="right">会员状态：</td>
                        <td width="35%" align="left">
					        <select id="state" name="state" class="easyui-combobox" style="width:175px;height:20px;">
						        <option value="">请选择</option>
						        <c:forEach items="${asslist }" var="state">
							    <option value="${state.key }">${state.value }</option>
						        </c:forEach>
					        </select>
				        </td>
                    </tr>
                    <tr>
				        <td colspan="4" align="center">
					       <a href="#" id="btn-back" onclick="closeDialog('student_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					       <a href="#" id="btn-add" onclick="submitAssociator();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				        </td>
			        </tr>
             </table>
      </form>
</body>
</html>