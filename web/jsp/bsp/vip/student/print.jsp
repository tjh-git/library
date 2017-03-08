<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<div id="printArea" style="margin-top: 50%;font-size: 40px;text-align: center; vertical-align: middle;">
    借阅证卡号：${student.readCard}<br>
    姓名：${student.name}<br>
    身份证号：${student.ID}<br>
    性别：${student.gender}<br>
    会员卡类型：${student.cardType}<br>
    加入时间：${student.addTime}<br>
    会员状态：${student.status}<br>
    学号：${student.stuID}<br>
    借阅证有效期：${student.deadLine}
</div>
<script>
    window.onload=function(){
        window.print();
    }
</script>
</body>
</html>
