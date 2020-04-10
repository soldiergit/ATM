//******************************************************************************
//
// ATM系统 -  Session.java 
// 参考了 http://www.cs.gordon.edu/courses/cs211/ATMExample/index.html
// 
//******************************************************************************

package atm;

import banking.Account;
import dao.LoginDao;

/**
 * 一个Session对象代表了现实世界里的一个人与ATM机器的对话过程.
 *
 * @author 何希
 * @version 10/06/2018
 */
public class Session {

    public static final int NOTREADY = 1; // 未开始
    public static final int AUTHENTICATION = 2; // 待验证
    public static final int CHOOSING = 3; // 交易选择
    public static final int INTRANSACTION = 4; // 交易中
    public static final int QUIT = 5; // 退出
    public static final int QUIT_WITH_CARD = 6; // 拔卡退出
    public static final int QUIT_WITHOUT_CARD = 7; // 留卡退出
    // 账号
    private int cardNo;
    // 密码
    private int pwd;
    // 会话的状态
    private int state = NOTREADY;
    // 此会话对应的账户
    private Account acct = null;
    // 当前的交易
    private Transaction trans = null;


    public void setState(int state) {
        this.state = state;
    }

    public Account getAccount() {
        return this.acct;
    }

    public Transaction getTransaction() {
        return trans;
    }

    public void setTransaction(Transaction trans) {
        this.trans = trans;
    }

    /**
     * 重新开始一个新会话
     */
    public void startOver() {
        state = NOTREADY;
        cardNo = 0;
        pwd = 0;
        acct = null;
    }

    /**
     * 确定银行卡是否有效。需要和后台数据库进行比较.
     * 现在的实现假设银行卡是有效的。
     *
     * @param cardNo
     * @return 银行卡是否正确。
     */
    public boolean verify(int cardNo) {
        this.cardNo = cardNo;
        this.state = AUTHENTICATION;
        return LoginDao.verifyCardNo(cardNo);
    }


    /**
     * 验证银行卡密码
     */
    public void auth(String pwd) {
        if (state == AUTHENTICATION) {
            ATM instance = ATM.getInstance();

            // 当用户没有输入
            if(pwd == null || pwd.length() == 0 || "0".equals(pwd)) {
                instance.getDisplay().setText("密码不能为空！");
            // 当用户按取消按钮时
            } else if ("exitATM".equals(pwd)) {
                instance.getSession().setState(0);
                instance.setState(ATM.IDLE);
                instance.getCardSlot().eject();
                instance.getSwitchButton().stateChange(ATM.IDLE);
                instance.getDisplay().setText("请收好您的卡片，请插入你的银行卡！");
                instance.getDigitButton().stateChange(2, 0, "");
            } else {
                this.pwd = Integer.valueOf(pwd);

                // 查询该账号所有信息
                Account account = LoginDao.selectCardInfo(this.cardNo);

                // 抽取账号中剩余输入密码次数信息属性
                int inputNum = account.getSurplusInputNum();

                // 剩余输入次数大于0
                if (inputNum > 0) {
                    if (this.pwd != 0) {
                        // 判断密码是否正确
                        if (this.pwd == account.getPwd()) {
                            // 密码正确时将账户所有信息返回指针存储
                            this.acct = account;

                            // 重置密码剩余输入次数
                            boolean flag = LoginDao.resetSurplusInput(this.cardNo);

                            // 判断重置密码剩余输入次数是否成功
                            if (!flag) {
                                System.err.println("重置剩余密码次数出现故障!");
                            }

                            state = CHOOSING;

                            instance.getDisplay().setText("请选择您需要的业务：" + "<br>" + "1:取款 2:存款 3:转账 4:查询 5:修改密码  0:退出");
                            instance.getDigitButton().stateChange(0, 1, "TransactionServlet");

                        }
                        // 账户密码不正确
                        else {
                            // 减少该账户剩余输入次数
                            boolean flag = LoginDao.reduceSurplusInputNum(this.cardNo);

                            //判断减少次数是否成功
                            if (flag) {
                                instance.getDisplay().setText("密码错误！请重新输入（还有" + (inputNum - 1) + "次输入机会）：");
                            } else {
                                System.out.println("输入密码错误时扣除可输入密码次数发生异常");
                            }

                        }
                    }
                    // 剩余输入次数小于0
                } else {
                    instance.getDisplay().setText("账户连续五次输入密码不正确，将暂停电子渠道和自助设备使用，仅能持卡通过建行网点柜面交易。");
                }
            }

        }
    }

    /**
     * 选择交易
     */
    public void selectTransaction(int options) {
        if (state == CHOOSING) {
            this.trans = Transaction.makeTransaction(this, this.acct, options);
            state = INTRANSACTION;
        }
    }

    /**
     * 获取Session的状态字符串
     */
    public String toString() {
        String output = "{";
        output += "\"state\":" + this.state;
        output += "}";
        return output;
    }
}
