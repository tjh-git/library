<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<iframe id="ifr" src="addIframe.jsp" style="display:none"></iframe>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="addTeacher">
            <input type="hidden" name="id" id="id" value="${vip.id}" />
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="readCard"> 借阅证卡号 </label>
                <div class="col-sm-9">
                    <input type="text" id="readCard" name="readCard" value="${vip.readCard}" placeholder="借阅证卡号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name"> 姓名 </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name" value="${vip.name}" placeholder="姓名" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="IDCard"> 身份证号 </label>
                <div class="col-sm-9">
                    <input type="text" id="IDCard" name="IDCard" value="${vip.IDCard}" placeholder="身份证号" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
            <div class="col-xs-12 col-sm-6">
                <div class="control-group">
                    <label class="control-label col-xs-6 col-sm-6">性别</label>
                    <div class="radio col-xs-6">
                        <label>
                            <input  type="radio" name= "gender" value="男" <c:if test="${empty vip.gender}"><c:out value="checked"/></c:if>  <c:if test="${vip.gender==\"男\"}"><c:out value="checked"/></c:if> class="ace col-xs-12" />
                            <span class="lbl ">男</span>
                        </label>
                        <label>
                            <input  type="radio" name= "gender" value="女" <c:if test="${vip.gender==\"女\"}"><c:out value="checked"/></c:if> class="ace col-xs-12" />
                            <span class="lbl">女</span>
                        </label>
                    </div>
                </div>
            </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="cardType"> 会员卡类型 </label>
                <div class="col-sm-9">
                    <input type="text" id="cardType" placeholder="会员卡类型" value="${vip.cardType}" name="cardType" value="教师" readonly class="col-xs-10 col-sm-5" />
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
                <label class="col-sm-3 control-label no-padding-right" for="addTime"> 加入时间 </label>
                <div class="col-sm-9">
                    <div class="input-group col-xs-5">
                        <input class="form-control date-picker" id="addTime" value="${vip.addTime}" name="addTime" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
                    </div>
                </div>
            </div>



            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="teachID"> 教职工号 </label>
                <div class="col-sm-9">
                    <input type="text" id="teachID" name="teachID" value="${vip.teachID}" placeholder="教职工号" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="teachClass"> 所教课程 </label>
                <div class="col-sm-9">
                    <input type="text" id="teachClass" name="teachClass" value="${vip.teachClass}" placeholder="所教课程" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="tel"> 手机 </label>
                <div class="col-sm-9">
                    <input type="text" id="tel" name="tel" placeholder="手机" value="${vip.tel}" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="qq"> qq </label>
                <div class="col-sm-9">
                    <input type="text" id="qq" name="qq" placeholder="qq" value="${vip.qq}" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="email"> 邮箱 </label>
                <div class="col-sm-9">
                    <input type="text" id="email" name="email" placeholder="邮箱" value="${vip.email}"  class="col-xs-10 col-sm-5" />
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
                    <button class="btn" type="reset" id="addTeachers">
                        <i class="ace-icon fa fa-undo bigger-110"></i>
                        批量上传
                    </button>
                </div>
            </div>
        </form>
    </div><!-- /.col -->
</div>

