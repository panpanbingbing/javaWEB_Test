package JSPServlet;

import Mysqlutil.MysqlUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login", "/user/register", "/user/welcome","/user/out"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/user/login")) {
            dologin(request, response);
        } else if (path.equals("/user/register")) {
            doregister(request, response);
        } else if ("/user/out".equals(path)) {
            doout(request, response);
        } else if (path.equals("/user/welcome")) {
            dowelcome(request, response);
        }
    }

    /**
     * 欢迎页
     *
     * @param request
     * @param response
     */
    private void dowelcome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = null;
        String password = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            boolean ok = false;
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            MysqlUtil mu = new MysqlUtil();
            try {
                conn = mu.deit();
                ps = conn.prepareStatement("select * from t_user where username=? and password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ok = true;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                mu.close(conn, ps, rs);
            }
            if (ok) {

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }


    }

    /**
     * 退出用户登录
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void doout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {

//            Cookie[] cookies = request.getCookies();

            session.invalidate();
            //清除Cookies
            Cookie[] cookies = request.getCookies();
            if (cookies!= null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect(request.getContextPath());
        }
    }

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void doregister(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        Connection conn = null;
        PreparedStatement ps = null;

        MysqlUtil mu = new MysqlUtil();
        int count = 0;
        try {
            conn = mu.deit();
            String sql = "INSERT into t_user (username,password,email) VALUES (?,?,?); ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            count = ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, null);
        }
        if (count >= 1) {


            response.sendRedirect(request.getContextPath() + "/JSP_Html/login.jsp");

        }

    }

    /**
     * 用户登录
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void dologin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = null;
        String password = null;


        username = request.getParameter("username");
        password = request.getParameter("password");


        response.setContentType("text/html;charset=UTF-8");
        boolean ok = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MysqlUtil mu = new MysqlUtil();
        try {
            conn = mu.deit();
            ps = conn.prepareStatement("select * from t_user where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                ok = true;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mu.close(conn, ps, rs);
        }
        if (ok) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            String f = request.getParameter("f");
            if (f != null && f.equals("1")) {
                Cookie cookie = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                cookie.setMaxAge(60 * 60 * 24);
                cookie2.setMaxAge(60 * 60 * 24);
                cookie.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                response.addCookie(cookie);
                response.addCookie(cookie2);
            }
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
