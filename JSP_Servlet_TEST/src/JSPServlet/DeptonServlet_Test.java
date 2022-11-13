package JSPServlet;

import Mysqlutil.MysqlUtil;
import bean.DeptClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet({"/dept/list", "/dept/add", "/dept/listall", "/dept/update", "/dept/delete"})
public class DeptonServlet_Test extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//
//        if ( session !=null &&session.getAttribute("username")!=null) {
//        if ( session.getAttribute("username")!=null) {

            String path = request.getServletPath();
            if (path.equals("/dept/list")) {
                dolist(request, response);
            } else if (path.equals("/dept/add")) {
                doadd(request, response);
            } else if (path.equals("/dept/listall")) {
                dolistall(request, response);
            } else if (path.equals("/dept/update")) {
                doupdate(request, response);
            } else if (path.equals("/dept/delete")) {
                dodelete(request, response);
            }
//        }else {
//            response.sendRedirect(request.getContextPath());
//        }
    }

    /**
     * 部门查询页面Servlet
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void dolist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MysqlUtil mu = new MysqlUtil();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        List<DeptClass> dept = new ArrayList<DeptClass>();
        try {
            conn = mu.deit();
            String sql = "SELECT deptno,dname,loc FROM dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept.add(new DeptClass(deptno, dname, loc));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, rs);
        }
        request.setAttribute("dept", dept);


        request.getRequestDispatcher("/JSP_Html/DeptonList.jsp").forward(request, response);

    }

    /**
     * 新增部门Servlet
     *
     * @param request
     * @param response
     */

    private void doadd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String depton = request.getParameter("depton");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        response.setContentType("text/html;chaeset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement ps = null;
        MysqlUtil mu = new MysqlUtil();
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
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, null);
        }

        if (sum >= 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }


    }

    /**
     * 部门详情和修改查询Servlet
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void dolistall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String depton = request.getParameter("tepton");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MysqlUtil mu = new MysqlUtil();
        List<DeptClass> dept = new ArrayList<DeptClass>();
        try {
            conn = mu.deit();
            String sql = "SELECT deptno,dname,loc FROM dept WHERE deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, depton);
            rs = ps.executeQuery();
            if (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                dept.add(new DeptClass(deptno, dname, loc));

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, rs);
        }
        request.setAttribute("dept", dept);
        if (request.getParameter("f").equals("update")) {

            request.getRequestDispatcher("/JSP_Html/DeptonUpdate.jsp").forward(request, response);

        } else if (request.getParameter("f").equals("listall")) {

            request.getRequestDispatcher("/JSP_Html/DeptonListALL.jsp").forward(request, response);
        }
    }

    /**
     * 部门修改Servlet
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void doupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String depton = request.getParameter("depton");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        Connection conn = null;
        PreparedStatement ps = null;

        MysqlUtil mu = new MysqlUtil();
        int cuntu = 0;
        try {
            conn = mu.deit();
//            String sql = "UPDATE dept SET deptno=?,dname=?,loc=?";
            String sql = "update dept set dname=?,loc=? where deptno= ? ;";
            ps = conn.prepareStatement(sql);

            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, depton);
            cuntu = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, null);
        }
        if (cuntu >= 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }


    }

    /**
     * 删除部门Servlet
     *
     * @param request
     * @param response
     */
    private void dodelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String tepton = request.getParameter("tepton");

        response.setContentType("text/html;charset = UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        MysqlUtil mu = new MysqlUtil();
        int sun = 0;


        try {
            conn = mu.deit();
            String sql = "delete from dept where  deptno = ? ";
            ps = conn.prepareCall(sql);
            ps.setString(1, tepton);
            sun = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, null);
        }

        if (sun >= 1) {
//            request.getRequestDispatcher("/list").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/dept/list");

        } else {
            out.println("<h1>删除失败</h1>");

        }


    }
}
