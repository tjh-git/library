<%@ page language="java" pageEncoding="UTF-8"%>

<div id="dialog-message" class="hide">
   <form class="form-horizontal" role="form" id="readCard_type">
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="school_id"> 学校编号 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control " placeholder="学校编号" id="school_id" name="school_id" type="text" />
               </div>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="school_name"> 学校名称 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control " placeholder="学校名称" id="school_name" name="school_name" type="text" />
               </div>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="school_pass"> 学校注册码 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control " placeholder="学校注册码" id="school_pass" name="school_pass" type="text" />
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