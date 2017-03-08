<%@ page language="java" pageEncoding="UTF-8"%>
<style>
    #show_kind{
        border:1px solid #1px solid #d5d5d5;
        width:83.33%;
        height: 100px;
        overflow-y: scroll;
        overflow-x: hidden;
        margin-top: 4px;
    }
    .book_kind_info{
        padding: 3px;
        margin: 3px;
        border-bottom: 0.3px dashed #f1f1f1;
    }
    .book_kind_info:hover{
        background-color: #f0f0f0;
    }
    /*#show_kind >.book_kind_info:nth-child(2n+1){*/
        /*background-color: #e1e1e1;*/
    /*}*/
    /*.kind_book{*/
        /*position: absolute;*/
        /*z-index: 1050;*/
    /*}*/
</style>
<div id="dialog-message" class="hide" >
   <form class="form-horizontal" role="form" id="readCard_type">
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="publishDate"> 出版时间 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control date-picker" placeholder="出版时间" id="publishDate" name="publishDate" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
               </div>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" for="loginDate"> 入库时间 </label>
           <div class="col-sm-9">
               <div class="input-group col-xs-10">
                   <input class="form-control date-picker" placeholder="入库时间" id="loginDate" name="loginDate" type="text" data-date-format="yyyy-mm-dd" />
                            <span class="input-group-addon ">
							    <i class="fa fa-calendar bigger-110"></i>
							</span>
               </div>
           </div>
       </div>
       <div class="form-group">
           <label class="col-sm-3 control-label no-padding-right" > 图书类型 </label>
           <div class="col-sm-9">
             <div class="input-group col-xs-10">
                 <input type="text" id="type" style="width: 100%"/>
             </div>
               <div class="kind_book" id="kind" style="display:none;">
                   <div id="show_kind">
                   </div>
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