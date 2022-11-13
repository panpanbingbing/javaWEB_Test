package com.jdp.JDBC_Servlet;

import java.sql.*;
import java.util.Scanner;

public class mysql_Test {
    private String username;
    private String password;
    private String email;

    public mysql_Test() {
    }

    public mysql_Test(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public mysql_Test(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int register() {
//        String username = null;
//        String password = null;
//        String email = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            String url ="jdbc:mysql://localhost:3306/mydata?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
//            jdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
            String url = "jdbc:mysql://localhost:3306/mytada";
            String user = "root";
            String passwords = "password";
            conn = DriverManager.getConnection(url, user, passwords);
//           conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata","root","123456");
//            conn = DriverManager.getConnection(url, user, "123456");
            String sql = "INSERT into t_user (username,password,email) VALUES (?,?,?); ";
            ps = conn.prepareCall(sql);
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ps.setString(3, this.email);

            int a = ps.executeUpdate();
            return a;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            return 0;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return 0;

    }


}
