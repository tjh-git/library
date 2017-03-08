<%@ page import="com.simple.BookManage.RequestBeans.Comment" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/resources/js/markdown/markdown.js"></script>
<div class="wrapper wrapper-content animated fadeInRight">
    <!-- 幻灯片开始 -->
    <!-- 幻灯片结束 -->
    <div class="row">
        <div class="col-lg-12">
            <div class="progress progress-striped active">
                <div class="progress progress-striped active">
                    <div style="width: 33%" class="progress-bar progress-bar-success">
                        <span class="sr-only">33% Complete (success)</span>
                    </div>
                    <div style="width: 33%" class="progress-bar progress-bar-warning">
                        <span class="sr-only">33% Complete (warning)</span>
                    </div>
                    <div style="width: 34%" class="progress-bar progress-bar-danger">
                        <span class="sr-only">34% Complete (danger)</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 首页文章开始 -->
    <div class="row">
        <style>
            .button_link{display:inline-block;position:relative;text-decoration:none;font-size:15px;color:#33ab6a;font-weight:bold;width:100%;height:100%;border:2px solid rgba(225,255,255,.8);-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;-webkit-transition:0.4s;-o-transition:0.4s;transition:0.4s;}
            .button_link:hover{border:2px solid rgba(255,255,255,1);}
            .button_link .line{display:inline-block;background-color:#23C6C8 ;position:absolute;-webkit-transition:0.5s ease;-o-transition:0.5s ease;transition:0.5s ease;}
            .button_link .line_top{height:2px;width:0;left:-50%;top:-2px;}
            .button_link:hover .line_top{width:100%;left:-2px;}
            .button_link .line_right{height:0;width:2px;top:-50%;right:-2px;}
            .button_link:hover .line_right{height:100%;top:-2px;}
            .button_link .line_bottom{width:2px;height:0;bottom:-50%;left:-2px;}
            .button_link:hover .line_bottom{height:100%;bottom:-2px;}
            .button_link .line_left{height:2px;width:0;right:-50%;bottom:-2px;}
            .button_link:hover .line_left{width:100%;right:-2px;}
        </style>
        <div class="col-lg-12">
            <div class="ibox" id="ibox">
                <c:forEach var="row" items="${result.rows}">
                <div class="ibox-content">

                    <div class="row">
                        <div class="col-lg-2">
                            <a href="${ctx}/comment/onecomment?id=${row.comment_id}" class="button_link">
                                <img alt="image" class="img-responsive" src="${row.cover_path}" style="max-height:150px;min-height:120px;">
                                <span class="line line_top"></span>
                                <span class="line line_right"></span>
                                <span class="line line_bottom"></span>
                                <span class="line line_left"></span>
                            </a>
                        </div>
                        <div class="col-lg-10">
                            <a href="http://www.lcm.wang/index.php/Home/Article/index/id/96.html" class="btn-link">
                                <h2>
                                    ${row.comment_title}                                    </h2>
                            </a>
                            <p>
                                <%
                                    Comment row=(Comment)pageContext.getAttribute("row");
                                    row.setComment(row.getComment().replace("\r\n"," ").replace("\r"," ").replace("\n"," "));
                                    pageContext.setAttribute("row",row);
                                %>
                                <script>document.write(markdown.toHTML("${row.comment}".substring(0,140)))</script>...</p>
                            <div class="row">
                                <div class="col-md-10">
                                    <span class="label label-primary" style="font-size:12px;">作者:${row.user_name}</span>
                                    <span class="label label-warning" style="font-size:12px;">时间:${row.write_date}</span>

                                </div>
                                <div class="col-md-2" style="margin-top:10px;">
                                    <div class="small text-right">
                                        <a href="${ctx}/comment/onecomment?id=${row.comment_id}"><button type="button" class="btn btn-w-m btn-danger">查看全文</button></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                                <div class="dataTables_paginate paging_simple_numbers">
                                    <c:set var="page_num" value="${result.pagenum}"></c:set>
                                    <ul class="pagination">
                                        <%
                                            int pagenum=(Integer)pageContext.getAttribute("page_num");
                                            for(int i=1;i<=pagenum;i++){

                                        %>
                                        <li class="paginate_button active">
                                            <a href="/comment/commentlist?page=<%=i%>"><%=i%></a>
                                        </li>
                                        <%
                                            }
                                        %>

                                    </ul>
                                </div>
            </div>
        </div>
        <!-- 首页文章结束 -->
    </div>
    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
            <h4 class="header green clearfix">
                读后感发布
            </h4>
            <div class="row">
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right" >
                        <h4 class="header green clearfix">
                            标题
                        </h4>
                    </label>
                    <br/>
                    <div class="col-sm-9">
                        <input type="text" id="title_input" placeholder="读后感标题" class="col-xs-10 col-sm-5" />
                    </div>
                </div>
            </div>

            <div class="col-sm-7">

                <div class="widget-box widget-color-blue">

                    <div class="widget-body">
                        <div class="widget-main no-padding">
													<textarea name="content" id="editor0" data-provide="markdown" data-iconlibrary="fa" rows="10"></textarea>
                        </div>

                        <div class="widget-toolbox padding-4 clearfix">
                            <div class="btn-group pull-left">
                                <button class="btn btn-sm btn-info">
                                    <i class="ace-icon fa fa-times bigger-125"></i>
                                    Cancel
                                </button>
                            </div>

                            <div class="btn-group pull-right">
                                <button class="btn btn-sm btn-purple">
                                    <i class="ace-icon fa fa-floppy-o bigger-125"></i>
                                    Save
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="hr hr-double dotted"></div>
            <script type="text/javascript">
                var $path_assets = "../assets";//this will be used in loading jQuery UI if needed!
            </script>

            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
        <button onclick="file_chose()" style="margin-left: 30px" class="btn btn-app btn-purple btn-sm">
            <i class="ace-icon fa fa-cloud-upload bigger-200"></i>
            上传封面
        </button>
        <button onclick="submitComment()" class="btn btn-app btn-pink btn-sm">
            <i class="ace-icon fa fa-share bigger-200"></i>
            发布
        </button>
        <form id="comment-form" method="post" enctype="multipart/form-data" action="/comment/add">
            <input type="hidden" id="title" name="comment_title">
            <input type="file"  name="image" style="display: none;" id="file_input">
            <textarea name="comment"  style="display: none" id="comment" ></textarea>
        </form>


        <script>
            function file_chose(){
                $("#file_input").click()
            }
            function submitComment(){
                $("#comment").val($("#editor0").val())
                $("#title").val($("#title_input").val())
                $("#comment-form").submit()
            }
        </script>
    </div><!-- /.row --><!-- /.col -->
    </div><!-- /.row -->
    <!-- 正文结束 -->
</div>
