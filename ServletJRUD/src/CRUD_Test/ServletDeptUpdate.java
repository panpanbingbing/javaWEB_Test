package CRUD_Test;

import Mysql_Util.MysqlUtil_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServletDeptUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String depton = req.getParameter("deptno");
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs=null;
        MysqlUtil_Test mu=new MysqlUtil_Test();
        String path = req.getContextPath();



        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();


        out.println("        <!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>daptnolist</title>");
        out.println("</head>");
        out.println("<body>");


        out.println("<form action='"+path+"/updaetdepton' method='post' align='center'>");
        out.println("<p>");
        out.println("部门编号：<input type='text' value='"+depton+"' name='depton'   readonly=“readonly”>");
        out.println("</p>");


        try {
            conn= mu.deit();
//            String sql="update dept set dname=?,loc=? where deptno=?";
            String sql = "select dname,loc from dept where deptno = ? ;";
            ps  = conn.prepareCall(sql);
            ps.setString(1,depton);
            rs= ps.executeQuery();
            if (rs.next()){
                String dname=rs.getString("dname");
                String loc = rs.getString("loc");

                out.println("<p>");
                out.println("                        部门名称：<input type='text' value='"+dname+"'  name='dname'>");
                out.println("</p>");
                out.println("<p>");
                out.println("                        部门地址：<input type='text' value='"+loc+"' name='loc'>");
                out.println("</p>");


            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            mu.close(conn,ps,rs);
        }


        out.println("<input type='submit' value='保存'>");
        out.println("<input type='button' value='后退' onclick='window.history.back()'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
