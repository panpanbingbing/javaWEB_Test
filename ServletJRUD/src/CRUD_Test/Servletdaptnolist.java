package CRUD_Test;

import Mysql_Util.MysqlUtil_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Servletdaptnolist extends Servletlist {

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>daptnolist</title>");
        out.println("</head>");
        out.println("<body>");


        out.println("<div align='center'>");

        out.println("<h1>部门详情</h1>");
        out.println("<hr>");
        MysqlUtil_Test mu = new MysqlUtil_Test();
        String deptnos = req.getParameter("deptno");

        try {
            conn = mu.deit();
            String sql = "select dname,loc from dept where deptno = ? ;";
            ps = conn.prepareCall(sql);
            ps.setString(1,deptnos);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.println("<p > 部门编号：" + deptnos + " </p >");
                out.println("<p > 部门名称： " + dname + " </p >");
                out.println("<p > 部门地址： " + loc + " </p >");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, rs);
        }
        out.println("<input type='button' value='后退' onclick='window.history.back()' >");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
