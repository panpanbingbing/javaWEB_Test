package CRUD_Test;

import Mysql_Util.MysqlUtil_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServletAddtepdon extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("TEU-8");
        String depton = req.getParameter("depton");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");
        resp.setContentType("text/html;chaeset=UTF-8");
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        MysqlUtil_Test mu = new MysqlUtil_Test();
        int sum = 0;
        try {
            conn = mu.deit();
            String sql = "INSERT INTO dept VALUES ( ?, ? ,? );";
            ps = conn.prepareCall(sql);
            ps.setString(1, depton);
            ps.setString(2, dname);
            ps.setString(3, loc);
            sum = ps.executeUpdate();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            out.println("<h1>添加失败</h1>");
        } finally {
            mu.close(conn, ps, null);
        }

        if (sum>=1){
//          req.getRequestDispatcher("/list").forward(req,resp);
          resp.sendRedirect(req.getContextPath()+"/list");
        }
    }
}
