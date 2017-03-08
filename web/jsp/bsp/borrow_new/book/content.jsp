<%@ page language="java" pageEncoding="UTF-8"%>
<div id="dialog-message" class="hide">
    <form class="form-horizontal" role="form" id="readCard_type">
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="code"> 图书编号 </label>
            <div class="col-sm-9">
                <div class="input-group col-xs-10">
                    <input class="form-control " placeholder="图书编号" id="code" name="code" type="text" />
                </div>
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
