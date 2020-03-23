package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author soldier
 * @Date 2020/3/23 10:11
 * @Email:583406411@qq.com
 * @Version 1.0
 * @Description:该类用于封装数据库增删查改操作
 */
public class DBOperation {

    /**
     * 此方法用于判断sql是增删查改的哪一种
     *
     * @param sql 需要判断的sql
     * @return 若为查询，返回1；若为增删改，返回2
     */
    public int checkSqlType(String sql) {
        int flag = 0;
        if (sql.toUpperCase().trim().startsWith("SELECT")) {
            flag = 1;
        } else if (sql.toUpperCase().trim().startsWith("UPDATE") || sql.toUpperCase().trim().startsWith("INSERT") || sql.toUpperCase().trim().startsWith("DELETE")) {
            flag = 2;
        }
        return flag;
    }

    /**
     * 确认数据库是否支持别名显示
     *
     * @param connection
     * @return
     */
    private boolean checkDBIsSupportAlis(Connection connection) {
        boolean flat = true;
        boolean aliasSupport = false;
        DatabaseMetaData dbMeta = null;
        try {
            dbMeta = connection.getMetaData();
            aliasSupport = dbMeta.supportsColumnAliasing();
            if (!aliasSupport) {
                flat = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flat;
    }

    /**
     * 根据指定的字符串来获取特定元素出现的数量
     *
     * @param str  指定的字符串
     * @param mark 特定元素的内容
     * @return 特定元素出现的次数
     */
    public int getMarksNum(String str, String mark) {
        //计数
        int num = 0;
        while (str.indexOf(mark) >= 0) {
            //当特定元素出现时，计数+1
            num++;
            //然后将此次出现的特定元素去掉，形成新的字符串来进行下一次循环
            str = str.substring(str.indexOf(mark) + 1);
        }
        return num;
    }

    /**
     * 将查询结果转换为List
     *
     * @param preparedStatement 预编译语句
     * @param resultSet         查询结果
     * @param showAlias         是否显示别名
     * @return
     */
    public ArrayList<HashMap<String, Object>> listConstractor(PreparedStatement preparedStatement, ResultSet resultSet, boolean showAlias) {
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        //生成结果集
        try {
            resultSet = preparedStatement.executeQuery();
            //获取结果集属性
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //获取结果集（属性）中的列数
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                //每行都需要新建一个Map
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    //如果支持别名，则使用别名显示，否则使用列名显示
                    if (showAlias) {
                        //向map中加入键值对，键的内容为别名，值的内容为结果集中的对应的内容
                        map.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
                    } else {
                        //如果不支持别名则使用列名显示
                        map.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
                    }
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据指定的sql和参数进行查询
     *
     * @param type      sql语句是否含参，0为无参，1为有参
     * @param sql       操作数据库的sql
     * @param params    参数列表
     * @param showAlias 是否显示别名
     * @return
     * @throws Exception
     */
    public ArrayList<HashMap<String, Object>> execute(int type, String sql, ArrayList<Object> params, boolean showAlias) throws Exception {
        //判断sql类型，并返回标识位
        int flag = this.checkSqlType(sql);
        //获取数据库连接
        Connection connection = new DBUtil().getConnection();
        //判断数据库是否支持查询结果以别名的方式显示
        boolean aliasSupport = this.checkDBIsSupportAlis(connection);
        if (aliasSupport == false) {
            showAlias = false;
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //增删改所对应的结果
        int result = 0;
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            //有参数则设置参数
            if (type == 1) {
                //检测sql中出现的？的次数
                int num = this.getMarksNum(sql, "?");
                //当sql中出现"?"的次数和输入的参数列表中的元素数量相等时，则可以进行查询，否则，抛出异常
                if (params.size() == num) {
                    //循环并设置参数
                    for (int i = 1; i <= params.size(); i++) {
                        preparedStatement.setObject(i, params.get(i - 1));
                    }
                } else {
                    throw new Exception("由于sql中的未知变量与参数列表中元素数量不一致，因此无法查询。");
                }
            }
            //如果sql为查询
            if (flag == 1) {
                list = this.listConstractor(preparedStatement, resultSet, showAlias);
            }
            //如果sql为增删改
            else if (flag == 2) {
                result = preparedStatement.executeUpdate();
                System.out.println("执行完毕，已影响" + result + "行。");
            }
            //其他的sql
            else {
                //关闭数据库连接
                new DBUtil().releaseDB(resultSet, preparedStatement, connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DBUtil().releaseDB(resultSet, preparedStatement, connection);
        }
        return list;
    }
}
