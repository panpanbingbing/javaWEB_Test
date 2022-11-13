import com.Mysql_Util.MysqlUtil_Test;
import com.alibaba.fastjson.JSON;
import com.baen.student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        student student = new student("lisi",20,"贵州");
//        student student1 = new student("zhangsan",25,"贵州");
//        student student2 = new student("王五",23,"贵州");
//        List<student> students = new ArrayList<student>();
//        students.add(student);
//        students.add(student1);
//        students.add(student2);
//        String o = JSON.toJSONString(student);
//        String s = JSON.toJSONString(students);
//        System.out.println(s);


//        MysqlUtil_Test mysqlUtil = new MysqlUtil_Test();
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        List<student> students = new ArrayList<student>();
//        try {
//            conn=mysqlUtil.deit();
//            String sql = "SELECT name,age,addr FROM t_student";
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                String addr = rs.getString("addr");
//                students.add(new student(name, age, addr));
//            }
////            JSON.toJSONString(students);
//            System.out.println(JSON.toJSONString(students));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            mysqlUtil.close(conn,ps,rs);
//        }




        MysqlUtil_Test mysqlUtil = new MysqlUtil_Test();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<student> students = new ArrayList<student>();
        try {
            conn=mysqlUtil.deit();
            String sql = "SELECT name,age,addr FROM t_student";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String addr = rs.getString("addr");
                students.add(new student(name, age, addr));
            }
            System.out.println(JSON.toJSONString(students));


//            out.println(JSON.toJSONString(students));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            mysqlUtil.close(conn,ps,rs);
        }

//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<student> students = new ArrayList<student>();
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mytada", "root","password");
//            String sql="SELECT name,age,addr FROM t_student";
//            ps = conn.prepareStatement(sql);
//            rs=ps.executeQuery();
//            while (rs.next()) {
//                String name=rs.getString("name");
//                int age=rs.getInt("age");
//                String addr=rs.getString("addr");
//                students.add(new student(name,age,addr));
//
//            }
//            System.out.println(JSON.toJSONString(students));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}