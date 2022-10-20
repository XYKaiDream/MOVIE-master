package cn.ccttll.common;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ConnectionUtil {
    public static String url = "jdbc:mysql://localhost:3306/movie3?useUnicode=true&characterEncoding=UTF-8";
    public  static String user = "root";
    public static String password = "123456";
    public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    public static Connection getConnection(){
        Connection conn= null;
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("url");
            dataSource.setUser("user");
            dataSource.setPassword("password");
            dataSource.setMaxPoolSize(20);

            dataSource.setInitialPoolSize(3);

            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(ResultSet rs, Statement stmt, Connection conn){
            if(rs!= null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }

}
