package dao;

import db.DBUtil;

/**
 * @Author soldier
 * @Date 2020/3/23 17:23
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:操作与账户类相关的Dao层
 */
public class AccountDao {

    /**
     * 取款执行扣款
     *
     * @param amount  扣款金额
     * @param cardNo 账户
     * @return
     */
    public static boolean deductMoney(double amount, int cardNo) {

        String sql = "update account_info set balance =balance -" + amount + " where cardNo =" + cardNo;

        return DBUtil.executeUpdate(sql);
    }


    /**
     * 执行存款
     *
     * @param amount  扣款金额
     * @param cardNo 账户
     * @return
     */
    public static boolean addMoney(double amount, int cardNo) {

        String sql = "update account_info set balance =balance +" + amount + " where cardNo =" + cardNo;

        return DBUtil.executeUpdate(sql);
    }

    /**
     * 查询余额
     *
     * @param cardNo
     * @return
     */
    public static double checkMoney(int cardNo) {
        String sql = "select balance from account_info where cardNo=" + cardNo;

        return DBUtil.executeSelect(sql);
    }
}
