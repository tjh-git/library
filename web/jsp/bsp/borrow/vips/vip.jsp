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
        //表格查询
        jQuery(function($)
        {
        $('#userTable').datagrid({
            title:'会员借阅情况', 				//标题
            method:'post',
            fit:true,
            iconCls:'icon-tip', 			//图标
            singleSelect:false, 			//多选
            height:366, 					//高度
            fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
            striped: true, 					//奇偶行颜色不同
            collapsible:true,				//可折叠
            url:"vip/bookShow", 			//数据来源
            sortName: 'bookname',				//排序的列
            sortOrder: 'desc', 				//倒序
            remoteSort: true, 				//服务器端排序
            idField:'inde', 				//主键字段
            queryParams:{}, 				//查询条件
            pagination:true, 				//显示分页
            rownumbers:true, 				//显示行号
            columns:[[
                {field:'ck',checkbox:true,width:2}, //显示复选框
                {field:'borrowerCode',title:'借阅证卡号',width:20,sortable:true,
                    formatter:function(value,row,index){return row.borrowerCode;}
                },
                {field:'borrowerName',title:'会员姓名',width:20,sortable:false,hidden:false,
                    formatter:function(value,row,index){return row.borrowerName;}
                },
                {field:'bookCode',title:'书籍编号',width:20,sortable:true,
                    formatter:function(value,row,index){return row.bookCode;}
                },
                {field:'bookName',title:'书籍名称',width:15,sortable:false,hidden:false,
                    formatter:function(value,row,index){return row.bookName;}
                },
                {field:'cardType',title:'会员卡类型',width:20,sortable:true,hidden:false,
                    formatter:function(value,row,index){return row.cardType;}
                },
                {field:'borrowTime',title:'借阅时间',width:30,sortable:false,hidden:false,
                    formatter:function(value,row,index){return row.borrowTime;}
                },
                {field:'needReturn',title:'应还时间',width:20,sortable:false,hidden:false,
                    formatter:function(value,row,index){return row.needReturn;}
                },
                {field:'returnTime',title:'实际归还时间',width:30,sortable:true,
                    formatter:function(value,row,index){return row.returnTime;}
                },
                {field:'money',title:'预存金额',width:20,sortable:false,
                    formatter:function(value,row,index){return row.money;}
                },
                {field:'status',title:'书籍状态',width:20,sortable:false,
                    formatter:function(value,row,index){return (row.status==1?"已还":(row.status==2?"未还":"逾期！"));}
                }
            ]],
            //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
            onLoadSuccess:function(){$('#userTable').datagrid('clearSelections');}
        });
        });
        function searchVIP(){
            var params = $('#userTable').datagrid('options').queryParams;
            //先取得 datagrid 的查询参数
            var fields =$('#userQuForm').serializeArray(); //自动序列化表单元素为JSON对象
            $.each( fields, function(i, field){
                //alert("["+field.name+":"+field.value+"]");
                params[field.name] = field.value; //设置查询参数
            });
            $('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
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
                <td width="60" align="right">会员编号：</td>
                <td width="80" align="left"><input name="code" class="easyui-textbox" style="width:80px;" /></td>
                <td width="70" align="right">查询条件：</td>
                <td>
                    <select class="easyui-combobox" name="enable" style="width:100px;" data-options="editable:false">
                        <option value="0">全部</option>
                        <option value="1">曾经借阅归还</option>
                        <option value="2">在借书籍</option>
                        <option value="3">逾期未换</option>
                    </select>
                </td>
                <td align="left">
                    <a href="#" onclick="searchVIP();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;
                    <a href="#" onclick="clearUserForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div region="center" border="false" >
    <table id="userTable"></table>
</div>
</body>
</html>