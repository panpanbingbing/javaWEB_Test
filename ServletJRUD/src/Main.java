import Mysql_Util.MysqlUtil_Test;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
//        MysqlUtil_Test mu=new MysqlUtil_Test();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Connection conn=null;
//
//        try {
//           conn= mu.deit();
//            System.out.println(conn);
//            try {
//                String sql = "select deptno,dname from dept; ";
//                conn = mu.deit();
//                ps = conn.prepareCall(sql);
//                rs = ps.executeQuery();
//                int i = 0;
//                while (rs.next()) {
//                    String deptno = rs.getString("deptno");
//                    String dname = rs.getString("dname");
//                    System.out.println(deptno+" "+dname);
//                }
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        mu.close(conn,ps,rs);



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
            String sql = "select deptno,dname from dept;";
            ps = conn.prepareCall(sql);
            rs= ps.executeQuery();
            while (rs.next()) {
                    String deptno = rs.getString("deptno");
                    String dname = rs.getString("dname");
                    System.out.println(deptno+" "+dname);
                }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
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
    }
}