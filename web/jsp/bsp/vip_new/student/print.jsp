<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<div id="printArea" style="margin-top: 50%;font-size: 40px;text-align: center; vertical-align: middle;">
    <div class="round">
        姓名：${student.name}<br>
        性别：${student.gender}<br>
        年级:${student.grade}<br>
        学号：${student.stuID}<br>
        借阅证卡号：${student.readCard}<br>
        会员卡类型：${student.cardType}<br>
    </div>
</div>
<script>
    window.onload=function(){
        window.print();
    }
</script>
<style>
    .round {
        background-color: #000;
        border: 1px solid #000;
        -moz-border-radius: 10px;
        -webkit-border-radius: 10px;
        color:#fff;
    }
</style>
</body>
</html>
