<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>教师管理</title>
    <meta http-equiv="pragma" content="no-cache">           
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
	<%@ include file="../../bsp/layout/taglibnew.jsp" %>
	<script type="text/javascript">
	
	       jQuery.ajaxSetup({cache:false});
	       
	       jQuery(function($){
	    	  $('#AssociatorTeTable').datagrid({
	    		    title:'教师列表', 				//标题
					method:'post',
					fit:true,
					iconCls:'icon-tip', 			//图标 ？
					singleSelect:false, 			//多选 ？
					fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
					striped: true, 					//奇偶行颜色不同                        
					collapsible:true,				//可折叠 ？
					url:"${ctx}/associator/associatorTeacherList", 			//数据来源
					sortName: 'id',				//排序的列
					sortOrder: 'desc', 				//倒序
					remoteSort: true, 				//服务器端排序  ？
					idField:'id', 				//主键字段 
					queryParams:{}, 				//查询条件 ？
					pagination:true, 				//显示分页  
					rownumbers:true, 
					columns:[[
		                         {field:'ck',checkbox:true,width:5},  
		                         {field:'name',title:'姓名',width:100,sortable:true,
		                             formatter:function(value,row,index){return row.name;}	
							     },
							     {field:'idcard',title:'身份证号',width:160,sortable:false,
							         formatter:function(value,row,index){return row.idcard}	
							     },
							     {field:'gender',title:'性别',width:100,sortable:false,
							         formatter:function(value,row,index){return row.gender}	
							     },
							     {field:'teacherNo',title:'教职工号',width:100,sortable:true,
		                        	 formatter:function(value,row,index){return row.teacherNo}
							     },
							     {field:'courses',title:'所教课程',width:100,sortable:false,
							         formatter:function(value,row,index){return row.courses}	 
							     },
							     {field:'phone',title:'手机号',width:100,sortable:true,
		                        	 formatter:function(value,row,index){return row.phone}
							     },
							     {field:'qq',title:'qq号',width:100,sortable:true,
		                        	 formatter:function(value,row,index){return row.qq}
							     },
							     {field:'mail',title:'常用邮箱',width:100,sortable:true,
		                        	 formatter:function(value,row,index){return row.mail}
							     },
							     {field:'cardno',title:'借阅证卡号',width:100,sortable:false,
							         formatter:function(value,row,index){return row.cardno}	
							     },
							     {field:'cardtypeName',title:'会员卡类型',width:100,sortable:false,
							         formatter:function(value,row,index){return row.cardtypeName}	
							     },
							     {field:'expiryDate',title:'借阅证有效期',width:100,sortable:false,
						             formatter:function(value,row,index){return row.expiryDate}	
							     },
							     {field:'joinDate',title:'加入时间',width:100,sortable:false,
							         formatter:function(value,row,index){return row.joinDate}	
							     },
							     {field:'stateName',title:'会员状态',width:80,sortable:false,
							     formatter:function(value,row,index){return row.stateName}	
							     }
						     ]],
				           toolbar:[
									{text:'新增', iconCls:'icon-add', 
										handler:function(){
											addTeAssociator();
										}
									},'-',
									{text:'修改', iconCls:'icon-edit', 
										handler:function(){
											updateTeAssociator();
										}
									},'-',
									{text:'删除', iconCls:'icon-remove', 
										handler:function(){
											delTeAssociator();
										}
									}
								],
						
						onLoadSuccess:function(){$('#AssociatorTable').datagrid('clearSelections');} 
					});
					
	    	  });

	       function addTeAssociator(){
	    	   $("<div id='teacher_box' style='padding:5px'></div>").show().dialog({
	    		   title:'新增教师信息',
	    		   href:'${ctx}/associator/addTeacherAssociator',
	    		   width:650,
	    		   height:380,
	    		   model:true,
	    		   onLoad : function(){$('#associatorTeacher').form('reset');},
	    		   onClose : function(){
	    			   $(this).dialog('destroy');
	    			   $('#AssociatorTeTable').datagrid('reload');
	    		   } 
	    	   });
	       }
	       
	       function updateTeAssociator(){
	    	   var rows = $('#AssociatorTeTable').datagrid('getSelections'); 
	    	   if(rows.length==0){
					$.messager.alert('提示',"请选择学生信息",'info');
					return;
				}
				if(rows.length > 1){
					$.messager.alert('提示',"只能选择一条学生信息修改",'info');
					return;
				}	
				
				$("<div id='teacher_box' style='padding:5px'></div>").show().dialog({
					title:'修改学生信息',
					href:'${ctx}/associator/updateTeacherAssociator',  
					width:650,
					height:380,
					modal:true,
					onLoad: function(){$("#associatorUpTeacher").form('load', rows[0]);}, //load?
					onClose : function() {
			               $(this).dialog('destory');
			               $('#AssociatorTeTable').datagrid('reload');
			        }
				});
	       }
	       
	       function delTeAssociator(){
	    	   $.messager.confirm('提示','确实要删除吗？',function(result){
	    		   if(result){
	    		   var rows = $('#AssociatorTeTable').datagrid('getSelections');
	    		   var ps = "";
	    		   $.each(rows,function(i,n){ //i ：选中的的行， n表示里面的各个字段值
							
							if(i==0){
								ps += "?id="+n.id;
							} else {
								ps += "&id="+n.id;  //?和&
							}	
						});
						
	    		       $.post('${ctx}/associator/delTeacher'+ps,function(data){
							$('#AssociatorTeTable').datagrid('reload');
							$.messager.alert('提示',data.mes,'info');
						});
	    		   }	
	    	   });
	       }
	       
	       function searchAssociator(){
				var params = $('#AssociatorTeTable').datagrid('options').queryParams;
				var fields =$('#associatorForm').serializeArray(); //自动序列化表单元素为JSON对象 ，那里的表单？
				$.each( fields, function(i, field){
					params[field.name] = field.value; 
				});

				$('#AssociatorTeTable').datagrid('reload');
	       }
	       
	       function clearAssociator(){
				$('#associatorForm').form('clear');
			}
	</script>
</head>
<body class="easyui-layout">
 <div region="north" border="false" style="height: 60px;">
		<form id="associatorForm">
			<table style="font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
				<tr>
					<td width="80" align="right">教师姓名：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="name" id="name"  />
					</td>
					
					<td width="80" align="right">身份证号：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="idcard" id="idcard"  />
					</td>
					
					<td width="80" align="right">会员状态：</td>
					<td align="left">
						<select id="state" name="state" class="easyui-combobox" style="width:100px;height:20px;">
						        <option value="">请选择</option>
						        <option value="01">正常使用</option>
						        <option value="02">会员逾期</option>
					    </select>
					</td>
					
					<td width="100" align="right">日期开始：</td>
					<td align="left">
						<input id="startdate" name="startdate" type="text" class="easyui-datebox" style="width:100px;height:20px;" >
					</td>
					<td width="100" align="right">日期终止：</td>
					<td align="left">
						<input id="enddate" name="enddate" type="text" class="easyui-datebox" style="width:100px;height:20px;" >
					</td>
					<td align="right" width="150">
						<a href="#" onclick="searchAssociator();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="clearAssociator();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
					</td>
				</tr>
			</table>
		</form>		
	</div>
      
      <div region="center" border="false" >
		<table id="AssociatorTeTable"></table>
	</div>
</body>
</html>