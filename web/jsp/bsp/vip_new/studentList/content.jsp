<%@ page language="java" pageEncoding="UTF-8"%>
<div id="dialog-message" class="hide">
    <form class="form-horizontal" role="form" id="vip_borrow_status">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="name"> 姓名 </label>
            <div class="col-sm-9">
                <input type="text" id="name" name="name" placeholder="姓名" class="col-xs-10 col-sm-11" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="readCard"> 卡号 </label>
            <div class="col-sm-9">
                <input type="text" id="readCard" name="readCard" placeholder="卡号" class="col-xs-10 col-sm-11" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" > 卡类型 </label>
            <div class="col-sm-9">
                <select name="enable" class="col-xs-10 col-sm-11" id="cardType">
                    <option value="">全部</option>
                    <option value="教师">教师</option>
                    <option value="学生">学生</option>
                </select>
            </div>
        </div>
    </form>
</div><!-- #dialog-message -->
<div class="row">
    <div class="col-xs-12">

        <table id="grid-table"></table>

        <div id="grid-pager"></div>

        <script type="text/javascript">
            var $path_base = "..";//in Ace demo this will be used for editurl parameter
        </script>

        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div><!-- /.row -->