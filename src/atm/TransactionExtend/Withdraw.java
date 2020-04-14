//******************************************************************************
//
// ATM系统 -  Withdraw.java 
// 参考了 http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
// 
//******************************************************************************

package atm.TransactionExtend;

import atm.ATM;
import atm.Session;
import atm.Transaction;
import banking.Account;
import banking.Voucher;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * @Author soldier
 * @Date 2020/3/23 18:42
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:取款交易
 */
public class Withdraw extends Transaction {

    public Withdraw(Session session, Account acct) {
        super(session, acct);
        // 当选择取款交易时,需要改变显示屏的显示,需要改变数字键盘的状态
        ATM machine = ATM.getInstance();
        machine.getDisplay().setText("请输入取款金额：");
        machine.getDigitButton().stateChange(1, 0, "WithdrawServlet");
    }

    /**
     * 重写：执行扣款
     */
    @Override
    public void execute(HttpServletRequest request) {

        ATM machine = ATM.getInstance();
        //判断用户是否按取消或0返回上一级菜单
        if (this.getAmount() == 0) {
            //进入交易选择状态
            machine.getSession().setState(Session.CHOOSING);
            machine.getDisplay().setText("请选择您需要的业务：" + "<br>" + "1:取款 2:存款 3:转账 4:查询 5:修改密码 0:退出 ");
            machine.getDigitButton().stateChange(0, 1, "TransactionServlet");
        }
        //判断取款金额是否为100的倍数数
        else if (this.getAmount() % 100 == 0) {

            //执行扣款
            int ret = this.getAccount().withdraw(this.getAmount());

            // 判断扣款是否成功
            if (ret == 0) {
                //扣款成功
                Account acc = this.getAccount();
                int money = (int) this.getAmount();
                //向数据库添加流水账单
//                Voucher voucher = new PrintBillDao().saveBill(acc.getId(), money, acc.getCardNo(), 2);
                Voucher voucher = new Voucher(1, 1, money, new Timestamp(System.currentTimeMillis()), acc.getCardNo(), "取款");
                //相关打印信息存入session
                request.getSession().setAttribute("voucher", voucher);

                //更新ATM荧屏显示文字
                machine.getDisplay().setText("取款成功。您的余额是" + this.getAccount().getBalance() + "<br>" + "打印:1 不打印:0");
                machine.getDigitButton().stateChange(0, 0, "TransactionPrintServlet");

            }
            // 扣取不成功
            else {
                machine.getDisplay().setText("您的余额不足，请重新输入取款金额:");
                machine.getDigitButton().stateChange(1, 0, "WithdrawServlet");
            }
        } else {
            // 取款金额不为100的倍数
            machine.getDisplay().setText("取款金额必须为100的倍数，请重新输入取款金额:");
            machine.getDigitButton().stateChange(1, 0, "WithdrawServlet");
        }
    }
}
