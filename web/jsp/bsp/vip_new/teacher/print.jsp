<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<div id="printArea" style="margin-top: 50%;font-size: 40px;text-align: center; vertical-align: middle;">
    <div class="round">
        姓名：${teacher.name}<br>
        性别：${teacher.gender}<br>
        教职工号：${teacher.teachID}<br>
        借阅证卡号：${teacher.readCard}<br>
        会员卡类型：${teacher.cardType}<br>
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
