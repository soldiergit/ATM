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
    <meta charset="utf-8">
    <title>ATM</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="./layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="./css/index.css" media="all"/>
</head>
<body>
<div class="layui-container grid-demo">
    <div class="layui-row" style="margin-top: 40px">
        <div class="layui-col-xs6 layui-col-md12">
            <div class="layui-bg-green"><h1>基于Web的自动取款机仿真系统</h1></div>
        </div>
    </div>
    <div class="layui-row" style="margin-top: 20px">
        <div class="layui-col-md12">
            <%-- layui-col-space10设置列间距时，layui-col-md*上不能有任何样式，否则失效--%>
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md7">
                    <div class="layui-bg-gray" style="height: 620px">
                        <!-- 荧屏 -->
                        <div class="layui-row">
                            <div class="layui-col-md10 layui-col-md-offset1" id="display"></div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md5">
                    <div class="layui-bg-gray" style="height: 620px">
                        <!--查看收据-->
                        <div class="layui-row">
                            <button type="button" class="layui-btn layui-btn-lg layui-btn-radius" id="receiptInfo" onclick="receiptInfo();">查看收据</button>
                        </div>
                        <!--收据信息-->
                        <div class="layui-row" style="margin-top: 5px;">
                            <div class="layui-col-md10 layui-col-md-offset1" style="background-color: white">
                                <iframe src="" id="printBillPage" class="layui-col-md12"
                                        style="height: 220px;"></iframe>
                            </div>
                        </div>
                        <!--1-9按键-->
                        <div class="layui-row layui-col-space10" style="margin-top: 10px;">
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="number" value="1" onclick="readNum(this);">1
                                </button>
                            </div>
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b2" value="2" onclick="readNum(this);">2
                                </button>
                            </div>
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b3" value="3" onclick="readNum(this);">3
                                </button>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space10" style="margin-top: 30px;">
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b4" value="4" onclick="readNum(this);">4
                                </button>
                            </div>
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b5" value="5" onclick="readNum(this);">5
                                </button>
                            </div>
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b6" value="6" onclick="readNum(this);">6
                                </button>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space10" style="margin-top: 30px;">
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b7" value="7" onclick="readNum(this);">7
                                </button>
                            </div>
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b8" value="8" onclick="readNum(this);">8
                                </button>
                            </div>
                            <div class="layui-col-md4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b9" value="9" onclick="readNum(this);">9
                                </button>
                            </div>
                        </div>
                        <div class="layui-row layui-col-space10" style="margin-top: 30px;">
                            <div class="layui-col-md4 layui-col-md-offset4">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="b0" value="0" onclick="readNum(this);">0
                                </button>
                            </div>
                        </div>
                        <!--确认 取消-->
                        <div class="layui-row layui-col-space10" style="margin-top: 10px;">
                            <div class="layui-col-md6">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="comfirm" onclick="submit();">确认
                                </button>
                            </div>
                            <div class="layui-col-md6">
                                <button type="button" class="layui-btn layui-btn-radius layui-btn-normal"
                                        id="cancel" onclick="cancel();">取消
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-row" style="margin-top: 20px">
        <div class="layui-col-xs6 layui-col-md12 layui-bg-gray">
            <button type="button" class="layui-btn layui-btn-lg layui-btn-radius layui-btn-danger" id="switch"></button>
            <button type="button" class="layui-btn layui-btn-lg layui-btn-radius layui-btn-warm" id="card">插卡</button>
        </div>
    </div>
</div>
<script type="text/javascript" src="./layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>
