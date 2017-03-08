<%@ page import="com.simple.bsp.properties.web.controller.PropertiesSetController" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.simple.bsp.properties.web.controller.GetProper" %>
<%@ page import="com.simple.bsp.properties.web.controller.GetPub" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" id="catalog_id" name="catalog_id" value="${catalog.catalog_id}">
<form class="form-horizontal" id="submitForm" role="form">
    <!-- #section:elements.form -->
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="bookname"> 图书名称</label>

        <div class="col-sm-9">
            <input type="text" onchange="get_name_pinyin_fun()" id="bookname" name="bookname" value="${catalog.bookname}" class="col-xs-10 col-sm-5"/>

        </div>
    </div>



        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="bookcode"> 图书条码 </label>
            <div class="col-sm-9">
											<span class="input-icon">
                                                <input type="text" id="bookcode" name="bookcode" value="${catalog.bookcode}" />

											</span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label">图书缩写</label>
											<span class="input-icon input-icon-right">
												<input type="text" id="bookab" name="bookab" value="${catalog.bookab}"/>
											</span>

                <!-- /section:elements.form.input-icon -->
            </div>
        </div>

    <!-- /section:elements.form -->
    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="bookname">其他题名 </label>
        <div class="col-sm-9">
											<span class="input-icon">
												<input type="text" id="other_putup" name="other_putup" value="${catalog.other_putup}" />
											</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">并列题名</label>
											<span class="input-icon input-icon-right">
												<input type="text" id="together_putup" name="together_putup" value="${catalog.together_putup}"/>
											</span>

            <!-- /section:elements.form.input-icon -->
        </div>
    </div>

    <!-- /section:elements.form -->
    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right"> 是否分卷 </label>

        <div class="col-sm-9">
            <span id="have_vol">
                <select  class=" col-sm-2 " name="have_vol"  onchange="select_vol()" id="have_vol_select">
                    <option value="1" <c:if test="${catalog.have_vol==1}"><c:out value="selected" /></c:if>>无</option>
                    <option value="2" <c:if test="${catalog.have_vol==2}"><c:out value="selected" /></c:if>>上册</option>
                    <option value="3" <c:if test="${catalog.have_vol==3}"><c:out value="selected" /></c:if>>中册</option>
                    <option value="4" <c:if test="${catalog.have_vol==4}"><c:out value="selected" /></c:if>>下册</option>
                    <option value="5" <c:if test="${catalog.have_vol==5}"><c:out value="selected" /></c:if>>其他</option>
                </select>

            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">丛书名称</label>
            <span class="">
                <input type="text" onchange="get_ser_pinyin_fun()" id="seriesname" name="seriesname" value="${catalog.seriesname}"/>
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">丛书简写</label>
            <span class="">
                <input type="text" id="seriesab" name="seriesab"  value="${catalog.seriesab}"/>
            </span>
        </div>
    </div>
    <script>
        function select_vol(){
            var select_val=$("#have_vol_select option:selected").val()
            if(select_val==5){
                $("#have_vol_select").remove()
                $("#have_vol").append("<input type='text' name='have_vol' />")
            }
        }
    </script>
    <script>
        function get_ser_pinyin_fun(){
            $("#seriesab").attr("value",query($("#seriesname").val()))
        }
    </script>
    <script>
        function get_name_pinyin_fun(){
            $("#bookab").attr("value",query($("#bookname").val()))
        }
    </script>
    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="writer">第一责任人 </label>
        <div class="col-sm-9">
											<span class="input-icon">
												<input type="text" id="writer" name="writer" value="${catalog.writer}"/>
											</span>
            <label>
                <input type="checkbox" id="show" onclick="showdefault(this)"  value="是" class="ace" />
                <span class="lbl"> 显示</span>
            </label>

            <div id="notDefault" style="display: inline-block">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label">第二责任人</label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" id="second_writer" name="second_writer" value="${catalog.second_writer}"/>
                                                </span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label class="control-label">翻译作者</label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" id="translator" name="translator" value="${catalog.translator}"/>
                                                </span>

            </div>
            <!-- /section:elements.form.input-icon -->
        </div>
    </div>
    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right">出版社</label>
        <div class="col-sm-9">

            <span class="input-icon input-icon-right">
                   <select name="publishcom" id="publishcom">
                    <%
                        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
                        GetPub pub = (GetPub) context .getBean("getPub");
                        List<String> book_publisher = pub.getAll(session);//得到List对象（）
                          for(String s:book_publisher)
                          {
                              Map<String,String> catalog=((Map<String,String>)(request.getAttribute("catalog")));
                              String o="<option value=\""+s+"\" - >"+s+"</option>";
                              if(catalog!=null&&catalog.get("publishcom").equals(s))out.println(o.replace("-","selected"));else
                                  out.println(o.replace("-",""));
                          }
                    %>
                   </select>
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label  class="control-label" for="booklanguage">编著语言 </label>
            <span class="input-icon">
                <select name="booklanguage" id="booklanguage">
                    <%
                        GetProper bean = (GetProper) context .getBean("getpro");
                        List<String> booklanguage = bean.getProperties("book_lunguage",session);//得到List对象（）
                        for(String s:booklanguage)
                        {
                            Map<String,String> catalog=((Map<String,String>)(request.getAttribute("catalog")));
                            String o="<option value=\""+s+"\" - >"+s+"</option>";
                            if(catalog!=null&&catalog.get("booklanguage").equals(s))out.println(o.replace("-","selected"));else
                                out.println(o.replace("-",""));
                        }
                    %>
                </select>
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label class="control-label">纸张信息</label>
                    <span class="input-icon input-icon-right">
                        <select id="paperInfo" name="paperInfo">
                            <option value="胶版纸" <c:if test="${catalog.paperInfo==\"胶版纸\"}"><c:out value="selected" /></c:if> >胶版纸</option>
                            <option value="铜版纸" <c:if test="${catalog.paperInfo==\"铜版纸\"}"><c:out value="selected" /></c:if>>铜版纸</option>
                        </select>
                    </span>
            <!-- /section:elements.form.input-icon -->
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right"> 图书类型 </label>

        <div class="col-sm-9">
            <span >
                <input  class="col-sm-2" id="booktype" name="booktype" value="${catalog.booktype}"/>
            </span>
            <label class="control-label  col-sm-3  ">出版日期</label>
            <div class="col-sm-3 input-group" >
                <span class="input-group-addon">
                    <i class="fa fa-calendar bigger-110"></i>
                </span>
                <input class="form-control date-picker" id="id-date-picker-1" name="publishdate" type="text" value="${catalog.publishdate}" data-date-format="yyyy-mm-dd">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right"> 图书印次 </label>
        <%--col-sm-3导致float:left--%>

        <div class="col-sm-9">
            <input type="text" class="spinner1" value="${catalog.printtimes}" name="printtimes" />
            &nbsp;&nbsp;&nbsp;
            <label class="control-label no-padding-right"> 图书版次 </label>
            <input type="text" name="editiontimes"  class="spinner1" value="${catalog.editiontimes}"/>
            &nbsp;&nbsp;&nbsp;
            <label class="control-label no-padding-right">图书开本</label>

            <select name="booksize" class="" id="form-field-select-1">
                <%
                    List<String> kab = bean.getProperties("book_size",session);//得到List对象（）
                    for(String s:kab)
                    {
                        Map<String,String> catalog=((Map<String,String>)(request.getAttribute("catalog")));
                        String o="<option value=\""+s+"\" - >"+s+"</option>";
                        if(catalog!=null&&catalog.get("booksize").equals(s))out.println(o.replace("-","selected"));else
                            out.println(o.replace("-",""));
                    }
                %>
            </select>
            &nbsp;&nbsp;&nbsp;
            <label class="control-label no-padding-right">图书装帧</label>

            <select name="bookbind" class="" id="form-field-select-2">
                <%
                    List<String> book_layout = bean.getProperties("book_layout",session);//得到List对象（）
                    for(String s:book_layout)
                    {
                        Map<String,String> catalog=((Map<String,String>)(request.getAttribute("catalog")));
                        String o="<option value=\""+s+"\" - >"+s+"</option>";
                        if(catalog!=null&&catalog.get("bookbind").equals(s))out.println(o.replace("-","selected"));else
                            out.println(o.replace("-",""));
                    }
                %>
            </select>
        </div>
    </div>

    <div class="space-4"></div>

    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" >图书页数</label>

        <div class="col-sm-9">
            <input type="text" class=""  name="bookpagenum" value="${catalog.bookpagenum}"/>
            &nbsp;&nbsp;&nbsp;
            <label class="control-label no-padding-right" >图书单价</label>
            <input type="text"  name="bookprice" value="${catalog.bookprice}"/>
            &nbsp;&nbsp;&nbsp;
                <label>
                    <input  name="is_refbook" type="checkbox" <c:if test="${catalog.is_refbook==\"是\"}"><c:out value="checked" /></c:if> value="是" class="ace" />
                    <span class="lbl"> 是否工具书</span>
                </label>
            &nbsp;&nbsp;&nbsp;
                <label>
                    <input  name="is_journal" type="checkbox" <c:if test="${catalog.is_journal==\"是\"}"><c:out value="checked" /></c:if> value="是" class="ace" />
                    <span class="lbl"> 是否杂志</span>
                </label>
        </div>
    </div>

    <div class="space-4"></div>
    <div class="clearfix form-actions">
        <div class="col-md-offset-3 col-md-9">
            <button class="btn btn-info" onclick="submit_Form()" type="button">
                <i class="ace-icon fa fa-check bigger-110"></i>
                提交编目信息
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
        var type=$("#catalog_id").val().toString();
        var data=$("#submitForm").serialize()

        if(type.length==0)
        {
            $.ajax({
                type:"post",
                url:"${ctx}/book/addBookInfo",
                data:data,
                success:function(data){
                    alert(data.mes)
                }
            })
        }else
        {
            data.catalog_id=type
            $.ajax({
                type:"post",
                url:"${ctx}/book/updateBookInfo",
                data:data,
                success:function(data){
                    console.log(data);
                   alert(data.mes);
                    window.location.href="/booklogin/cataloglist";
                }
            })
        }

    }
</script>
