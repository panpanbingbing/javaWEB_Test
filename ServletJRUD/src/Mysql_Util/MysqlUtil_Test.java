package Mysql_Util;

import java.sql.*;
import java.util.ResourceBundle;

public class MysqlUtil_Test {

    private String MysqlDriver;
    private String MytadUrl;
    private String MysqlUesr;
    private String MysqlPassword;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public MysqlUtil_Test() {
        ResourceBundle rb = ResourceBundle.getBundle("peag");
        this.MysqlDriver = rb.getString("MysqlDriver");
        this.MytadUrl = rb.getString("MytadUrl");
        this.MysqlUesr = rb.getString("MysqlUesr");
        this.MysqlPassword = rb.getString("MysqlPassword");
    }

    public Connection deit() throws ClassNotFoundException, SQLException {
        Class.forName(MysqlDriver);
        conn = DriverManager.getConnection(MytadUrl, MysqlUesr, MysqlPassword);
        return conn;
    }
    public void close(Connection conn,PreparedStatement ps, ResultSet rs){
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
