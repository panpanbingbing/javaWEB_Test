package Mysql_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        MysqlUtil_Test mu=new MysqlUtil_Test();
        PreparedStatement ps = null;
        ResultSet rs = null;
//        System.out.println();
        try {
            Connection conn= mu.deit();
            System.out.println(conn);
            try {
                String sql = "select deptno,dname from dept; ";
                conn = mu.deit();
                ps = conn.prepareCall(sql);
                rs = ps.executeQuery();
                int i = 0;
                while (rs.next()) {
                    String deptno = rs.getString("deptno");
                    String dname = rs.getString("dname");
                    System.out.println(deptno+" "+dname);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            mu.close(conn,ps,rs);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
