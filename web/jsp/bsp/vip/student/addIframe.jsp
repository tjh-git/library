<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../../layout/taglibnew.jsp" %>
</head>
<body>
<form enctype="multipart/form-data" action="/vip/student/addStudentBatch" id="fileupload" method="post">
    <input type="file" id="file" name="file1" onchange="addit()"/>
    <input type="hidden" id="fileName" name="fileName" value=""/>
</form>
<script>
    function addit()
    {
        $("#fileName").val($("#file").val());
        $("#fileupload").submit();
    }
</script>
</body>
</html>
