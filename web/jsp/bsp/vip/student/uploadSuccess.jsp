<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <%@ include file="../../layout/taglibnew.jsp" %>
</head>
<body>
    <script>
        alert("上传成功，增加了"+${success}+"条记录");
        window.parent.location.reload(true);
    </script>
</body>
</html>
