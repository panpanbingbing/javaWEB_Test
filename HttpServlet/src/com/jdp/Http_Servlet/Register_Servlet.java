package com.jdp.Http_Servlet;

import com.jdp.JDBC_Servlet.mysql_Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Register_Servlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req,resp);
//    }

    /**
     *用户注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username= req.getParameter("username");
        String password= req.getParameter("password");
        String email= req.getParameter("email");
        mysql_Test zc = new mysql_Test(username, password, email);
        int a = zc.register();
        if (a >= 1) {
            out.println("注册成功");
        } else {
            out.println("重新输入");
        }



    }
}
