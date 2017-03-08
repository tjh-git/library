<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../bsp/layout/taglibnew.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>                                       
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>信息发布系统</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta name="renderer" content="ie-stand" />
</head>

<body style="overflow-x:hidden;background:#f0f0f0">
<script type="text/javascript">
	jQuery.ajaxSetup({cache:false});//ajax不缓存
	jQuery(function($){
		//加载datagride
		$('#newsTable').datagrid({
			title:'信息列表', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//单选
			height:370, 					//高度
			fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:true,				//可折叠
			url:"${ctx}/news/queryNewsList", 			//数据来源
			sortName: 'newsId',				//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'newsId', 					//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'sno',title:'id',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.newsId;}
					},
					{field:'newsTitle',title:'标题',width:400,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.newsTitle;}
					},
					{field:'newsTypeName',title:'类型',width:100,sortable:false,hidden:false,
						formatter:function(value,row,index){
							return row.newsTypeName;
						}
					},
					{field:'newsIsChecked',title:'状态',width:60,sortable:false,hidden:false,
						formatter:function(value,row,index)
						{
						var i=row.newsIsChecked;
						if(i==0){
							return "未审核";
							}else if(i==1){
							return "核准";
							}else{
								return "驳回";
							}
					    }
					},
					{field:'newsAuthor',title:'发布人',width:100,sortable:false,
						formatter:function(value,row,index){return row.newsAuthor;}
					},
					{field:'newsPublishTime',title:'发布时间',width:120,sortable:false,hidden:false,
						formatter:function(value,row,index){return row.newsPublishTime;}
					},
					{field:'newsSubTitle',title:'副标题',width:200,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.newsSubTitle;}
					},
					{field:'newsIsTop',title:'是否置顶',width:60,sortable:false,hidden:true,
						formatter:function(value,row,index){
						var  i=row.newsIsTop;
						if(i==1){
							return "置顶";
						}else{
							return "不置顶";
							}
						}
					}
					,
					{field:'newsIsLinkTitle',title:'标题类型',width:60,sortable:false,hidden:false,
						formatter:function(value,row,index){
						var  i=row.newsIsLinkTitle;
						if(i==1){
							return "链接标题";
						}else{
							return "非链接标题";
							}
						}
					},
					{field:'newsLinkUrl',title:'链接路径',width:200,sortable:false,hidden:false,
						formatter:function(value,row,index){
						return row.newsLinkUrl;
					}
					},
					{field:'newsIsImageNews',title:'是否图片标题',width:150,sortable:false,hidden:false,
						formatter:function(value,row,index){
						var i=row.newsIsChecked;
						if(i==1){
							return "是";
							}else{
								return "否";
							}
					}
					},
					{field:'newsImageUrl',title:'图片标题路径',width:150,sortable:false,hidden:false,
						formatter:function(value,row,index){
						return row.newsImageUrl;
					}
					},
					{field:'newsCompany',title:'单位',width:150,sortable:false,hidden:false,
						formatter:function(value,row,index){
						return row.newsCompany;
					}
					}
					]],
			toolbar:[
					{text:'添加信息', iconCls:'icon-add', 
						handler:function(){addNewsRow();}
					},'-',
					{text:'修改信息', iconCls:'icon-edit',
						handler:function(){updateNewsRow();}
					},'-',
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteNewsRow();}
					},
					'-',
					{text:'预览', iconCls:'icon-edit',
						handler:function(){preview();}
					},
					'-',
					{text:'发布到其他栏目', iconCls:'icon-auth',
						handler:function(){synchronousPush();}
					},'-'
					],
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#newsTable').datagrid('clearSelections');}
		});
		
	});


	//新增
	function addNewsRow(){
		showWindow({
			title:'信息添加',
			href:'news/news4Add?newstype='+jQuery("#newsType  option:selected").val(),
			width:600,
			height:400,
		onLoad: function(){
			$('#newsAddForm').form('reset');
		},
		onBeforeClose:function(){
			if ($('#newsAccessory').length > 0) { //注意jquery下检查一个元素是否存在必须使用 .length >0 来判断
			     $('#newsAccessory').uploadify('destroy');
			}
			if ($('#newsImage').length > 0) { //注意jquery下检查一个元素是否存在必须使用 .length >0 来判断
			     $('#newsImage').uploadify('destroy');
			}
			$("#newsAddForm").form('reset');
		}
		});
	}
	//预览
		function preview(){
			var rows = $('#newsTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要预览的记录",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条记录进行预览",'info');
				return;
			}
			var sno=rows[0].newsId;
		//	window.location.href='news/preview?sno='+sno;
			window.open('${ctx}/newsDetail?sno='+sno);
			}

		//更新    
		function detail(){
			var rows = $('#newsTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的记录",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条记录进行查看",'info');
				return;
			}
			var sno=rows[0].newsId;
			showWindow({
				title:'信息查看',
				href:'${ctx}/news/detail?sno='+sno,
				width:600,
				height:400,
			onLoad: function(){
				$("#newsDetailForm").form('load', rows[0]);
				}
			});
		}

	//同步推送
	function synchronousPush(){
		var rows = $('#newsTable').datagrid('getSelections');
		//alert(rows[0].userId);
		//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
		if(rows.length==0){
			$.messager.alert('提示',"请选择您同步推送的信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条信息进行推送",'info');
			return;
		}
		showWindow({
			title:'信息同步推送',
			href:'${ctx}/news/synchronousPushPopwin?newsId='+rows[0].newsId,
			width:520,
			height:400,
			onLoad: function(){
				
			}
		});
	}
		
	//更新    
	function updateNewsRow(){
		var rows = $('#newsTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要修改的记录",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条记录进行修改",'info');
			return;
		}
		var sno=rows[0].newsId;
		showWindow({
			title:'信息修改',
			href:'${ctx}/news/news4Update?sno='+sno,
			width:600,
			height:400,
			onLoad: function(){
			$("#newsUpdateForm").form('reset');
			$("#newsUpdateForm").form('load', rows[0]);
			
			},
			onBeforeClose:function(){
				$("#newsUpdateForm").form('reset');
			},
			onBeforeClose:function(){
				if ($('#newsAccessory').length > 0) { //注意jquery下检查一个元素是否存在必须使用 .length >0 来判断
				     $('#newsAccessory').uploadify('destroy');
				}
				if ($('#newsImage').length > 0) { //注意jquery下检查一个元素是否存在必须使用 .length >0 来判断
				     $('#newsImage').uploadify('destroy');
				}
				$("#newsUpdateForm").form('reset');
			}
		});
	}
			
	//删除
	function deleteNewsRow(){
		var rows = $('#newsTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的信息",'info');
			return;
		}
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?sno="+n.newsId;
					} else {
						ps += "&sno="+n.newsId;
					}	
				});
				$.post('${ctx}/news/delNews'+ps,function(data){
					$('#newsTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}
	//表格查询
	function searchNews(){
		var params = $('#newsTable').datagrid('options').queryParams;
		//先取得 datagrid 的查询参数
		var fields =$('#searchNewsForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		});
		$('#newsTable').datagrid('reload'); //设置好查询参数 reload一下就可以了
	}
	
	//弹出窗口
	function showWindow(options){
		jQuery("#myPopWindow").window(options);
	}
	//关闭弹出窗口
	function closeWindow(){
		
		$("#myPopWindow").window('close');
	}
	//清空查询条件
	function clearNewsForm(){
		$('#searchNewsForm').form('clear');
	}
	
</script>

<form id="searchNewsForm" name="searchNewsForm">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<table style="width:98%; font-size:12px; border:0px solid #f0f0f0;">
		<tr>
			<td width="30%" align="left">信息类型：
				<select id="newsType"  name="newsType" style=" width:50%; height: 30px">
					<option value="" >全部</option>
					<c:forEach items="${newsTypeList}" var="nType">
						<option value="${nType.newsTypeCode}" ><c:out value="${nType.newsTypeName}" /></option>
					</c:forEach>
				</select>
			</td>
			<td width="30%" align="left">信息标题：
				<input style="width: 49%;height: 20px" name="newsTitle">
			</td>
			<td width="30%" align="left">
				审核状态：
				<select  name="newsIsChecked" style="width: 50%;height: 30px">
					<option value="0">未审核</option>
					<option value="1">核准</option>
					<option value="2">驳回</option>
					<option value="3" selected="selected">全部</option>
				</select>
			</td>
			</tr>
			<tr style="display:none;">
				<td width="30%" align="left">
					图片信息：
					<select  name="newsIsImageNews" style="width: 50%;height: 30px">
						<option value="" selected="selected" >全部</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
				<td width="38%" align="left">
					附件标题：
					<select  name="newsIsAccessory" style="width: 40%;height: 30px">
						<option value="" selected="selected" >全部</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
				<td width="38%" align="left">
					链接标题：
					<select  name="newsIsLinkTitle" style="width: 40%;height: 30px">
						<option value="" selected="selected" >全部</option>
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
			</tr>
			<tr>
			<td width="30%" align="left">
				开始日期：&nbsp;<input id="publishStartTime" name="publishStartTime" class="Wdate" type="text"
						style="width: 49%;height: 25px" data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
			</td>
			<td width="30%" align="left">
				结束日期：&nbsp;<input id="publishEndTime" name="publishEndTime" class="Wdate" type="text"
						style="width: 49%;height: 25px" data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
			</td>
			<td  width="38%" align="left">
				发布人：&nbsp;&nbsp;&nbsp;&nbsp;
				<select id="newsModifier" name="newsModifier" style="width: 50%;height: 30px">
					<option value="" >全部</option>
					<c:forEach items="${userNewsNameList}" var="nName">
						<option value="${nName.userAccount}" ><c:out value="${nName.userName}" /></option>
					</c:forEach>
				</select>
			</td> 
			</tr>
			<tr>
				<td width="30%" align="left"></td>
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="searchNews();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
					&nbsp;&nbsp;&nbsp;	&nbsp;&nbsp;&nbsp;
				<a href="#" onclick="clearNewsForm()" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
				</td> 
				<td width="38%" align="left"></td>	
			</tr>
	</table>
	</div>
</form>
<div style="padding:5" id="tabdiv">
	<table id="newsTable"></table>
</div>
</body>

</html>