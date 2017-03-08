<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/3
  Time: 4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="../bsp/layout/taglibnew.jsp" %>
</head>
<body>


<form id='upload' method='post' enctype='multipart/form-data' action='${ctx}/bookInfo/uploadXLS'><input type='file' id='upload-input'  name='uploadFile' onchange='submit()' /></form>

</body>

<script>

    function submit(){
        $("#upload").submit();
    }
</script>
</html>
