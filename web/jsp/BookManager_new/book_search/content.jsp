<%@ page language="java" pageEncoding="UTF-8"%>
<div class="row">
    <div class="col-xs-12">
        <form class="form-horizontal" role="form" id="book_status">
            <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="readCard"> 图书名称 </label>
                <div class="col-sm-9">
                    <input type="text" id="readCard" name="readCard" placeholder="输入图书名称检索图书信息和位置" class="col-xs-10 col-sm-5" />
                </div>
            </div>
            <div class="clearfix form-actions" >
                <div class="col-md-offset-3 col-md-12">
                    <button class="btn btn-info" type="reset" id="search">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        查询书籍信息
                    </button>
                </div>
            </div>
        </form>
    </div><!-- /.col -->
</div>

<div class="row" id="table" style="display: none">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="col-xs-12">
                <table id="simple-table" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>

                        <th>图书名称</th>
                        <th>版次</th>
                        <th>印次</th>

                        <th>
                            所在馆藏
                        </th>
                        <th>所在库位</th>

                        <th>查看相关读后感</th>
                    </tr>
                    </thead>

                    <tbody id="table-body">
                    <tr >
                        <td >奇怪的书</td>
                        <td >第三版</td>
                        <td >第二次</td>
                        <td >第二图书馆</td>
                        <td >304号</td>
                        <td><button onclick="getcomment()" class="btn btn-xs btn-warning">
                            <i class="ace-icon fa fa-flag bigger-120"></i>
                        </button></td>

                    </tr>




                    </tbody>
                </table>
            </div><!-- /.span -->
        </div><!-- /.row -->

        <div class="hr hr-18 dotted hr-double"></div>
    </div><!-- /.col -->
</div><!-- /.row -->
<script>
    function getcomment(id){
        window.location.href="/comment/commentlist"
    }
</script>

