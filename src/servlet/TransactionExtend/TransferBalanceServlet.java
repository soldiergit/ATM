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
 * @Date 2020/3/23 21:26
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:执行转账
 */
public class TransferBalanceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int options = Integer.parseInt(req.getParameter("num"));
		// 执行转账
        ATM.getInstance().getSession().getAccount().transfer(options,req);
		String json = ATM.getInstance().getResponse();
		resp.setContentType("text/json");  
		resp.setCharacterEncoding("UTF-8"); 
		resp.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
