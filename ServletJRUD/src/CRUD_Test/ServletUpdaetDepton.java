package CRUD_Test;

import Mysql_Util.MysqlUtil_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServletUpdaetDepton extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String depton = req.getParameter("depton");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");

        Connection conn=null;
        PreparedStatement ps=null;
        MysqlUtil_Test mu =new MysqlUtil_Test();
        int sum=0;
        try {
            conn=mu.deit();
            String sql= "update dept set dname=?,loc=? where deptno= ? ;";
            ps=conn.prepareCall(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,depton);
            sum = ps.executeUpdate();


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            mu.close(conn,ps,null);
        }
        if (sum >=1){
//            req.getRequestDispatcher("/list").forward(req,resp);
            resp.sendRedirect(req.getContextPath()+"/list");
        }

    }
}
