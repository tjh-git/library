<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书管理</title>
    <meta http-equiv="pragma" content="no-cache">           
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
	<%@ include file="../../bsp/layout/taglibnew.jsp" %>
	<script type="text/javascript">
	
	       jQuery.ajaxSetup({cache:false});
	       jQuery(function($){
		    	  $('#BookTable').datagrid({
		    		    title:'图书列表', 				//标题
						method:'post',
						fit:true,
						iconCls:'icon-tip', 			//图标 ？
						singleSelect:false, 			//多选 ？
						fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
						striped: true, 					//奇偶行颜色不同                        
						collapsible:true,				//可折叠 ？
						url:"${ctx}/bookInfo/bookList", 			//数据来源
						sortName: 'id',				//排序的列
						sortOrder: 'desc', 				//倒序
						remoteSort: true, 				//服务器端排序  ？
						idField:'id', 				//主键字段 
						queryParams:{}, 				//查询条件 ？
						pagination:true, 				//显示分页  
						rownumbers:true, 
	                    columns:[[
	                            {field:'ck',checkbox:true,width:5},
	                            {field:'tstm',title:'图书条码',width:100,sortable:true,
	                                formatter:function(value,row,index){
	                                	return row.tstm;
	                                }	
						        },
	                            {field:'tsmc',title:'图书名称',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tsmc}	
						        },  
						        {field:'ywfj',title:'有无分卷',width:100,sortable:false,
						            formatter:function(value,row,index){return row.ywfj}	
						        },
						        {field:'csmc',title:'丛书名称',width:100,sortable:false,
						            formatter:function(value,row,index){return row.csmc}	
						        },
						        {field:'bzzz',title:'编著作者',width:100,sortable:false,
						            formatter:function(value,row,index){return row.bzzz}	
						        },
						        {field:'fyzz',title:'翻译作者',width:100,sortable:false,
						            formatter:function(value,row,index){return row.fyzz}	
						        },
						        {field:'isbn',title:'图书ISBN',width:100,sortable:false,
						            formatter:function(value,row,index){return row.isbn}	
						        },
						        {field:'cbs',title:'出版社',width:100,sortable:false,
						            formatter:function(value,row,index){return row.cbs}	
						        },
						        {field:'tsyyName',title:'图书语言',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tsyyName}	
						        },
						        {field:'tslxName',title:'图书类型',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tslxName}	
						        },
						        {field:'cbsj',title:'出版日期',width:100,sortable:false,
						            formatter:function(value,row,index){return row.cbsj}	
						        },
						        {field:'tsbc',title:'图书版次',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tsbc}	
						        },
						        {field:'tskc',title:'图书印次',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tskc}	
						        },
						        {field:'tskb',title:'图书开本',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tskb}	
						        },
						        {field:'yszz',title:'图书装帧',width:100,sortable:false,
						            formatter:function(value,row,index){return row.yszz}	
						        },
						        {field:'tsys',title:'图书页数',width:100,sortable:false,
						            formatter:function(value,row,index){return row.tsys}	
						        },
						        {field:'dj',title:'定价',width:100,sortable:false,
						            formatter:function(value,row,index){return row.dj}	
						        },
						        {field:'sfgjs',title:'是否工具书',width:100,sortable:false,
						            formatter:function(value,row,index){return row.sfgjs}	
						        },
						        {field:'sfqj',title:'是否期刊',width:100,sortable:false,
						            formatter:function(value,row,index){return row.sfqj}	
						        },
						        {field:'lrrp',title:'录入日期',width:100,sortable:false,
						            formatter:function(value,row,index){return row.lrrp}	
						        },
						        {field:'userId',title:'操作员',width:100,sortable:false,
						            formatter:function(value,row,index){return row.userId}	
						        }
						       
						       ]],
						       
					           toolbar:[
										{text:'新增', iconCls:'icon-add', 
											handler:function(){
												addBook();
											}
										},'-',
										{text:'修改', iconCls:'icon-edit', 
											handler:function(){
												updateBook();
											}
										},'-',
										{text:'删除', iconCls:'icon-remove', 
											handler:function(){
												delBook();
											}
										},
									],
							
							onLoadSuccess:function(){$('#BookTable').datagrid('clearSelections');} 
						});
						
		    	  });
					
	       function addBook(){
	    	   $("<div id='book_box' style='padding:5px'></div>").show().dialog({
	    		   title:'新增学生信息',
	    		   href:'${ctx}/bookInfo/addBook',
	    		   width:650,
	    		   height:300,
	    		   model:true,
	    		   onLoad : function(){$('#addBookList').form('reset');},
	    		   onClose : function(){
	    			   $(this).dialog('destroy');
	    			   $('#BookTable').datagrid('reload');
	    		   }
	    		   
	    		   
	    	   });
	       }
	       
	       function updateBook(){
	    	   var rows = $('#BookTable').datagrid('getSelections'); 
	    	   if(rows.length==0){
					$.messager.alert('提示',"请选择图书信息",'info');
					return;
				}
				if(rows.length > 1){
					$.messager.alert('提示',"只能选择一条图书信息修改",'info');
					return;
				}	
				
				$("<div id='book_box' style='padding:5px'></div>").show().dialog({
					title:'修改图书信息',
					href:'${ctx}/bookInfo/updateBookList',
					width:650,
					height:300,
					modal:true,
					onLoad: function(){$("#updateBookList").form('load', rows[0]);}, //load?
					onClose : function() {
			               $(this).dialog('destroy');
			               $('#BookTable').datagrid('reload');
			        }
				});
	       }
	       
	       function delBook(){
	    	   $.messager.confirm('提示','确实要删除吗？',function(result){
	    		   if(result){
	    		   var rows = $('#BookTable').datagrid('getSelections');
	    		   var ps = "";
	    		   $.each(rows,function(i,n){ //i ：选中的的行， n表示里面的各个字段值
							
							if(i==0){
								ps += "?id="+n.id;
							} else {
								ps += "&id="+n.id;  //?和&
							}	
						});
						
	    		       $.post('${ctx}/bookInfo/delBook'+ps,function(data){
							$('#BookTable').datagrid('reload');
							$.messager.alert('提示',data.mes,'info');
						});
	    		   }	
	    	   });
	       }
	
	
	function searchBook(){
		var params = $('#BookTable').datagrid('options').queryParams;
		var fields =$('#bookForm').serializeArray(); //自动序列化表单元素为JSON对象 ，那里的表单？
		$.each( fields, function(i, field){
			params[field.name] = field.value; 
		});

		$('#BookTable').datagrid('reload');
       
	 }
   
        function clearBook(){
		$('#bookForm').form('clear');
	}
	</script>
</head>
<body class="easyui-layout">

        <div region="north" border="false" style="height: 60px;">
		<form id="bookForm">
			<table style="font-size:12px; border:0px solid #f0f0f0;margin:10px 0 0 0;" >
				<tr>
					<td width="80" align="right">图书名称：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="tsmc" id="tsmc"  />
					</td>
					
					
					<td width="80" align="right">图书ISBN：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="isbn" id="isbn"  />
					</td>
					
					
					<td width="80" align="right">编著作者：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="bzzz" id="bzzz"  />
					</td>
					
					<td width="80" align="right">出版社：</td>
					<td align="left">
						<input type="text" class="easyui-textbox" style="width:100px;height:20px;" name="cbs" id="cbs"  />
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
						<a href="#" onclick="searchBook();" class="easyui-linkbutton" iconCls="icon-search">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" onclick="clearBook();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
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