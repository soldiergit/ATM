<%--
  Created by IntelliJ IDEA.
  User: soldier
  Date: 2020/3/23
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <title>ATM</title>
    <link href="css/index.css" rel="stylesheet" type="text/css"/>
</head>
<body bgcolor="#ffffff">

<div id="main">

    <!-- 荧屏 -->
    <div id="display"></div>

    <div id="receipt">
        <center>
            <button type="button" id="receiptInfo" onclick="receiptInfo();">查 看 收 据</button>
            <br/>
            <div style="width: 270px;height: 210px;background-color: white;margin:0 auto">
                <iframe src="" id="printBillPage" style="width: 270px;height: 210px;"></iframe>
            </div>
        </center>
        <center>
            <button type="button" id="b1" class="number" value="1" onclick="readNum(this);">1</button>
            <button type="button" id="b2" class="number" value="2" onclick="readNum(this);">2</button>
            <button type="button" id="b3" class="number" value="3" onclick="readNum(this);">3</button>
            <button type="button" id="b4" class="number" value="4" onclick="readNum(this);">4</button>
            <button type="button" id="b5" class="number" value="5" onclick="readNum(this);">5</button>
            <button type="button" id="b6" class="number" value="6" onclick="readNum(this);">6</button>
            <button type="button" id="b7" class="number" value="7" onclick="readNum(this);">7</button>
            <button type="button" id="b8" class="number" value="8" onclick="readNum(this);">8</button>
            <button type="button" id="b9" class="number" value="9" onclick="readNum(this);">9</button>
            <button type="button" id="b0" class="number" value="0" onclick="readNum(this);">0</button>
        </center>
        <br/>
        <center>
            <button type="button" id="comfirm" class="command" onclick="submit();">确认</button>
            <button type="button" id="cancel" class="command" onclick="cancel();">取消</button>
        </center>
    </div>

    <!--div id="panel">
        <div id="left_panel">
        </div>
        <div id="right_panel">
            <button type="button" id="log">显示日志</button>
        </div>
    </div-->
    <div id="panel">
        <!--button type="button" class="button" id="on" onclick="turnon();">开机</button>
        <button type="button" class="button" id="off">关机</button-->
        <button type="button" class="button" id="switch"></button>
        <button type="button" class="button" id="card">插卡</button>
    </div>

</div>

</body>
</html>
