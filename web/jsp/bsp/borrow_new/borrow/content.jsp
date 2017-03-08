<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="book_status">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="readCard"> 借阅证卡号 </label>
                <div class="col-sm-9">
                    <input type="text" id="readCard" name="readCard" placeholder="借阅证卡号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="userName"> 借阅会员姓名 </label>
                <div class="col-sm-9">
                    <input type="text" id="userName" name="userName" readonly placeholder="借阅会员姓名" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="bookCode"> 书籍编号 </label>
                <div class="col-sm-9">
                    <input type="text" id="bookCode" name="bookCode" placeholder="书籍编号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="clearfix form-actions" >
                <div class="col-md-offset-3 col-md-12">
                    <button class="btn btn-info" type="reset" id="borrow">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        确定借书
                    </button>
                </div>
            </div>
        </form>
    </div><!-- /.col -->
</div>

