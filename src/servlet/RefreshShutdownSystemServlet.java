package servlet;

import atm.ATM;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author soldier
 * @Date 2020/4/12 14:13
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:当用户关闭或刷新ATM机操作页面时执行
 */
public class RefreshShutdownSystemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 重置
        ATM.refreshShutdownSystem();

        String json = ATM.getInstance().getResponse();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
