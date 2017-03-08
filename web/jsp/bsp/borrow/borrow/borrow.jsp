<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
    <title>图书馆管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="Simple">
    <%@ include file="../../layout/taglibnew.jsp" %>
    <script type="text/javascript">
        jQuery.ajaxSetup({cache:false});//ajax不缓存
        function borrowBook(){
            //先取得 datagrid 的查询参数
            var fields =$('#userQuForm').serializeArray(); //自动序列化表单元素为JSON对象
            var data;
            $.each( fields, function(i, field){
                data=field.name+"="+field.value;
            });
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'借阅书籍',
                href:'borrow/borrowBook?'+data,
                width:450,
                height:400,
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }
        //关闭窗口
        function closeDialog(id){
            $("#"+id).dialog("destroy");
        }
    </script>
</head>
<body class="easyui-layout">
<div id="mm" style="width: 500px; text-align: left;"></div>
<div region="north" border="false" style="height: 60px;">
    <form id="userQuForm">
        <table style="width:100%; font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
            <tr>
                <td width="100" align="right">借阅证卡号：</td>
                <td width="80" align="left"><input name="readCard" class="easyui-textbox" style="width:80px;" /></td>
                <td align="left">
                    <a href="#" onclick="borrowBook();" class="easyui-linkbutton" iconCls="icon-search">借书
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>