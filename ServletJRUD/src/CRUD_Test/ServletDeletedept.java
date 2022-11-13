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
import java.sql.SQLException;

public class ServletDeletedept extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        String tepton = request.getParameter("tepton");
//        request.getAuthType("text/html");
        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn=null;
        PreparedStatement ps=null;
        MysqlUtil_Test mu=new MysqlUtil_Test();
        int sun=0;


        try {
            conn=mu.deit();
            String sql ="delete from dept where  deptno = ? ";
            ps= conn.prepareCall(sql);
            ps.setString(1,tepton);
            sun = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            mu.close(conn,ps,null);
        }

        if (sun >=1){
            request.getRequestDispatcher("/list").forward(request,response);

        }else {
        out.println("<h1>删除失败</h1>");

        }


    }
}
