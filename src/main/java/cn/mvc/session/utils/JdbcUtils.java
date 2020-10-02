package cn.mvc.session.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {
    /**
     * 获取MySQL数据库连接对象
     * @return
     */
    //数据库连接池，C3P0
    private static DataSource dataSource = null;
    static {
        //静态代码块只会执行一次
        //得到连接池
        dataSource = new ComboPooledDataSource("mysql");
    }
    /**
     * 获取数据库MySQL数据连接对象conn
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**
     * 通用的关闭数据库连接的方法
     * @param conn
     */
    public static void closeConn(Connection conn){
        if (conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void rollbackTransation(Connection conn){
        if (conn!=null){
            try{
                conn.rollback();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}

