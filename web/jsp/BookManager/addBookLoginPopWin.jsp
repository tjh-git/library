<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en">

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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







</script>
<script>
    //添加
    function submitBookLoginInfo(){
        var r = $('#BookLoginInfoAddForm').form('validate');
        if(!r) {
            return false;
        }

        $.post("${ctx}/booklogin/addBookLoginInfo",$("#BookLoginInfoAddForm").serializeArray(),function(data){
            $.messager.alert('提示',data.mes,'info',function(){
                $("#edit_box").dialog("destroy");
                $('#BookTable').datagrid('reload');
            });
        });

    }
</script>
<div id='edit_box' style='padding:5px;width: 800px;height: 440px'>
    <form id="BookLoginInfoAddForm" method="post" style="margin:0;text-align: center;">
        <table style="width:100%; font-size:12px; border:0px solid #f0f0f0;" cellpadding="0" cellspacing="0" class="bordertable">
            <tr>
                <td width="15%" align="right">进书单价：</td>
                <td width="35%" align="left" >
                    ￥<input id="book_buy_price" name="book_buy_price" type="text" class="easyui-numberbox" style="width:175px;height:20px;" required="true"  >
                </td>
                <td width="15%" align="right">进货数量：</td>
                <td width="35%" align="left" >
                    <input id="buy_num" name="buy_num" type="text" class="easyui-numberbox" style="width:175px;height:20px;" required="true"  >
                </td>
            </tr>
            <tr>
                <td width="15%" align="right">图书条码：</td>
                <td width="35%" align="left">
                    <input name="bookcode" id="bookcode" style="width:100px;height:20px;"  class="easyui-numberbox h25"  required="true" >
                <td width="15%" align="right">进书来源：</td>
                <td width="35%" align="left">
                    <input name="book_from" id="book_from" style="width:100px;height:20px;"  class="easyui-textbox"  required="true" >
                </td>

            </tr>

            <tr>
                <td width="15%" align="right">借阅人群：</td>
                <td width="35%" align="left">
                    <select id="book_for" name="book_for" class="easyui-combobox" style="width:175px;height:20px;">
                        <option value="1">学生</option>
                        <option value="2">教师</option>
                        <option value="3">其余人员</option>
                    </select>
                </td>
                <td width="15%" align="right">馆藏位置：</td>
                <td width="35%" align="left">
                    <select id="book_position" name="book_position" class="easyui-combobox" style="width:175px;height:20px;">
                        <option value="1">藏馆1</option>
                        <option value="2">藏馆2</option>
                        <option value="3">藏馆3</option>
                        <option value="4">藏馆4</option>
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