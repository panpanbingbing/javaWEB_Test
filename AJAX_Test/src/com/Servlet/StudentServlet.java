package com.Servlet;


import com.Mysql_Util.MysqlUtil_Test;
import com.alibaba.fastjson.JSON;
import com.baen.student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



@WebServlet({"/student"})
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/http:charset=UTF-8");
        PrintWriter out = response.getWriter();



        MysqlUtil_Test mysqlUtil = new MysqlUtil_Test();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<student> students = new ArrayList<student>();
        try {

            conn=mysqlUtil.deit();
//            System.out.println(conn);
            String sql = "SELECT name,age,addr FROM t_student";
             ps = conn.prepareStatement(sql);
             rs = ps.executeQuery();
             while (rs.next()) {
                 String name = rs.getString("name");
                 int age = rs.getInt("age");
                 String addr = rs.getString("addr");
                 students.add(new student(name, age, addr));
             }
//            System.out.println(JSON.toJSONString(students));


             out.println(JSON.toJSONString(students));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            mysqlUtil.close(conn,ps,rs);
        }

//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//            List<student> students = new ArrayList<student>();
////            boolean first = false;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mytada", "root","password");
//            String sql="SELECT name,age,addr FROM t_student";
//             ps = conn.prepareStatement(sql);
//             rs=ps.executeQuery();
//            while (rs.next()) {
//                String name=rs.getString("name");
//                int age=rs.getInt("age");
//                String addr=rs.getString("addr");
//                students.add(new student(name,age,addr));
//
//            }
//            out.println(JSON.toJSONString(students));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
