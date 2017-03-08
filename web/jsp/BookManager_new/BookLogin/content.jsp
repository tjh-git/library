<%@ page import="com.simple.bsp.properties.web.controller.PropertiesSetController" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.simple.bsp.properties.web.controller.GetProper" %><%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="widget-box col-xs-6">
    <div class="widget-header widget-header-small">
        <h5 class="widget-title lighter">输入图书编目</h5>
    </div>

    <div class="widget-body">
        <div class="widget-main">
            <form class="form-search">
                <div class="row">
                    <div class="col-xs-12 col-sm-8">
                            <input type="text" class=" search-query" id="bookcode_search" placeholder="输入图书编码" />
                                <button type="button"  onclick="search_book_code()" style="display: inline-block;margin-left: 10px;" class="btn btn-purple btn-sm">
                                    <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                    搜索
                                </button>
                            </span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="col-xs-12">
                <table id="simple-table" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>

                        <th>图书编码</th>
                        <th>图书名称</th>
                        <th>图书价格</th>
                        <th>
                            图书分册
                        </th>
                        <th class="hidden-480">选中</th>

                    </tr>
                    </thead>

                    <tbody id="search-result">

                    </tbody>
                </table>
            </div><!-- /.span -->
        </div><!-- /.row -->

        <div class="hr hr-18 dotted hr-double"></div>


        <div class="hr hr-18 dotted hr-double"></div>


        <!-- PAGE CONTENT ENDS -->

<script>
    function search_book_code(){
        $.ajax({
            type:"post",
            url:"${ctx}/book/getBookList",
            data:{
                bookcode:$("#bookcode_search").val()==""?0:$("#bookcode_search").val(),
            },
            success:function(data){
                $("#search-result").find("tr").remove()
                var array=data.rows
                for(var i=0;i<array.length;i++){
                    var obj=array[i]
                    var vol
                    switch (obj.have_vol){
                        case 1:
                           vol="无"
                            break
                        case 2:
                            vol="上册"
                            break
                        case 3:
                            vol="中册"
                            break
                        case 4:
                            vol="下册"
                            break
                        case 5:
                            vol="其他"
                            break
                    }
                    $("#search-result").append(
                            "<tr>" +
                            "<td>"+obj.bookcode+"</td>" +
                            "<td>"+obj.bookname+"</td>" +
                            "<td>"+obj.bookprice+"</td>"+
                            "<td>"+vol+"</td>"+
                            "<td><button class='btn btn-xs btn-success' onclick='get_selected_cata("+obj.catalog_id+")'> <i class='ace-icon fa fa-check bigger-120'></i></button></td>"+
                            "</tr>"
                    )


                }

            }
        })
    }
    function get_selected_cata(catalog_id){
        $.ajax({
            type:"post",
            url:"${ctx}/book/getBookList",
            data:{
                catalog_id:catalog_id
            },
            success:function(data){
                var obj=data.rows[0]
                $("#catalog_id").val(obj.catalog_id)
                $("#bookname").val(obj.bookname)
                $("#bookprice").val(obj.bookprice)
                $("#id-date-picker-1").val(obj.publishdate)
                $("#book-login-form").show()
            }
        })
    }
</script>
<form class="form-horizontal" id="book-login-form" role="form" style="display: none;float:left">
    <!-- #section:elements.form -->

    <input type="hidden" name="catalog_id" id="catalog_id" >
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="bookname">图书名称 </label>
        <div class="col-sm-9">
                                        <span class="input-icon input-icon-right">
                                            <input type="text" id="bookname" name="bookname" />
                                        </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">图书定价</label>
                                        <span class="input-icon input-icon-right">
                                            ￥<input type="text" id="bookprice" name="bookprice"/>
                                        </span>

            <!-- /section:elements.form.input-icon -->
        </div>
    </div>

    <!-- /section:elements.form -->
    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right"> 所在书库 </label>

        <div class="col-sm-9">
            <span id="book_position">
                <select  class=" col-sm-2 " name="book_position"  id="book_position_select">
                    <%
                        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
                        GetProper bean = (GetProper) context .getBean("getpro");
                        List<String> book_code = bean.getProperties("book_code",session);//得到List对象（）
                        for(int i=0;i<book_code.size();i++)
                        {
                            out.println("<option value=\""+(i+1)+"\">"+book_code.get(i)+"</option>");
                        }
                    %>
                </select>

            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">库位设置-开始编号</label>
            <input type="text" name="start_pos" />

            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">图书来源</label>
            <span class="" id="book_from">
                <select  class="" name="book_from" id="book_from_select">
                   <%
                       List<String> book_from = bean.getProperties("book_from",session);//得到List对象（）
                       for(String s:book_from)
                       {
                           out.println("<option value=\""+s+"\">"+s+"</option>");
                       }
                   %>
                </select>
            </span>
        </div>
    </div>
    <div class="space-4"></div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right"> 入库数量 </label>
        <%--col-sm-3导致float:left--%>

        <div class="col-sm-9">
            <input type="text" class="spinner1" id="buy_num" name="buy_num" />
            &nbsp;&nbsp;&nbsp;
            <label class="control-label no-padding-right">进货单价</label>
            ￥<input type="text" name="book_buy_price" id="book_buy_price" value="0" class="spinner1" />
        </div>
    </div>

    <div class="space-4"></div>


    <div class="form-group">

        <label class="control-label  col-sm-3  ">出版日期</label>
        <div class="col-sm-9">
            <div class="col-sm-3 input-group" >
                <span class="input-group-addon">
                    <i class="fa fa-calendar bigger-110"></i>
                </span>
                <input class="date-picker" id="id-date-picker-1" name="publishdate" type="text" data-date-format="yyyy-mm-dd">
            </div>

        </div>
    </div>
    <div class="space-4"></div>
    <div class="form-group">
        <label class="control-label  col-sm-3  "></label>
        <div class="col-sm-9">
            <label>
                <input  name="teacher_touch" checked="checked" type="checkbox" value="1" class="ace" />
                <span class="lbl">教师可借</span>
            </label>
            <label>
                <input  name="student_touch" type="checkbox" checked="checked" value="1" class="ace" />
                <span class="lbl">学生可借</span>
            </label>
        </div>

    </div>
    <div class="space-4"></div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" onclick="submit_Form()" type="button">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交库存添加信息
            </button>

            &nbsp; &nbsp; &nbsp;
            <button class="btn" type="reset" onclick="reset()">
                <i class="ace-icon fa fa-undo bigger-110"></i>
                重新输入
            </button>
        </div>
    </div>
</form>
<script>
    function submit_Form(){
        $.ajax({
            type:"post",
            url:"${ctx}/booklogin/addBookLoginInfo",
            data:$("#book-login-form").serialize(),
            success:function(data,status){
                if(data.mes=="订单信息录入成功"){
                    alert("添加成功")
                    window.location=window.location.href//refresh
                }
                else{
                    alert("添加失败")
                }

            },
            error:function(){
                alert("添加失败")
            }
        })
    }
</script>

