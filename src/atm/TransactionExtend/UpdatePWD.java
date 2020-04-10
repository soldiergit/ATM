package atm.TransactionExtend;

import atm.ATM;
import atm.Session;
import atm.Transaction;
import banking.Account;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author soldier
 * @Date 2020/4/10 15:52
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:修改用户密码
 */
public class UpdatePWD extends Transaction {

    public static final int TRANS_UNSTART = 1; // 未开始
    public static final int TRANS_GETDATA = 2; // 获取交易请求
    public static final int TRANS_SUCCESS = 3; // 交易成功
    public static final int TRANS_FAILURE = 4; // 交易失败
    public static final int TRANS_EXIT = 4; // 退出

    Account otherAccount = null;


    public Account getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(Account otherAccount) {
        this.otherAccount = otherAccount;
    }

    /**
     * @param session
     * @param acct
     * @author
     * @version
     */
    public UpdatePWD(Session session, Account acct) {
        super(session, acct);
        // 当选择修改密码交易时,需要改变显示屏的显示,需要改变数字键盘的状态
        ATM machine = ATM.getInstance();
        machine.getDisplay().setText("请输入新的密码：");
        machine.getDigitButton().stateChange(1, 1, "UpdatePWDServlet");
    }
}
