<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en">
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

    //添加角色
    function addTeacher(){
        var r = $('#teacherAddForm').form('validate');
        if(!r) {
            return false;
        }
        $.post("teacher/addTeacher",$("#teacherAddForm").serializeArray(),function(data){
            closeWindow();
            $('#roleTable').datagrid('reload');
            $.messager.alert('提示',data.mes,'info');
        });
    }
    //关闭窗口
    function closeWindow(){
        $("#edit_box").dialog("destroy");
    }
</script>

<!-- 验证使用jquery-validation -->
<form id="teacherAddForm" method="post" style="margin:0;text-align: center;">
    <table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
        <tr>
            <td width="15%" align="right">借阅证卡号：</td>
            <td width="35%" align="left">
                <input name="readCard" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
            <td width="15%" align="right">姓名：</td>
            <td width="35%" align="left">
                <input name="name" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
        </tr>
        <tr>
            <td width="15%" align="right">身份证号：</td>
            <td width="35%" align="left">
                <input name="ID" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
            <td width="15%" align="right">性别：</td>
            <td width="35%" align="left">
                <input type="radio" name="gender" value="1" checked/>男
                <input type="radio" name="gender" value="0" />女
            </td>
        </tr>
        <tr>
            <td width="15%" align="right">会员卡类型：</td>
            <td width="35%" align="left">
                <input name="cardType" style="width:200" value="教师"  readonly class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
            <td width="15%" align="right">加入时间：</td>
            <td width="35%" align="left">
                <input name="addTime" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
        </tr>
        <tr>
            <td width="15%" align="right">会员状态：</td>
            <td width="35%" align="left">
                <input name="status" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
            <td width="15%" align="right">教职工号：</td>
            <td width="35%" align="left">
                <input name="teachID" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
        </tr>
        <tr>
            <td width="15%" align="right">所教课程：</td>
            <td width="35%" align="left">
                <input name="teachClass" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
            <td width="15%" align="right">手机：</td>
            <td width="35%" align="left">
                <input name="tel" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
        </tr>
        <tr>
            <td width="15%" align="right">qq：</td>
            <td width="35%" align="left">
                <input name="qq" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
            <td width="15%" align="right">常用邮箱：</td>
            <td width="35%" align="left">
                <input name="email" style="width:200" class="easyui-validatebox" required="true" validType="length[2,32]">
            </td>
        </tr>
    </table>
    <br>
    <table width="600px">
        <tr>
            <td colspan="2" align="center">
                <a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
                <a href="#" id="btn-add" onclick="addTeacher();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>

</form>

</body>

</html>