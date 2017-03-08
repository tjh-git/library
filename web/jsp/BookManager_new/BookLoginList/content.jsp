<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/7
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<select id="col_select">
    <option value="bookname">图书名称</option>
    <option value="bookcode">图书条码</option>
    <option value="bookab">书名简写</option>
    <option value="writer">作者</option>
</select>
等于
<input type="text" id="value_input">
<button id="search_click">搜索</button>
<button id="reset_click">重置</button>
<br/>
<br/>
<table id="grid-table"></table>

<div id="grid-pager"></div>

<script type="text/javascript">
    var $path_base = "..";//in Ace demo this will be used for editurl parameter
</script>
