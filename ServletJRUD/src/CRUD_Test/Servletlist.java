package CRUD_Test;

import Mysql_Util.MysqlUtil_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Servletlist extends HttpServlet {


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doGet(req,resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MysqlUtil_Test mu = new MysqlUtil_Test();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        String psth = req.getContextPath();
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>list</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div align='center'>");


        out.println("      <script type='text/javascript'>");
        out.println("                function dels(tepton ){");
//        out.println("           window.confirm('亲，确定删除吗?')");
        out.println("if (window.confirm('亲，确定删除吗？')) {");
        out.println("            document.location.href='"+psth+"/delete?tepton='+tepton");
        out.println("}");
        out.println("        }");
        out.println("</script>");


        out.println("<h1 align='center'>部门列表</h1>");
        out.println("<hr>");
        out.println("<table  border='1px' width='50%' >");
        out.println("");
        out.println("<tr>");
        out.println("    <th>序号</th>");
        out.println("    <th>部门编号</th>");
        out.println("    <th>部门名称</th>");
        out.println("    <th>操作</th>");
        out.println("");


        try {
            String sql = "SELECT deptno,dname FROM dept";
            conn = mu.deit();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();
            int i = 0;

            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                out.println("            <tr>");
                out.println("    <td>" + (++i) + "</td>");
                out.println("    <td>" + deptno + "</td>");
                out.println("    <td>" + dname + "</td>");
                out.println("    <td>");
//                out.println("        <a href=‘ javascript:viod(0);’ onclick=‘dels(" + deptno + ")’ >删除</a>");
                out.println("<a href='javascript:viod(0)' onclick='dels("+deptno+")' >删除</a>");
                out.println("        <a href='" + psth + "/update?deptno=" + deptno + "'>修改</a>");
                out.println("        <a href='" + psth + "/deptnolist?deptno=" + deptno + "'>详情</a>");
                out.println("    </td>");
                out.println("</tr>");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, rs);
        }

        out.println("    </table>");
        out.println("<hr>");
        out.println("<div align='center'>");
        out.println("<a href='"+psth+"/html/add.html' >新增部门</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
