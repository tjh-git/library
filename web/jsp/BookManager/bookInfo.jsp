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
                url:"${ctx}/book/getBookList", 			//数据来源
                sortName: 'id',				//排序的列
                sortOrder: 'desc', 				//倒序
                remoteSort: true, 				//服务器端排序  ？
                idField:'bookcode', 				//主键字段
                queryParams:{}, 				//查询条件 ？
                pagination:true, 				//显示分页
                rownumbers:true, 				//显示行号
                //图书条码、图书名称、书名简写、ISBN编号、出版社等条件筛选查询
                columns:[[
                    {field:'ck',checkbox:true,width:5},//显示复选框
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
                    {field:'BookISBN',title:'ISBN编号',width:80,sortable:false,align:'center',
                        formatter:function(value,row,index){
                            return row.isbn;
                        }
                    },
                    {field:'PublishCom',title:'出版社',width:80,sortable:false,
                        formatter:function(value,row,index){return row.publishcom;}
                    },
                    {field:'writer',title:'图书作者',width:80,sortable:false,
                        formatter:function(value,row,index){return row.writer;}
                    },
                    {field:'translator',title:'图书翻译者',width:80,sortable:false,
                        formatter:function(value,row,index){return row.translator;}
                    },
                    {field:'booktype',title:'图书类型',width:80,sortable:false,
                        formatter:function(value,row,index){return row.booktype;}
                    },
                    {field:'publishdate',title:'出版日期',width:80,sortable:false,
                        formatter:function(value,row,index){return row.publishdate;}
                    },
                    {field:'bookprice',title:'定价',width:80,sortable:false,
                        formatter:function(value,row,index){return "￥"+row.bookprice;}
                    },
                    {field:'getdate',title:'出版日期',width:80,sortable:false,
                        formatter:function(value,row,index){return row.getdate;}
                    },
                    {field:'operator',title:'操作员',width:80,sortable:false,
                        formatter:function(value,row,index){return row.operator_name;}
                    }


                ]],
                toolbar:[
                    {text:'新增', iconCls:'icon-add',
                        handler:function(){
                            addBookInfo();
                        }
                    },'-',
                    {text:'修改', iconCls:'icon-edit',
                        handler:function(){
                            updateBookInfo();
                        }
                    },'-',
                    {text:'批量添加', iconCls:'icon-edit',
                        handler:function(){
                            uploadFile();
                        }
                    },'-',
                    {text:'删除', iconCls:'icon-remove',
                        handler:function(){
                            delBookInfo();
                        }
                    },'-'
                ],
                //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
                onLoadSuccess:function(){$('#BookTable').datagrid('clearSelections');}  //clearSelection的动作
            });

        });
        //新增           对应toolbar中的新增函数 ，下方同上
        function addBookInfo(){
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'新增图书编目',
                href:'${ctx}/book/addBookInfoPopWin',
                width:830,
                height:530,
                modal:true,
                onLoad: function(){$('#BookInfoAddForm').form('reset');}, //？ onLoad和onClose的意思
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }
        //上传文件
        function uploadFile(){
            $("#iframe").contents().find("#upload-input").click();
        }
        //修改
        function updateBookInfo(){
            var rows = $('#BookTable').datagrid('getSelections'); //得到选中的修改列 getRows row[0].
            if(rows.length==0){
                $.messager.alert('提示',"请选择图书信息",'info');
                return;
            }
            if(rows.length > 1){
                $.messager.alert('提示',"只能选择一条图书信息修改",'info');
                return;
            }
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'修改图书信息',
                href:'${ctx}/book/addBookInfoPopWin',  //跳转到updateBookInfoPopWin页面的没看出来数据
                width:830,
                height:530,
                modal:true,
                onLoad: function(){$("#BookInfoAddForm").form('load', rows[0]);}, //load?
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }

        //删除
        function delBookInfo(){

            $.messager.confirm('提示','确定要删除吗?',function(result){
                if (result){
                    var rows = $('#BookTable').datagrid('getSelections');
                    var ps = "";
                    console.log(rows)
                    if(rows.length==0){
                        alert("请选择要删除的数据")
                        return
                    }
                    $.each(rows,function(i,n){ //i ：选中的的行， n表示里面的各个字段值
                        console.log(n.bookcode)
                        if(i==0){
                            ps += "?id="+n.bookcode;
                        } else {
                            ps += "&id="+n.bookcode;  //?和&
                        }
                    });
                    $.post('${ctx}/book/delBookInfo'+ps,function(data){
                        $('#BookTable').datagrid('reload');
                        $.messager.alert('提示',data.mes,'info');
                    });
                }
            });

        }

        //审核通过
        function checkBookInfo(){
            $.messager.confirm('提示','确定审核通过吗?',function(result){
                if (result){
                    var rows = $('#BookTable').datagrid('getSelections');
                    var ps = "";
                    $.each(rows,function(i,n){
                        if(i==0){
                            ps += "?id="+n.id;
                        } else {
                            ps += "&id="+n.id;
                        }
                    });
                    $.post('${ctx}/finance/checkBookInfo'+ps,function(data){
                        $('#BookTable').datagrid('reload');
                        $.messager.alert('提示',data.mes,'info');
                    });
                }
            });
        }
        function reload(){


            $("#BookTable").datagrid("reload");

        }
        //表格查询
        function searchBookInfo(){
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
        function clearBookInfoForm(){
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
<iframe id="iframe"  style="display:none;" src="/book/fileUpload"></iframe>
<div region="north" border="false" style="height: 60px;">
    <form id="BookInfoForm">
        <table style="font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
            <tr>
                <%--图书条码、图书名称、书名简写、ISBN编号、出版社--%>
                <td width="80" align="right">图书条码：</td>
                <td align="left">
                    <input type="text" class="easyui-numberbox" style="width:100px;height:20px;" name="bookcode" id="bookcode"  />
                </td>
                <td width="80" align="right">图书名称：</td>
                <td align="left">
                    <input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="bookname" id="bookname"  />
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
                <td width="100" align="right">出版社名称：</td>
                <td align="left">
                    <input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="publishcom" id="publishcom"  />
                </td>
                <td align="right" width="170" >
                    <a href="#" onclick="searchBookInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="clearBookInfoForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
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