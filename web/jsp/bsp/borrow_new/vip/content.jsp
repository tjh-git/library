<%@ page language="java" pageEncoding="UTF-8"%>
<div id="dialog-message" class="hide">
   <form class="form-horizontal" role="form" id="vip_borrow_status">
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="code"> 会员编号 </label>
           <div class="col-sm-9">
               <input type="text" id="code" name="code" placeholder="会员编号" class="col-xs-10 col-sm-11" />
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" > 查询类型 </label>
           <div class="col-sm-9">
              <select name="enable" class="col-xs-10 col-sm-11" id="enable">
                <option value="0">全部</option>
                <option value="1">曾经借阅归还</option>
                <option value="2">在借</option>
                <option value="3">逾期</option>
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