<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="addStudent">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="readCard"> 借阅证卡号 </label>
                <div class="col-sm-9">
                    <input type="text" id="readCard" name="readCard" placeholder="借阅证卡号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <input type="text" id="id" name="id" hidden />
            <div class="form-group" hidden>
                <label class="col-sm-3 control-label no-padding-right" for="name"> 姓名 </label>

                <div class="col-sm-9">
                    <input type="text" id="name" name="name" readonly placeholder="姓名" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="cardType"> 会员卡类型 </label>
                <div class="col-sm-9">
                    <input type="text" id="cardType" readonly placeholder="会员卡类型" name="cardType" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="status"> 会员状态 </label>
                <div class="col-sm-9">
                    <input type="text" id="status" name="status" readonly placeholder="会员状态" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group" hidden>
                <label class="col-sm-3 control-label no-padding-right" for="money"> 余额 </label>
                <div class="col-sm-9">
                    <input type="text" id="money" name="money" readonly placeholder="余额" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="savemoney"> 充值金额 </label>
                <div class="col-sm-9">
                    <input type="text" id="savemoney" name="savemoney" placeholder="充值金额" class="col-xs-10 col-sm-5" />
                </div>
            </div>

            <div class="clearfix form-actions" >
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

