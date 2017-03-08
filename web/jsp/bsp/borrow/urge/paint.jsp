<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<div id="printArea" style="margin-top: 50%;font-size: 40px;text-align: center; vertical-align: middle;">
    借阅证卡号：${result.user_readCard}<br>
    会员姓名：${result.user_name}<br>
    会员卡类型：${result.user_cardType}<br>
    逾期书籍编号：${result.book_code}<br>
    逾期书籍书名：${result.book_name}<br>
    借阅时间：${result.borrowTime}<br>
    应还时间：${result.deadLine}<br>
    罚款金额：${result.user_fine}<br>
</div>
<script>
    window.onload=function(){
        window.print();
    }
</script>
</body>
</html>
