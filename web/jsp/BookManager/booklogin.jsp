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
                title:'订单列表', 				//标题
                method:'post',
                fit:true,
                iconCls:'icon-tip', 			//图标 ？
                singleSelect:false, 			//多选
                fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
                striped: true, 					//奇偶行颜色不同
                collapsible:true,				//可折叠 ？
                url:"${ctx}/booklogin/getLoginList", 			//数据来源
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
                    {field:'Book_id',title:'订单号',width:100,sortable:true,
                        formatter:function(value,row,index){
                            return row.order_id;
                        }
                    },
                    {field:'BookName',title:'图书名称',width:80,sortable:false,hidden:true,
                        formatter:function(value,row,index){
                            return row.bookname;
                        }
                    },

                    {field:'PublishCom',title:'出版社',width:80,sortable:false,
                        formatter:function(value,row,index){return row.publishcom;}
                    },
                    {field:'writer',title:'图书作者',width:80,sortable:false,
                        formatter:function(value,row,index){return row.writer;}
                    },
                    {field:'booktype',title:'图书类型',width:80,sortable:false,
                        formatter:function(value,row,index){return row.booktype;}
                    },
                    {field:'publishdate',title:'出版日期',width:80,sortable:false,
                        formatter:function(value,row,index){return row.publishdate;}
                    },
                    {field:'book_buy_price',title:'图书单价',width:80,sortable:false,
                        formatter:function(value,row,index){return row.book_buy_price;}
                    },
                    {field:'buy_num',title:'购买总量',width:80,sortable:false,
                        formatter:function(value,row,index){return row.buy_num;}
                    },
                    {field:'buy_amount_price',title:'购买总价',width:80,sortable:false,
                        formatter:function(value,row,index){return row.buy_amount_price;}
                    },
                    {field:'getdate',title:'入库日期',width:80,sortable:false,
                        formatter:function(value,row,index){return row.buy_date;}
                    },
                    {field:'operator',title:'操作员',width:80,sortable:false,
                        formatter:function(value,row,index){return row.operator_name;}
                    }


                ]],
                toolbar:[
                    {text:'图书入库', iconCls:'icon-add',
                        handler:function(){
                            addBookInfo();
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
                href:'${ctx}/booklogin/popwin',
                width:830,
                height:330,
                modal:true,
                onLoad: function(){$('#BookInfoAddForm').form('reset');}, //？ onLoad和onClose的意思
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
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

<div region="center" border="false" >
    <table id="BookTable"></table>
</div>
</body>
</html>