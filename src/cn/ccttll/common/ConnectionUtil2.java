package cn.ccttll.common;

import java.sql.*;
public final class ConnectionUtil2 {

    private static String url = "jdbc:mysql://localhost:3306/movie3?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "123456";

    private ConnectionUtil2(){}
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Load Driver  Failure!");
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Connection  Failure!");
            e.printStackTrace();
        }
        return null;
    }
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
