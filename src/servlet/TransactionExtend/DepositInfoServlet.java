package servlet.TransactionExtend;

import atm.ATM;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author soldier
 * @Date 2020/3/23 21:26
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:存款
 */
public class DepositInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取页面存款金额
		int num = Integer.parseInt(req.getParameter("num"));
		//设置即将存款金额
		ATM.getInstance().getSession().getTransaction().setAmount(num);
		//执行存款
		ATM.getInstance().getSession().getTransaction().execute(req);
		String json = ATM.getInstance().getResponse();
		resp.setContentType("text/json");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}

