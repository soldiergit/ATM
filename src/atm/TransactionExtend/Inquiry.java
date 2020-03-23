package atm.TransactionExtend;

import atm.ATM;
import atm.Session;
import atm.Transaction;
import banking.Account;

/**
 * @Author soldier
 * @Date 2020/3/23 18:42
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:显示余额信息
 */
public class Inquiry extends Transaction {

    /**
     * 显示余额信息
     *
     * @param session
     * @param acct
     */
    public Inquiry(Session session, Account acct) {
        super(session, acct);
        // 当选择存款交易时,需要改变显示屏的显示,需要改变数字键盘的状态
        ATM machine = ATM.getInstance();
        Account acc = getAccount();
        machine.getDisplay().setText(
                "姓名：" + acc.getFullName() + "<br>" +
                "账号：" + acc.getCardNo() + "<br>" +
                "余额：" + acc.getBalance() + "元<br>" +
                "请按任意键退出");
        machine.getDigitButton().stateChange(0, 0, "InquiryServlet");
    }

}
