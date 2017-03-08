<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en">
<html>
<head></head>
<body>
<script type="text/javascript">
    //关闭窗口
    if("${borrow.isOut}"=="0")
    {
        closeWindow();
        alert("该书籍未被借阅！");
    }else
    if("${borrow.isOut}"=="-8")
    {
        closeWindow();
        alert("未找到该书籍信息");
    }
    if(${borrow.fine}!=0)
    {
        $("#isOut").val("已逾期！罚款${borrow.fine}元")
    }
    function returnOneBook()
    {
        $.ajax({
            url:"return/returnOneBook",
            data:"code="+${borrow.bookCode},
            type:"post",
            success:function(data)
            {
                if(data==1)alert("还书成功");
                if(data==-1)alert("还书失败");
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
            <td width="30%" align="right">书名：</td>
            <td width="70%" align="left">
                <input name="bookName" style="width:150px;height:20px;" readonly value="${borrow.bookName}" class="easyui-validatebox textbox" required="true" validType="length[0,16]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">书籍编号：</td>
            <td width="70%" align="left">
                <input name="bookCode" style="width:150px;height:20px;" readonly value="${borrow.bookCode}"    class="easyui-validatebox textbox" required="true" validType="length[2,16]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">是否外借：</td>
            <td width="70%" align="left">
                <input id="isOut" style="width:150px;height:20px;"readonly value="${borrow.isOut}" class="easyui-validatebox textbox"  required="true" validType="chkPwd[1,32]">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">借阅次数：</td>
            <td width="70%" align="left">
                <input id="borrowNum" name="ID" type="text" name="orgName" readonly value="${borrow.borrowNum}"  style="width:250px"><a style="color:red">*</a>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">借阅人姓名：</td>
            <td width="70%" align="left">
                <input id="borrowerName" name="borrowerName" type="text" readonly value="${borrow.borrowerName}"   style="width:250px"><a style="color:red">*</a>
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">借阅人借阅卡号：</td>
            <td width="70%" align="left">
                <input name="borrowerCode" class="easyui-textbox" readonly value="${borrow.borrowerCode}" style="width:150px;height:20px;" maxlength="32">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">归还时间：</td>
            <td width="70%" align="left">
                <input name="returnTime" class="easyui-textbox" readonly value="${borrow.returnTime}" style="width:150px;height:20px;" maxlength="32">
            </td>
        </tr>
        <tr>
            <td width="30%" align="right">借阅时间：</td>
            <td width="70%" align="left">
                <input name="borrowTime" class="easyui-textbox"  readonly value="${borrow.borrowTime}" style="width:150px;height:20px;" maxlength="16">
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <a href="#" id="btn-add" onclick="returnOneBook()"  class="easyui-linkbutton" iconCls="icon-save">确认还书</a>
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