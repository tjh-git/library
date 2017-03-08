<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="book_status">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="code"> 书籍编号 </label>
                <div class="col-sm-9">
                    <input type="text" id="code" name="code" placeholder="书籍编号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="bookName"> 书名 </label>
                <div class="col-sm-9">
                    <input type="text" id="bookName" name="bookName" readonly placeholder="书名" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="borrowNum"> 借阅次数 </label>
                <div class="col-sm-9">
                    <input type="text" id="borrowNum" name="borrowNum" readonly placeholder="借阅次数" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="borrowTime"> 借阅时间 </label>
                <div class="col-sm-9">
                    <input type="text" id="borrowTime" name="borrowTime" readonly placeholder="借阅时间" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="borrowerCode"> 借阅人编号 </label>
                <div class="col-sm-9">
                    <input type="text" id="borrowerCode" name="borrowerCode" readonly placeholder="借阅人编号" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="borrowerName"> 借阅人姓名 </label>
                <div class="col-sm-9">
                    <input type="text" id="borrowerName" name="borrowerName" readonly placeholder="借阅人姓名" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="money"> 余额 </label>
                <div class="col-sm-9">
                    <input type="text" id="money" name="money" readonly placeholder="余额" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="fine"> 罚款金额 </label>
                <div class="col-sm-9">
                    <input type="text" id="fine" name="fine" readonly placeholder="罚款金额" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="form-group"  hidden>
                <label class="col-sm-3 control-label no-padding-right" for="isOut"> 书籍状态 </label>
                <div class="col-sm-9">
                    <input type="text" id="isOut" name="isOut" readonly placeholder="书籍状态" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="clearfix form-actions" >
                <div class="col-md-offset-3 col-md-12">
                    <button class="btn btn-info" type="reset" id="return_">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        确定还书
                    </button>
                </div>
            </div>
        </form>
    </div><!-- /.col -->
</div>

