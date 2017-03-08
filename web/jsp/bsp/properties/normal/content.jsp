<%@ page language="java" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->
        <form class="form-horizontal" role="form" id="tags">
        <table>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >图书装帧</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="book_layout" id="book_layout" class="form-field-tags" value="" placeholder="Enter tags ..." />
                                <select name="book_layout_def" id="book_layout_def">
                                </select>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >图书语言</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="book_lunguage" id="book_lunguage"  class="form-field-tags" value="" placeholder="Enter tags ..." />
                                <select name="book_lunguage_def" id="book_lunguage_def">
                                </select>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >图书开本</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="book_size" id="book_size" class="form-field-tags" value="" placeholder="Enter tags ..." />
                                <select name="book_size_def" id="book_size_def">
                                </select>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >图书来源</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="book_from" id="book_from" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >书库编码</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="book_code" id="book_code" class="form-field-tags" value="" placeholder="Enter tags ..." />
                                <select name="book_code_def" id="book_code_def">
                                </select>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >班级编码</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="class_code" id="class_code" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >年级编码</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="grade_code" id="grade_code" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >用户卡类型</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="user_type" id="user_type" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >图书馆分馆</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="library_div" id="library_div" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >学生最大借书量</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="s_max_borrow" id="s_max_borrow" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >教师最大借书量</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="t_max_borrow" id="t_max_borrow" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >借阅证年限</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="borrow_max_time" id="borrow_max_time" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >教师超期罚款金额</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="t_overtime_fine" id="t_overtime_fine" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >学生超期罚款金额</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="s_overtime_fine" id="s_overtime_fine" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >教师单本最长借阅时限（天）</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="t_one_length" id="t_one_length" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >学生单本最长借阅时限（天）</label>
                        <div class="col-sm-9">
                            <div class="inline">
                                <input type="text" name="s_one_length" id="s_one_length" class="form-field-tags" value="" placeholder="Enter tags ..." />
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >教师是否超期罚款</label>
                        <div class="controls col-xs-12 col-sm-9">
                            <div class="col-xs-3">
                                <label>
                                    <input type="checkbox" name="t_isFine" id="t_isFine" class="ace ace-switch ace-switch-4 btn-empty"/>
                                    <span class="lbl"></span>
                                </label>
                            </div>
                     </div>
                </div>
                <td>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" >学生是否超期罚款</label>
                        <div class="controls col-xs-12 col-sm-9">
                            <div class="col-xs-3">
                                <label>
                                    <input type="checkbox" name="s_isFine" id="s_isFine" class="ace ace-switch ace-switch-4 btn-empty"/>
                                    <span class="lbl"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
            <div class="clearfix form-actions">
                <div class="col-md-offset-3 col-md-9">
                    <button class="btn btn-info" type="button" id="submit">
                        <i class="ace-icon fa fa-check bigger-110"></i>
                        更新参数
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>