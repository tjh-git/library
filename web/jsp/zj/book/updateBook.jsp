<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

     <head></head>
     
<body>
      <script type="text/javascript">
      
                function submitAssociator(){
                	var r = $('#updateBookList').form('validate');
                	
                	if(!r){
             		   return false;
             	   }
                	
                	if($("#tsyy").combotree('getValue')==''){
             		   
             		   $.messager.alert('提示','请选择图书语言类型','info');
             		   return;
             	   }
             	   
                    if($("#ylsx").combotree('getValue')==''){
             		   
             		   $.messager.alert('提示','请选择图书类型','info');
             		   return;
             	   }
                    
                    $.post("${ctx}/bookInfo/updateBookTwo",$("#updateBookList").serializeArray(),function(data){
                   	  $.messager.alert('提示',data.mes,'info',function(){
                   		 $('#book_box').dialog('destroy');
                   		 $('#BookTable').datagrid('reload');
                   	  }); 
                      }); 
                	
                }
      
      </script>
      
      <form id="updateBookList" method="post" style="margin:0;text-align: center;">
      
             <input type="hidden" name="id" id="id"/>
             <table style="width:100%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
                    
                    <tr>
                        <td width="15%" align="right">图书条码：</td>
                        <td width="35%" align="left">
                             <input type="text" name="tstm" id="tstm" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,128]">
                        </td>
                        <td width="15%" align="right">图书名称：</td>
                        <td width="35%" align="left">
				            <input name="tsmc" id="tsmc" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,128]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">有无分卷：</td>
                        <td width="35%" align="left">
           &nbsp;&nbsp;&nbsp;<input type="radio" name="ywfj" id="ywfj" value="有" required="true" validType="chkAccount[1,4]"/>&nbsp;有&nbsp;&nbsp;&nbsp;
                             <input type="radio" name="ywfj" id="ywfj" value="无" required="true" validType="chkAccount[1,4]"/>&nbsp;无
                        </td>
                        <td width="15%" align="right">丛书名称：</td>
                        <td width="35%" align="left">
				            <input name="csmc" id="csmc" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,128]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">编著作者：</td>
                        <td width="35%" align="left">
                             <input type="text" name="bzzz" id="bzzz" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,128]">
                        </td>
                        <td width="15%" align="right">翻译作者：</td>
                        <td width="35%" align="left">
				            <input name="fyzz" id="fyzz" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,128]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">图书ISBN：</td>
                        <td width="35%" align="left">
                             <input type="text" name="isbn" id="isbn" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,128]">
                        </td>
                        <td width="15%" align="right">出版社：</td>
                        <td width="35%" align="left">
				            <input name="cbs" id="cbs" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,256]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">图书语言：</td>
                        <td width="35%" align="left">
					        <select id="tsyy" name="tsyy" class="easyui-combobox" style="width:175px;height:20px;">
						        <option value="">请选择</option>
						        <c:forEach items="${tsyylist }" var="tsyy">
							    <option value="${tsyy.key }">${tsyy.value }</option>
						        </c:forEach>
					        </select>
				        </td>
				        
                        <td width="15%" align="right">图书类型：</td>
                        <td width="35%" align="left">
					        <select id="ylsx" name="ylsx" class="easyui-combobox" style="width:175px;height:20px;">
						        <option value="">请选择</option>
						        <c:forEach items="${tslxlist }" var="ylsx">
							    <option value="${ylsx.key }">${ylsx.value }</option>
						        </c:forEach>
					        </select>
				        </td>
                        
                    </tr>
                    
                     <tr>
                        <td width="15%" align="right">出版日期：</td>
                        <td width="35%" align="left" >
                             <input id="cbsj" name="cbsj" type="text" class="easyui-datebox" style="width:175px;height:20px;" required="true" value="${cbsj }" >
				        </td>
                        <td width="15%" align="right">图书版次：</td>
                        <td width="35%" align="left">
				            <input name="tsbc" id="tsbc" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,64]">
				        </td>
                    </tr>
      
                     <tr>
                        <td width="15%" align="right">图书印次：</td>
                        <td width="35%" align="left">
                             <input type="text" name="tskc" id="tskc" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,64]">
                        </td>
                        <td width="15%" align="right">图书开本：</td>
                        <td width="35%" align="left">
				            <input name="tskb" id="tskb" style="width:100px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,4]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">图书装帧：</td>
                        <td width="35%" align="left">
                             <input type="text" name="yszz" id="yszz" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,4]">
                        </td>
                        <td width="15%" align="right">图书页数：</td>
                        <td width="35%" align="left">
				            <input name="tsys" id="tsys" style="width:100px;height:20px;"  class="easyui-numberbox  h25" required="true"  validType="length[1,10]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">定价：</td>
                        <td width="35%" align="left">
                             <input type="text" name="dj" id="dj" style="width:100px;height:20px;"  class="easyui-validatebox textbox"  required="true" validType="chkAccount[1,128]">
                        </td>
                        <td width="15%" align="right">是否工具书：</td>
                        <td width="35%" align="left">
				            <input name="sfgjs" id="sfgjs" style="width:175px;height:20px;"  class="easyui-validatebox textbox" required="true"  validType="length[1,4]">
				        </td>
                    </tr>
                    
                    <tr>
                        <td width="15%" align="right">是否期刊：</td>
                        <td width="35%" align="left">
           &nbsp;&nbsp;&nbsp;<input type="radio" name="sfqj" id="sfqj" value="是" required="true" validType="chkAccount[1,4]"/>&nbsp;是&nbsp;&nbsp;&nbsp;
                             <input type="radio" name="sfqj" id="sfqj" value="否" required="true" validType="chkAccount[1,4]"/>&nbsp;否
                        </td>
                        <td width="15%" align="right">出版日期：</td>
                        <td width="35%" align="left" >
                             <input id="lrrp" name="lrrp" type="text" class="easyui-datebox" style="width:175px;height:20px;" required="true" value="${lrrp }" >
				        </td>
                    </tr>
                    
                    <tr>
				        <td colspan="4" align="center">
					       <a href="#" id="btn-back" onclick="closeDialog('book_box');" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					       <a href="#" id="btn-add" onclick="submitAssociator();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				        </td>
			        </tr>
                    
             </table>
      </form>
</body>
</html>