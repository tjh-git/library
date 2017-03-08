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
        function searchBook(){
            //先取得 datagrid 的查询参数
            var fields =$('#userQuForm').serializeArray(); //自动序列化表单元素为JSON对象
            var data;
            $.each( fields, function(i, field){
                data=field.name+"="+field.value;
            });
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'查询书籍借阅信息',
                href:'books/bookShow?'+data,
                width:450,
                height:400,
                onLoad: function(){
                },
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }
        //清空查询条件
        function clearUserForm(){
            $('#userQuForm').form('clear');
            //searchUser();
        }
        //关闭窗口
        function closeDialog(id){
            $("#"+id).dialog("destroy");
        }
        //弹出窗口
        function showWindow(options){
            jQuery("#myPopWindow").window(options);
        }
    </script>
</head>
<body class="easyui-layout">
<div id="mm" style="width: 500px; text-align: left;"></div>
<div region="north" border="false" style="height: 60px;">
    <form id="userQuForm">
        <table style="width:100%; font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
            <tr>
                <td width="60" align="right">图书编号：</td>
                <td width="80" align="left"><input name="code" class="easyui-textbox" style="width:80px;" /></td>
                <td align="left">
                    <a href="#" onclick="searchBook();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="clearUserForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>