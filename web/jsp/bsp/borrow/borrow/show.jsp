<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en">
<html>
<head></head>
<body>
<script type="text/javascript">
    //关闭窗口
    var signal=${status.signal};
    if(signal==0)
    {
        closeWindow();
        alert("借阅证过期");
    }else if(signal==1)
    {
        closeWindow();
        alert("已达到最大借阅数量");
    }else if(signal==2)
    {
        closeWindow();
        alert("存在超期未还书籍");
    }
    function borrowOneBook()
    {
        $.ajax({
            url:"borrow/borrowOneBook",
            data:"bookCode="+$("#bookCode").val()+"&readCard="+${status.readCard},
            type:"post",
            success:function(data)
            {
                alert(data);
                closeWindow();
            }
        })
    }
    function closeWindow()
    {
        $("#edit_box").dialog("destroy");
    }
</script>
<form id="bookShow" method="post" style="margin:0;text-align:center;">
    <table style="width:100%; font-size:12px;" cellpadding="0" cellspacing="0" class="bordertable">
        <tr>
            <td width="30%" align="right">借阅卡号：</td>
            <td width="70%" align="left">
                <input name="bookName" style="width:150px;height:20px;" readonly value="${status.readCard}" class="easyui-validatebox textbox" required="true" validType="length[0,16]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">姓名：</td>
            <td width="70%" align="left">
                <input name="bookCode" style="width:150px;height:20px;" readonly value="${status.userName}"    class="easyui-validatebox textbox" required="true" validType="length[2,16]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">借阅书籍编号：</td>
            <td width="70%" align="left">
                <input name="bookCode" id="bookCode" style="width:150px;height:20px;" class="easyui-validatebox textbox" required="true" validType="length[2,16]">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="#" id="btn-add" onclick="borrowOneBook()"  class="easyui-linkbutton" iconCls="icon-save">确认借阅</a>
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