<%@ page language="java" pageEncoding="UTF-8"%>
<div id="dialog-message" class="hide">
   <form class="form-horizontal" role="form" id="readCard_type">
       <div class="form-group">
           <label class="col-sm-5 control-label no-padding-right" > 借阅卡类型 </label>
           <div class="col-sm-6">
              <select name="model" class="col-xs-10 col-sm-11" id="model">
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