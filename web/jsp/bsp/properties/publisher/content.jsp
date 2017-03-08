<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-xs-12">
        <table id="simple-table" class="table table-striped table-bordered table-hover">
        </table>
        <div class="modal-footer no-margin-top">
            <div class="form-group" style="margin-top: 20px">
                <label class="col-sm-3 control-label no-padding-right " for="publisher_add"> 添加出版社 </label>
                <div class="col-sm-9">
                    <input type="text" id="publisher_add" name="publisher_add" placeholder="出版社名称" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" id="add"  style="position: absolute;right: 116px; top:-37px;padding-top: 3.5px;padding-bottom: 3.5px;">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                       提交
                    </button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="publisher_find"> 查询出版社 </label>
                <div class="col-sm-9">
                    <input type="text" id="publisher_find" name="publisher_find" placeholder="出版社名称" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" id="find"
                        style="  position: absolute;right: 116px; top: -37px;padding-top: 3.5px;padding-bottom: 3.5px;"
                    >
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        查询
                    </button>
                </div>
            </div>
            <ul  class="pagination pull-right no-margin" id="fenye">
            </ul>
        </div>
    </div><!-- /.span -->
</div><!-- /.row -->
<!--   <tr>
<td>
清华大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
山东大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
北京大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
机械工业出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
</tr>
<tr>
<td>
清华大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
山东大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
北京大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
机械工业出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
</tr>
<tr>
<td>
清华大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
山东大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
北京大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
机械工业出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
</tr>
<tr>
<td>
清华大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
山东大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
北京大学出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
<td>
机械工业出版社
<div class="hidden-sm hidden-xs btn-group">
<button class="btn btn-xs btn-danger">
<i class="ace-icon fa fa-trash-o bigger-120"></i>
</button>
</div>
</td>
</tr>
-->