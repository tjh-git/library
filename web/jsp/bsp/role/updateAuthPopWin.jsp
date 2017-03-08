<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>
    
    <style type="text/css">
    
    .content
    {
    	width:100%;
    	height:410px;
    	overflow:hidden;
    	border-top:0px solid #E1E9DC;
        border-bottom:0px solid #E1E9DC;
        border-left:0px solid #E1E9DC;
        border-right:0px solid #E1E9DC;
    }
    .top
    {
    	align:left;
    	width:100%;
    	height:25px;
    	text-align:left;
    	padding-left:5px;
    	padding-bottom:5px;
    	font-size:13px;
        border-bottom:0px solid #E1E9DC;
    }
    .invisibleButton
    {
    	padding-top:10px;
    	width:100px;
        height:25px;
        border:none;
        background:#ffffff;
    }
    .bottom
    {
    	float:left;
    	padding-top:2px;
    	width:100%;
    	height:30px;
        border-top:0px solid #E1E9DC;
    }
    .select
    {
    	margin:0 5;
    	float:left;
    	width:180px;
    	height:400px;
    	overflow:hidden;
    	border-top:0px solid #E1E9DC;
        border-bottom:0px solid #E1E9DC;
        border-left:0px solid #E1E9DC;
        border-right:0px solid #E1E9DC;
    }
    .chooseArea
    {
    	float:left;
    	width:120px;
    	height:200px;
    	overflow:hidden;
    }
    .myButton
    {
    	padding-left:5px;
        background:#f1f1f1;
        width:100px;
        height:25px;
        border:none;
        font-size:13px;
    	border-top:1px solid #1C7887;
    	border-left:1px solid #1C7887;
    	border-right:1px solid #1C7887;
    	border-bottom:1px solid #1C7887;
        cursor:pointer;
    }
    
    </style>
    
    <script type="text/javascript">
	$(function(){
	    //移到右边
	    $('#add').click(function() {
	    //获取选中的选项，删除并追加给对方
	        $('#allAuths option:selected').appendTo('#myAuths');
	    });
	    //移到左边
	    $('#remove').click(function() {
	        $('#myAuths option:selected').appendTo('#allAuths');
	    });
	    //全部移到右边
	    $('#add_all').click(function() {
	        //获取全部的选项,删除并追加给对方
	        $('#allAuths option').appendTo('#myAuths');
	    });
	    //全部移到左边
	    $('#remove_all').click(function() {
	        $('#myAuths option').appendTo('#allAuths');
	    });
	    //双击选项
	    $('#allAuths').dblclick(function(){ //绑定双击事件
	        //获取全部的选项,删除并追加给对方
	        $("option:selected",this).appendTo('#myAuths'); //追加给对方
	    });
	    //双击选项
	    $('#myAuths').dblclick(function(){
	       $("option:selected",this).appendTo('#allAuths');
	    });
	});
	
	//加载系统角色
	$(function(){
		var allAuths = $("#allAuths");
		$.ajax({
			type:'POST',
			url:"role/getAllAuth",
			success:function(data){
				//allAuths.clear();
				$.each(data, function(n, val) {
					allAuths.append('<option value="' + val.id + '">' + val.name + '</option>');
            	});
			}
		});
	});

	//加载角色已有的权限
	$(function(){
		var myAuths = $("#myAuths");
		$.ajax({
			type:'POST',
			url:"role/getMyAuth",
			success:function(data){
				//myAuths.clear();
				$.each(data, function(n, val) {
					myAuths.append('<option value="' + val.id + '">' + val.name + '</option>');
            	});
			}
		});
	});

	//更新角色权限
	function upRoleAuth(){
		var chooseOpts = "";
        $("#myAuths option").each(function() {
        	chooseOpts += $(this).val()+',';
    	});

		//更新
		$.post("role/updateRoleAuth?roleIds="+chooseOpts, $("#roleAuthForm").serializeArray(),function(data){
			$('#myPopWindow').window('close');
			$.messager.alert('提示',data.mes,'info');
		});
	}
	
	</script>
	
	<form id="roleAuthForm" method="post" style="margin:0;text-align: center;">
		
    	<div class="content">
    		<div class="top">
    			当前角色：<input class="invisibleButton" name="roleName">
    		</div>
    		<div class="select">
        		<select multiple="multiple" id="allAuths" style="width:180px;height:380px;">
        		</select>
    		</div>
    
    		<div class="chooseArea">
    			<input class="myButton" type="button" id="add" value=" 添加 >"/>
    			<p>
    			<input class="myButton" type="button" id="add_all" value="全部添加 >>"/>
    			<p>
    			<input class="myButton" type="button" id="remove" value="移除 < "/>
    			<p>
    			<input class="myButton" type="button" id="remove_all" value="全部移除 << "/>
    		</div>

    		<div class="select">
        		<select multiple="multiple" id="myAuths" style="width:180px;height:380px;">
        		</select>
    		</div>
		</div>
		
		<div class="bottom">
    		<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
			<a href="#" id="btn-add" onclick="upRoleAuth();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    	</div>
		
	</form>
	
	</body>
	
</html> 