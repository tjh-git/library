<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button onclick="uploadFile()" class="btn btn-primary">
    <i class="ace-icon fa  fa-book  align-top bigger-125"></i>
    批量上传
</button>
<button id="search" class="btn btn-success">
    <i class="ace-icon fa fa-globe align-top bigger-125"></i>
    搜索
</button>

<div id="dialog-message" class="hide">
    <form class="form-horizontal" role="form" id="book_catalog">
        <input id="page" name="page" value="" style="display: none"/>
        <input id="rows" name="rows" value="" style="display: none"/>
        <input id="sort" name="sidx" value="" style="display: none"/>
        <input id="order" name="sord" value="" style="display: none"/>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="bookcode"> 图书条码 </label>
            <div class="col-sm-9">
                <input type="text" id="bookcode" name="bookcode" placeholder="图书条码" class="col-xs-10 col-sm-11" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="bookname"> 图书名称 </label>
            <div class="col-sm-9">
                <input type="text" id="bookname" name="bookname" placeholder="图书名称" class="col-xs-10 col-sm-11" />
            </div>
        </div>
    </form>
</div><!-- #dialog-message -->
<table id="grid-table"></table>

<div id="grid-pager"></div>

<script type="text/javascript">
    var $path_base = "..";//in Ace demo this will be used for editurl parameter
</script>
<iframe id="iframe"  style="display:none;" src="/book/fileUpload"></iframe>
