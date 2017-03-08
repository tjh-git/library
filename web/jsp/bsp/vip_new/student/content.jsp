<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.simple.bsp.properties.web.controller.GetProper" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<iframe id="ifr" src="addIframe.jsp" style="display:none"></iframe>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="addStudent">
            <input type="hidden" name="id" id="id" value="${vip.id}">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="readCard"> 借阅证卡号 </label>
                <div class="col-sm-9">
                    <input type="text" id="readCard" name="readCard" value="${vip.readCard}" placeholder="借阅证卡号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 姓名 </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name" placeholder="姓名" value="${vip.readCard}" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="IDCard"> 身份证号 </label>
                <div class="col-sm-9">
                    <input type="text" id="IDCard" name="IDCard" placeholder="身份证号" value="${vip.IDCard}"  class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
            <div class="col-xs-12 col-sm-6">
                <div class="control-group">
                    <label class="control-label col-xs-6 col-sm-6">性别</label>
                    <div class="radio col-xs-6">
                        <label>
                            <input  type="radio" name= "gender" value="男" <c:if test="${empty vip.gender}"><c:out value="checked"/></c:if> <c:if test="${vip.gender==\"男\"}"><c:out value="checked"/></c:if> class="ace col-xs-12" />
                            <span class="lbl ">男</span>
                        </label>
                        <label>
                            <input  type="radio" name= "gender" value="女"  <c:if test="${vip.gender==\"女\"}"><c:out value="checked"/></c:if> class="ace col-xs-12" />
                            <span class="lbl">女</span>
                        </label>
                    </div>
                </div>
            </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="cardType"> 会员卡类型 </label>
                <div class="col-sm-9">
                    <input type="text" id="cardType" placeholder="会员卡类型" name="cardType" value="学生" readonly class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="addTime"> 加入时间 </label>
                <div class="col-sm-9">
                    <div class="input-group col-xs-5">
                        <input class="form-control date-picker" placeholder="加入时间"  value="${vip.addTime}" id="addTime" name="addTime" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
                    </div>
                </div>
            </div>



            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="status"> 会员状态 </label>
                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="status" name="status">
                        <option value="有效" <c:if test="${vip.status==\"有效\"}"><c:out value="selected"/></c:if>  >有效</option>
                        <option value="无效" <c:if test="${vip.status==\"无效\"}"><c:out value="selected"/></c:if> >无效</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="stuID"> 学号 </label>
                <div class="col-sm-9">
                    <input type="text" id="stuID" name="stuID" placeholder="学号" value="${vip.stuId}" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="grade"> 年级 </label>
                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="grade" name="grade">
                        <%
                            ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
                            GetProper bean = (GetProper) context .getBean("getpro");
                            List<String> grade = bean.getProperties("grade_code",session);//得到List对象（）
                            for(String s:grade)
                            {
                                Map<String,String> vip=((Map<String,String>)(request.getAttribute("vip")));
                                String o="<option value=\""+s+"\" - >"+s+"</option>";
                                if(vip!=null&&vip.get("grade").equals(s))out.println(o.replace("-","selected"));else
                                    out.println(o.replace("-",""));
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="clazz"> 班级 </label>
                <div class="col-sm-9">
                    <select class="col-xs-10 col-sm-5" id="clazz" name="clazz">
                        <%
                            List<String> clazz = bean.getProperties("class_code",session);//得到List对象（）
                            for(String s:clazz)
                            {
                                Map<String,String> vip=((Map<String,String>)(request.getAttribute("vip")));
                                String o="<option value=\""+s+"\" - >"+s+"</option>";
                                if(vip!=null&&vip.get("clazz").equals(s))out.println(o.replace("-","selected"));else
                                    out.println(o.replace("-",""));
                            }
                        %>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="deadLine"> 借阅证有效期 </label>
                <div class="col-sm-9">
                    <div class="input-group col-xs-5">
                        <input class="form-control date-picker" value="${vip.deadLine}" placeholder="借阅证有效期" id="deadLine" name="deadLine" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
                    </div>
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" id="submit">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        提交
                    </button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset" id="reset">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        重置
                    </button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="button" id="addStudents">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        批量上传
                    </button>
                </div>
            </div>
        </form>
    </div><!-- /.col -->
</div>

