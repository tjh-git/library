<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="addSchool">
            <input type="hidden" value="${school.school_id}" name="school_id" id="school_id" />
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_name"> 学校名称 </label>
                <div class="col-sm-9">
                    <input type="text" id="school_name" name="school_name"   value="${school.school_name}" placeholder="学校名称" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_position"> 学校地址 </label>

                <div class="col-sm-9">
                    <input type="text" id="school_position" name="school_position"  value="${school.school_position}" placeholder="学校地址" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_contact"> 联系人 </label>
                <div class="col-sm-9">
                    <input type="text" id="school_contact" name="school_contact"   value="${school.school_contact}" placeholder="联系人" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_post"> 学校邮编 </label>
                <div class="col-sm-9">
                    <input type="text" id="school_post" placeholder="学校邮编"  value="${school.school_post}" name="school_post" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_tel"> 学校电话 </label>
                <div class="col-sm-9">
                    <input type="text" id="school_tel" name="school_tel"  value="${school.school_tel}" placeholder="学校电话" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_pass"> 学校注册码 </label>
                <div class="col-sm-9">
                    <input type="text" id="school_pass" name="school_pass"  value="${school.school_pass}" placeholder="学校注册码" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="school_href"> 读者荐购平台地址 </label>
                <div class="col-sm-9">
                    <input type="text" id="school_href" name="school_href" value="${school.school_href}" placeholder="读者荐购平台地址" class="col-xs-10 col-sm-5" />
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
                </div>
            </div>
        </form>
    </div><!-- /.col -->
</div>

