<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../bsp/layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>                                       
<!doctype html>

<html>
<head>
	<title>信息发布系统</title>
    <meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css" />
	<meta name="renderer" content="ie-stand" />
</head>

<body>

 	<style type="text/css" >
 		.table2{width:100%;}
    	.table2 td{border:solid 1px #000000; text-align:center; font-size:12px;padding:5px 0 5px 0;}
    </style>

	<script type="text/javascript">
	var editor = CKEDITOR.replace('newsContent2',{
			filebrowserUploadUrl : '${ctx}/news/upload?Type=file',  
			filebrowserImageUploadUrl : '${ctx}/news/upload?Type=image',  
			toolbar : 'Full',
			height: '500px',
			filebrowserFlashUploadUrl : '${ctx}/news/upload?Type=flash'
				}
			);
	// CKEDITOR.instances.newsContent2.setData($("#newsContent").val());
</script>
<script type="text/javascript">

$(function() {
	var count=0;
	var newsimagefinish = true;
	$("#newsImage").uploadify(
			{
			    uploader:'news/uploadNewsImage2',
				swf : '${ctx}/resources/js/uploadify.swf',
				script : '',
				buttonText : '<div>选择文件</div>',
				buttonImage : '${ctx}/resources/css/browseBtn.png',//替换上传钮扣
				width : 75,//buttonImg的大小
				height : 20,//
				folder : 'UploadFile', //文件上传路径
				queueID : 'newsImageQueue', //div层的id
				progressData : 'percentage',
				removeTimeout : 100,
				auto : true,
				multi : true, //是否支持多文件上传
				queueSizeLimit : 1,//最多上传文件个数
				fileSizeLimit  : '0', //文件的最大值
				fileTypeExts : '*.jpg;*.png;*.jpeg;*.bmp',//文件的格式
				fileTypeDesc : '请选择图片格式的文件', //设置文件上传格式显示文字
				onSelect:function(file){
					newsimagefinish = false;
					$("#btn-add").linkbutton({disabled:true});
		        },
				onUploadSuccess : function(file, data, response) {
					//在每一个文件上传成功或失败之后触发，返回上传的文件对象或返回一个错误，如果你想知道上传是否成功，最后使用onUploadSuccess或onUploadError事件
					$("#newsImageUrl").val(data);
					newsimagefinish = true;
					if(newsimagefinish){
						$("#btn-add").linkbutton({disabled:false});
					}
				},
				onClearQueue : function(queueItemCount) {
					alert(queueItemCount
							+ ' 文件被移除队列');
				},
				onUploadProgress : function(file, bytesUploaded,
						bytesTotal, totalBytesUploaded, totalBytesTotal) {
					$('#pregress').html(
							'总共需要上传' + bytesTotal + '字节，' + '已上传'
									+ totalBytesTotal + '字节')
				},
			onCancel : function(file) {
					if(count.data>0){
						alert(file.name+"  文件名不能重复，改文件将被移除队列");
						count=0;
					}else{
						 alert(file.name + ' 被移除队列'); 
						}
				},
				onFallback : function() {
					   alert('Flash 没有找到，请安装新的Flash插件');
				}
			});
	$("#newsImage").css('display','none');
	
	var count1=0;
	var newsAccessoryfinish = true;
	$("#newsAccessory").uploadify(
			{
			    uploader:'news/uploadNewsAccessory',
				swf : '${ctx}/resources/js/uploadify.swf',
				script : '',
				buttonText : '<div>选择文件</div>',
				buttonImage : '${ctx}/resources/css/browseBtn.png',//替换上传钮扣
				width : 75,//buttonImg的大小
				height : 20,//
				folder : 'UploadFile', //文件上传路径
				queueID : 'newsAccessoryQueue', //div层的id
				progressData : 'percentage',
				removeTimeout : 100,
				auto : true,
				multi : true, //是否支持多文件上传
				queueSizeLimit : 1,//最多上传文件个数
				fileSizeLimit  : '0', //文件的最大值
				fileTypeExts : '*.*',//文件的格式
				fileTypeDesc : '请选择文件', //设置文件上传格式显示文字
				onSelect:function(file){
					newsAccessoryfinish = false;
					$("#btn-add").linkbutton({disabled:true});
		        },
				onUploadSuccess : function(file, data, response) {
					//在每一个文件上传成功或失败之后触发，返回上传的文件对象或返回一个错误，如果你想知道上传是否成功，最后使用onUploadSuccess或onUploadError事件
					$("#newsAccessoryUrl").val(data);
					newsAccessoryfinish = true;
					if(newsAccessoryfinish){
						$("#btn-add").linkbutton({disabled:false});
					}
				},
				onClearQueue : function(queueItemCount) {
					alert(queueItemCount
							+ ' 文件被移除队列');
				},
				onUploadProgress : function(file, bytesUploaded,
						bytesTotal, totalBytesUploaded, totalBytesTotal) {
					$('#pregress').html(
							'总共需要上传' + bytesTotal + '字节，' + '已上传'
									+ totalBytesTotal + '字节')
				},
			onCancel : function(file) {
					if(count1.data>0){
						alert(file.name+"  文件名不能重复，改文件将被移除队列");
						count1=0;
					}else{
						 alert(file.name + ' 被移除队列'); 
						}
				},
				onFallback : function() {
					   alert('Flash 没有找到，请安装新的Flash插件');
				}
			});
	$("#newsAccessory").css('display','none');
});
		$(function(){
			
			var rows = $('#newsCheckTable').datagrid('getSelections');
			 if(rows[0].newsIsLinkTitle==1){
					$("#newsLinkUrl").css('display','block');
					$("#newsLinkUrl").val(rows[0].newsLinkUrl);
				 }
			 if(rows[0].newsIsImageNews==1){
					$("#newsImage").css('display','block');
					$("#newsImageQueue").css('display','block');
				 }
			
		});

		function newsCheckLik(){
			var i=$('input[name="newsIsLinkTitle"]:checked').val();
			if(i==1){
				$("#newsLinkUrl").css('display','block');
				}else{
					$("#newsLinkUrl").css('display','none');
				}
			}
		function chnewsCheckImage(){
		
			var i=$('input[name="newsIsImageNews"]:checked').val();
			if(i==1){
				$("#newsImage").css('display','block');
				$("#newsImageQueue").css('display','block');
				}else{
					$("#newsImage").css('display','none');
					$("#newsImageQueue").css('display','none');
				}
			}

		function chnewsCheckAccessory(){
			
			var i=$('input[name="newsIsAccessory"]:checked').val();
			if(i==1){
				$("#newsAccessory").css('display','block');
				$("#newsAccessoryQueue").css('display','block');
				}else{
					$("#newsAccessory").css('display','none');
					$("#newsAccessoryQueue").css('display','none');
				}
			}
			
		//添加新闻
		function updateCheckNews(){
			var val = CKEDITOR.instances.newsContent2.getData();  
	//		if(val.length==0||val==""){
	//			alert("新闻内容不能为空");
	//			return  ;
	//		}
			var newsTitle=$("#upNewsTitle").val();
			var newsSubTitle=$("#upNewsSubTitle").val();
			if(newsTitle.length==0||newsTitle==""){
				alert("标题不能为空");
				return ;
			}
			if(newsSubTitle.length>256){
				alert("副标题过长");
				return;
			}
			
			<%--
			var  newsAuthor=$("#newsAuthor").val();
			if(newsAuthor.length==0||newsAuthor==""){
				alert("作者不能为空");
				return;
				}
			if(newsAuthor.length>32){
				alert("作者长度过长");
				return ;
				}
			--%>
			
			var newsIsImageNews=$('input[name="newsIsImageNews"]:checked').val(); 
		
			if(newsIsImageNews==1){
				var newsImageUrl=$("#newsImageUrl").val();
				if(newsImageUrl==""||newsImageUrl.length==0){
					alert("当选择图片标题时，图片不能为空");
					return ;
					}
			}
			
			var newsIsAccessory=$('input[name="newsIsAccessory"]:checked').val(); 
			
			if(newsIsAccessory==1){
				var newsAccessoryUrl=$("#newsAccessoryUrl").val();
				if(newsAccessoryUrl==""||newsAccessoryUrl.length==0){
					alert("当选择附件标题时，附件不能为空");
					return ;
					}
			}
			
			var newsIsLinkTitle=$('input[name="newsIsLinkTitle"]:checked').val();
			
			if(newsIsLinkTitle==1){
				var newsLikUrl=$("#newsLinkUrl").val();
				if(newsLikUrl==""||newsLikUrl.length==0){
					alert("当选择链接标题时，链接URL不能为空");
					return;
				}
			}
			$("#newsContent").val(base64encode(utf16to8((val))));
			var r = $('#newsAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("news/updateNews",$("#newsCheckUpdateForm").serializeArray(),function(data){
				$('#myPopWindow').window('close');
				$('#newsCheckTable').datagrid('reload');
				$.messager.alert('提示',data.mes,'info');
			});
		
		}
