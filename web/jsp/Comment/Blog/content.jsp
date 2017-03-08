<%@ page import="com.simple.BookManage.RequestBeans.Comment" %>
<%@ page import="java.util.ArrayList" %>
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
    <div class="row">
        <div class="col-lg-12" style="padding:0px;">
            <div class="col-lg-9">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="text-center article-title" style="margin:20px 0px 20px">
                            <span class="text-muted">
                                <h1>
                                    ${result[0].comment_title}</h1>
                            </span></div>
                        <div style="margin-bottom:50px;">
                            <img src="${result[0].cover_path}" alt="" >
                        </div>
                        <c:set var="rs" value="${result[0]}"></c:set>
                        <div id="beijing">
                            <%
                                Comment comment=(Comment)pageContext.getAttribute("rs");
                                String str=comment.getComment();
                                String [] lines=str.split("\r\n");
                                System.out.println(lines[0]);
                                pageContext.setAttribute("lines",lines);
                            %>
                        <c:forEach var="line" items="${lines}">
                        <p><script>document.write(markdown.toHTML("${line}"))</script></p><br/>
                        </c:forEach>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            <label id="title" class="col-sm-3 control-label no-padding-right">请老师评价</label>
                            <div class="form-group">
                                <div class="col-sm-9">
                                    <!-- #section:plugins/misc.raty -->
                                    <div class="rating inline" id="star" style="float:left"></div>
                                    <div id="after_score">

                                    </div>
                                    <!-- /section:plugins/misc.raty -->
                                    &nbsp;&nbsp;
                                    <button class="btn btn-xs btn-yellow" onclick="submitScore()">提交</button>
                                </div>
                            </div>
<script>
    function submitScore(){
        $.ajax({
            type:"post",
            url:"",
            data:{
                score:$("input [name=score]").val(),
            },
            success:function(data){
                $(this).remove()
                $("#star").remove()
                $("#after_score").append('<i data-alt="1" class="star-on-png"></i>')
                $("#after_score").append('<i data-alt="2" class="star-on-png"></i>')
                $("#after_score").append('<i data-alt="3" class="star-on-png"></i>')
            }
        })

    }
</script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>