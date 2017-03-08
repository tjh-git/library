<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en">
<html>
<head></head>
<body>
<script type="text/javascript">
    //更新用户
    function saveTMoney() {
        //更新
        $.post("list/saveTMoney", $("#moneyForm").serializeArray(), function (data) {
            closeWindow();
            $('#userTable').datagrid('reload');
            $.messager.alert('提示', data.mes, 'info');
        });
    }
    function closeWindow(){
        $("#edit_box").dialog("destroy");
    }
</script>
<form id="moneyForm" method="post" style="margin:0;text-align:center;">
    <table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
        <tr style="display: none;">
            <td width="30%" align="right">索引：</td>
            <td width="70%" align="left">
                <input name="inde" style="width:150px;height:20px;" class="easyui-validatebox textbox" required="true" validType="length[0,16]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">借阅证卡号：</td>
            <td width="70%" align="left">
                <input name="readCard"  readonly style="width:150px;height:20px;" class="easyui-validatebox textbox" required="true" validType="length[2,16]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">姓名：</td>
            <td width="70%" align="left">
                <input id="name" name="name" readonly style="width:150px;height:20px;" class="easyui-validatebox textbox"
                       required="true" validType="chkPwd[1,32]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">余额：</td>
            <td width="70%" align="left">
                <input id="money" name="money" readonly style="width:150px;height:20px;" class="easyui-validatebox textbox"
                       required="true" validType="chkPwd[1,32]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">存入金额：</td>
            <td width="70%" align="left">
                <input id="saveMoney" name="saveMoney" style="width:150px;height:20px;" class="easyui-validatebox textbox"
                       required="true" validType="chkPwd[1,32]">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton"iconCls="icon-back">返回</a>
                <a href="#" id="btn-add" onclick="saveTMoney();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
            </td>
        </tr>
    </table>
</form>
<div id="upTreeContent" style="display:none; position: absolute; z-index:1; background: #ffffff; border: 1px solid #808080;
		height:200px; overflow-y:auto;">
    <ul id="upOrgTree" class="ztree" style="margin-left:5; margin-top:0; width:193px;"></ul>
</div>
</body>
</html>