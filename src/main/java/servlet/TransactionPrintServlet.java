package servlet;

import atm.ATM;
import atm.Session;
import banking.PrintMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author soldier
 * @Date 2020/4/10 17:38
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:交易凭条打印
 */
public class TransactionPrintServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int flag = Integer.parseInt(req.getParameter("num"));
        ATM machine = ATM.getInstance();
        if (flag == 1) {
            //打印凭条
            //保存PrintMessage对象
            PrintMessage printMessage = new PrintMessage("soldier银行凭条信息", "凭条编号:", "ATM机编号:", "操作金额:", "操作时间:", "银行卡号:", "操作类型:");
            req.getSession().setAttribute("printMessage", printMessage);
            //进入交易选择状态
            machine.getSession().setState(Session.CHOOSING);
            machine.getDisplay().setText("凭条成功,请点击【查看收据】按钮查看" + "<br>" + "请选择您需要的业务：" + "<br>" + "1:取款 2:存款 3:转账 4:查询 5:修改密码  0:退出");
            machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
        } else if (flag == 0) {
            //进入交易选择状态
            machine.getSession().setState(Session.CHOOSING);
            machine.getDisplay().setText("请选择您需要的业务：" + "<br>" + "1:取款 2:存款 3:转账 4:查询 5:修改密码  0:退出");
            machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
        } else {
            //用户按的数字非0非1
            machine.getDisplay().setText("输入错误，请重新输入" + "<br>" + "不打印:0 打印:1");
            //充值ATM状态和下次即将调整的servlet名字
            machine.getDigitButton().stateChange(0, 0, "TransactionPrintServlet");
        }
        String json = ATM.getInstance().getResponse();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
