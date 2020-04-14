//******************************************************************************
//
// ATM系统 -  Account.java 
// 参考了 http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
// 
//******************************************************************************

package banking;

import atm.ATM;
import atm.TransactionExtend.Transfer;
import dao.AccountDao;
import dao.LoginDao;
import dao.PrintBillDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * 一个Account对象代表了现实世界里的一个银行账户.
 *
 * @author 何希
 * @version 10/06/2018
 */
public class Account {

    private int id;

    // 账号
    private int cardNo;

    // 密码
    private int pwd;

    // 姓名
    private String fullName;

    // 余额
    private Double balance;

    // 剩余可输入密码次数
    int surplusInputNum;

    /**
     * 取款
     */
    public int withdraw(double amount) {
        //判断账户当前余额是否足够扣款
        if (this.balance >= amount) {
            //执行扣款
            boolean flag = AccountDao.deductMoney(amount, this.cardNo);
            //判断扣款是否执行成功
            if (flag) {
                //执行成功，刷新当前余额
                this.balance = this.balance - amount;
                return 0;
            } else {
                //执行出现异常
                System.out.println("系统扣款异常");
            }

        }
        return 1;
    }

    /**
     * 存款
     */
    public int deposit(double amount) {
        ATM machine = ATM.getInstance();
        //判断账户一次存钱不能超过10000
        if (amount <= 10000) {
            //执行存款
            boolean flag = AccountDao.addMoney(amount, this.cardNo);
            //判断存款是否执行成功
            if (flag) {
                //执行成功，刷新当前余额
                this.balance = this.balance + amount;
                return 0;
            } else {
                //执行出现异常
                System.out.println("系统存款异常");
            }

        } else {
            machine.getDisplay().setText("一次存款不得超过10000，请重新输入存款金额：");
            machine.getDigitButton().stateChange(1, 0, "DepositServlet");
        }
        return 1;
    }

    /**
     * 判断账户【转账收款方】是否存在
     */
    public void verifyReceivingParty(int cardNo) {
        Account a1 = ATM.getInstance().getSession().getAccount();

        if (cardNo > 0 || a1.getCardNo() != cardNo) {
            boolean flag = LoginDao.verifyCardNo(cardNo);
            if (flag) {
                Transfer t = (Transfer) ATM.getInstance().getSession().getTransaction();
                Account a = new Account();
                a.setCardNo(cardNo);
                t.setOtherAccount(a);
                ATM.getInstance().getDisplay().setText("收款账号：【" + cardNo + "】,请输入转账金额：");
                ATM.getInstance().getDigitButton().stateChange(1, 0, "TransferBalanceServlet");
            } else {
                ATM.getInstance().getDisplay().setText("账号无效不存在，请重新输入：");
            }
        }
    }

    /**
     * 转账
     * @param options   转账金额
     * @param req       request对象
     */
    public void transfer(int options, HttpServletRequest req) {

        Account a1 = ATM.getInstance().getSession().getAccount();
        Transfer t = (Transfer) ATM.getInstance().getSession().getTransaction();
        Account a2 = t.getOtherAccount();

        //本人余额
        double d1 = AccountDao.checkMoney(a1.getCardNo());
        if (d1 > options) {
            boolean flag = AccountDao.deductMoney(options, a1.getCardNo());
            boolean flag1 = AccountDao.addMoney(options, a2.getCardNo());

            //向数据库添加流水账单
//            Voucher voucher = new PrintBillDao().saveBill(a1.getId(), options, a1.getCardNo(), 3);
            Voucher voucher = new Voucher(1, 1, options, new Timestamp(System.currentTimeMillis()), a1.getCardNo(), "转账");
            //相关打印信息存入session
            req.getSession().setAttribute("voucher", voucher);

            ATM.getInstance().getDisplay().setText("转账成功，是否打印凭条 <br>" + "打印:1 不打印:0");
            ATM.getInstance().getDigitButton().stateChange(0, 0, "TransactionPrintServlet");
        } else {
            ATM.getInstance().getDisplay().setText("余额不足，请重新输入：");
            ATM.getInstance().getDigitButton().stateChange(0, 0, "TransactionServlet");
        }
    }

    /**
     * 修改密码
     */
    public int updatePWD(String pwd) {

        boolean flag = LoginDao.updatePWD(this.cardNo, Integer.valueOf(pwd));

        ATM.getInstance().getDisplay().setText("密码修改成功！" + "<br>" + "请按任意键退出，重新登录！");
        ATM.getInstance().getDigitButton().stateChange(0, 0, "TransactionServlet");

        if (flag) return 1;
        else return 0;
    }

    public Account() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getSurplusInputNum() {
        return surplusInputNum;
    }

    public void setSurplusInputNum(int surplusInputNum) {
        this.surplusInputNum = surplusInputNum;
    }
}
