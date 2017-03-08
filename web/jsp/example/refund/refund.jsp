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
	<%@ include file="../../bsp/layout/taglibnew.jsp" %>
	<script type="text/javascript">
		
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		jQuery(function($){

			//加载datagride
			$('#RefundTable').datagrid({
				title:'退费列表', 				//标题
				method:'post',
				fit:true,
				iconCls:'icon-tip', 			//图标 ？
				singleSelect:false, 			//多选 ？
				fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:true,				//可折叠 ？
				url:"${ctx}/finance/refundList", 			//数据来源
				sortName: 'id',				//排序的列
				sortOrder: 'desc', 				//倒序
				remoteSort: true, 				//服务器端排序  ？
				idField:'id', 				//主键字段 
				queryParams:{}, 				//查询条件 ？
				pagination:true, 				//显示分页  
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:5},//显示复选框   
						{field:'studentId',title:'姓名',width:100,sortable:true,                              
							formatter:function(value,row,index){
								return row.studentId;
							}                    
						},
						{field:'type',title:'退费类型',width:80,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.type;}
						},
						{field:'typeName',title:'退费类型',width:80,sortable:false,hidden:false,
							formatter:function(value,row,index){return row.typeName;}
						},
						{field:'money',title:'退费金额(元)',width:80,sortable:false,align:'center',
							formatter:function(value,row,index){
								return "<div width='100%' align='right'>"+row.money+"</div>";
							}
						},
						{field:'date',title:'退费日期',width:80,sortable:false,
							formatter:function(value,row,index){return row.date;}
						},
						{field:'state',title:'审核状态',width:80,sortable:false,
							formatter:function(value,row,index){return row.state=='1'?'审核通过':'未审核';}
						},
						{field:'remarks',title:'备注',width:180,sortable:false,
							formatter:function(value,row,index){return row.remarks;}
						},
						{field:'userId',title:'制单人',width:100,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.userId;}
						},
						{field:'userName',title:'制单人姓名',width:100,sortable:false,
							formatter:function(value,row,index){return row.userName;}
						},
						{field:'time',title:'制单时间',width:130,sortable:false,
							formatter:function(value,row,index){return row.time;}
						}
						]],
				toolbar:[
							{text:'新增', iconCls:'icon-add', 
								handler:function(){
									addRefundInfo();
								}
							},'-',
							{text:'修改', iconCls:'icon-edit', 
								handler:function(){
									updateRefundInfo();
								}
							},'-',
							{text:'删除', iconCls:'icon-remove', 
								handler:function(){
									delRefundInfo();
								}
							},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#RefundTable').datagrid('clearSelections');}  //clearSelection的动作
			});
			
		});
		//新增           对应toolbar中的新增函数 ，下方同上
		function addRefundInfo(){  
			$("<div id='edit_box' style='padding:5px'></div>").show().dialog({
				title:'新增退费信息',
				href:'${ctx}/finance/addRefundInfoPopWin',
				width:650,
				height:300,
				modal:true,
				onLoad: function(){$('#refundInfoAddForm').form('reset');}, //？ onLoad和onClose的意思
				onClose : function() {
		               $(this).dialog('destroy');
		        }
			});
		}				
		
		//修改
		function updateRefundInfo(){
			var rows = $('#RefundTable').datagrid('getSelections'); //得到选中的修改列
			if(rows.length==0){
				$.messager.alert('提示',"请选择退费信息",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条学退费信息修改",'info');
				return;
			}	
			if(rows[0].state=='1'){
				$.messager.alert('提示',"该退费信息已审核通过，不允许修改",'info');
				return;
			}
			
			$("<div id='edit_box' style='padding:5px'></div>").show().dialog({
				title:'修改退费信息',
				href:'${ctx}/finance/updateRefundInfoPopWin',  //跳转到updateRefundInfoPopWin页面的没看出来数据
				width:650,
				height:300,
				modal:true,
				onLoad: function(){$("#refundInfoUpForm").form('load', rows[0]);}, //load?
				onClose : function() {
		               $(this).dialog('destroy');
		        }
			});
		}
		
		//删除
		function delRefundInfo(){
			
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#RefundTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){ //i ：选中的的行， n表示里面的各个字段值
						if(n.state=='1'){
							$.messager.alert('提示',"存在审核通过信息，不允许删除",'info');
							return;
						}
						if(i==0){
							ps += "?id="+n.id;
						} else {
							ps += "&id="+n.id;  //?和&
						}	
					});
					$.post('${ctx}/finance/delRefundInfo'+ps,function(data){
						$('#RefundTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
			
		}
		
		//审核通过
		function checkRefundInfo(){
			$.messager.confirm('提示','确定审核通过吗?',function(result){
				if (result){
					var rows = $('#RefundTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?id="+n.id;
						} else {
							ps += "&id="+n.id;
						}	
					});
					$.post('${ctx}/finance/checkRefundInfo'+ps,function(data){
						$('#RefundTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
				
		//表格查询
		function searchRefundInfo(){
			var params = $('#RefundTable').datagrid('options').queryParams;  //? 1, 什么意思，得到什么结果 额外参数
			//先取得 datagrid 的查询参数
			var fields =$('#refundInfoForm').serializeArray(); //自动序列化表单元素为JSON对象 ，那里的表单？
			console.log(fields)
			$.each( fields, function(i, field){           // 循环，function中的i 和 field 是表示fields当中的吗
				//alert("["+field.name+":"+field.value+"]");

				params[field.name] = field.value; //设置查询参数
			});
			$('#RefundTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了 // reload重新加载的动作流程，
		}

		//清空查询条件
		function clearRefundInfoForm(){
			$('#refundInfoForm').form('clear');
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
	<div region="north" border="false" style="height: 60px;">
		<form id="refundInfoForm">
			<table style="font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
				<tr>
					<td width="80" align="right">学员姓名：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="studentNameF" id="studentNameF"  />
					</td>
					<td width="100" align="right">退费日期开始：</td>
					<td align="left">
						<input id="beginDate" name="beginDate" type="text" class="easyui-datebox" style="width:100px;height:20px;" >
					</td>
					<td width="100" align="right">退费日期终止：</td>
					<td align="left">
						<input id="endDate" name="endDate" type="text" class="easyui-datebox" style="width:100px;height:20px;" >
					</td>
					<td align="right" width="170">
						<a href="#" onclick="searchRefundInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="clearRefundInfoForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
					</td>
				</tr>
			</table>
		</form>		
	</div>
	<div region="center" border="false" >
		<table id="RefundTable"></table>
	</div>
</body>
</html>