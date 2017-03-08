<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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

        jQuery(function($){

            //加载机构树
            $(document).ready(function() {
                var jqueryObj=$("#userOrg");
                makeOrgTree(jqueryObj);
            });
            $('#cc').combotree('loadData', [{"id":"org0000","text":"山东省综治委护路办","children":[{"id":"org1437472737192","text":"部门A","children":[{"id":"org1437475663613","text":"组织A"}]}]}]);
            //加载datagride
            $('#userTable').datagrid({
                title:'教师列表', 				//标题
                method:'post',
                fit:true,
                iconCls:'icon-tip', 			//图标
                singleSelect:false, 			//多选
                height:366, 					//高度
                fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
                striped: true, 					//奇偶行颜色不同
                collapsible:true,				//可折叠
                url:"teacher/teacherList", 			//数据来源
                sortName: 'readCard',				//排序的列
                sortOrder: 'desc', 				//倒序
                remoteSort: true, 				//服务器端排序
                idField:'readCard', 				//主键字段
                queryParams:{}, 				//查询条件
                pagination:true, 				//显示分页
                rownumbers:true, 				//显示行号
                columns:[[
                    {field:'ck',checkbox:true,width:2}, //显示复选框
                    {field:'inde',title:'索引',width:20,sortable:true,hidden:true,
                        formatter:function(value,row,index){return row.inde;}
                    },
                    {field:'readCard',title:'借阅证卡号',width:20,sortable:true,
                        formatter:function(value,row,index){return row.readCard;}
                    },
                    {field:'name',title:'姓名',width:20,sortable:false,hidden:false,
                        formatter:function(value,row,index){return row.name;}
                    },
                    {field:'ID',title:'身份证号',width:20,sortable:true,
                        formatter:function(value,row,index){return row.ID;}
                    },
                    {field:'gender',title:'教师性别',width:20,sortable:false,hidden:false,
                        formatter:function(value,row,index){return row.gender == '1'?'男':'女';}
                    },
                    {field:'cardType',title:'会员卡类型',width:30,sortable:true,hidden:false,
                        formatter:function(value,row,index){return row.cardType;}
                    },
                    {field:'addTime',title:'加入时间',width:30,sortable:false,hidden:false,
                        formatter:function(value,row,index){return row.addTime;}
                    },
                    {field:'status',title:'会员状态',width:30,sortable:false,hidden:false,
                        formatter:function(value,row,index){return row.status;}
                    },
                    {field:'teachID',title:'教职工号',width:30,sortable:true,
                        formatter:function(value,row,index){return row.teachID;}
                    },
                    {field:'teachClass',title:'所教课程',width:20,sortable:false,
                        formatter:function(value,row,index){return row.teachClass;}
                    },
                    {field:'tel',title:'手机',width:20,sortable:false,
                        formatter:function(value,row,index){return row.tel;}
                    },
                    {field:'qq',title:'QQ',width:30,sortable:false,
                        formatter:function(value,row,index){return row.qq;}
                    },
                    {field:'email',title:'常用邮箱',width:20,sortable:false,
                        formatter:function(value,row,index){return row.email;}
                    },
                    {field:'money',title:'预存金额',width:20,sortable:false,
                        formatter:function(value,row,index){return row.money;}
                    }
                ]],
                toolbar:[
                    {text:'新增', iconCls:'icon-add',
                        handler:function(){addTeacherRow();}
                    },'-',
                    {text:'更新', iconCls:'icon-edit',
                        handler:function(){updateTeacherRow();}
                    },'-',
                    {text:'删除', iconCls:'icon-remove',
                        handler:function(){deleteTeacherRow();}
                    },'-',
                    {
                        text:'批量上传',iconCls:'icon-add',
                        handler:function(){addTeacherBatch();}
                    },'-',
                ],
                //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
                onLoadSuccess:function(){$('#userTable').datagrid('clearSelections');}
            });
        });
        //新增
        function addTeacherRow(){
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'增加教师信息',
                href:'${ctx}/vip/teacher/addPopWin',
                width:600,
                height:400,
                onLoad: function(){$('#userAddForm').form('reset');},
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }
        //更新
        function updateTeacherRow(){
            var rows = $('#userTable').datagrid('getSelections');
            //alert(rows[0].userId);
            //这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
            if(rows.length==0){
                $.messager.alert('提示',"请选择你要更新的教师",'info');
                return;
            }
            if(rows.length > 1){
                $.messager.alert('提示',"只能选择一位教师进行更新",'info');
                return;
            }
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'更新教师信息',
                href:'teacher/updatePopWin',
                width:600,
                height:400,
                onLoad: function(){
                    $("#teacherUpForm").form('load', rows[0]);
                },
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }
        //删除
        function deleteTeacherRow(){
            $.messager.confirm('提示','确定要删除吗?',function(result){
                if (result){
                    var rows = $('#userTable').datagrid('getSelections');
                    var ps = "";
                    $.each(rows,function(i,n){
                        if(i==0){
                            ps += "?readCard="+n.readCard;
                        } else {
                            ps += "&readCard="+n.readCard;
                        }
                    });
                    $.post('teacher/delTeachers'+ps,function(data){
                        $('#userTable').datagrid('reload');
                        $.messager.alert('提示',data.mes,'info');
                    });
                }
            });
        }
        //编辑教师角色
        function updateUserRoleRow(){
            var rows = $('#userTable').datagrid('getSelections');
            //这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选
            if(rows.length==0){
                $.messager.alert('提示',"请选择您要编辑角色的教师",'info');
                return;
            }
            if(rows.length > 1){
                $.messager.alert('提示',"只能选择一位教师进行编辑",'info');
                return;
            }
            $("<div id='edit_box' style='padding:5px'></div>").show().dialog({
                title:'编辑教师角色',
                href:'user/updateRolePopWin?userId='+rows[0].userId,
                width:520,
                height:480,
                onLoad: function(){
                    $("#userRoleForm").form('load', rows[0]);
                },
                onClose : function() {
                    $(this).dialog('destroy');
                }
            });
        }
        //表格查询
        function searchUser(){
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
        function addTeacherBatch()
        {
            $("#ifr").contents().find("#file").click();
        }
    </script>
</head>
<body class="easyui-layout">
<iframe src="teacher/addIframe" style="display: none" id="ifr" name="ifr"></iframe>
<%--<div region="north" border="false" style="height: 60px;">
    <form id="userQuForm">
        <table style="width:100%; font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
            <tr>
                <td width="50" align="right">adadw：</td>
                <td width="80" align="left"><input name="userAccount" class="easyui-textbox" style="width:80px;" /></td>
                <td width="50" align="right">adadwa：</td>
                <td width="80" align="left"><input name="userName" class="easyui-textbox" style="width:80px;" /></td>
                <td width="80" align="right">adwadw：</td>
                <td width="80" align="left">
                    <input id="userOrg" style="width:100px;" class="easyui-combotree" name="userOrg" data-options="url:'${ctx }/org/comboTree?pid=0'">
                </td>
                <td width="80" align="right">adwadw：</td>
                <td width="80" align="left">
                    <select class="easyui-combobox" name="enable" style="width:80px;" data-options="editable:false">
                        <option value="">全部</option>
                        <option value="1">正常</option>
                        <option value="0">禁用</option>
                    </select>
                </td>
                <td align="left">
                    <a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="#" onclick="clearUserForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>

                </td>
            </tr>
        </table>
    </form>
</div>--%>
<div region="center" border="false" >
    <table id="userTable"></table>
</div>
</body>
</html>