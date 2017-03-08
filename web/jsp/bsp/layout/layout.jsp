<%@ page language="java" pageEncoding="UTF-8"%>
<%
String userAcc = request.getSession().getAttribute("userAccount").toString();
String userName = request.getSession().getAttribute("userName").toString();
%>
<!doctype html>
<html>
<head>
    <title>山东省综治委铁路护路联防平台</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
	<%@ include file="taglibnew.jsp" %>  	
	<style type="text/css">
		.common_column{border:solid 1px #dadada;margin-right:5px;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.column_kjcd{border:solid 1px #dadada;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.column_nbtz{width:365px;float:left;border:solid 1px #dadada;margin-right:5px;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.column_gwlz{width:368px;float:left;border:solid 1px #dadada;margin-right:5px;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.column_dwxw{width:250px;float:right;border:solid 1px #dadada;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.column_hypx{width:740px;float:left;border:solid 1px #dadada;margin-right:5px;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.column_zbgl{width:250px;float:right;border:solid 1px #dadada;margin-bottom:5px;background:url('${ctx}/resources/image/oa/oa_bg_title.png') left top repeat-x;}
		.oa_title{line-height:30px;font-weight:bold;padding-left:30px;height:30px;border-bottom:solid 1px #dadada;background:url('${ctx}/resources/image/oa/oa_icon_title.png') 8px 6px no-repeat;}
		.kjcd{padding-left:50px;}
		.kjcd li{width:71px;float:left;margin-right:45px;text-align:center;}
		.news li{padding:5px 0 5px 15px;background:url('${ctx}/resources/image/website/icon_news.png') 5px 9px no-repeat;}
		.news li span{float:right;margin-right:10px;}
		.news_meeting li{padding:5px 0 5px 15px;width:355px;background:url('${ctx}/resources/image/website/icon_news.png') 5px 9px no-repeat;float:left;}
		.news_meeting li span{float:right;margin-right:10px;}		
		.w250{width:250px;}
		.w365{width:365px;}
		.w368{width:368px;}
		.fl{float:left;}
		.fr{float:right;}
		.cb{clear:both;}
		#wrap a{font-size:14px;color:#ffffff;font-weight:bold;}
		#wrap a:hover{color:#bec0bf;}
	</style>	    
    <script type="text/javascript">
    jQuery.ajaxSetup({cache:false});//ajax不缓存
    $(function (){
    	$(".waiting").bind({
    		click:function(){
    			alert('正在建设中。')
    		}
    	});
    	
    	//$("#indexPage").css({'line-height:':'422px'});
    	//alert($("#indexPage").height())
		//菜单树参数设定
    	var menuTreeSetting = {
      		async : {
      		  	enable : true, 				//设置 zTree是否开启异步加载模式
      		  	url : "menu/getUserMenu", 	//Ajax 获取数据的 URL 地址
      		  	autoParam : [ "id" ]		//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
      		},
      		data:{ //必须使用data
      		  	simpleData : {
      		  		enable : true,
      		  		idKey : "id", 	//id编号命名 默认
      		  		pIdKey : "pId", //父id编号命名 默认
      		  		rootPId : 0 	//用于修正根节点父节点数据，即 pIdKey 指定的属性值
      		  	}
      		},
      		//回调函数
      		callback : {
      		  	//单击树形菜单，打开新页面至tab页中(此处将资源url放在了[title]属性中,放在[url]属性中时会打开两遍url连接)
      		  	onClick : function(event, treeId, treeNode, clickFlag) {
      		  		var zTree = $.fn.zTree.getZTreeObj("menuTree");
    	        	var nodes = zTree.getSelectedNodes();
    	        	if(nodes[0].title != undefined){
    		        	var param = nodes[0].title.split("_");
    		        	param[1] = '${ctx}'+param[1];
    		        	if(param[0] == "i"){
    			        	//将iframe添加到tab页中(适用于页面较复杂的刷新页面)
    		        		var iframe = document.createElement("iframe");
    		        		var strIframe="<iframe src='"+param[1]+"' scrolling='auto' frameborder='0' style='width:100%;height:99%;'></iframe>";
    		        		iframe.src = param[1];  
    		            	iframe.frameBorder = 0;   
    		            	iframe.height = '100%';   
    		            	iframe.width = '100%'; 
    		        		OpenIframe(nodes[0].name, strIframe);
    			    	}else if (param[0] == "a") {
    				    	//将url添加到tab页中(适用于Ajax不刷新页面)
    			    		OpenAjax(nodes[0].name, param[1]);
    					}
    		    	}
      		  	},
      			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
      			onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
      		  		alert("加载错误：" + XMLHttpRequest);  
      			},
      		  	onAsyncSuccess : function(event, treeId, treeNode, msg){
      		  		//alert("加载成功!");
      		  		var zTree = $.fn.zTree.getZTreeObj("menuTree");
      		  		zTree.expandAll(true);//展开全部菜单
      		  	}
      		}
      	};

    	//在右边center区域打开菜单，新增tab(Iframe方式)
	    function OpenIframe(text, iframe) {
	        if ($("#tabs").tabs('exists', text)) {
	            $('#tabs').tabs('select', text);
	            
	        } else {
	            $('#tabs').tabs('add', {
	            	style:{paddingTop:5,paddingLeft:5,paddingBottom:5,paddingRight:5},
	                title : text,
	                closable : true,
	                content : iframe
	            });
	        }
	        //$('#tabs').tabs('getSelected').css({padding:'5px'});
	    };


	    
	    //绑定tabs的右键菜单
	    $("#tabs").tabs({
	        onContextMenu : function (e, title) {
	            e.preventDefault();
	            $('#tabsMenu').menu('show', {
	                left : e.pageX,
	                top : e.pageY
	            }).data("tabTitle", title);
	        }
	    });
	    
	    //实例化menu的onClick事件
	    $("#tabsMenu").menu({
	        onClick : function (item) {
	            CloseTab(this, item.name);
	        }
	    });
	    
	    //几个关闭事件的实现
	    function CloseTab(menu, type) {
	        var curTabTitle = $(menu).data("tabTitle");
	        var tabs = $("#tabs");
	        
	        if (type === "close") {
	            tabs.tabs("close", curTabTitle);
	            return;
	        }
	        
	        var allTabs = tabs.tabs("tabs");
	        var closeTabsTitle = [];
	        
	        $.each(allTabs, function () {
	            var opt = $(this).panel("options");
	            if (opt.closable && opt.title != curTabTitle && type === "Other") {
	                closeTabsTitle.push(opt.title);
	            } else if (opt.closable && type === "All") {
	                closeTabsTitle.push(opt.title);
	            }
	        });
	        
	        for (var i = 0; i < closeTabsTitle.length; i++) {
	            tabs.tabs("close", closeTabsTitle[i]);
	        }
	    };

		$(document).ready(function(){
			//加载菜单树
			$.fn.zTree.init($("#menuTree"), menuTreeSetting);
		});
    });
	function iframeForward(text, url){
		var strIframe="<iframe src='"+url+"' scrolling='auto' frameborder='0' style='width:100%;height:99%;'></iframe>";
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
            
        } else {
            $('#tabs').tabs('add', {
            	style:{paddingTop:5,paddingLeft:5,paddingBottom:5,paddingRight:5},
                title : text,
                closable : true,
                content : strIframe
            });
        }    	
	}
	function messageIframeForward(text, url ,id){
		$.post('${ctx}/message/updateMessageIsRead',{'id':id,'isRead':'1'},function(data){
			//alert(parseInt(data.msg))
			$('#messageTable').datagrid('reload');
			if(parseInt(data.msg)==1){
				var strIframe="<iframe src='"+url+"' scrolling='auto' frameborder='0' style='width:100%;height:99%;'></iframe>";
		        if ($("#tabs").tabs('exists', text)) {
		            $('#tabs').tabs('select', text);
		            
		        } else {
		            $('#tabs').tabs('add', {
		            	style:{paddingTop:5,paddingLeft:5,paddingBottom:5,paddingRight:5},
		                title : text,
		                closable : true,
		                content : strIframe
		            });
		        } 				
			}
		},'json');
	}	
	//在右边center区域打开菜单，新增tab(Ajax方式)
    function OpenAjax(text, url) {
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
            $('#tabs').tabs('add', {
                title : text,
                closable : true,
                href : url
            });
        }
    };
  
	</script>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:100px;background:  url('${ctx}/resources/image/main/bg_top.png')">
    	<table style="width:100%;margin:0;padding:0;" cellspacing="0" cellpadding="0" >
    		<tr>
     			<td width="70%">
     				<div style="height:99px;background:url('${ctx}/resources/image/main/logo.png') left top no-repeat;"></div>
     			</td>
				<td>
					<div style="width:475px;height:100px;background:url('${ctx}/resources/image/main/bg_right.png') center center no-repeat;">
						<div style="width:150px;float:left;height:100%;line-height:100px;text-align:center;font-weight:bold;"><a href="${ctx}/website/index" target="_blank" >网站首页</a></div>
						<div style="width:170px;float:left;height:100%;line-height:100px;text-align:center;font-weight:bold;">您好：<%=userAcc%> [<%=userName%>]</div>
						<div style="width:145px;float:left;height:100%;line-height:100px;text-align:center;font-weight:bold;">
		     				<a href="${ctx}/logout" style="text-decoration: none;">
		     					退出系统
		     				</a>						
						</div>
					</div>
				</td>
     		</tr>
    	</table>
    </div>
    <div data-options="region:'west',split:true" title="导航菜单" style="width:230px;">
    	<div>
			<ul id="menuTree" class="ztree"></ul>
		</div>
    </div>
    <div data-options="region:'center'">
    	<div class="easyui-tabs" fit="true" border="false" id="tabs">
      		<div title="首页" style="padding:5px;">
				<div id="wrap" style="width:720px;height:410px;background:#dadada;margin:0 auto;margin-top:50px;">
					<div style="width:150px;height:410px;background:#2777ec url('${ctx}/resources/image/main/tmp1.png') center center no-repeat;float:left;position:relative;">
						<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:20px;color:#ffffff;font-weight:bold;">快速导航</div>
					</div>
					<div style="width:560px;height:410px;float:right;">
						<div style="width:180px;height:130px;background:#008d00 url('${ctx}/resources/image/main/tmp2.png') center center no-repeat;float:left;margin-right:10px;position:relative;">
							<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:14px;color:#ffffff;font-weight:bold;">
								<a href="#" onclick="iframeForward('收件箱','${ctx}/mail/received')"><span>收件箱</span></a>
							</div>
						</div>
						<div style="width:180px;height:130px;background:#5334ad url('${ctx}/resources/image/main/tmp3.png') center center no-repeat;float:left;margin-right:10px;position:relative;">
							<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:14px;color:#ffffff;font-weight:bold;">
								<a href="#" onclick="iframeForward('日程管理','${ctx}//schedule/schedule')"><span>日程管理</span></a>
							</div>
						</div>
						<div style="width:180px;height:130px;background:#da532c url('${ctx}/resources/image/main/tmp4.png') center center no-repeat;float:right;position:relative;">
							<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:14px;color:#ffffff;font-weight:bold;">
								<a href="#" onclick="iframeForward('通讯录管理','${ctx}/address/address')"><span>通讯录</span></a>
							</div>
						</div>
						<div class="cb" style="height:10px;"></div>
						<div style="width:560px;height:130px;background:#b21a41 url('${ctx}/resources/image/main/tmp5.png') center center no-repeat;float:left;position:relative;">
							<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:14px;color:#ffffff;font-weight:bold;">
								<a href="#" onclick="iframeForward('巡防队伍','${ctx}/patrol/team')"><span>巡防队伍</span></a>
							</div>
						</div>
						<div class="cb" style="height:10px;"></div>
						<div style="width:275px;height:130px;background:#0097a9 url('${ctx}/resources/image/main/tmp6.png') center center no-repeat;float:left;margin-right:10px;position:relative;">
							<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:14px;color:#ffffff;font-weight:bold;">
								<a href="#" onclick="iframeForward('值班管理','${ctx}/duty/duty')"><span>值班管理</span></a>
							</div>
						</div>
						<div style="width:275px;height:130px;background:#9d7459 url('${ctx}/resources/image/main/tmp7.png') center center no-repeat;float:left;position:relative;">
							<div style="width:100%;text-align:center;position:absolute;bottom:0;left:0px;font-size:14px;color:#ffffff;font-weight:bold;">
								<a href="#" onclick="iframeForward('会议、培训管理','${ctx}/meeting/meetingquery')"><span>会议/培训</span></a>
							</div>
						</div>
					</div>
					<div class="cb"></div>
				</div>															
      		</div>
    	</div>
    </div>
    <div id="tabsMenu" class="easyui-menu" style="width:120px;">  
    	<div name="close">关闭</div>  
    	<div name="Other">关闭其他</div>  
    	<div name="All">关闭所有</div>
  	</div>    
  	<div id="myPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" 
  		collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;">
  	</div>
  	
	<div id="myPop2Window" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" 
	  	collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;">
	</div>    
</body>
</html>