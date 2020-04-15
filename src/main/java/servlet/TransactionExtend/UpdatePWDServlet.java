package servlet.TransactionExtend;

import atm.ATM;
import atm.Session;
import banking.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author soldier
 * @Date 2020/4/10 15:26
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:修改用户密码
 */
public class UpdatePWDServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面输入的密码
        String pwd = req.getParameter("num");
        ATM.getInstance().getSession().getAccount().updatePWD(pwd);
        // 更新机器状态（默认选择退出操作）
        ATM.getInstance().getSession().setState(Session.CHOOSING);
        String json = ATM.getInstance().getResponse();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
