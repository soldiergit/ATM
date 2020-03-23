var num = 0; //使用数字键输入的数字

// ATM对象
var atm = {status: 0}; // 0:空闲 1:关闭 2:处理中
atm.refresh = function refresh(status) {
    this.status = status;
};

// 屏幕对象
var display = {text: ""};
display.refresh = function refresh(text) {
    this.text = text;
    $("#display").html(this.text);
};
display.show = function show(text) {
    $("#display").html(text);
};

// 按钮对象
var switchButton = {text: "", disable: true};
switchButton.refresh = function refresh(text, disable) {
    this.text = text;
    this.disable = disable;
    $("#switch").html(text);
    $("#switch").unbind("click");
    if (atm.status == 0) {
        $("#switch").click(turnoff);
    } else if (atm.status == 1) {
        $("#switch").click(turnon);
    }
    $("#switch").attr('disable', disable);
};

// 插卡孔
var cardSlot = {text: "", inserted: false};
cardSlot.refresh = function refresh(text, inserted) {
    this.text = text;
    this.inserted = inserted;
    $("#card").html(text);
    $("#card").attr('disable', inserted);
};

// 数字按钮
var digitButton = {state: 2, visibility: 0, servletName: ""};
digitButton.refresh = function refresh(state, visibility, servletName) {
    this.state = state;
    this.visibility = visibility;
    this.servletName = servletName;
};

function readNum(obj) {
    var digit = Number(obj.value);
    // console.log(digitButton);
    if (digitButton.state == 0) {
        submitNum(digit);
    } else if (digitButton.state == 1) {
        num = 10 * num + digit;
        var str = display.text + "<br/>";
        str += num;
        display.show(str);
    } else if (digitButton.state == 2) {

    }
}

// 在DOM树加载完成后就会执行
$(document).ready(function () {
    $("#card").click(insertCard);
    getStatus();
});

function refresh(resp) {
    atm.refresh(resp.ATM.state);
    display.refresh(resp.display.text);
    switchButton.refresh(resp.switchbutton.text, resp.switchbutton.disable);
    cardSlot.refresh(resp.cardslot.text, resp.cardslot.inserted);
    digitButton.refresh(resp.digitbutton.state, resp.digitbutton.visibility, resp.digitbutton.servletName);
}

// 返回当前ATM系统各个对象的状态
function getStatus() {
    $.post('/ATM/GetStatusServlet', function (responseText) {
        refresh(responseText);
    });
}

// 开启ATM
function turnon() {
    $.post('/ATM/TurnOnServlet', function (responseText) {
        refresh(responseText);
    });
}

// 关闭ATM
function turnoff() {
    $.post('/ATM/TurnOffServlet', function (responseText) {
        refresh(responseText);
    });
}

// 插入银行卡
function insertCard() {
    // 账号输入框
    var cardNo = window.prompt("请输入账号", "");
    $.post('/ATM/CardInsertedServlet', 'cardNo=' + cardNo, function (responseText) {
        refresh(responseText);
    });
}

function submitNum(number) {
    $.post('/ATM/' + digitButton.servletName, 'num=' + number, function (responseText) {
        refresh(responseText);
        num = 0;
    });
}

function submit() {
    submitNum(num);
}

function cancel(){
    $.post('/ATM/'+digitButton.servletName,'num=exitATM', function(responseText) {
        refresh(responseText);
    });
}

function receiptInfo() {
    window.location.reload();
}