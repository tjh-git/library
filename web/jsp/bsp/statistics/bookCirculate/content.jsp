<%@ page language="java" pageEncoding="UTF-8"%>

<div id="dialog-message" class="hide">
   <form class="form-horizontal" role="form" id="readCard_type">
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="start"> 开始时间 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control date-picker" placeholder="开始时间" id="start" name="start" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
               </div>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="end"> 结束时间 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control date-picker" placeholder="结束时间" id="end" name="end" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
               </div>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" > 查询类型 </label>
           <div class="col-sm-9">
              <select name="action" class="col-xs-10 col-sm-11" id="action">
                <option value="">全部</option>
                <option value="借阅">借阅</option>
                <option value="归还">归还</option>
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