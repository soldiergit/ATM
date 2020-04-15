package servlet;

import atm.ATM;
import atm.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author soldier
 * @Date 2020/4/10 13:11
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:用户在输入取款金额过程中、输入存款金额过程中、输入转账账户过程中、输入转账金额、修改密码过程中点击返回按钮时
 */
public class CancelServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ATM machine = ATM.getInstance();

        //进入交易选择状态
        machine.getSession().setState(Session.CHOOSING);
        machine.getDisplay().setText("请选择您需要的业务：" + "<br>" + "1:取款 2:存款 3:转账 4:查询 5:修改密码  0:退出");
        machine.getDigitButton().stateChange(0, 1, "TransactionServlet");

        String json = ATM.getInstance().getResponse();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
