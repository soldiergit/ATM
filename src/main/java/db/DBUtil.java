package db;


import java.sql.*;

/**
 * @Author soldier
 * @Date 2020/3/23 10:08
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:
 */
public class DBUtil {
    private static String jdbcName = "com.mysql.jdbc.Driver";
//    private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/bank?useUnicode=true&characterEncoding=utf-8";
    private static String dbUrl = "jdbc:mysql://116.62.48.112:3306/atm?useUnicode=true&characterEncoding=utf-8";
//    private String dbUserName = "root";
    private String dbUserName = "admin";
    private String dbPassword = "123456";

    /**
     * 初始化时获取数据库连接
     */
    public DBUtil() {
        init();
    }

    /**
     * 初始化驱动
     */
    private void init() {
        try {
            Driver driver = (Driver) Class.forName(jdbcName).newInstance();
            //将driver注册
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createConnection();
    }

    /**
     * 创建数据库连接
     */
    public Connection createConnection() {
        Connection connection = null;
        try {
            //创建连接
            connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 获取数据库连接
     */
    public Connection getConnection() {
        Connection connection = createConnection();
        //判断是否为空
        while (connection == null) {
            //创建connection，步进数
            createConnection();
            //防止其他线程过来拿连接
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param resultSet         结果集
     * @param preparedStatement 预处理指令
     * @param connection        数据库连接
     */
    public void releaseDB(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getCon() {
        Connection con = null;
        try {
            Class.forName(jdbcName);
            con = DriverManager.getConnection(dbUrl, this.dbUserName, this.dbPassword);
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库引擎失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接错误");
        }
        return con;
    }

    public static void closeConn(Connection con) {
        try {
            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStmt(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询数据存在性，当查询数据存在时返回true否则false
     *
     * @param sql 语句
     * @return bool
     */
    public static boolean executeBool(String sql) {

        boolean flag = false;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            DBUtil db = new DBUtil();
            con = db.getCon();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        } finally {
            DBUtil.closeConn(con);
            DBUtil.closeStmt(stmt);
            DBUtil.closeResultSet(rs);
        }

        System.out.println(sql);
        return flag;
    }

    /**
     * 根据sql语句更新数据
     *
     * @param sql 语句
     * @return 更新成功返回true否则false
     * @author
     * @version
     */
    public static boolean executeUpdate(String sql) {
        Connection con = null;
        PreparedStatement stmt = null;

        boolean flag = false;
        try {
            DBUtil db = new DBUtil();
            con = db.getCon();
            stmt = con.prepareStatement(sql);
            stmt.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            flag = false;
        }

        System.out.println(sql);
        return flag;
    }

    /**
     * 根据sql查询数据库用户余额
     *
     * @param sql   语句
     * @return      更新成功返回true否则false
     */
    public static double executeSelect(String sql) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            DBUtil db = new DBUtil();
            con = db.getCon();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con);
            DBUtil.closeStmt(stmt);
            DBUtil.closeResultSet(rs);
        }

        System.out.println(sql);
        return 0.00;
    }
}
