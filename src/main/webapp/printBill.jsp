<%--
  Created by IntelliJ IDEA.
  User: soldier
  Date: 2020/3/23
  Time: 23:37
  To change this template use File | Settings | File Templates.
  该页面用于显示用户的凭条信息，模拟订单打印完成。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>soldier银行凭条</title>
    <style type="text/css">
        .box{
            margin: 0 auto;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="box">
    <h4>${sessionScope.printMessage.title}</h4>
    <div class="textTop">
       ${sessionScope.printMessage.printNum}${sessionScope.voucher.watercourse_num}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.ATMNum}${sessionScope.voucher.atm_id}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.operaMoney}${sessionScope.voucher.money}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.operaTime}${sessionScope.voucher.updateTime}
    </div>
    <div class="textBox">
        ${sessionScope.printMessage.operaCard}${sessionScope.voucher.cardNo}
    </div>
   <div class="textBottom">
        ${sessionScope.printMessage.operaNum}${sessionScope.voucher.optionNum}
    </div>
</div>
</body>
</html>
