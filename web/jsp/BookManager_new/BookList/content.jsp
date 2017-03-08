<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<select id="col_select1" class="col_select">
    <option value="bookcode">图书条码</option>
</select>
等于
<input type="text" class="value_input" id="col_select1_v" for="col_select1">
<select id="col_select2" class="col_select">
    <option value="bookname">书名名称</option>
</select>
等于
<input type="text" class="value_input" id="col_select2_v" for="col_select2">
<br />
<select id="col_select3" class="col_select">
    <option value="bookab">图书简写</option>
</select>
等于
<input type="text" class="value_input" id="col_select3_v" for="col_select3">

<br />
<button id="search_click">搜索</button>
<button id="reset_click">重置</button>
<br/>

<br/>
<table id="grid-table"></table>

<div id="grid-pager"></div>

<script type="text/javascript">
    var $path_base = "..";//in Ace demo this will be used for editurl parameter
</script>
