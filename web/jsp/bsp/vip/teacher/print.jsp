<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<div id="printArea" style="margin-top: 50%;font-size: 40px;text-align: center; vertical-align: middle;">
    借阅证卡号：${teacher.readCard}<br>
    姓名：${teacher.name}<br>
    身份证号：${teacher.ID}<br>
    性别：${teacher.gender}<br>
    会员卡类型：${teacher.cardType}<br>
    加入时间：${teacher.addTime}<br>
    会员状态：${teacher.status}<br>
    教职工号：${teacher.teachID}<br>
    所教课程：${teacher.teachClass}<br>
    手机：${teacher.tel}<br>
    qq：${teacher.qq}<br>
    常用邮箱：${teacher.email}<br>
</div>
<script>
    window.onload=function(){
        window.print();
    }
</script>
</body>
</html>
