<%--
  Created by IntelliJ IDEA.
  User: lovesyxfuffy
  Date: 2016/6/3
  Time: 4:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    alert("上传成功")
    window.parent.reload()
    window.location="${ctx}/bookInfo/fileUpload"
</script>
</body>
</html>
