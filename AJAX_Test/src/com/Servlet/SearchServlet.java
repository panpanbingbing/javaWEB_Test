package com.Servlet;

import com.Mysql_Util.MysqlUtil_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet({"/query"})
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywords = request.getParameter("keywords");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StringBuilder sb = new StringBuilder();
        MysqlUtil_Test mysqlUtil = new MysqlUtil_Test();

        try {
//            conn = DBUtil.getConnection();
            conn = mysqlUtil.deit();
            String sql = "select * from t_user where username like  ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, keywords + "%");
            rs = ps.executeQuery();
            //[{"content":"javaweb"},{"content":"java"}]
            sb.append("[");
            while (rs.next()) {
                String content = rs.getString("username");
                sb.append("{\"username\":\"" + content + "\"},");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
//            DBUtil.close(conn, ps, rs);
            mysqlUtil.close(conn,ps,rs);
        }
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(sb.substring(0, sb.length() - 1) + "]");
    }
}
