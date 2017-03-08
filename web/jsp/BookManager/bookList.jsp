<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/2
  Time: 4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
    <title>水厂管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="Simple">
    <%@ include file="../bsp/layout/taglibnew.jsp" %>
    <script type="text/javascript">

        jQuery.ajaxSetup({cache:false});//ajax不缓存

        jQuery(function($){

            //加载datagride
            $('#BookTable').datagrid({
                title:'退费列表', 				//标题
                method:'post',
                fit:true,
                iconCls:'icon-tip', 			//图标 ？
                singleSelect:false, 			//多选
                fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
                striped: true, 					//奇偶行颜色不同
                collapsible:true,				//可折叠 ？
                url:"${ctx}/bookLogin/getBookList", 			//数据来源
                sortName: 'id',				//排序的列
                sortOrder: 'desc', 				//倒序
                remoteSort: true, 				//服务器端排序  ？
                idField:'book_id', 				//主键字段
                queryParams:{}, 				//查询条件 ？
                pagination:true, 				//显示分页
                rownumbers:true, 				//显示行号
                //图书条码、图书名称、书名简写、ISBN编号、出版社等条件筛选查询
                columns:[[
                    {field:'ck',checkbox:true,width:5},//显示复选框
                    {field:'book_id',title:'书籍编号',width:100,sortable:true,
                        formatter:function(value,row,index){
                            return row.book_id;
                        }
                    },
                    {field:'BookCode',title:'条码号',width:100,sortable:true,
                        formatter:function(value,row,index){
                            return row.bookcode;
                        }
                    },
                    {field:'BookName',title:'图书名称',width:80,sortable:false,hidden:true,
                        formatter:function(value,row,index){
                            return row.bookname;
                        }
                    },
                    {field:'BookAd',title:'书名简写',width:80,sortable:false,hidden:false,
                        formatter:function(value,row,index){return row.bookab;}
                    },

                    {field:'writer',title:'图书作者',width:80,sortable:false,
                        formatter:function(value,row,index){return row.writer;}
                    },

                    {field:'booktype',title:'图书类型',width:80,sortable:false,
                        formatter:function(value,row,index){return row.booktype;}
                    },
                ]],
                toolbar:[

                    {text:'打印', iconCls:'icon-print',
                        handler:function(){
                            print();
                        }
                    },'-'
                ],
                //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
                onLoadSuccess:function(){$('#BookTable').datagrid('clearSelections');}  //clearSelection的动作
            });

        });
        function print(){

        }
        //表格查询
        function searchBookList(){
            var params = $('#BookTable').datagrid('options').queryParams;  //? 1, 什么意思，得到什么结果 额外参数
            //先取得 datagrid 的查询参数
            var fields =$('#BookInfoForm').serializeArray(); //自动序列化表单元素为JSON对象 ，那里的表单？
            console.log(fields)
            $.each( fields, function(i, field){           // 循环，function中的i 和 field 是表示fields当中的吗

                //alert("["+field.name+":"+field.value+"]");
                if(field.value!='')
                    params[field.name] = field.value; //设置查询参数
                else
                    delete params[field.name]
            });
            console.log($('#BookTable').datagrid('options').queryParams)
            $('#BookTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了 // reload重新加载的动作流程，
        }

        //清空查询条件
        function clearBookListForm(){
            $('#BookInfoForm').form('clear');
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
<iframe id="iframe"  style="display:none;" src="/book/fileUpload"></iframe>>
<div region="north" border="false" style="height: 60px;">
    <form id="BookInfoForm">
        <table style="font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
            <tr>
                <%--图书条码、图书名称、书名简写、ISBN编号、出版社--%>
                <td width="80" align="right">图书编号：</td>
                <td align="left">
                    <input type="text" class="easyui-numberbox" style="width:100px;height:20px;" name="book_id" id="book_id"  />
                </td>
                <td width="80" align="right">图书类型：</td>
                <td align="left">
                    <select id="booktype" name="booktype" class="easyui-combobox" style="width:175px;height:20px;">
                        <option value="1">种类1</option>
                        <option value="2">种类2</option>
                        <option value="3">种类3</option>
                    </select>
                </td>
                <td width="80" align="right">图书条码：</td>
                <td align="left">
                    <input type="text" class="easyui-numberbox" style="width:100px;height:20px;" name="bookcode" id="bookcode"  />
                </td>

            </tr>
            <tr>
                <td width="100" align="right">书名简写：</td>
                <td align="left">
                    <input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="bookab" id="bookab"  />
                </td>
                <td width="80" align="right">ISBN编号：</td>
                <td align="left">
                    <input type="text" class="easyui-numberbox" style="width:100px;height:20px;" name="isbn" id="isbn"  />
                </td>
                <td width="100" align="right">图书作者：</td>
                <td align="left">
                    <input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="writer" id="writer"  />
                </td>
                <td align="right" width="170" >
                    <a href="#" onclick="searchBookList();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="clearBookListForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
                </td>
            </tr>

        </table>
    </form>
</div>
<div region="center" border="false" >
    <table id="BookTable"></table>
</div>
</body>
</html>