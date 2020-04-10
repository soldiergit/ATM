package atm.TransactionExtend;

import atm.ATM;
import atm.Session;
import atm.Transaction;
import banking.Account;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author soldier
 * @Date 2020/3/23 18:42
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:转账交易
 */
public class Transfer extends Transaction {

    Account otherAccount = null;

    public Transfer(Session session, Account acct) {
        super(session, acct);
        // 当选择转账交易时,需要改变显示屏的显示,需要改变数字键盘的状态
        ATM machine = ATM.getInstance();
        machine.getDisplay().setText("请输入转账收款方账户（卡号）：");
        machine.getDigitButton().stateChange(1, 0, "TransferAccountServlet");
    }

    public Account getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(Account otherAccount) {
        this.otherAccount = otherAccount;
    }
}
