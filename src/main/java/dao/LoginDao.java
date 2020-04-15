package dao;

import banking.Account;
import db.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author soldier
 * @Date 2020/3/23 12:08
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:用户登录ATMdao
 */
public class LoginDao {

    /**
     * 验证账号是否存在
     *
     * @param cardNo
     * @return
     */
    public static boolean verifyCardNo(int cardNo) {
        String sql = "select * from account_info where cardNo=" + cardNo;
        return DBUtil.executeBool(sql);
    }

    /**
     * 通过账号获取其信息
     *
     * @param cardNo
     * @return
     */
    public static Account selectCardInfo(int cardNo) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Account account = null;

        try {
            DBUtil db = new DBUtil();

            con = db.getCon();

            stmt = con.prepareStatement("select * from account_info where cardNo=" + cardNo);

            rs = stmt.executeQuery();

            while (rs.next()) {
                account = new Account();
                account.setId(rs.getInt(1));
                account.setCardNo(rs.getInt(2));
                account.setPwd(rs.getInt(3));
                account.setFullName(rs.getString(4));
                account.setBalance(rs.getDouble(5));
                account.setSurplusInputNum(rs.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con);
            DBUtil.closeStmt(stmt);
            DBUtil.closeResultSet(rs);
        }
        return account;
    }

    /**
     * 输入的密码错误时更新数据库中可输入密码的剩余次数
     *
     * @param cardNo
     * @return
     */
    public static boolean reduceSurplusInputNum(int cardNo) {
        String sql = "update account_info set surplusInputNum =surplusInputNum-1 where cardNo =" + cardNo;

        return DBUtil.executeUpdate(sql);
    }

    /**
     * 密码正确时，重置为5
     *
     * @param cardNo
     * @return
     */
    public static boolean resetSurplusInput(int cardNo) {
        String sql = "update account_info set surplusInputNum =5 where cardNo =" + cardNo;

        return DBUtil.executeUpdate(sql);
    }

    /**
     * 修改密码
     * @param cardNo
     * @param pwd
     * @return
     */
    public static boolean updatePWD(int cardNo, int pwd) {
        String sql = "update account_info set pwd =" + pwd +" where cardNo =" + cardNo;

        return DBUtil.executeUpdate(sql);
    }

}