</script>
<form id="newsCheckUpdateForm"  method="post" style="">
<input type="hidden" id="newsId" name="newsId" value="${news.newsId}" />
<input  type="hidden" id="newsIsChecked" name="newsIsChecked" value="${news.newsIsChecked}"     >
	<table class="table2" width="95%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="25%" >说明：</td>
			<td width="75%" colspan="3">
				作者和发布日期可以不填写：系统将为您自动填充相应的账户姓名和所属机构，时间为当前发布日期；
				文字粘贴请选择无格式粘贴按钮，字体请统一选择宋体和14号字体；
			</td>
		</tr>
		<tr>
			<td width="25%" >标题：</td>
			<td width="75%" colspan="3">
				<input type="text" id="upNewsTitle" value="${news.newsTitle }"  name="newsTitle" style="width: 90%;height: 25px" />
			</td>
		</tr>
		<tr >
			<td width="25%"  >副标题：</td>
			<td width="75%" colspan="3">
				<input type="text" id="upNewsSubTitle"  value="${news.newsSubTitle }" name="newsSubTitle" style="width: 90%;height: 25px" />
			</td>
		</tr>
		<tr style="display: none;">
			<td width="100%" colspan="4">
				置顶：<input type="radio" name="newsIsTop"  value="0" checked="checked" />否
				<input type="radio" name="newsIsTop" value="1" />是
			</td>
		</tr>
		<tr>
			<td width="25%" align="left">信息类型：
			<select id="upNewsType" name="newsType" style="width: 50%;height: 30px">
				<c:forEach items="${newsTypeList}" var="nType">
					<c:choose>
						<c:when test="${nType.newsTypeCode==news.newsType}">
							<option value="${nType.newsTypeCode}" selected="selected" ><c:out value="${nType.newsTypeName}" /></option>
						</c:when>
						<c:otherwise><option value="${nType.newsTypeCode}" ><c:out value="${nType.newsTypeName}" /></option></c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			</td>
			<td width="25%" align="left">
			作者：<input id="newsAuthor" name="newsAuthor" value="${news.newsAuthor }" />
			</td>
			<td width="25%" align="left">
			发布日期：		<input id="newsPublishTime" name="newsPublishTime" class="Wdate" type="text"
						style="width: 49%;height: 25px" data-options="required:true"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="${news.newsPublishTime }" >
			</td>
			<td  style="display: none;" width="25%" align="left" >
				单位：<input id="newsCompany" name="newsCompany" />
			</td>
		</tr>
		<tr>
			<td width="25%" align="right">链接标题：</td>
				<c:if test="${news.newsIsLinkTitle==0 }">
				<td  align="left" >
					<input type="radio" name="newsIsLinkTitle"  value="0"	checked="checked" onchange="newsCheckLik()" />否
					<input type="radio" name="newsIsLinkTitle"  value="1"  onchange="newsCheckLik()" />是
				</td>
				<td colspan="2">
					<input type="text" align="right" style="display: none;width: 95%;height: 18px" id="newsLinkUrl" name="newsLinkUrl"/> 
				</td>
				</c:if>
				<c:if test="${news.newsIsLinkTitle==1 }">
				<td  align="left" >
					<input type="radio" name="newsIsLinkTitle"  value="0"	 onchange="newsCheckLik()" />否
					<input type="radio" name="newsIsLinkTitle"  value="1" checked="checked" onchange="newsCheckLik()" />是	
				</td>
				<td colspan="2">
					<input  style="width:90%" type="text"  id="newsLinkUrl" name="newsLinkUrl"/> 
				</td>
				</c:if>   
		</tr>
		<tr>
			<td width="25%" align="right">图片信息</td>
			<td width="25%" align="left">

				<input type="radio" name="newsIsImageNews"  value="0" checked="checked" onchange="chnewsCheckImage()" />否
				<input type="radio" name="newsIsImageNews" value="1"   onchange="chnewsCheckImage()"/>是
			</td>
			<td width="25%" align="left"  >
				<div id="newsImageQueue"  style="width: 300px; height: 30px;overflow: auto;display: none;"></div>
			</td>
			<td width="25%">
			<input type="file" style="display: none"  name="newsImage" id="newsImage">
			<input type="hidden" name="newsImageUrl" id="newsImageUrl">
			</td>
		</tr>
		<tr>
			<td width="25%" align="right">附件信息</td>
			<td width="25%" align="left">
				<input type="radio" name="newsIsAccessory"  value="0" checked="checked" onchange="chnewsCheckAccessory()" />否
				<input type="radio" name="newsIsAccessory" value="1"   onchange="chnewsCheckAccessory()"/>是
			</td>
			<td width="25%" align="left"  >
				<div id="newsAccessoryQueue"  style="width: 300px; height: 30px;overflow: auto;display: none;"></div>
			</td>
			<td width="25%">
			<input type="file" style="display: none"  name="newsAccessory" id="newsAccessory">
			<input type="hidden" name="newsAccessoryUrl" id="newsAccessoryUrl">
			</td>
		</tr>
		<tr>
			<td  align="center" colspan="4">
				<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="btn-add" onclick="updateCheckNews();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
			<input type="hidden" id="newsContent"   name="newsContent"  />
			<textarea  id="newsContent2"   name="newsContent2"><c:out value='${news.newsContent}' /></textarea>
			</td>
		</tr>
		<tr>
			<td  align="center" colspan="4">
				<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
				<a href="#" id="btn-add" onclick="updateCheckNews();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			</td>
		</tr>
	</table>
	
</form>
</body>

</html>